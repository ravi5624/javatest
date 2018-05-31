package nst.app.helper;

import nst.app.common.HelperUtil;
import nst.app.dto.EscalatorInstallationDTO;
import nst.app.dto.newgen.NGEscalatorInstallationDTO;
import nst.app.enums.AddressType;
import nst.app.enums.AgencyType;
import nst.app.enums.UserType;
import nst.app.manager.AgencyManager;
import nst.app.model.AgencyDetail;
import nst.app.model.PortalUser;
import nst.app.model.forms.le.EscalatorInstallation;
import nst.common.base.BaseHelper;
import nst.common.error.AppException;
import nst.common.security.LoginUser;
import nst.kernal.SystemConstants;
import nst.util.AllUtil;
import nst.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class EscalatorInstallationHelper extends
    BaseHelper<EscalatorInstallation, EscalatorInstallationDTO> {

  @Autowired
  AttachmentHelper attachmentHelper;

  @Autowired
  AgencyManager agencyManager;

  @Autowired
  AddressHelper addressHelper;

  public EscalatorInstallation toModel(EscalatorInstallationDTO EscalatorInstallationDTO) {
    EscalatorInstallation portalUser = new EscalatorInstallation();
    return toModel(portalUser, EscalatorInstallationDTO);
  }

  @Override
  public EscalatorInstallation toModel(EscalatorInstallation model,
      EscalatorInstallationDTO dto) {
    HelperUtil.toModel(model.getForm(), dto);

//======================== Part 1 ============================
    LoginUser loginUser = LoginUserUtil.loadLoginUser();
    if (loginUser.hasAuthority(UserType.OWNER.getType()) || model.getOwner().getId().equals(loginUser.getUserId())) {
      if(loginUser.hasAuthority(UserType.OWNER.getType())) {
        if (Objects.isNull(dto.getAgencyId())) {
          model.setAgency(null);
        } else {
          AgencyDetail agencyDetail = agencyManager.findById(dto.getAgencyId());
          //TODO [SAGAR]: Also check agency expiry date is not less than current date.
          if (!agencyDetail.getAgencyType().equals(AgencyType.E_M_AGENCY)) {
            throw AppException.createWithMessageCode(SystemConstants.Model.INVALID_REQUEST, SystemConstants.Model.INVALID_AGENCY);
          }
          model.setAgency(agencyDetail);
        }
      }
      model.getForm().setApplicantName(dto.getApplicantName());
      model.setEiApplicantName(dto.getEiApplicantName());
      model.setEscalatorLicenseNumber(dto.getEscalatorLicenseNumber());
      model.setApplicationFor(dto.getApplicationFor());
      model.setApplicantEmail(dto.getApplicantEmail());
      model.setApplicantMobile(dto.getApplicantMobile());
      model.setIsLocalAgentAppointed(dto.getIsLocalAgentAppointed());
      model.setPersonName(dto.getPersonName());
      model.setPersonEmail(dto.getPersonEmail());
      model.setPersonMobile(dto.getPersonMobile());
      model.setMakerName(dto.getMakerName());
      model.setMakerEmail(dto.getMakerEmail());
      model.setMakerMobile(dto.getMakerMobile());
      model.setEscalatorIdentification(dto.getEscalatorIdentification());
      model.setFromFloor(dto.getFromFloor());
      model.setToFloor(dto.getToFloor());
      model.setEscalatorSiteName(dto.getEscalatorSiteName());

      if(!HelperUtil.toBoolean(dto.getIsLocalAgentAppointed())){
        model.getForm().removeAddressFor(AddressType.AGENT);
        model.setLocalAgentName(null);
        model.setLocalAgentEmail(null);
        model.setLocalAgentContactNo(null);
      }else{
        model.setLocalAgentName(dto.getLocalAgentName());
        model.setLocalAgentEmail(dto.getLocalAgentEmail());
        model.setLocalAgentContactNo(dto.getLocalAgentContactNo());
        addressHelper.manageAddress(model.getForm(), dto.getLocalAgentAddress());
      }
      addressHelper.manageAddress(model.getForm(), dto.getApplicantAddress());
      addressHelper.manageAddress(model.getForm(), dto.getPersonAddress());
      addressHelper.manageLEAddress(model.getForm(), dto.getLiftSiteAddress());
      addressHelper.manageAddress(model.getForm(), dto.getMakerAddress());
    }
//====================== Part 1 ===============================
//====================== Part 2 ===============================
    if (loginUser.hasAuthority(UserType.EM_AGENCY.getType())) {
      model.setRatedSpeed(dto.getRatedSpeed());
      model.setBalusmadesWidth(dto.getBalusmadesWidth());
      model.setHorizontalDistance(dto.getHorizontalDistance());
      model.setRatedLoad(dto.getRatedLoad());
      model.setEscalatorPersonCapacity(dto.getEscalatorPersonCapacity());
      model.setEscalatorAngle(dto.getEscalatorAngle());
      model.setEscalatorWidth(dto.getEscalatorWidth());
      model.setVerticalRise(dto.getVerticalRise());
      model.setDescription(dto.getDescription());
      model.setConstructionDetails(dto.getConstructionDetails());
      model.setCommencementWork(AllUtil.toUIDate(dto.getCommencementWork()));
      model.setCompletionWork(AllUtil.toUIDate(dto.getCompletionWork()));
    }
    return model;
  }

  public EscalatorInstallation blankModel(Object portalUser) {
    EscalatorInstallation ownerDetail = new EscalatorInstallation();
    LoginUserUtil.getLoginUser();
    PortalUser pUser = (PortalUser) portalUser;
    ownerDetail.getForm().setUser(pUser);

    LoginUser loginUser = LoginUserUtil.loadLoginUser();
    if (loginUser.hasAuthority(UserType.EM_AGENCY.getType())) {
      AgencyDetail agencyDetail = agencyManager.findByUser(pUser);
      ownerDetail.setAgency(agencyDetail);
    }

    return ownerDetail;
  }

  public EscalatorInstallationDTO fromModel(EscalatorInstallation model) {
    EscalatorInstallationDTO dto = new EscalatorInstallationDTO();
    //======================== Part 1 ============================
    HelperUtil.toDTO(dto, model);
    AgencyDetail agency = model.getAgency();
    if(agency != null) {
      dto.setAgencyId(agency.getId());
      dto.setAgencyName(agency.getName());
    }
    dto.setEscalatorLicenseNumber(model.getEscalatorLicenseNumber());
    dto.setApplicationFor(model.getApplicationFor());
    dto.setEiApplicantName(model.getEiApplicantName());
    dto.setApplicantEmail(model.getApplicantEmail());
    dto.setApplicantMobile(model.getApplicantMobile());
    dto.setIsLocalAgentAppointed(model.getIsLocalAgentAppointed());
    dto.setLocalAgentName(model.getLocalAgentName());
    dto.setLocalAgentEmail(model.getLocalAgentEmail());
    dto.setLocalAgentContactNo(model.getLocalAgentContactNo());
    dto.setPersonName(model.getPersonName());
    dto.setPersonEmail(model.getPersonEmail());
    dto.setPersonMobile(model.getPersonMobile());
    dto.setMakerName(model.getMakerName());
    dto.setMakerEmail(model.getMakerEmail());
    dto.setMakerMobile(model.getMakerMobile());
    dto.setEscalatorIdentification(model.getEscalatorIdentification());
    dto.setFromFloor(model.getFromFloor());
    dto.setToFloor(model.getToFloor());
    dto.setEscalatorSiteName(model.getEscalatorSiteName());
//====================== Part 1 ===============================
//====================== Part 2 ===============================
    dto.setRatedSpeed(model.getRatedSpeed());
    dto.setBalusmadesWidth(model.getBalusmadesWidth());
    dto.setHorizontalDistance(model.getHorizontalDistance());
    dto.setRatedLoad(model.getRatedLoad());
    dto.setEscalatorPersonCapacity(model.getEscalatorPersonCapacity());
    dto.setEscalatorAngle(model.getEscalatorAngle());
    dto.setEscalatorWidth(model.getEscalatorWidth());
    dto.setVerticalRise(model.getVerticalRise());
    dto.setDescription(model.getDescription());
    dto.setConstructionDetails(model.getConstructionDetails());
    dto.setCommencementWork(AllUtil.formatUIDate(model.getCommencementWork()));
    dto.setCompletionWork(AllUtil.formatUIDate(model.getCompletionWork()));

    dto.setApplicantAddress(addressHelper.fromModel(model.getForm().getAddressFor(AddressType.APPLICANT)));
    dto.setLocalAgentAddress(addressHelper.fromModel(model.getForm().getAddressFor(AddressType.AGENT)));
    dto.setLiftSiteAddress(addressHelper.leAddressDTO(model.getForm().getAddressFor(AddressType.LIFTSITE)));
    dto.setPersonAddress(addressHelper.fromModel(model.getForm().getAddressFor(AddressType.PERSON)));
    dto.setMakerAddress(addressHelper.fromModel(model.getForm().getAddressFor(AddressType.MAKER)));
    dto.setAttachments(attachmentHelper.fromModel(model.getForm().getAttachments()));

    return dto;
  }

  public NGEscalatorInstallationDTO toNGDTO(EscalatorInstallation model) {

    NGEscalatorInstallationDTO dto = new NGEscalatorInstallationDTO();
    HelperUtil.getNGDTO(model,dto);
    //======================== Part 1 ============================
    AgencyDetail agency = model.getAgency();
    if(agency != null) {
      dto.setAgencyId(agency.getId());
      dto.setAgencyName(agency.getName());
    }
    dto.setEscalatorLicenseNumber(model.getEscalatorLicenseNumber());
    dto.setApplicationFor(model.getApplicationFor());
    dto.setApplicantName(model.getEiApplicantName());
    dto.setApplicantEmail(model.getApplicantEmail());
    dto.setApplicantMobile(model.getApplicantMobile());
    dto.setIsLocalAgentAppointed(model.getIsLocalAgentAppointed());
    dto.setLocalAgentName(model.getLocalAgentName());
    dto.setLocalAgentEmail(model.getLocalAgentEmail());
    dto.setLocalAgentContactNo(model.getLocalAgentContactNo());
    dto.setPersonName(model.getPersonName());
    dto.setPersonEmail(model.getPersonEmail());
    dto.setPersonMobile(model.getPersonMobile());
    dto.setMakerName(model.getMakerName());
    dto.setMakerEmail(model.getMakerEmail());
    dto.setMakerMobile(model.getMakerMobile());
    dto.setEscalatorIdentification(model.getEscalatorIdentification());
    dto.setFromFloor(model.getFromFloor());
    dto.setToFloor(model.getToFloor());
    dto.setEscalatorSiteName(dto.getEscalatorSiteName());
//====================== Part 1 ===============================
//====================== Part 2 ===============================
    dto.setRatedSpeed(model.getRatedSpeed());
    dto.setBalusmadesWidth(model.getBalusmadesWidth());
    dto.setHorizontalDistance(model.getHorizontalDistance());
    dto.setRatedLoad(model.getRatedLoad());
    dto.setEscalatorPersonCapacity(model.getEscalatorPersonCapacity());
    dto.setEscalatorAngle(model.getEscalatorAngle());
    dto.setEscalatorWidth(model.getEscalatorWidth());
    dto.setVerticalRise(model.getVerticalRise());
    dto.setDescription(model.getDescription());
    dto.setConstructionDetails(model.getConstructionDetails());
    dto.setCommencementWork(AllUtil.formatNGDate(model.getCommencementWork()));
    dto.setCompletionWork(AllUtil.formatNGDate(model.getCompletionWork()));
    dto.setApplicantAddress(addressHelper.fromModel(model.getForm().getAddressFor(AddressType.APPLICANT)));
    dto.setLocalAgentAddress(addressHelper.fromModel(model.getForm().getAddressFor(AddressType.AGENT)));
    dto.setLiftSiteAddress(addressHelper.toLINGDTO(model.getForm().getAddressFor(AddressType.LIFTSITE)));
    dto.setPersonAddress(addressHelper.fromModel(model.getForm().getAddressFor(AddressType.PERSON)));
    dto.setMakerAddress(addressHelper.fromModel(model.getForm().getAddressFor(AddressType.MAKER)));
    dto.setAttachments(attachmentHelper.fromModelNG(model.getForm().getAttachments()));
    return dto;
  }
}