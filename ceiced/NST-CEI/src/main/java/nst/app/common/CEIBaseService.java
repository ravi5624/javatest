package nst.app.common;

import nst.app.NewgenUtil;
import nst.app.dto.ApplicationQueryDTO;
import nst.app.dto.LicenseSearchDTO;
import nst.app.enums.ApplicationType;
import nst.app.enums.FileStatus;
import nst.app.enums.PaymentStatus;
import nst.app.enums.UserType;
import nst.app.helper.ApplicationQueryHelper;
import nst.app.manager.ApplicationAttachmentManager;
import nst.app.manager.ApplicationQueryManager;
import nst.app.manager.FormManager;
import nst.app.manager.SystemManager;
import nst.app.model.ApplicationFees;
import nst.app.model.PortalUser;
import nst.app.model.forms.Form;
import nst.app.model.forms.le.ApplicationAttachment;
import nst.app.model.forms.le.ApplicationQuery;
import nst.app.model.forms.le.CommonForm;
import nst.app.model.forms.le.Transaction;
import nst.app.service.PortalUserService;
import nst.common.base.*;
import nst.common.error.AppException;
import nst.common.error.AuthorizationException;
import nst.common.security.LoginUser;
import nst.common.security.LoginUserService;
import nst.config.MyLogger;
import nst.dto.TransactionDTO;
import nst.kernal.SystemConstants;
import nst.kernal.SystemConstants.Model;
import nst.manager.UserNotificationManager;
import nst.util.LoginUserUtil;
import nst.util.VelocityFormatter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

