package nst.app.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nst.app.CEIUtil;
import nst.app.dto.newgen.NGQueryRequestDTO;
import nst.app.dto.newgen.NGQueryRequestDTO.NGQueryDTO;
import nst.app.dto.newgen.NGRequestDTO;
import nst.app.enums.ApplicationType;
import nst.app.enums.FileStatus;
import nst.app.enums.QueryStatus;
import nst.app.manager.ApplicationQueryManager;
import nst.app.manager.FormManager;
import nst.app.model.forms.le.ApplicationQuery;
import nst.app.model.forms.le.CommonForm;
import nst.app.model.forms.le.Query;
import nst.common.Service;
import nst.common.error.AppException;
import nst.config.MyLogger;
import nst.kernal.SystemConstants;
import nst.kernal.SystemConstants.Model;
import nst.manager.UserNotificationManager;
import nst.util.AllUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;

@Component
@Transactional
public class NewgenService implements Service {

  @Autowired
  ApplicationQueryManager manager;
  @Autowired
  AgencyService agencyService;

  @Autowired
  FormManager formManager;

  @Autowired
  UserNotificationManager notificationManager;

  public void request(NGRequestDTO dto) throws JsonProcessingException {
    MyLogger.logService("NewgenService", "Request", dto.toJSON());
    if (StringUtils.isEmpty(dto.getApplicationId())) {
      throw AppException.createWithMessage(Model.INVALID_REQUEST, Model.INVALID_REQUEST);
    }

    if (!(dto.getStatusEnum() == FileStatus.APPROVED || dto.getStatusEnum() == FileStatus.NOT_APPROVED)) {
      throw AppException.createWithMessage(Model.INVALID_REQUEST,
          "Invalid FileStatus: Expected as Approved but got as " + dto.getFileStatus());
    }
    completed(dto);
    return;
  }

  public void queryRequested(NGQueryRequestDTO dto) {
    /*ToDo: Validate DTO*/
    NGQueryDTO queryDTO = dto.getData();
    CommonForm commonForm = formManager.get(queryDTO.getApplicationId());
    if (FileStatus.SUBMITTED != commonForm.getFileStatus() && FileStatus.QUERY_REPLIED != commonForm
        .getFileStatus()) {
      /*ToDo: Email to support team about this Instance.*/
      throw AppException.createWithMessage(Model.INVALID_REQUEST,
          "Current Application Status :" + commonForm.getFileStatus().name());
    }

    ApplicationQuery applicationQuery = new ApplicationQuery(commonForm);
    applicationQuery.setPackId(queryDTO.getPackId());
    applicationQuery.setQueryStatus(QueryStatus.QUERIED);

    queryDTO.getQueries().forEach(queryDetailDTO -> {
      Query query1 = applicationQuery.myQuery(null);
      query1.setQueryId(Long.parseLong(queryDetailDTO.getQId()));
      query1.setRaise(queryDetailDTO.getQRaise());
      query1.setRaiseBy(queryDetailDTO.getRaiseBy());
      Date ngDate = AllUtil.toNGDate(queryDetailDTO.getRaiseOn());
      if (ngDate == null){
        ngDate = AllUtil.toDate(queryDetailDTO.getRaiseOn(), SystemConstants.FORMAT_DATE_BACKSLASH);
      }
      query1.setRaiseOn(ngDate);
    });

    manager.save(applicationQuery);
    commonForm.setFileStatus(FileStatus.QUERIED);
    formManager.save(commonForm);
      notificationManager.addNotification(CEIUtil.emailOnFileStatusUpdate(commonForm, new Date(), FileStatus.QUERIED, "emails/APP_status_changed.vm"), Boolean.FALSE);
  }

  private void completed(NGRequestDTO dto) throws JsonProcessingException {
    CommonForm commonForm = formManager.get(dto.getApplicationId());

    if (FileStatus.SUBMITTED != commonForm.getFileStatus() && FileStatus.QUERY_REPLIED != commonForm
        .getFileStatus()) {
      /*ToDo: Email to support team about this Instance.*/
      throw AppException.createWithMessage("INVALID_REQUEST",
          "Current Application Status :" + commonForm.getFileStatus().name());
    }

    commonForm.setFileStatus(dto.getStatusEnum());

    if(dto.getStatusEnum() == FileStatus.APPROVED) {
//    commonForm.setLicenseNumber(dto.getLicenseNumber());
      if (StringUtils.isEmpty(dto.getFileNo())) {
        throw AppException.createWithMessage("INVALID_REQUEST","Invalid File No");
      }
    }

    if(dto.getComments() != null && !((ArrayList) dto.getComments()).isEmpty()) {
       commonForm.setOtherComments(new ObjectMapper().writeValueAsString(dto.getComments()));
    }
    commonForm.setRemarks(dto.getRemarks());
    commonForm.setOutwardNo(dto.getOutwardNo());
    formManager.save(commonForm);

    if(dto.getStatusEnum() == FileStatus.APPROVED) {
      handleApproval(commonForm, dto);
    }

    //notificationManager.addNotification(CEIUtil.emailOnFileStatusUpdate(commonForm, commonForm.getApprovedOn(), dto.getStatusEnum(), "emails/APP_status_changed.vm"));
  }

  private void handleApproval(CommonForm commonForm, NGRequestDTO dto){
    if (ApplicationType.EMAL == commonForm.getApplicationType()
            || ApplicationType.ITAL == commonForm.getApplicationType()
            || ApplicationType.OMAL == commonForm.getApplicationType()
            ){
      agencyService.handleNewApproval(commonForm, dto);
      return;
    }
    if (ApplicationType.ALRENE == commonForm.getApplicationType()){
      agencyService.handleRenewalApproval(commonForm, dto);
      return;
    }
    if (ApplicationType.ALMODI == commonForm.getApplicationType()){
      agencyService.handleModificationApproval(commonForm, dto);
      return;
    }
  }
}