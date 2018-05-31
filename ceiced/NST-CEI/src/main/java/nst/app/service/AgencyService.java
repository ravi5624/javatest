package nst.app.service;

import nst.app.dto.*;
import nst.app.dto.newgen.NGRequestDTO;
import nst.app.enums.AgencyType;
import nst.app.enums.ApplicationType;
import nst.app.enums.UserType;
import nst.app.helper.AgencyHelper;
import nst.app.helper.OwnerDetailHelper;
import nst.app.manager.*;
import nst.app.model.EAndMAgency;
import nst.app.model.IAndTAgency;
import nst.app.model.OAndMAgency;
import nst.app.model.forms.le.CommonForm;
import nst.app.model.forms.le.EAndMAgencyLicense;
import nst.app.model.forms.le.IAndTAgencyLicense;
import nst.app.model.forms.le.OAndMAgencyLicense;
import nst.common.Service;
import nst.common.base.BaseModelDTO;
import nst.common.error.AppException;
import nst.common.security.LoginUser;
import nst.kernal.SystemConstants;
import nst.util.LoginUserUtil;
import nst.util.RepoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Component
@Transactional
public class AgencyService implements Service {

  @Autowired
  AgencyManager manager;

  @Autowired
  OwnerDetailManager ownerDetailManager;

  @Autowired
  EAndMAgencyLicenseManager eAndMAgencyLicenseManager;

  @Autowired
  IAndTAgencyLicenseManager iAndTAgencyLicenseManager;

  @Autowired
  OAndMAgencyLicenseManager oAndMAgencyLicenseManager;

  @Autowired
  AgencyHelper helper;

  @Autowired
  OwnerDetailHelper ownerDetailHelper;

  public static final int PAGE_SIZE = 100;
  public static final int START_INDEX = 1;


  public EAndMAgencyDTO save(EAndMAgencyDTO dto) {
    EAndMAgency agency = helper.toModel(dto);
    agency = manager.save(agency);
    return helper.fromModel(agency);
  }

  public OAndMAgencyDTO save(OAndMAgencyDTO dto) {
    OAndMAgency agency = helper.toModel(dto);
    agency = manager.save(agency);
    return helper.fromModel(agency);
  }

  public IAndTAgencyDTO save(IAndTAgencyDTO dto) {
    IAndTAgency agency = helper.toModel(dto);
    agency = manager.save(agency);
    return helper.fromModel(agency);
  }

  public Object getDetail() {
    LoginUser loginUser = LoginUserUtil.loadLoginUser();
    Long agencyId = loginUser.getUserId();
    if (loginUser.hasAuthority(UserType.EM_AGENCY.getType())) {
      return helper.fromModel(manager.getEMForUser(agencyId));
    }
    if (loginUser.hasAuthority(UserType.IT_AGENCY.getType())) {
      return helper.fromModel(manager.getITForUser(agencyId));
    }
    if (loginUser.hasAuthority(UserType.OM_AGENCY.getType())) {
      return helper.fromModel(manager.getOMForUser(agencyId));
    }
    if (loginUser.hasAuthority(UserType.OWNER.getType())) {
      return ownerDetailHelper.fromModel(ownerDetailManager.loadById(agencyId));
    }
    throw AppException
        .createWithMessageCode(SystemConstants.Rest.ERROR_BAD_REQUEST, "INVALID_USER");
  }

  public Object getDetail(Long agencyId) {
    LoginUser loginUser = LoginUserUtil.loadLoginUser();
    if (loginUser.hasAuthority(UserType.EM_AGENCY.getType())) {
      return helper.fromModel(manager.getEM(agencyId));
    }
    if (loginUser.hasAuthority(UserType.IT_AGENCY.getType())) {
      return helper.fromModel(manager.getIT(agencyId));
    }
    if (loginUser.hasAuthority(UserType.OM_AGENCY.getType())) {
      return helper.fromModel(manager.getOM(agencyId));
    }
    if (loginUser.hasAuthority(UserType.OWNER.getType())) {
      return ownerDetailHelper.fromModel(ownerDetailManager.loadById(agencyId));
    }
    throw AppException
        .createWithMessageCode(SystemConstants.Rest.ERROR_BAD_REQUEST, "INVALID_USER");
  }

  public List<LicenseSearchDTO> searchByLicense(String agencyAuthNo) {
    LoginUser loginUser = LoginUserUtil.loadLoginUser();
    if (loginUser.hasAuthority(UserType.EM_AGENCY.getType())) {
      return helper.fromAgencyModel(manager.searchByLicense(AgencyType.E_M_AGENCY, agencyAuthNo));
    }
    if (loginUser.hasAuthority(UserType.OM_AGENCY.getType())) {
      return helper.fromAgencyModel(manager.searchByLicense(AgencyType.O_M_AGENCY, agencyAuthNo));
    }
    if (loginUser.hasAuthority(UserType.IT_AGENCY.getType())) {
      return helper.fromAgencyModel(manager.searchByLicense(AgencyType.I_T_AGENCY, agencyAuthNo));
    }
    throw AppException
        .createWithMessageCode(SystemConstants.Rest.ERROR_BAD_REQUEST, SystemConstants.Rest.UNAUTHORISED_USER);
  }

