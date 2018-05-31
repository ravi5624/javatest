package nst.app.helper;

import nst.app.common.HelperUtil;
import nst.app.dto.OperatingEscalatorDTO;
import nst.app.dto.newgen.NGOperatingEscalatorDTO;
import nst.app.enums.AddressType;
import nst.app.enums.AgencyType;
import nst.app.enums.ApplicationType;
import nst.app.enums.UserType;
import nst.app.manager.AgencyManager;
import nst.app.model.AgencyDetail;
import nst.app.model.PortalUser;
import nst.app.model.forms.le.OperatingEscalator;
import nst.common.base.BaseHelper;
import nst.common.error.AppException;
import nst.common.security.LoginUser;
import nst.kernal.SystemConstants;
import nst.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class OperatingEscalatorHelper extends
    BaseHelper<OperatingEscalator, OperatingEscalatorDTO> {

  @Autowired
  AddressHelper addressHelper;

  @Autowired
  AttachmentHelper attachmentHelper;

  @Autowired
  AgencyManager agencyManager;

  public OperatingEscalator toModel(OperatingEscalatorDTO operatingEscalatorDTO) {
    OperatingEscalator operatingEscalator = new OperatingEscalator();
    return toModel(operatingEscalator, operatingEscalatorDTO);
  }

  @Override
  public OperatingEscalator toModel(OperatingEscalator model,
      OperatingEscalatorDTO dto) {
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
      model.setOeApplicantName(dto.getOeApplicantName());
      model.setApplicantEmail(dto.getApplicantEmail());
      model.setApplicantMobile(dto.getApplicantMobile());
      model.setIsLocalAgentAppointed(dto.getIsLocalAgentAppointed());
      model.setEscalatorSiteName(dto.getEscalatorSiteName());
      model.setPersonName(dto.getPersonName());
      model.setPersonEmail(dto.getPersonEmail());
      model.setPersonMobile(dto.getPersonMobile());
      model.setMakerName(dto.getMakerName());
      model.setMakerEmail(dto.getMakerEmail());
      model.setMakerMobile(dto.getMakerMobile());
      model.setEscalatorIdentification(dto.getEscalatorIdentification());
      model.setFromFloor(dto.getFromFloor());
      model.setToFloor(dto.getToFloor());
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
      addressHelper.manageLEAddress(model.getForm(), dto.getLiftSiteAddress());
      addressHelper.manageAddress(model.getForm(), dto.getPersonAddress());
      addressHelper.manageAddress(model.getForm(), dto.getMakerAddress());
    }
//====================== Part 1 ===============================
    if (loginUser.hasAuthority(UserType.EM_AGENCY.getType())) {

      model.setFromFloor(dto.getFromFloor());
      model.setToFloor(dto.getToFloor());
      model.setEscalatorType(dto.getEscalatorType());
      model.setRatedLoad(dto.getRatedLoad());
      model.setRatedSpeed(dto.getRatedSpeed());
      model.setEscalatorPassengerCapacity(dto.getEscalatorPassengerCapacity());
      model.setEscalatorAngle(dto.getEscalatorAngle());
      model.setEscalatorWidth(dto.getEscalatorWidth());
      model.setVerticalRise(dto.getVerticalRise());
      model.setDescription(dto.getDescription());
      model.setTotalHeadRoom(dto.getTotalHeadRoom());
      model.setConstructionDetails(dto.getConstructionDetails());
      model.setApproxReaction(dto.getApproxReaction());
    }
    return model;

//    throw AuthorizationException.create(SystemConstants.Rest.UNAUTHORISED_USER);
  }

  public OperatingEscalator blankModel(Object portalUser) {
    OperatingEscalator operatingEscalator = new OperatingEscalator();
    LoginUserUtil.getLoginUser();
    PortalUser pUser = (PortalUser) portalUser;
    operatingEscalator.getForm().setUser(pUser);
    operatingEscalator.getForm().setApplicationType(ApplicationType.OESCL);

    LoginUser loginUser = LoginUserUtil.loadLoginUser();
    if (loginUser.hasAuthority(UserType.EM_AGENCY.getType())) {
      AgencyDetail agencyDetail = agencyManager.findByUser(pUser);
      operatingEscalator.setAgency(agencyDetail);
    }

    return operatingEscalator;
  }

  public OperatingEscalatorDTO fromModel(OperatingEscalator model) {
    OperatingEscalatorDTO dto = new OperatingEscalatorDTO();

    HelperUtil.toDTO(dto, model);

    AgencyDetail agency = model.getAgency();
    if(agency != null) {
      dto.setAgencyId(agency.getId());
      dto.setAgencyName(agency.getName());
    }

    dto.setOeApplicantName(model.getOeApplicantName());
    dto.setApplicantEmail(model.getApplicantEmail());
    dto.setApplicantMobile(model.getApplicantMobile());
    dto.setIsLocalAgentAppointed((model.getIsLocalAgentAppointed()));
    dto.setLocalAgentName(model.getLocalAgentName());
    dto.setLocalAgentEmail(model.getLocalAgentEmail());
    dto.setLocalAgentContactNo(model.getLocalAgentContactNo());
    dto.setEscalatorSiteName(model.getEscalatorSiteName());
    dto.setMakerName(model.getMakerName());
    dto.setMakerMobile(model.getMakerMobile());
    dto.setPersonName(model.getPersonName());
    dto.setPersonEmail(model.getPersonEmail());
    dto.setPersonMobile(model.getPersonMobile());
    dto.setEscalatorIdentification(model.getEscalatorIdentification());
    dto.setMakerEmail(model.getMakerEmail());
    dto.setFromFloor(model.getFromFloor());
    dto.setToFloor(model.getToFloor());
    dto.setEscalatorType(model.getEscalatorType());
    dto.setRatedLoad(model.getRatedLoad());
    dto.setRatedSpeed(model.getRatedSpeed());
    dto.setEscalatorPassengerCapacity(model.getEscalatorPassengerCapacity());
    dto.setEscalatorAngle(model.getEscalatorAngle());
    dto.setEscalatorWidth(model.getEscalatorWidth());
    dto.setVerticalRise(model.getVerticalRise());
    dto.setDescription(model.getDescription());
    dto.setTotalHeadRoom(model.getTotalHeadRoom());
    dto.setConstructionDetails(model.getConstructionDetails());
    dto.setApproxReaction(model.getApproxReaction());

    dto.setApplicantAddress(addressHelper.fromModel(model.getForm().getAddressFor(AddressType.APPLICANT)));
    dto.setAttachments(attachmentHelper.fromModel(model.getForm().getAttachments()));
    dto.setLocalAgentAddress(addressHelper.fromModel(model.getForm().getAddressFor(AddressType.AGENT)));
    dto.setLiftSiteAddress(addressHelper.leAddressDTO(model.getForm().getAddressFor(AddressType.LIFTSITE)));
    dto.setPersonAddress(addressHelper.fromModel(model.getForm().getAddressFor(AddressType.PERSON)));
    dto.setMakerAddress(addressHelper.fromModel(model.getForm().getAddressFor(AddressType.MAKER)));


    return dto;
  }

  public NGOperatingEscalatorDTO toNGDTO(OperatingEscalator model) {
    NGOperatingEscalatorDTO dto = new NGOperatingEscalatorDTO();

    HelperUtil.getNGDTO(model, dto);

    AgencyDetail agency = model.getAgency();
    if(agency != null) {
      dto.setAgencyId(agency.getId());
      dto.setAgencyName(agency.getName());
    }

    dto.setApplicantName(model.getOeApplicantName());
    dto.setApplicantEmail(model.getApplicantEmail());
    dto.setApplicantMobile(model.getApplicantMobile());
    dto.setIsLocalAgentAppointed((model.getIsLocalAgentAppointed()));
    dto.setLocalAgentName(model.getLocalAgentName());
    dto.setLocalAgentEmail(model.getLocalAgentEmail());
    dto.setLocalAgentContactNo(model.getLocalAgentContactNo());
    dto.setEscalatorSiteName(model.getEscalatorSiteName());
    dto.setEscalatorTownPlanningNo(model.getEscalatorTownPlanningNo());
    dto.setEscalatorFpNo(model.getEscalatorFpNo());
    dto.setEscalatorRsNo(model.getEscalatorRsNo());
    dto.setEscalatorSubPlotNo(model.getEscalatorSubPlotNo());
    dto.setEscalatorBlockTenamentNo(model.getEscalatorBlockTenamentNo());
    dto.setEscalatorCitySurveyNo(model.getEscalatorCitySurveyNo());
    dto.setMakerName(model.getMakerName());
    dto.setMakerMobile(model.getMakerMobile());
    dto.setPersonName(model.getPersonName());
    dto.setPersonEmail(model.getPersonEmail());
    dto.setPersonMobile(model.getPersonMobile());
    dto.setEscalatorIdentification(model.getEscalatorIdentification());
    dto.setMakerEmail(model.getMakerEmail());
    dto.setFromFloor(model.getFromFloor());
    dto.setToFloor(model.getToFloor());
    dto.setEscalatorType(model.getEscalatorType());
    dto.setRatedLoad(model.getRatedLoad());
    dto.setRatedSpeed(model.getRatedSpeed());
    dto.setEscalatorPassengerCapacity(model.getEscalatorPassengerCapacity());
    dto.setEscalatorAngle(model.getEscalatorAngle());
    dto.setEscalatorWidth(model.getEscalatorWidth());
    dto.setVerticalRise(model.getVerticalRise());
    dto.setDescription(model.getDescription());
    dto.setTotalHeadRoom(model.getTotalHeadRoom());
    dto.setConstructionDetails(model.getConstructionDetails());
    dto.setApproxReaction(model.getApproxReaction());

    dto.setApplicantAddress(addressHelper.fromModel(model.getForm().getAddressFor(AddressType.APPLICANT)));
    dto.setAttachments(attachmentHelper.fromModelNG(model.getForm().getAttachments()));
    dto.setLocalAgentAddress(addressHelper.fromModel(model.getForm().getAddressFor(AddressType.AGENT)));
    dto.setLiftSiteAddress(addressHelper.toLINGDTO(model.getForm().getAddressFor(AddressType.LIFTSITE)));
    dto.setPersonAddress(addressHelper.fromModel(model.getForm().getAddressFor(AddressType.PERSON)));
    dto.setMakerAddress(addressHelper.fromModel(model.getForm().getAddressFor(AddressType.MAKER)));

    return dto;
  }
}