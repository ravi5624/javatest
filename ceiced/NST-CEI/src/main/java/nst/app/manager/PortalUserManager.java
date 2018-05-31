package nst.app.manager;

import nst.app.dto.DashboardDTO;
import nst.app.dto.LicenseSearchDTO;
import nst.app.enums.AgencyType;
import nst.app.enums.ApplicationType;
import nst.app.enums.FileStatus;
import nst.app.enums.UserType;
import nst.app.model.EAndMAgency;
import nst.app.model.OwnerDetail;
import nst.app.model.PortalUser;
import nst.app.model.forms.le.CommonForm;
import nst.app.repo.*;
import nst.common.base.BaseManager;
import nst.common.base.BaseRepository;
import nst.common.error.AppException;
import nst.common.security.LoginUser;
import nst.kernal.SystemConstants.Model;
import nst.util.LoginUserUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class PortalUserManager extends BaseManager<PortalUser> {

  @Autowired
  PortalUserRepository portalUserRepository;

  public PortalUser getByUserName(String userName) {
    return portalUserRepository.findByUserName(userName);
  }

  public PortalUser getByEmail(String email) {
    return portalUserRepository.findByEmail(email);
  }

  public List<PortalUser> getByUserTypeAndUserName(UserType userType, String userName) {
    return portalUserRepository.findOneByUserTypeAndUserNameContainingIgnoreCase(userType, userName);
  }

    public PortalUser getByUserTypeAndUserNameAndEmailValidate(UserType userType, String userName) {
        return portalUserRepository.findByUserTypeAndUserNameAndEmailValidatedIsTrue(userType, userName);
    }


    public PortalUser getByUserTypeAndEmail(UserType userType, String userEmail) {
    return portalUserRepository.findByUserTypeAndEmail(userType, userEmail);
  }

  public PortalUser getByUserTypeAndContactNo(UserType userType, String contactNo) {
    return portalUserRepository.findByUserTypeAndContactNo(userType, contactNo);
  }

  public PortalUser getByEmailCode(String emailCode) {
    return portalUserRepository.findByEmailCode(emailCode);
  }

  @Override
  public BaseRepository<PortalUser> getRepository() {
    return portalUserRepository;
  }

  @Autowired
  OwnerDetailRepository ownerDetailRepository;

  @Autowired
  ApplicationRepository applicationRepository;

  @Autowired
  LiftInstallationRepository liftInstallationRepository;
  @Autowired
  EscalatorInstallationRepository escalatorInstallationRepository;
  @Autowired
  OperatingLiftRepository operatingLiftRepository;
  @Autowired
  OperatingEscalatorRepository operatingEscalatorRepository;
  @Autowired
  EAndMAgencyRepository eAndMAgencyRepository;
  @Autowired
  OAndMAgencyRepository oAndMAgencyRepository;
  @Autowired
  IAndTAgencyRepository iAndTAgencyRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  public void testAll() {
    PortalUser portalUser = new PortalUser();
    portalUser.setUserType(UserType.EM_AGENCY);
    portalUser.setUserName("Vishal");
    portalUser.setFirstName("Vishal");
    portalUser.setLastName("Patel");
    portalUser.setPassword(passwordEncoder.encode("Vishal"));
    portalUser.setEmail("vishal.patel@nascentinfo.com");

    portalUser = portalUserRepository.save(portalUser);

    List<PortalUser> all = portalUserRepository.findAll();

    EAndMAgency agency = new EAndMAgency();
    agency.getAgencyDetail().setUser(portalUser);
    agency.getAgencyDetail().setAgencyType(AgencyType.E_M_AGENCY);
    (agency).setName("E & M AgencyDetail");
    eAndMAgencyRepository.save(agency);

    OwnerDetail ownerDetail = new OwnerDetail();
    ownerDetail.setOwnerName("Owner");
    ownerDetail.setUser(portalUser);
    ownerDetailRepository.save(ownerDetail);
  }

  public PortalUser get(long id) {
    return portalUserRepository.findById(id);
  }

  public PortalUser findByUniqueId(String uniqueId) {
    return portalUserRepository.findByUniqueId(uniqueId);
  }

  public DashboardDTO countMyAppStatus(Long userId) {
    List<Object[]> obj = applicationRepository.findAppByFileStatus(userId);

    DashboardDTO dashboardDTO = new DashboardDTO();

    obj.stream().forEach(data ->{
      if(FileStatus.DRAFT.getType().equalsIgnoreCase((String)data[0]) ||
          FileStatus.OWNER_SUBMITTED.getType().equalsIgnoreCase((String)data[0])) {
        dashboardDTO.setPending(new Integer(data[1].toString()));
      }
      if(FileStatus.SUBMITTED.getType().equalsIgnoreCase((String)data[0]) ||
          FileStatus.IN_PROCESS.getType().equalsIgnoreCase((String)data[0]) ||
          FileStatus.QUERIED.getType().equalsIgnoreCase((String)data[0]) ||
          FileStatus.QUERY_REPLIED.getType().equalsIgnoreCase((String)data[0])) {
        dashboardDTO.setApplied(new Integer(data[1].toString()));
      }
      if(FileStatus.APPROVED.getType().equalsIgnoreCase((String)data[0]) ||
          FileStatus.REJECTED.getType().equalsIgnoreCase((String)data[0])) {
        dashboardDTO.setApproved(new Integer(data[1].toString()));
      }
    });
    return dashboardDTO;
  }

  public List<CommonForm> getMyApps(ApplicationType applicationType, String applicantName, Pageable pagination) {
      LoginUser loginUser = LoginUserUtil.loadLoginUser();
      if (applicantName.isEmpty() && Objects.isNull(applicationType)) {
          /*TODO find all application related to applicate*/
          return applicationRepository.findAllByUserIdOrderByIdDesc(loginUser.getUserId(), pagination);
      } else if (!Objects.isNull(applicationType) && !applicantName.isEmpty()) {
          return applicationRepository.findAllByApplicationTypeAndUserIdAndApplicantNameIgnoreCaseContainingOrderByIdDesc(applicationType, loginUser.getUserId(), applicantName,pagination);
      } else if (!applicantName.isEmpty()) {
          return applicationRepository.findAllByUserIdAndApplicantNameIgnoreCaseContainingOrderByIdDesc(loginUser.getUserId(), applicantName, pagination);
      } else
          return applicationRepository.findByApplicationTypeAndUserIdOrderByIdDesc(applicationType, loginUser.getUserId(), pagination);
  }

  public long countMyApps(ApplicationType applicationType, String applicantName) {
    LoginUser loginUser = LoginUserUtil.loadLoginUser();
      if (applicantName.isEmpty() && Objects.isNull(applicationType)) {
          /*TODO find all application related to applicate*/
          return applicationRepository.countAllByUserIdOrderByIdDesc(loginUser.getUserId());
      } else if (!Objects.isNull(applicationType) && !applicantName.isEmpty()) {
          return applicationRepository.countByApplicationTypeAndUserIdAndApplicantNameIgnoreCaseContainingOrderByIdDesc(applicationType, loginUser.getUserId(), applicantName);
      } else if (!applicantName.isEmpty()) {
          return applicationRepository.countAllByUserIdAndApplicantNameIgnoreCaseContainingOrderByIdDesc(loginUser.getUserId(), applicantName);
      } else
          return applicationRepository.countByApplicationTypeAndUserIdOrderByIdDesc(applicationType, loginUser.getUserId());
  }


  public List<CommonForm> getMyAppsForEMAgency(Long userId,ApplicationType applicationType,String applicantName, Pageable pagination) {
      List<CommonForm> commonForms = new ArrayList<>();
      if(applicationType != null  && StringUtils.isNotEmpty(applicantName) ){
          return applicationRepository.forEMAgencyWithLikeNameAndType(userId, FileStatus.OWNER_SUBMITTED.getType(),
                  applicationType.toString(),applicantName, pagination.getPageSize(), pagination.getOffset());
      }
      else if(StringUtils.isNotEmpty(applicantName) && applicationType == null ){
          return  applicationRepository.forEMAgencyWithOnlyApplicantName(userId, FileStatus.OWNER_SUBMITTED.getType()
                  , applicantName,pagination.getPageSize(), pagination.getOffset());
      } else if(StringUtils.isEmpty(applicantName) && applicationType != null ){
          return  applicationRepository.forEMAgency(userId, FileStatus.OWNER_SUBMITTED.getType()
                  , applicationType.toString(),pagination.getPageSize(), pagination.getOffset());
      }
      else
          return applicationRepository.forEMAgency(userId, FileStatus.OWNER_SUBMITTED.getType()
              , "",pagination.getPageSize(), pagination.getOffset());

  }

    public long countMyAppsForEMAgency(Long userId,ApplicationType applicationType, String applicantName) {
        if(applicationType != null  && StringUtils.isNotEmpty(applicantName) ){
            return applicationRepository.forEMAgencyWithLikeNameAndTypeCount(userId, FileStatus.OWNER_SUBMITTED.getType(),
                    applicationType.toString(),applicantName);
        }
        else if(StringUtils.isNotEmpty(applicantName) && applicationType == null ){
            return  applicationRepository.forEMAgencyWithOnlyApplicantNameCount(userId, FileStatus.OWNER_SUBMITTED.getType()
                    , applicantName);
        } else if(StringUtils.isEmpty(applicantName) && applicationType != null ){
            return  applicationRepository.forEMAgencyCount(userId, FileStatus.OWNER_SUBMITTED.getType()
                    , applicationType.toString());
        }
        else
            return applicationRepository.forEMAgencyCount(userId, FileStatus.OWNER_SUBMITTED.getType()
                    , "");
  }

  public CommonForm searchApplication(LicenseSearchDTO searchDTO) {
    CommonForm form = applicationRepository
        .findByApplicationTypeAndLicenseNumberAndIssueDateAndExpiryDate(
            searchDTO.getApplicationType(),
            searchDTO.getLicenseNumber(),
            searchDTO.issueDate(),
            searchDTO.expiryDate());
    return form;
  }

  public Object loadApplication(long applicationId, ApplicationType applicationType) {
    switch (applicationType) {
      case LIL:
        return liftInstallationRepository.findByForm_id(applicationId);
      case EIL:
        return escalatorInstallationRepository.findByForm_id(applicationId);
      case OLIFT:
        return operatingLiftRepository.findByForm_id(applicationId);
      case OESCL:
        return operatingEscalatorRepository.findByForm_id(applicationId);
      case EMAL:
        return eAndMAgencyRepository.getByForm(applicationId);
      case ITAL:
        return iAndTAgencyRepository.getByForm(applicationId);
      case OMAL:
        return oAndMAgencyRepository.getByForm(applicationId);
    }
    throw AppException.createWithMessageCode(Model.INVALID_REQUEST, Model.INVALID_APPLICATION);
  }
}