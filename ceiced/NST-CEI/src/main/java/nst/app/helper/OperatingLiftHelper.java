package nst.app.helper;

import nst.app.common.HelperUtil;
import nst.app.dto.OperatingLiftDTO;
import nst.app.dto.newgen.NGOperatingLiftDTO;
import nst.app.enums.AddressType;
import nst.app.enums.AgencyType;
import nst.app.enums.ApplicationType;
import nst.app.enums.UserType;
import nst.app.manager.AgencyManager;
import nst.app.model.AgencyDetail;
import nst.app.model.PortalUser;
import nst.app.model.forms.le.OperatingLift;
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
public class OperatingLiftHelper extends
    BaseHelper<OperatingLift, OperatingLiftDTO> {

  @Autowired
  AddressHelper addressHelper;

  @Autowired
  AttachmentHelper attachmentHelper;

  @Autowired
  AgencyManager agencyManager;

  public OperatingLift toModel(OperatingLiftDTO operatingLiftDTO) {
    OperatingLift operatingLift = new OperatingLift();
    return toModel(operatingLift, operatingLiftDTO);
  }

  @Override
  public OperatingLift toModel(OperatingLift model,
                               OperatingLiftDTO dto) {
    HelperUtil.toModel(model.getForm(), dto);

    model.getForm().setLicenseNumber(dto.getLicenseNumber());
    model.getForm().setIssueDate(AllUtil.toUIDate(dto.getIssueDate()));
    model.getForm().setExpiryDate(AllUtil.toUIDate(dto.getExpiryDate()));
    //===== Part 1 ===========================
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
      model.setApplicantEmail(dto.getApplicantEmail());
      model.setApplicantMobile(dto.getApplicantMobile());
      model.setApplicantName(dto.getOlApplicantName());
      model.setLocalAgentAppointed(HelperUtil.toBoolean(dto.getLocalAgentAppointed()));
      model.setLocalAgentName(dto.getLocalAgentName());
      model.setLocalAgentEmail(dto.getLocalAgentEmail());
      model.setLocalAgentContactNo(dto.getLocalAgentContactNo());
      model.setLiftSiteName(dto.getLiftSiteName());
      model.setPersonName(dto.getPersonName());
      model.setPersonEmail(dto.getPersonEmail());
      model.setPersonMobile(dto.getPersonMobile());

      addressHelper.manageAddress(model.getForm(), dto.getApplicantAddress());
      addressHelper.manageAddress(model.getForm(), dto.getLocalAgentAddress());
      addressHelper.manageAddress(model.getForm(), dto.getPersonAddress());
      addressHelper.manageLEAddress(model.getForm(), dto.getLiftSiteAddress());
    }
    //===== Part 1 ===========================
    //===== Part 2 ===========================
    if (loginUser.hasAuthority(UserType.EM_AGENCY.getType())) {

      model.setLiftType(dto.getLiftType());
      model.setLiftSubCategory(dto.getLiftSubCategory());
      model.setOtherCategoryLift(dto.getOtherCategoryLift());
      model.setMakeLift(dto.getMakeLift());
      model.setLiftPassengerCapacity(dto.getLiftPassengerCapacity());
      model.setRatedLoad((dto.getRatedLoad()));
      model.setRatedSpeed(dto.getRatedSpeed());
      model.setTotalLiftWeight(dto.getTotalLiftWeight());
      model.setTotalCounterWeight(dto.getTotalCounterWeight());
      model.setSuspensionRopeSize(dto.getSuspensionRopeSize());
      model.setPitDepth(dto.getPitDepth());
      model.setTravelMeters(dto.getTravelMeters());
      model.setTravelTime(dto.getTravelTime());
      model.setBasementFloors(dto.getBasementFloors());
      model.setGroundFloors(dto.getGroundFloors());
      model.setMezzaineFloors(dto.getMezzaineFloors());
      model.setServedFloors(dto.getServedFloors());
      model.setEntrances(dto.getEntrances());
      model.setFloorClosed(dto.getFloorClosed());
      model.setDummyFloors(dto.getDummyFloors());
      model.setHeadRoomDetail(dto.getHeadRoomDetail());
    }
    return model;
  }

  public OperatingLift blankModel(Object object) {
    OperatingLift operatingLift = new OperatingLift();
    LoginUserUtil.getLoginUser();
    PortalUser pUser = (PortalUser) object;
    operatingLift.getForm().setUser(pUser);
    operatingLift.getForm().setApplicationType(ApplicationType.OLIFT);

    LoginUser loginUser = LoginUserUtil.loadLoginUser();
    if (loginUser.hasAuthority(UserType.EM_AGENCY.getType())) {
      AgencyDetail agencyDetail = agencyManager.findByUser(pUser);
      operatingLift.setAgency(agencyDetail);
    }

    return operatingLift;
  }

  public OperatingLiftDTO fromModel(OperatingLift model) {

    OperatingLiftDTO dto = new OperatingLiftDTO();
    dto.setLicenseNumber(model.getForm().getLicenseNumber());
    dto.setIssueDate(AllUtil.formatUIDate(model.getForm().getIssueDate()));
    dto.setExpiryDate(AllUtil.formatUIDate(model.getForm().getExpiryDate()));
    HelperUtil.toDTO(dto, model);

    dto.setLicenseNumber(model.getForm().getLicenseNumber());
    dto.setIssueDate(AllUtil.formatUIDate(model.getForm().getIssueDate()));
    dto.setExpiryDate(AllUtil.formatUIDate(model.getForm().getExpiryDate()));
//===== Part 1 ===========================

    AgencyDetail agency = model.getAgency();
    if(agency != null) {
      dto.setAgencyId(agency.getId());
      dto.setAgencyName(agency.getName());
    }

    dto.setOlApplicantName(model.getApplicantName());
    dto.setApplicantEmail(model.getApplicantEmail());
    dto.setApplicantMobile(model.getApplicantMobile());
    dto.setLocalAgentAppointed(HelperUtil.fromBoolean(model.getLocalAgentAppointed()));
    dto.setLocalAgentName(model.getLocalAgentName());
    dto.setLocalAgentEmail(model.getLocalAgentEmail());
    dto.setLocalAgentContactNo(model.getLocalAgentContactNo());
    dto.setLiftSiteName(model.getLiftSiteName());
    dto.setPersonName(model.getPersonName());
    dto.setPersonEmail(model.getPersonEmail());
    dto.setPersonMobile(model.getPersonMobile());
    //===== Part 1 ===========================
    //===== Part 2 ===========================
    dto.setLiftType(model.getLiftType());
    dto.setLiftSubCategory(model.getLiftSubCategory());
    dto.setOtherCategoryLift(model.getOtherCategoryLift());
    dto.setMakeLift(model.getMakeLift());
    dto.setLiftPassengerCapacity(model.getLiftPassengerCapacity());
    dto.setRatedLoad((model.getRatedLoad()));
    dto.setRatedSpeed(model.getRatedSpeed());
    dto.setTotalLiftWeight(model.getTotalLiftWeight());
    dto.setTotalCounterWeight(model.getTotalCounterWeight());
    dto.setSuspensionRopeSize(model.getSuspensionRopeSize());
    dto.setPitDepth(model.getPitDepth());
    dto.setTravelMeters(model.getTravelMeters());
    dto.setTravelTime(model.getTravelTime());
    dto.setBasementFloors(model.getBasementFloors());
    dto.setGroundFloors(model.getGroundFloors());
    dto.setMezzaineFloors(model.getMezzaineFloors());
    dto.setServedFloors(model.getServedFloors());
    dto.setEntrances(model.getEntrances());
    dto.setFloorClosed(model.getFloorClosed());
    dto.setDummyFloors(model.getDummyFloors());
    dto.setHeadRoomDetail(model.getHeadRoomDetail());
    dto.setLiftSiteAddress(addressHelper.leAddressDTO(model.getForm().getAddressFor(AddressType.LIFTSITE)));
    dto.setApplicantAddress(addressHelper.fromModel(model.getForm().getAddressFor(AddressType.APPLICANT)));
    dto.setLocalAgentAddress(addressHelper.fromModel(model.getForm().getAddressFor(AddressType.AGENT)));
    dto.setPersonAddress(addressHelper.fromModel(model.getForm().getAddressFor(AddressType.PERSON)));

    dto.setAttachments(attachmentHelper.fromModel(model.getForm().getAttachments()));

    return dto;
  }

  public NGOperatingLiftDTO toNGDTO(OperatingLift model) {

    NGOperatingLiftDTO dto = new NGOperatingLiftDTO();
    dto.setIssueDate(AllUtil.formatUIDate(model.getForm().getIssueDate()));
    dto.setExpiryDate(AllUtil.formatUIDate(model.getForm().getExpiryDate()));
    HelperUtil.getNGDTO(model,dto);
    dto.setLicenseNumber(model.getForm().getLicenseNumber());
    dto.setIssueDate(AllUtil.formatUIDate(model.getForm().getIssueDate()));
    dto.setExpiryDate(AllUtil.formatUIDate(model.getForm().getExpiryDate()));

    //===== Part 1 ===========================
    AgencyDetail agency = model.getAgency();
    if(agency != null) {
      dto.setAgencyId(agency.getId());
      dto.setAgencyName(agency.getName());
    }

    dto.setApplicantName(model.getApplicantName());
    dto.setApplicantEmail(model.getApplicantEmail());
    dto.setApplicantMobile(model.getApplicantMobile());
    dto.setLocalAgentAppointed(HelperUtil.fromBoolean(model.getLocalAgentAppointed()));
    dto.setLocalAgentName(model.getLocalAgentName());
    dto.setLocalAgentEmail(model.getLocalAgentEmail());
    dto.setLocalAgentContactNo(model.getLocalAgentContactNo());
    dto.setLiftSiteName(model.getLiftSiteName());
    dto.setPersonName(model.getPersonName());
    dto.setPersonEmail(model.getPersonEmail());
    dto.setPersonMobile(model.getPersonMobile());
    //===== Part 1 ===========================
    //===== Part 2 ===========================
    dto.setLiftType(model.getLiftType());
    dto.setLiftSubCategory(model.getLiftSubCategory());
    dto.setOtherCategoryLift(model.getOtherCategoryLift());
    dto.setMakeLift(model.getMakeLift());
    dto.setLiftPassengerCapacity(model.getLiftPassengerCapacity());
    dto.setRatedLoad((model.getRatedLoad()));
    dto.setRatedSpeed(model.getRatedSpeed());
    dto.setTotalLiftWeight(model.getTotalLiftWeight());
    dto.setTotalCounterWeight(model.getTotalCounterWeight());
    dto.setSuspensionRopeSize(model.getSuspensionRopeSize());
    dto.setPitDepth(model.getPitDepth());
    dto.setTravelMeters(model.getTravelMeters());
    dto.setTravelTime(model.getTravelTime());
    dto.setBasementFloors(model.getBasementFloors());
    dto.setGroundFloors(model.getGroundFloors());
    dto.setMezzaineFloors(model.getMezzaineFloors());
    dto.setServedFloors(model.getServedFloors());
    dto.setEntrances(model.getEntrances());
    dto.setFloorClosed(model.getFloorClosed());
    dto.setDummyFloors(model.getDummyFloors());
    dto.setHeadRoomDetail(model.getHeadRoomDetail());

    dto.setApplicantAddress(addressHelper.fromModel(model.getForm().getAddressFor(AddressType.APPLICANT)));
    dto.setLocalAgentAddress(addressHelper.fromModel(model.getForm().getAddressFor(AddressType.AGENT)));
    dto.setPersonAddress(addressHelper.fromModel(model.getForm().getAddressFor(AddressType.PERSON)));
    dto.setLiftSiteAddress(addressHelper.toLINGDTO(model.getForm().getAddressFor(AddressType.LIFTSITE)));
    dto.setAttachments(attachmentHelper.fromModelNG(model.getForm().getAttachments()));

    return dto;
  }
}