package nst.app.common;

import nst.app.CEIUtil;
import nst.app.NewgenUtil;
import nst.app.dto.newgen.NGBaseDTO;
import nst.app.dto.newgen.NGQuerySubmitDTO;
import nst.app.dto.newgen.NGSubmitDTO;
import nst.app.enums.ApplicationType;
import nst.app.enums.FileStatus;
import nst.app.enums.QueryStatus;
import nst.app.helper.ApplicationQueryHelper;
import nst.app.manager.ApplicationQueryManager;
import nst.app.manager.FormManager;
import nst.app.model.forms.Form;
import nst.app.model.forms.le.ApplicationQuery;
import nst.app.model.forms.le.CommonForm;
import nst.app.model.forms.le.Query;
import nst.common.base.BaseManager;
import nst.common.base.BaseModel;
import nst.common.error.AppException;
import nst.common.security.LoginUser;
import nst.dto.APIResponseDTO;
import nst.dto.EmailDTO;
import nst.kernal.ActivityLog;
import nst.kernal.ActivityType;
import nst.kernal.SystemConstants;
import nst.kernal.SystemConstants.Model;
import nst.kernal.SystemConstants.Rest;
import nst.manager.UserNotificationManager;
import nst.service.BackgroundService;
import nst.util.LoginUserUtil;
import nst.util.ResourceReader;
import nst.util.VelocityFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;

@Repository
public abstract class CEIBaseManager<M extends BaseModel> extends BaseManager<M> {

  @Autowired
  FormManager formManager;
  @Autowired
  protected AppConfig appConfig;
  @Autowired
  protected UserNotificationManager notificationManager;
  @Autowired
  NewgenUtil newgenUtil;

  @Autowired
  BackgroundService backgroundService;

  @Autowired
  ApplicationQueryManager queryManager;

  @Autowired
  protected ApplicationQueryHelper applicationQueryHelper;

  @Override
  public M findForm(Long formId) {
    if (formId == null) {
      throw AppException.createWithMessageCode(SystemConstants.BAD_REQUEST, Model.INVALID_REQUEST);
    }
    return getCEIRepository().findByForm_id(formId);
  }

  public M get(long id) {
    return getCEIRepository().findByForm_id(id);
  }

  public M getByApplicationId(long applicationId) {
    return getCEIRepository().findByForm_id(applicationId);
  }

  public CEIBaseRepository<M> getCEIRepository() {
    if (getRepository() instanceof CEIBaseRepository) {
      return (CEIBaseRepository<M>) getRepository();
    }
    return null;
  }

  public boolean validatePayment(long applicationId, ApplicationType applicationType) {
    if (!applicationType.isPayable()) {
      return true;
    }
    return true;
    /*Transaction transaction = formManager.getFormTransaction(applicationId);
    if (transaction != null && transaction.getStatus() == PaymentStatus.PAID) {
      return true;
    }
    throw AppException.createWithMessageCode(Model.INVALID_REQUEST, "NOT_PAID");*/
  }

  protected void performSubmit(CommonForm model, NGBaseDTO parameters) {

    if (model.canSubmit(LoginUserUtil.loadLoginUser()) == Boolean.FALSE) {
      throw AppException.createWithMessage(Model.INVALID_REQUEST,"Current Application Status is " + model.getFileStatus().name());
    }

    validatePayment(model.getId(), model.getApplicationType());
    APIResponseDTO responseDTO = newgenUtil
        .submitApplication(model.getApplicationType(), parameters);
    NGSubmitDTO ngSubmitDTO = responseDTO.toDTO(NGSubmitDTO.class);


    /*To Test via TestCase*/
    //NGSubmitDTO ngSubmitDTO = NGSubmitDTO.FromJson();

    if (ngSubmitDTO.isSuccess()) {
      model.setFileStatus(FileStatus.SUBMITTED);
      /*ToDo: Check if PID not provided. Send Email notify to Support team.*/
      model.setPid(ngSubmitDTO.getPid());
      model.setRemarks(ngSubmitDTO.getRemarks());

      printLog(ActivityType.SUBMITTED, model.getId(), "1", "2");
        notificationManager.addNotification(CEIUtil.emailOnFileStatusUpdate(model, model.getSubmittedOn(), FileStatus.SUBMITTED, "emails/APP_status_changed.vm"), Boolean.FALSE);

      return;
    }
    throw AppException.createWithMessageCode(Rest.NEWGEN_SUBMIT_ERROR, Rest.NEWGEN_SUBMIT_ERROR);
  }

    /*ToDo: Remove this method*/
    protected void sendEmailOnSubmit(Form m) {
        if (true) {
            return;
        }
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setTo(m.getOwner().getEmail());
        emailDTO.setSubject(
                "Application Submitted : " + m.getUniqueId() + " for " + m.getApplicationType().getName());
        emailDTO.setFrom("CEICED - DEV TEAM");
        emailDTO.setUserId(m.getOwner().getId());

        String body = ResourceReader.readAsString("emails/APP_SUBMITTED.vm");

        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("baseUrl", appConfig.getBaseUrl());

        emailDTO.setBody(VelocityFormatter.format(body, dataMap));
        notificationManager.addNotification(emailDTO, Boolean.FALSE);
    }

  protected void printLog(ActivityType activityType, Long objectId, String... action) {
    LoginUser loginUser = LoginUserUtil.loadLoginUser();
    backgroundService.log(new ActivityLog(activityType, objectId, loginUser.getUsername(), action));
  }

  public void submitQuery(Long applicationId, Long queryId) {
    ApplicationQuery applicationQuery = queryManager.getForApplication(queryId, applicationId);
    if (QueryStatus.REPLIED == applicationQuery.getQueryStatus()) {
      throw AppException.createWithMessageCode(Model.INVALID_REQUEST, "ALREADY_REPLIED");
    }
    CommonForm form = applicationQuery.getForm();
      ListIterator<Query> queryList = applicationQuery.getQueryList().listIterator();

      while (queryList.hasNext()) {
          Query query = queryList.next();
          if (StringUtils.isEmpty(query.getReply())) {
              throw AppException.createWithMessageCode(Rest.ERROR_BAD_REQUEST, "Queried reply cannot be empty");
          }
      }
      /*TODO: Validate Query Reply should not be blank.*/
    APIResponseDTO responseDTO = newgenUtil
        .submitQuery(form.getApplicationType(), applicationQueryHelper.toNGDTO(applicationQuery));
    NGQuerySubmitDTO ngSubmitDTO = responseDTO.toDTO(NGQuerySubmitDTO.class);

    if (ngSubmitDTO.isSuccess()) {
      form.setFileStatus(FileStatus.QUERY_REPLIED);
      applicationQuery.setQueryStatus(QueryStatus.REPLIED);
      applicationQuery.setRemarks(ngSubmitDTO.getRemarks());
      queryManager.save(applicationQuery);

      formManager.save(form);
      printLog(ActivityType.QUERY_REPLIED, form.getId(), "QUERY_REPLIED");
        notificationManager.addNotification(CEIUtil.emailOnFileStatusUpdate(form, applicationQuery.getRepliedOn(), FileStatus.QUERY_REPLIED, "emails/APP_status_changed.vm"), Boolean.FALSE);
      return;
    }
    throw AppException.createWithMessageCode(Rest.NEWGEN_SUBMIT_ERROR, Rest.NEWGEN_SUBMIT_ERROR);
  }
}