  /*ToDo: Add Agency name as Search Key & Return Page only....*/
  public List<? extends BaseModelDTO> getFor(UserType byName) {
    if (byName == UserType.EM_AGENCY) {
      return helper.fromEMModel(manager.getAllEM());
    }
    if (byName == UserType.IT_AGENCY) {
      return helper.fromITModel(manager.getAllIT());
    }
    if (byName == UserType.OM_AGENCY) {
      return helper.fromOMModel(manager.getAllOM());
    }
    throw AppException
        .createWithMessageCode(SystemConstants.Rest.ERROR_BAD_REQUEST, "INVALID_USER");
  }

  public List<AgencyDetailDTO> search(String type, String key) {
    if (StringUtils.isEmpty(type)) {
      throw AppException.createWithMessageCode(SystemConstants.Model.INVALID_REQUEST, SystemConstants.Model.INVALID_TYPE);
    }
    if (type.equalsIgnoreCase(AgencyType.E_M_AGENCY.getType()) ||
        type.equalsIgnoreCase(AgencyType.I_T_AGENCY.getType()) ||
        type.equalsIgnoreCase(AgencyType.O_M_AGENCY.getType())) {
      return helper.fromAgencyDetail(manager.search(key, RepoUtil.pagination(START_INDEX, PAGE_SIZE), AgencyType.valueOf(type.toUpperCase())));
    }
    if (type.equalsIgnoreCase("ALL")) {
      return helper.fromAgencyDetail(manager.search(key, RepoUtil.pagination(START_INDEX, PAGE_SIZE)));
    }
    throw AppException
        .createWithMessageCode(SystemConstants.Rest.ERROR_BAD_REQUEST, SystemConstants.Model.INVALID_TYPE);
  }

  public void handleNewApproval(CommonForm commonForm, NGRequestDTO dto) {
      if (ApplicationType.EMAL == commonForm.getApplicationType()) {
          EAndMAgencyLicense eAndMAgencyLicense = eAndMAgencyLicenseManager.get(commonForm.getId());
          EAndMAgencyDTO eAndMAgencyDTO = new EAndMAgencyDTO();
          eAndMAgencyDTO.setAgencyName(eAndMAgencyLicense.getAgencyName());
          eAndMAgencyDTO.setUserId(commonForm.getUser().getId());
          eAndMAgencyDTO.setFormId(commonForm.getId());
          eAndMAgencyDTO.setLicenseNumber(dto.getLicenseNumber());
          eAndMAgencyDTO.setIssueDate(dto.getIssueDate());
          eAndMAgencyDTO.setExpiryDate(dto.getExpiryDate());
          eAndMAgencyDTO.setMobileNumber(dto.getMobileNumber());
          eAndMAgencyDTO.setEmail(dto.getEmail());
          eAndMAgencyDTO.setAgencyAddress(dto.getAgencyAddress());
          save(eAndMAgencyDTO);
          return;
      }
      if (ApplicationType.ITAL == commonForm.getApplicationType()) {
          IAndTAgencyLicense eAndMAgencyLicense = iAndTAgencyLicenseManager.get(commonForm.getId());
          IAndTAgencyDTO iAndTAgencyDTO = new IAndTAgencyDTO();
          iAndTAgencyDTO.setAgencyName(eAndMAgencyLicense.getAgencyName());
          iAndTAgencyDTO.setUserId(commonForm.getUser().getId());
          iAndTAgencyDTO.setFormId(commonForm.getId());
          iAndTAgencyDTO.setLicenseNumber(dto.getLicenseNumber());
          iAndTAgencyDTO.setIssueDate(dto.getIssueDate());
          iAndTAgencyDTO.setExpiryDate(dto.getExpiryDate());
          iAndTAgencyDTO.setMobileNumber(dto.getMobileNumber());
          iAndTAgencyDTO.setEmail(dto.getEmail());
          iAndTAgencyDTO.setAgencyAddress(dto.getAgencyAddress());

          save(iAndTAgencyDTO);
          return;
      }
      if (ApplicationType.OMAL == commonForm.getApplicationType()) {
          OAndMAgencyLicense oAndMAgencyLicense = oAndMAgencyLicenseManager.get(commonForm.getId());
          OAndMAgencyDTO oAndMAgencyDTO = new OAndMAgencyDTO();
          oAndMAgencyDTO.setAgencyName(oAndMAgencyLicense.getAgencyName());
          oAndMAgencyDTO.setUserId(commonForm.getUser().getId());
          oAndMAgencyDTO.setFormId(commonForm.getId());
          oAndMAgencyDTO.setLicenseNumber(dto.getLicenseNumber());
          oAndMAgencyDTO.setIssueDate(dto.getIssueDate());
          oAndMAgencyDTO.setExpiryDate(dto.getExpiryDate());
          oAndMAgencyDTO.setMobileNumber(dto.getMobileNumber());
          oAndMAgencyDTO.setEmail(dto.getEmail());
          oAndMAgencyDTO.setAgencyAddress(dto.getAgencyAddress());
          save(oAndMAgencyDTO);
          return;
      }
  }
  public void handleRenewalApproval(CommonForm commonForm, NGRequestDTO dto) {
      //TODO: implement code to update authNo, issueDate, & expiryDate in agency type.
//      AgencyLicenseRenewal agencyLicenseRenewal = agencyLicenseRenewalManager.get(commonForm.getId());
  }
  public void handleModificationApproval(CommonForm commonForm, NGRequestDTO dto) {
      //TODO: implement code to update authNo, issueDate, & expiryDate in agency type.
  }
}