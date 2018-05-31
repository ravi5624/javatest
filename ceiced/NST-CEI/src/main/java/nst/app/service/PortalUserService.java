package nst.app.service;

import nst.app.common.AppConfig;
import nst.app.dto.*;
import nst.app.enums.ApplicationType;
import nst.app.enums.FileStatus;
import nst.app.enums.PaymentStatus;
import nst.app.enums.UserType;
import nst.app.helper.*;
import nst.app.manager.FormManager;
import nst.app.manager.PortalUserManager;
import nst.app.model.EAndMAgency;
import nst.app.model.IAndTAgency;
import nst.app.model.OAndMAgency;
import nst.app.model.PortalUser;
import nst.app.model.forms.le.*;
import nst.common.AbstractService;
import nst.common.base.BaseDTO;
import nst.common.base.PageableDOT;
import nst.common.error.AppException;
import nst.common.security.LoginUser;
import nst.dto.CellDTO;
import nst.dto.EmailDTO;
import nst.helper.UserNotificationHelper;
import nst.kernal.SystemConstants;
import nst.kernal.SystemConstants.Model;
import nst.manager.UserNotificationManager;
import nst.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Transactional
public class PortalUserService extends AbstractService {

  public static final String USER_ALREADY_EXISTS = "USER_ALREADY_EXISTS";
  public static final String USER_NOT_EXISTS = "USER_NOT_EXISTS";

  private String certURL;
  @Autowired
  PortalUserManager manager;

  @Autowired
  FormManager formManager;

  @Autowired
  AppConfig appConfig;

  @Autowired
  UserNotificationHelper userNotificationHelper;

  @Autowired
  UserNotificationManager notificationManager;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  LiftInstallationHelper liftInstallationHelper;
  @Autowired
  EscalatorInstallationHelper escalatorInstallationHelper;
  @Autowired
  OperatingLiftHelper operatingLiftHelper;
  @Autowired
  OperatingEscalatorHelper operatingEscalatorHelper;
  @Autowired
  AgencyHelper agencyHelper;

  public PortalUserDTO register(PortalUserDTO user) {
    List<PortalUser> byUserName = manager.getByUserTypeAndUserName(UserType.valueOf(user.getType()), user.getUserName());
    if (byUserName != null && byUserName.size() > 0) {
      throw AppException.createWithMessageCode(SystemConstants.Rest.ERROR_BAD_REQUEST, USER_ALREADY_EXISTS);
    }
    validateEmail(UserType.valueOf(user.getType().toUpperCase()), user.getEmail());
//    validateCell(user.getContactNo());
    PortalUser portalUser = PortalUserHelper.toPortalUser(user);
    portalUser.setPassword(passwordEncoder.encode(user.getPassword()));
    portalUser.setResetPassword(user.getPassword());

    PortalUser save = manager.save(portalUser);

    sendNotification(save);

    return PortalUserHelper.fromPortalUser(save);
  }