@Transactional
public abstract class CEIBaseService<M extends BaseModel, P extends BaseModelDTO> extends
    BaseService<M> {

  @Autowired
  FormManager formManager;

  @Autowired
  AppConfig appConfig;

  @Autowired
  UserNotificationManager notificationManager;

  @Autowired
  LoginUserService loginUserService;

  @Autowired
  PortalUserService portalUserService;

  @Autowired
  ApplicationQueryManager queryManager;

  @Autowired
  protected NewgenUtil newgenUtil;

  @Autowired
  protected SystemManager systemManager;

  @Autowired
  ApplicationAttachmentManager applicationAttachmentManager;

  @Autowired
  protected ApplicationQueryHelper applicationQueryHelper;

  public CommonForm getCommonForm(long applicationId, ApplicationType applicationType) {
    CommonForm commonForm = formManager.loadById(applicationId);
    if (commonForm.getApplicationType() != applicationType) {
      throw AuthorizationException
          .create(String.format("[%s : %s]", applicationId, applicationType.name()),
              LoginUserUtil.getLoginUser());
    }
    return commonForm;
  }

  public CommonForm getCommonForm(long applicationId) {
    CommonForm commonForm = formManager.loadById(applicationId);

    LoginUser loginUser = LoginUserUtil.loadLoginUser();
    //Note: Allowed agency to upload file in owner created forms(LIL, EIL, OESCL, OLIFT)
    LiftEscalatorValidationUtil.checkFormPermission(loginUser, commonForm);
    return commonForm;
  }

  public CommonForm getCommonForm(ApplicationType applicationType) {
    try {
      LoginUser loginUser = LoginUserUtil.loadLoginUser();
      CommonForm commonForm = formManager.findByUserIdAndApplicationType(loginUser.getUserId(), applicationType);
      return commonForm;
    } catch (Exception e){
      throw AppException.createWithMessageCode(SystemConstants.Rest.ERROR_BAD_REQUEST, SystemConstants.Rest.SINGLE_APPLICATION_ALLOWED);
    }
  }

  public P save(P p) {
    M m = null;
    if (p.getId() == null) {
      m = getHelper().toModel(p);
    } else {
      m = getManager().loadById(p.getId());
    }
    return getHelper().fromModel(getManager().save(m));
  }

  public P create() {
    allowToCreate();
    M m = getHelper().blankModel(portalUserService.get(LoginUserUtil.loadLoginUser().getUserId()));
    M save = getManager().save(m);
    return getHelper().fromModel(save);
  }

  public P duplicate(String identifier1,String identifier2) {
    allowToCreate();
    M duplicateFrom = get(identifier1, identifier2);
    if (duplicateFrom == null){
      return create();
    }
    M newModel = getHelper().blankModel(portalUserService.get(LoginUserUtil.loadLoginUser().getUserId()));
    newModel = getHelper().toModel(newModel, duplicateFrom);
    M save = getManager().save(newModel);
    return getHelper().fromModel(save);
  }

  public P get(long id) {
    M model = getCEIManager().findForm(id);
    if (model instanceof Form){
      allowToLoad((Form)model);
    }
    if (model == null) {
      throw AppException
          .createWithMessageCode(SystemConstants.BAD_REQUEST, Model.INVALID_APPLICATION);
    }
    return getHelper().fromModel(model);
  }

  public BaseDTO getJson(long id) {
    M model = getCEIManager().findForm(id);
    if (model == null) {
      throw AppException
          .createWithMessageCode(SystemConstants.BAD_REQUEST, Model.INVALID_APPLICATION);
    }
    return getHelper().toNGDTO(model);
  }

  public String getPrintView(long id) {
    return VelocityFormatter.formatFromResource("printView/" + applicationType().getType() + ".vm", get(id));
  }

  public P saveForm(P p) {
    M m = null;
    if (p.getId() == null) {
      m = getHelper().toModel(p);
    } else {
      m = getCEIManager().findForm(p.getId());
    }
    if (m instanceof Form) {
      allowToEdit((Form) m);
    }

    return getHelper().fromModel(getManager().save(getHelper().toModel(m, p)));
  }

  public P search(LicenseSearchDTO p) {
    return null;
  }

  public List<P> getMyForms() {
    return getHelper().fromModel(getManager().getAll());
  }

  public abstract BaseHelper<M, P> getHelper();

  public P submitForm(Long id) {
    return submit(getCEIManager().findForm(id));
  }

  public P submitForm(P p) {
    M m = getCEIManager().findForm(p.getId());
    m = getHelper().toModel(m, p);
    save(m);
    return submit(m);
  }

  public void delete(Long id) {
    M model = getCEIManager().findForm(id);
    if (model instanceof Form){
      if(FileStatus.DRAFT != ((Form) model).getFileStatus()) {
        throw AppException.createWithMessage(Model.INVALID_REQUEST,"Not allowed to delete as status is " + ((Form) model).getFileStatus().name());
      }
      allowToDelete((Form)model);
    }
    if (model == null) {
      throw AppException
          .createWithMessageCode(SystemConstants.BAD_REQUEST, Model.INVALID_APPLICATION);
    }
    getCEIManager().delete(model);
  }

  protected P submit(M m) {
    return getHelper().fromModel(getCEIManager().submitForm(m));
  }

  private CEIBaseManager<M> getCEIManager() {
    if (getManager() instanceof CEIBaseManager) {
      return (CEIBaseManager<M>) getManager();
    }
    return null;
  }

  public TransactionDTO processPayment(Long id) {
    CommonForm commonForm = formManager.getById(id);
    Transaction transaction = formManager.getFormTransaction(id);

    if (transaction != null && transaction.getStatus() == PaymentStatus.PAID) {
      MyLogger.log("CEIBaseService", "Already Paid %s : %s, %s", commonForm.getUniqueId(),
          transaction.getStatus(), transaction.getPaidOn());
      return HelperUtil.toTransactionDTO(transaction);
    }
    if (transaction == null) {
      transaction = new Transaction(commonForm);
    }

    ApplicationFees fees = systemManager.findFees(commonForm.getApplicationType());
    transaction.setStatus(PaymentStatus.PENDING);
    transaction.setAmount(fees.getFees());
    transaction.setTransactionId(commonForm.getUniqueId());

    formManager.saveTransaction(transaction);

    return HelperUtil.toTransactionDTO(transaction);
  }

  public void allowToCreate() {
    //systemManager.isEnabled(applicationType());
    LoginUser loginUser = LoginUserUtil.loadLoginUser();
    boolean allowed = loginUser.getAuthorities().stream().anyMatch(au -> {
      return UserType.getByType(au.getAuthority()).hasOperation(applicationType());
    });
    //System.out.println(loginUser.getAuthorities() +" = " + applicationType() +" = " + allowed);
    if (allowed) {
      return;
    }
    throw AppException.createWithMessageCode(SystemConstants.BAD_REQUEST, SystemConstants.Rest.UNAUTHORISED_USER);
  }

  public void allowToEdit(Form m) {
    allowToCreate();
    LoginUser loginUser = LoginUserUtil.loadLoginUser();
    if (m.getOwner().getId().equals(loginUser.getUserId())) {
      if (FileStatus.DRAFT != ((Form) m).getFileStatus()) {
        throw AppException.createWithMessage(Model.INVALID_REQUEST,
            "Not allowed to edit as status is " + ((Form) m).getFileStatus().name());
      }
      return;
    }
    throw AppException.createWithMessageCode(SystemConstants.BAD_REQUEST, SystemConstants.Rest.UNAUTHORISED_USER);
  }

  public void allowToLoad(Form m) {
    LoginUser loginUser = LoginUserUtil.loadLoginUser();
    if (m.getOwner().getId().equals(loginUser.getUserId()) == false) {
      throw AppException.createWithMessageCode(SystemConstants.BAD_REQUEST, SystemConstants.Rest.UNAUTHORISED_USER);
    }
  }

  public void allowToDelete(Form m) {
    allowToLoad(m);
  }

  public abstract ApplicationType applicationType();

  public List<ApplicationQueryDTO> getAllQueries(Long applicationId) {
    List<ApplicationQuery> queryList = queryManager.getAllForApplication(applicationId);
    return applicationQueryHelper.fromModel(queryList);
  }

  public List<ApplicationQueryDTO> getRepliedQueries(Long applicationId) {
    List<ApplicationQuery> queryList = queryManager.getRepliedQueries(applicationId);
    return applicationQueryHelper.fromModel(queryList);
  }

  public ApplicationQueryDTO getLatestQuery(Long applicationId) {
    ApplicationQuery applicationQuery = queryManager.getLatestQuery(applicationId);
    return applicationQueryHelper.fromModel(applicationQuery);
  }


  public HashMap<String, String> getComments(Long applicationId) {
    CommonForm commonForm = formManager.get(applicationId);
    HashMap<String, String> commentArray = new HashMap<>();

    commentArray.put("applicationId",commonForm.getId().toString());
    commentArray.put("otherComments",commonForm.getOtherComments());
    commentArray.put("fileStatus",commonForm.getFileStatus().toString());

    return commentArray;
  }


  public ApplicationQueryDTO saveQuery(ApplicationQueryDTO dto) {
    CommonForm commonForm = formManager.get(dto.getApplicationId());

    if (FileStatus.QUERIED != commonForm.getFileStatus()) {
      throw AppException.createWithMessage(Model.INVALID_REQUEST,
          "Not allowed to save as status is " + commonForm.getFileStatus().name());
    }

    ApplicationQuery applicationQuery = queryManager.getForApplication(dto.getId(), dto.getApplicationId());
    applicationQueryHelper.toModel(applicationQuery, dto);
    queryManager.save(applicationQuery);
    return applicationQueryHelper.fromModel(applicationQuery);
  }

  public void submitQuery(Long applicationId, Long queryId) {
    getCEIManager().submitQuery(applicationId, queryId);
  }

  public ApplicationAttachment deleteAttachment(ApplicationAttachment applicationAttachment) {
    applicationAttachmentManager.delete(applicationAttachment);
    return applicationAttachment;
  }

  public void validateFormExist(ApplicationType applicationType) {
    LoginUser loginUser = LoginUserUtil.loadLoginUser();
    PortalUser portalUser = portalUserService.get(loginUser.getUserId());
    formManager.validateUniqueApplication(portalUser, applicationType);
  }
}