  private void sendNotification(PortalUser portalUser) {
    EmailDTO emailDTO = new EmailDTO();
    emailDTO.setTo(portalUser.getEmail());
    emailDTO.setSubject(appConfig.getWelcomeSubject());
    emailDTO.setFrom(appConfig.getEmailFromName());
    emailDTO.setUserId(portalUser.getId());

    String body = ResourceReader.readAsString("emails/USER_REGI.vm");
    String smsBody = appConfig.getSmsBody();
    smsBody = smsBody
        .replace("{0}",portalUser.getUserType().toString())
        .replace("{1}",portalUser.getCellOtp());
    Map<String, String> dataMap = new HashMap<>();
    dataMap.put("baseUrl", appConfig.getBaseUrl());
    dataMap.put("userName", portalUser.getPortalUserName());
    dataMap.put("userId", portalUser.getUserName());
    dataMap.put("uniqueId", portalUser.getUniqueId());
    dataMap.put("emailCode", portalUser.getEmailCode());
    emailDTO.setBody(VelocityFormatter.format(body, dataMap));

    CellDTO cellDTO = new CellDTO();
    cellDTO.setUserId(portalUser.getId());
    cellDTO.setTo(portalUser.getContactNo());
    cellDTO.setServer(appConfig.getSmsServer());
    cellDTO.setSenderId(appConfig.getSmsSender());
    cellDTO.setPassword(appConfig.getSmsPassword());
    cellDTO.setUserName(appConfig.getSmsUserName());
    cellDTO.setBody(smsBody);

    notificationManager.addNotification(emailDTO, cellDTO);
  }
  private void forgotPasswordEmail(PortalUser portalUser) {
    EmailDTO emailDTO = new EmailDTO();
    emailDTO.setTo(portalUser.getEmail());
    emailDTO.setSubject(appConfig.getForgetPasswordSubject());
    emailDTO.setFrom(appConfig.getEmailFromName());
    emailDTO.setUserId(portalUser.getId());

    String body = ResourceReader.readAsString("emails/USER_FORGET_PASS.vm");

    Map<String, String> dataMap = new HashMap<>();
    dataMap.put("userName", portalUser.getPortalUserName());
    dataMap.put("userId", portalUser.getUserName());
    dataMap.put("resetLink", appConfig.getHostUrl() + "?" + portalUser.getEmailCode() + SystemConstants.SUFFIX_RESET_PASSWORD);
    emailDTO.setBody(VelocityFormatter.format(body, dataMap));
    notificationManager.addNotification(emailDTO, Boolean.TRUE);
  }

  private void sendOTP(PortalUser portalUser) {

    String smsBody = appConfig.getSmsBody();
    smsBody
    .replace("{0}",portalUser.getUserType().toString())
    .replace("{1}",portalUser.getCellOtp());

    CellDTO cellDTO = new CellDTO();
    cellDTO.setUserId(portalUser.getId());
    cellDTO.setTo(portalUser.getContactNo());
    cellDTO.setServer(appConfig.getSmsServer());
    cellDTO.setSenderId(appConfig.getSmsSender());
    cellDTO.setPassword(appConfig.getSmsPassword());
    cellDTO.setUserName(appConfig.getSmsUserName());
    cellDTO.setBody(smsBody);

    notificationManager.sendSMS(cellDTO);
  }

  public List<PortalUserDTO> getAll() {
    return PortalUserHelper.fromPortalUser(manager.getAll());
  }

  public void update(PortalUserDTO portalUser) {
    PortalUser byEmail = manager.loadById(portalUser.getId(), portalUser.getVersion());

    byEmail.setFirstName(portalUser.getFirstName());
    byEmail.setLastName(portalUser.getLastName());

    manager.save(byEmail);
  }

  public PortalUser get(long id) {
    return manager.get(id);
  }

  public void testAll() {
    manager.testAll();
  }

  public PortalUserDTO  getDetail() {
    LoginUser loginUser = LoginUserUtil.loadLoginUser();
    return getDetail(loginUser.getUserId());
  }

  public DashboardDTO getAppStatusCount() {
    LoginUser loginUser = LoginUserUtil.loadLoginUser();
    DashboardDTO dashboardDTO = manager.countMyAppStatus(loginUser.getUserId());
    return dashboardDTO;
  }

  public PortalUserDTO getDetail(Long userId) {
    PortalUser portalUser = manager.get(userId);
    return PortalUserHelper.fromPortalUser(portalUser);
  }

  public PortalUserDTO getDetail(String userName) {
    PortalUser portalUser = manager.getByUserName(userName);
    return PortalUserHelper.fromPortalUser(portalUser);
  }

  public PageableDOT getMyApps(ApplicationType applicationType, String applicantName, Pageable pagination) {
    LoginUser loginUser = LoginUserUtil.loadLoginUser();
    List<CommonForm> applications = null;
    long count = 0;

    UserType userType = null;
    if(loginUser != null){
      if(loginUser.hasAuthority(UserType.WIREMAN.getType())){
        userType = UserType.WIREMAN;
      }
      if (loginUser.hasAuthority(UserType.SUPERVISOR.getType())){
        userType = UserType.SUPERVISOR;
      }
    }
    UserType myUserType = userType;

    if (loginUser.hasAuthority(UserType.EM_AGENCY.getType())) {
      applications = manager.getMyAppsForEMAgency(loginUser.getUserId(),applicationType, applicantName,pagination);
      count = manager.countMyAppsForEMAgency(loginUser.getUserId(),applicationType,applicantName);
    } else {
      applications = manager.getMyApps(applicationType, applicantName, pagination);
      count = manager.countMyApps(applicationType, applicantName);
    }

    return PageableDOT.create(count, applications.stream().map(app -> {

      ApplicationDTO dto = new ApplicationDTO();
      dto.setApplcantName(app.getApplicantName());
      dto.setId(app.getId());
      dto.setApplicationId(app.getUniqueId());
      dto.setFileNo(app.getFileNumber());
      dto.setPid(app.getPid());
      dto.setSubmittedOn(app.getSubmittedOn());
      dto.setCreatedOn(app.getCreatedDate());
      dto.setApprovedOn(app.getApprovedOn());

      dto.setApplicationName(app.getApplicationType().getName(myUserType));
      dto.setType(app.getApplicationType().getType());
      dto.setStatus(app.getFileStatus().name());
      dto.setEditable(FileStatus.DRAFT == app.getFileStatus() || (loginUser.hasAuthority(UserType.EM_AGENCY.getType()) && FileStatus.OWNER_SUBMITTED == app.getFileStatus()));
      dto.setHasQuery(FileStatus.QUERIED == app.getFileStatus());
      dto.setCanDelete(
          FileStatus.DRAFT == app.getFileStatus() && !(app.getTransactionStatus() != null
              && app.getTransactionStatus() == PaymentStatus.PAID));
      dto.setPaymentStatus(
          app.getTransactionStatus() == null ? "PENDING" : app.getTransactionStatus().name());

      provideFormPartAccess(loginUser, app, dto);
      return dto;
    }).collect(Collectors.toList()));
  }

  //TODO [SAGAR]: Verify with Vishalbhai
  private void provideFormPartAccess(LoginUser loginUser, CommonForm app, ApplicationDTO dto) {
    ApplicationType appType = app.getApplicationType();

    if ((appType == ApplicationType.LIL || appType == ApplicationType.EIL || appType == ApplicationType.OESCL || appType == ApplicationType.OLIFT)) {
      if (loginUser.hasAuthority(UserType.OWNER.getType())) {
        dto.addPart(ApplicationTypeDTO.PartDTO.create("PART-1", true, FileStatus.DRAFT == app.getFileStatus()));
        dto.addPart(ApplicationTypeDTO.PartDTO.create("PART-2", true, false));
        if (appType == ApplicationType.LIL) {
          dto.addPart(ApplicationTypeDTO.PartDTO.create("PART-3", true, false));
        }
      } else if (loginUser.hasAuthority(UserType.EM_AGENCY.getType())) {
        boolean isAgency = app.getUser().getId().equals(loginUser.getUserId());
        dto.addPart(ApplicationTypeDTO.PartDTO.create("PART-1", true, isAgency));
        dto.addPart(ApplicationTypeDTO.PartDTO.create("PART-2", true, isAgency ? isAgency : FileStatus.OWNER_SUBMITTED == app.getFileStatus()));
        if (appType == ApplicationType.LIL) {
          dto.addPart(ApplicationTypeDTO.PartDTO.create("PART-3", true, isAgency ? isAgency : FileStatus.OWNER_SUBMITTED == app.getFileStatus()));
        }
      }
    }
  }

  public PageableDOT getMyNotifications(Pageable pagination) {
    return PageableDOT.create(notificationManager.getMyNotificationCount(),
        userNotificationHelper.toDTO(notificationManager.getMyNotifications(pagination)));
  }

  public long myNotificationCount() {
    return notificationManager.myNotificationCount();
  }

  public BaseDTO searchApplication(LicenseSearchDTO dto) {
    CommonForm commonForm = manager.searchApplication(dto);
    if (commonForm == null) {
      throw AppException.createWithMessageCode(SystemConstants.Rest.ERROR_BAD_REQUEST,
          SystemConstants.Rest.NOT_FOUND);
    }
    Object application = manager
        .loadApplication(commonForm.getId(), commonForm.getApplicationType());
    switch (commonForm.getApplicationType()) {
      case LIL:
        return liftInstallationHelper.fromModel((LiftInstallation) application);
      case EIL:
        return escalatorInstallationHelper.fromModel((EscalatorInstallation) application);
      case OLIFT:
        return operatingLiftHelper.fromModel((OperatingLift) application);
      case OESCL:
        return operatingEscalatorHelper.fromModel((OperatingEscalator) application);
      case EMAL:
        return agencyHelper.fromModel((EAndMAgency) application);
      case ITAL:
        return agencyHelper.fromModel((IAndTAgency) application);
      case OMAL:
        return agencyHelper.fromModel((OAndMAgency) application);
    }
    throw AppException.createWithMessageCode(Model.INVALID_REQUEST, Model.INVALID_APPLICATION);
  }

  public InputStream getCert(Long id, String uniqueId) {
    CommonForm commonForm = formManager.get(uniqueId);
    if (!id.equals(commonForm.getId())) {
      throw AppException.createWithMessageCode(SystemConstants.Rest.ERROR_BAD_REQUEST, Model.INVALID_APPLICATION);

    }
    if (FileStatus.APPROVED != commonForm.getFileStatus()) {
      throw AppException.createWithMessageCode(SystemConstants.Rest.ERROR_BAD_REQUEST, Model.INVALID_APPLICATION);
    }
    if ("true".equalsIgnoreCase(appConfig.getIsLocal())) {
      certURL = appConfig.getCertRoot() + "certificate.pdf";
      return ResourceReader.readAsStream(certURL);
    }
    certURL = appConfig.getCertURL() + commonForm.getPid() +"/"+ commonForm.getPid() +".pdf";
    return IOUtil.fromOtherSys(certURL, appConfig.getCertUser(), appConfig.getCertPassword());
  }

  public void validateEmail(UserType userType,String email) {

    if (StringUtils.isEmpty(email.trim())) {
      throw AppException.createWithMessageCode(SystemConstants.Rest.ERROR_BAD_REQUEST, Model.INVALID_EMAIL);
    }
    if(manager.getByUserTypeAndEmail(userType, email) != null){
      throw AppException.createWithMessageCode(SystemConstants.Rest.ALERT, SystemConstants.Rest.EMAIL_ALREADY_EXIST);
    }
  }

  public void validateContactNo(UserType userType, String contactNo) {

    if (StringUtils.isEmpty(contactNo.trim())) {
      throw AppException.createWithMessageCode(SystemConstants.Rest.ERROR_BAD_REQUEST, Model.INVALID_NUMBER);
    }
    if(manager.getByUserTypeAndContactNo(userType, contactNo) != null){
      throw AppException.createWithMessageCode(SystemConstants.Rest.ALERT, SystemConstants.Rest.MOBILE_ALREADY_EXIST);
    }
  }

  public void validateUser(UserType userType, String userName) {
    if (StringUtils.isEmpty(userName.trim())) {
      throw AppException.createWithMessage(Model.INVALID_REQUEST, Model.INVALID_REQUEST);
    }
    List<PortalUser> byUserName = manager.getByUserTypeAndUserName(userType, userName);
    if (byUserName != null && byUserName.size() > 0) {
      throw AppException.createWithMessageCode(SystemConstants.Rest.ALERT, USER_ALREADY_EXISTS);
    }
  }

  public void verifyEmail(String uniqueId, String code) {
    PortalUser portalUser = manager.findByUniqueId(uniqueId);
    if (portalUser == null) {
      throw AppException.createWithMessageCode(Model.INVALID_REQUEST, Model.INVALID_REQUEST);
    }
    if (portalUser.getEmailValidated()) {
      throw AppException.createWithMessageCode(Model.INVALID_REQUEST, Model.ALREADY_VALIDATED);
    }
    if (!portalUser.getEmailCode().equals(code)) {
      throw AppException.createWithMessageCode(Model.INVALID_REQUEST, Model.INVALID_ACTIVATION_CODE);
    }
    portalUser.setEmailValidated(Boolean.TRUE);
    portalUser.setEmailCode(null);
    manager.save(portalUser);
  }

  public void verifyOTP(String otp, Long userId) {
    PortalUser portalUser = manager.getById(userId);
    if (portalUser == null) {
      throw AppException.createWithMessageCode(Model.INVALID_REQUEST, Model.INVALID_REQUEST);
    }
    if (portalUser.getOtpValidated()) {
      throw AppException.createWithMessageCode(Model.INVALID_REQUEST, Model.ALREADY_VALIDATED);
    }
    if (portalUser.isOtpExpired()) {
      throw AppException.createWithMessageCode(Model.INVALID_REQUEST, Model.TOKEN_EXPIRED);
    }
    if (!portalUser.getCellOtp().equals(otp)) {
      throw AppException.createWithMessageCode(Model.INVALID_REQUEST, Model.INVALID_ACTIVATION_CODE);
    }
    portalUser.setOtpValidated(Boolean.TRUE);
    portalUser.setCellOtp(null);
    manager.save(portalUser);
  }

  public void forgetPassword(String userEmail, UserType userType) {
    if (StringUtils.isEmpty(userEmail.trim())) {
      throw AppException.createWithMessage(Model.INVALID_REQUEST, Model.INVALID_REQUEST);
    }

    PortalUser portalUser = manager.getByUserTypeAndEmail(userType, userEmail);
    if(portalUser == null){
      throw AppException.createWithMessage(Model.INVALID_REQUEST, USER_NOT_EXISTS);
    } else if(portalUser.getEmailValidated() == null ||  portalUser.getEmailValidated() == false){
      throw AppException.createWithMessage(Model.INVALID_REQUEST, Model.EMAIL_NOT_VERIFIED);
    }
    portalUser.setEmailCode(AllUtil.getUUID());
    manager.save(portalUser);
    forgotPasswordEmail(portalUser);
  }

  public void resendOtp(Long userId) {
    PortalUser portalUser = manager.getById(userId);
    if(portalUser == null){
      throw AppException.createWithMessage(Model.INVALID_REQUEST, USER_NOT_EXISTS);
    }
    portalUser.setCellOtp(AllUtil.getOtpCode());
    manager.save(portalUser);
    sendOTP(portalUser);
  }
  public void changePassword(String oldPassword, String newPassword) {
    LoginUser loginUser = LoginUserUtil.loadLoginUser();
    if (loginUser == null) {
      throw AppException.createWithMessage(Model.INVALID_REQUEST, SystemConstants.Rest.UNAUTHORISED_USER);
    }
    PortalUser portalUser = manager.get(loginUser.getUserId());
    if (portalUser == null) {
      throw AppException.createWithMessage(Model.INVALID_REQUEST, SystemConstants.Rest.NO_USER);
    }
    if(!portalUser.getResetPassword().equals(oldPassword)) {
      throw AppException.createWithMessage(Model.INVALID_REQUEST, SystemConstants.Rest.PASSWORD_NOT_MATCH);
    }
    portalUser.setPassword(passwordEncoder.encode(newPassword));
    portalUser.setResetPassword(newPassword);
    manager.save(portalUser);
  }

  public void resetPassword(String encryptKey, String newPassword) {
    PortalUser portalUser = manager.getByEmailCode(encryptKey);
    if (portalUser == null) {
      throw AppException.createWithMessage(Model.INVALID_REQUEST, SystemConstants.BAD_REQUEST);
    }
    portalUser.setPassword(passwordEncoder.encode(newPassword));
    portalUser.setEmailCode(null);
    manager.save(portalUser);
  }
}
