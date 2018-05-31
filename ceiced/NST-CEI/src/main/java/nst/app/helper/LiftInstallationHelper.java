package nst.app.helper;

import nst.app.common.HelperUtil;
import nst.app.dto.LiftInstallationDTO;
import nst.app.dto.newgen.NGLiftInstallationDTO;
import nst.app.enums.AddressType;
import nst.app.enums.AgencyType;
import nst.app.enums.BufferType;
import nst.app.enums.UserType;
import nst.app.manager.AgencyManager;
import nst.app.model.AgencyDetail;
import nst.app.model.PortalUser;
import nst.app.model.forms.le.LiftInstallation;
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
public class LiftInstallationHelper extends
        BaseHelper<LiftInstallation, LiftInstallationDTO> {

    @Autowired
    AttachmentHelper attachmentHelper;

    @Autowired
    AddressHelper addressHelper;

    @Autowired
    BufferDetailHelper bufferDetailHelper;

    @Autowired
    AgencyManager agencyManager;

    public LiftInstallation toModel(LiftInstallationDTO dto) {
        LiftInstallation model = new LiftInstallation();
        return toModel(model, dto);
    }

    @Override
    public LiftInstallation toModel(LiftInstallation to, LiftInstallation from) {
        to.setAlarmSystemDetail(from.getAlarmSystemDetail());
        return to;
    }

    public LiftInstallation toModel(LiftInstallation model, LiftInstallationDTO dto) {
        HelperUtil.toModel(model.getForm(), dto);

        LoginUser loginUser = LoginUserUtil.loadLoginUser();
        if (loginUser.hasAuthority(UserType.OWNER.getType()) || model.getOwner().getId().equals(loginUser.getUserId())) {
            if(loginUser.hasAuthority(UserType.OWNER.getType())){
                if(Objects.isNull(dto.getAgencyId())) {
                    model.setAgency(null);
                }else{
                    AgencyDetail agencyDetail = agencyManager.findById(dto.getAgencyId());
                    //TODO [SAGAR]: Also check agency expiry date is not less than current date.
                    if(!agencyDetail.getAgencyType().equals(AgencyType.E_M_AGENCY)){
                        throw AppException.createWithMessageCode(SystemConstants.Model.INVALID_REQUEST, SystemConstants.Model.INVALID_AGENCY);
                    }
                    model.setAgency(agencyDetail);
                }
            }
            model.getForm().setApplicantName(dto.getApplicantName());
            model.setLiftApplication(dto.getLiftApplication());
            model.setLiftLicenseNumber(dto.getLiftLicenseNumber());
            model.setLeApplicantName(dto.getLeApplicantName());
            model.setApplicantEmail(dto.getApplicantEmail());
            model.setApplicantMobile(dto.getApplicantMobile());
            model.setIsLocalAgent(HelperUtil.toBoolean(dto.getIsLocalAgent()));
            model.setPersonName(dto.getPersonName());
            model.setPersonEmail(dto.getPersonEmail());
            model.setPersonMobile(dto.getPersonMobile());
            if(!HelperUtil.toBoolean(dto.getIsLocalAgent())){
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
        }
        if (loginUser.hasAuthority(UserType.EM_AGENCY.getType())) {
            model.setLiftType(dto.getLiftType());
            model.setNumberOfLift(dto.getNumberOfLift());
            model.setInternalCarSize(dto.getInternalCarSize());
            model.setSubCategoryLift(dto.getSubCategoryLift());
            model.setOtherCategoryLift(dto.getOtherCategoryLift());
            model.setMakeOfLift(dto.getMakeOfLift());
            model.setNumberOfPassengers(dto.getNumberOfPassengers());
            model.setRatedLoad(dto.getRatedLoad());
            model.setRatedSpeed(dto.getRatedSpeed());
            model.setTravelMeters(dto.getTravelMeters());
            model.setTravelTime(dto.getTravelTime());
            model.setBasementFloors(dto.getBasementFloors());
            model.setGroundFloors(dto.getGroundFloors());
            model.setMezzaineFloors(dto.getMezzaineFloors());
            model.setServedFloors(dto.getServedFloors());
            model.setNumberOfEntrances(dto.getNumberOfEntrances());
            model.setClosedFloors(dto.getClosedFloors());
            model.setDummyFloors(dto.getDummyFloors());
            model.setControlMethodForLift(dto.getControlMethodForLift());
            model.setLiftDoorOperationType(dto.getLiftDoorOperationType());
            model.setCarLength(dto.getCarLength());
            model.setCarBreadth(dto.getCarBreadth());
            model.setPlatformDetails(dto.getPlatformDetails());
            model.setCarFrameDetails(dto.getCarFrameDetails());
            model.setCarDoorDetails(dto.getCarDoorDetails());
            model.setLandingDoorDetails(dto.getLandingDoorDetails());
            model.setLockingArrangementDetails(dto.getLockingArrangementDetails());
            model.setCounterweightPosition(dto.getCounterweightPosition());
            model.setCounterweightApproxKg(dto.getCounterweightApproxKg());
            model.setLiftPitLength(dto.getLiftPitLength());
            model.setLiftPitWidth(dto.getLiftPitWidth());
            model.setLiftPitBreadth(dto.getLiftPitBreadth());
            model.setLiftWellLength(dto.getLiftWellLength());
            model.setLiftWellBreadth(dto.getLiftWellBreadth());
            model.setLiftWellHeight(dto.getLiftWellHeight());
            model.setLiftWellAndBeamDetails(dto.getLiftWellAndBeamDetails());
            model.setHeadRoomDistance(dto.getHeadRoomDistance());
            model.setBottomCarRunBy(dto.getBottomCarRunBy());
            model.setBottomCounterWeightRunBy(dto.getBottomCounterWeightRunBy());
            model.setBottomCounterWeightClearance(dto.getBottomCounterWeightClearance());
            model.setTopCounterWeightClearance(dto.getTopCounterWeightClearance());
            model.setBottomCarClearance(dto.getBottomCarClearance());
            model.setTopCarClearance(dto.getTopCarClearance());
            model.setLiftWellDimLength(dto.getLiftWellDimLength());
            model.setLiftWellDimBreadth(dto.getLiftWellDimBreadth());
            model.setLiftWellDimHeight(dto.getLiftWellDimHeight());
            model.setCarCounterDetail(dto.getCarCounterDetail());
            model.setCarBodyWork(dto.getCarBodyWork());
            model.setFrontDistance(dto.getFrontDistance());
            model.setRearDistance(dto.getRearDistance());
            model.setLeftDistance(dto.getLeftDistance());
            model.setRightDistance(dto.getRightDistance());
            model.setMachineRoomDetails(dto.getMachineRoomDetails());
            model.setMachineRoomLength(dto.getMachineRoomLength());
            model.setMachineRoomBreadth(dto.getMachineRoomBreadth());
            model.setMachineRoomDimHeight(dto.getMachineRoomDimHeight());
            model.setMachineGearDetail(dto.getMachineGearDetail());
            model.setSheavePulleyDetail(dto.getSheavePulleyDetail());
            model.setSheavePulleyDiameter(dto.getSheavePulleyDiameter());
            model.setDiverterPulleyDetail(dto.getDiverterPulleyDetail());
            model.setRopingSize(dto.getRopingSize());
            model.setNoOfRope(dto.getNoOfRope());
            model.setSupportCableDesc(dto.getSupportCableDesc());
            model.setOverSpeedGovernor(dto.getOverSpeedGovernor());
            model.setSafetyGearDetail(dto.getSafetyGearDetail());
            //TODO clear fields if IsRetiringCam = no
            model.setIsRetiringCam(HelperUtil.toBoolean(dto.getIsRetiringCam()));
            model.setRetiringCamDetail(dto.getRetiringCamDetail());
            model.setConstructionDetail(dto.getConstructionDetail());
            model.setCallIndicatorDetail(dto.getCallIndicatorDetail());
            model.setEmergencyStopSwitchDetail(dto.getEmergencyStopSwitchDetail());
            model.setFloorLevelDetail(dto.getFloorLevelDetail());
            model.setFloorSelectorDetail(dto.getFloorSelectorDetail());
            model.setSlackRopeDetail(dto.getSlackRopeDetail());
            model.setTerminalSlowDetail(dto.getTerminalSlowDetail());
            model.setTerminalStopNormalDetail(dto.getTerminalStopNormalDetail());
            model.setTerminalStopFinalDetail(dto.getTerminalStopFinalDetail());
            //TODO clear fields if IsFiremanSwitch = no
            model.setIsFiremanSwitch(HelperUtil.toBoolean(dto.getIsFiremanSwitch()));
            model.setFiremanSwitchDetail(dto.getFiremanSwitchDetail());
            model.setMainSwitchDetails(dto.getMainSwitchDetails());
            model.setWiringMachineDetail(dto.getWiringMachineDetail());
            model.setWiringLiftPitDetail(dto.getWiringLiftPitDetail());
            model.setControlSwitchDetail(dto.getControlSwitchDetail());
            model.setOverCurrentProtection(dto.getOverCurrentProtection());
            model.setAlarmSystemDetail(dto.getAlarmSystemDetail());
            model.setEarthingDetail(dto.getEarthingDetail());
            model.setEmergencySignalDetail(dto.getEmergencySignalDetail());
            model.setPowerDetail(dto.getPowerDetail());
            model.setMainCablesDetail(dto.getMainCablesDetail());
            model.setCommencementWork(AllUtil.toUIDate(dto.getCommencementWork()));
            model.setCompletionWork(AllUtil.toUIDate(dto.getCompletionWork()));
            bufferDetailHelper.manageBufferDetails(model, dto.getCarBufferDetail());
            bufferDetailHelper.manageBufferDetails(model, dto.getCounterWeightBufferDetail());
        }
        return model;
    }

    public LiftInstallation blankModel(Object portaluser) {
        LiftInstallation ownerDetail = new LiftInstallation();
        PortalUser pUser = (PortalUser) portaluser;
        ownerDetail.getForm().setUser(pUser);

        LoginUser loginUser = LoginUserUtil.loadLoginUser();
        if (loginUser.hasAuthority(UserType.EM_AGENCY.getType())) {
            AgencyDetail agencyDetail = agencyManager.findByUser(pUser);
            ownerDetail.setAgency(agencyDetail);
        }
        return ownerDetail;
    }

    public LiftInstallationDTO fromModel(LiftInstallation model) {
        LiftInstallationDTO dto = new LiftInstallationDTO();
        HelperUtil.toDTO(dto, model);

        AgencyDetail agency = model.getAgency();
        if(agency != null) {
            dto.setAgencyId(agency.getId());
            dto.setAgencyName(agency.getName());
        }
        dto.setLiftApplication(model.getLiftApplication());
        dto.setLiftLicenseNumber(model.getLiftLicenseNumber());
        dto.setLeApplicantName(model.getLeApplicantName());
        dto.setApplicantEmail(model.getApplicantEmail());
        dto.setApplicantMobile(model.getApplicantMobile());
        dto.setIsLocalAgent(HelperUtil.fromBoolean(model.getIsLocalAgent()));
        dto.setLocalAgentName(model.getLocalAgentName());
        dto.setLocalAgentEmail(model.getLocalAgentEmail());
        dto.setLocalAgentContactNo(model.getLocalAgentContactNo());
        dto.setPersonName(model.getPersonName());
        dto.setPersonEmail(model.getPersonEmail());
        dto.setPersonMobile(model.getPersonMobile());
        dto.setLiftType(model.getLiftType());
        dto.setSubCategoryLift(model.getSubCategoryLift());
        dto.setOtherCategoryLift(model.getOtherCategoryLift());
        dto.setMakeOfLift(model.getMakeOfLift());
        dto.setNumberOfLift(model.getNumberOfLift());
        dto.setNumberOfPassengers(model.getNumberOfPassengers());
        dto.setRatedLoad(model.getRatedLoad());
        dto.setRatedSpeed(model.getRatedSpeed());
        dto.setTravelMeters(model.getTravelMeters());
        dto.setTravelTime(model.getTravelTime());
        dto.setBasementFloors(model.getBasementFloors());
        dto.setGroundFloors(model.getGroundFloors());
        dto.setMezzaineFloors(model.getMezzaineFloors());
        dto.setServedFloors(model.getServedFloors());
        dto.setNumberOfEntrances(model.getNumberOfEntrances());
        dto.setClosedFloors(model.getClosedFloors());
        dto.setDummyFloors(model.getDummyFloors());
        dto.setControlMethodForLift(model.getControlMethodForLift());
        dto.setLiftDoorOperationType(model.getLiftDoorOperationType());
        dto.setCarLength(model.getCarLength());
        dto.setCarBreadth(model.getCarBreadth());
        dto.setInternalCarSize(model.getInternalCarSize());
        dto.setPlatformDetails(model.getPlatformDetails());
        dto.setCarFrameDetails(model.getCarFrameDetails());
        dto.setCarDoorDetails(model.getCarDoorDetails());
        dto.setLandingDoorDetails(model.getLandingDoorDetails());
        dto.setLockingArrangementDetails(model.getLockingArrangementDetails());
        dto.setCounterweightPosition(model.getCounterweightPosition());
        dto.setCounterweightApproxKg(model.getCounterweightApproxKg());
        dto.setLiftPitLength(model.getLiftPitLength());
        dto.setLiftPitWidth(model.getLiftPitWidth());
        dto.setLiftPitBreadth(model.getLiftPitBreadth());
        dto.setLiftWellLength(model.getLiftWellLength());
        dto.setLiftWellBreadth(model.getLiftWellBreadth());
        dto.setLiftWellHeight(model.getLiftWellHeight());
        dto.setLiftWellAndBeamDetails(model.getLiftWellAndBeamDetails());
        dto.setHeadRoomDistance(model.getHeadRoomDistance());
        dto.setBottomCarRunBy(model.getBottomCarRunBy());
        dto.setBottomCounterWeightRunBy(model.getBottomCounterWeightRunBy());
        dto.setBottomCounterWeightClearance(model.getBottomCounterWeightClearance());
        dto.setTopCounterWeightClearance(model.getTopCounterWeightClearance());
        dto.setBottomCarClearance(model.getBottomCarClearance());
        dto.setTopCarClearance(model.getTopCarClearance());
        dto.setLiftWellDimLength(model.getLiftWellDimLength());
        dto.setLiftWellDimBreadth(model.getLiftWellDimBreadth());
        dto.setLiftWellDimHeight(model.getLiftWellDimHeight());
        dto.setCarCounterDetail(model.getCarCounterDetail());
        dto.setCarBodyWork(model.getCarBodyWork());
        dto.setFrontDistance(model.getFrontDistance());
        dto.setRearDistance(model.getRearDistance());
        dto.setLeftDistance(model.getLeftDistance());
        dto.setRightDistance(model.getRightDistance());
        dto.setMachineRoomDetails(model.getMachineRoomDetails());
        dto.setMachineRoomLength(model.getMachineRoomLength());
        dto.setMachineRoomBreadth(model.getMachineRoomBreadth());
        dto.setMachineRoomDimHeight(model.getMachineRoomDimHeight());
        dto.setMachineGearDetail(model.getMachineGearDetail());
        dto.setSheavePulleyDetail(model.getSheavePulleyDetail());
        dto.setSheavePulleyDiameter(model.getSheavePulleyDiameter());
        dto.setDiverterPulleyDetail(model.getDiverterPulleyDetail());
        dto.setRopingSize(model.getRopingSize());
        dto.setNoOfRope(model.getNoOfRope());
        dto.setSupportCableDesc(model.getSupportCableDesc());
        dto.setOverSpeedGovernor(model.getOverSpeedGovernor());
        dto.setSafetyGearDetail(model.getSafetyGearDetail());
        dto.setIsRetiringCam(HelperUtil.fromBoolean(model.getIsRetiringCam()));
        dto.setRetiringCamDetail(model.getRetiringCamDetail());
        dto.setConstructionDetail(model.getConstructionDetail());
        dto.setCallIndicatorDetail(model.getCallIndicatorDetail());
        dto.setEmergencyStopSwitchDetail(model.getEmergencyStopSwitchDetail());
        dto.setFloorLevelDetail(model.getFloorLevelDetail());
        dto.setFloorSelectorDetail(model.getFloorSelectorDetail());
        dto.setSlackRopeDetail(model.getSlackRopeDetail());
        dto.setTerminalSlowDetail(model.getTerminalSlowDetail());
        dto.setTerminalStopNormalDetail(model.getTerminalStopNormalDetail());
        dto.setTerminalStopFinalDetail(model.getTerminalStopFinalDetail());
        dto.setIsFiremanSwitch(HelperUtil.fromBoolean(model.getIsFiremanSwitch()));
        dto.setFiremanSwitchDetail(model.getFiremanSwitchDetail());
        dto.setMainSwitchDetails(model.getMainSwitchDetails());
        dto.setWiringMachineDetail(model.getWiringMachineDetail());
        dto.setWiringLiftPitDetail(model.getWiringLiftPitDetail());
        dto.setControlSwitchDetail(model.getControlSwitchDetail());
        dto.setOverCurrentProtection(model.getOverCurrentProtection());
        dto.setAlarmSystemDetail(model.getAlarmSystemDetail());
        dto.setEarthingDetail(model.getEarthingDetail());
        dto.setEmergencySignalDetail(model.getEmergencySignalDetail());
        dto.setPowerDetail(model.getPowerDetail());
        dto.setMainCablesDetail(model.getMainCablesDetail());
        dto.setCommencementWork(AllUtil.formatUIDate(model.getCommencementWork()));
        dto.setCompletionWork(AllUtil.formatUIDate(model.getCompletionWork()));

        dto.setOwnerSubmittedOn(AllUtil.formatUIDate(model.getOwnerSubmittedOn()));
        dto.setCarBufferDetail(bufferDetailHelper.fromModel(model.getBufferDetailFor(BufferType.CAR_BUFFER)));
        dto.setCounterWeightBufferDetail(bufferDetailHelper.fromModel(model.getBufferDetailFor(BufferType.COUNTER_WEIGHT_BUFFER)));

//        dto.setCounterWeightBufferDetail(bufferDetailHelper.fromModel(model.getCounterWeightBufferDetail()));
        dto.setApplicantAddress(addressHelper.fromModel(model.getForm().getAddressFor(AddressType.APPLICANT)));
        dto.setLocalAgentAddress(addressHelper.fromModel(model.getForm().getAddressFor(AddressType.AGENT)));
        dto.setLiftSiteAddress(addressHelper.leAddressDTO(model.getForm().getAddressFor(AddressType.LIFTSITE)));
        dto.setPersonAddress(addressHelper.fromModel(model.getForm().getAddressFor(AddressType.PERSON)));

        dto.setAttachments(attachmentHelper.fromModel(model.getForm().getAttachments()));

        return dto;
    }



    public NGLiftInstallationDTO toNGDTO(LiftInstallation model) {

        NGLiftInstallationDTO dto = new NGLiftInstallationDTO();
        HelperUtil.getNGDTO(model, dto);

        AgencyDetail agency = model.getAgency();
        if(agency != null) {
            dto.setAgencyId(agency.getId());
            dto.setAgencyName(agency.getName());
        }
        dto.setId(model.getId());
        dto.setLiftApplication(model.getLiftApplication());
        dto.setLiftLicenseNumber(model.getLiftLicenseNumber());
        dto.setApplicantName(model.getLeApplicantName());
        dto.setApplicantEmail(model.getApplicantEmail());
        dto.setApplicantMobile(model.getApplicantMobile());
        dto.setIsLocalAgent(HelperUtil.fromBoolean(model.getIsLocalAgent()));
        dto.setLocalAgentName(model.getLocalAgentName());
        dto.setLocalAgentEmail(model.getLocalAgentEmail());
        dto.setLocalAgentContactNo(model.getLocalAgentContactNo());
        dto.setPersonName(model.getPersonName());
        dto.setPersonEmail(model.getPersonEmail());
        dto.setPersonMobile(model.getPersonMobile());
        dto.setLiftType(model.getLiftType());
        dto.setSubCategoryLift(model.getSubCategoryLift());
        dto.setOtherCategoryLift(model.getOtherCategoryLift());
        dto.setMakeOfLift(model.getMakeOfLift());
        dto.setNumberOfLift(model.getNumberOfLift());
        dto.setNumberOfPassengers(model.getNumberOfPassengers());
        dto.setRatedLoad(model.getRatedLoad());
        dto.setRatedSpeed(model.getRatedSpeed());
        dto.setTravelMeters(model.getTravelMeters());
        dto.setTravelTime(model.getTravelTime());
        dto.setBasementFloors(model.getBasementFloors());
        dto.setGroundFloors(model.getGroundFloors());
        dto.setMezzaineFloors(model.getMezzaineFloors());
        dto.setServedFloors(model.getServedFloors());
        dto.setNumberOfEntrances(model.getNumberOfEntrances());
        dto.setClosedFloors(model.getClosedFloors());
        dto.setDummyFloors(model.getDummyFloors());
        dto.setControlMethodForLift(model.getControlMethodForLift());
        dto.setLiftDoorOperationType(model.getLiftDoorOperationType());
        dto.setCarLength(model.getCarLength());
        dto.setCarBreadth(model.getCarBreadth());
        dto.setInternalCarSize(model.getInternalCarSize());
        dto.setPlatformDetails(model.getPlatformDetails());
        dto.setCarFrameDetails(model.getCarFrameDetails());
        dto.setCarDoorDetails(model.getCarDoorDetails());
        dto.setLandingDoorDetails(model.getLandingDoorDetails());
        dto.setLockingArrangementDetails(model.getLockingArrangementDetails());
        dto.setCounterweightPosition(model.getCounterweightPosition());
        dto.setCounterweightApproxKg(model.getCounterweightApproxKg());
        dto.setLiftPitLength(model.getLiftPitLength());
        dto.setLiftPitWidth(model.getLiftPitWidth());
        dto.setLiftPitBreadth(model.getLiftPitBreadth());
        dto.setLiftWellLength(model.getLiftWellLength());
        dto.setLiftWellBreadth(model.getLiftWellBreadth());
        dto.setLiftWellHeight(model.getLiftWellHeight());
        dto.setLiftWellAndBeamDetails(model.getLiftWellAndBeamDetails());
        dto.setHeadRoomDistance(model.getHeadRoomDistance());
        dto.setBottomCarRunBy(model.getBottomCarRunBy());
        dto.setBottomCounterWeightRunBy(model.getBottomCounterWeightRunBy());
        dto.setBottomCounterWeightClearance(model.getBottomCounterWeightClearance());
        dto.setTopCounterWeightClearance(model.getTopCounterWeightClearance());
        dto.setBottomCarClearance(model.getBottomCarClearance());
        dto.setTopCarClearance(model.getTopCarClearance());
        dto.setLiftWellDimLength(model.getLiftWellDimLength());
        dto.setLiftWellDimBreadth(model.getLiftWellDimBreadth());
        dto.setLiftWellDimHeight(model.getLiftWellDimHeight());
        dto.setCarCounterDetail(model.getCarCounterDetail());
        dto.setCarBodyWork(model.getCarBodyWork());
        dto.setFrontDistance(model.getFrontDistance());
        dto.setRearDistance(model.getRearDistance());
        dto.setLeftDistance(model.getLeftDistance());
        dto.setRightDistance(model.getRightDistance());
        dto.setMachineRoomDetails(model.getMachineRoomDetails());
        dto.setMachineRoomLength(model.getMachineRoomLength());
        dto.setMachineRoomBreadth(model.getMachineRoomBreadth());
        dto.setMachineRoomDimHeight(model.getMachineRoomDimHeight());
        dto.setMachineGearDetail(model.getMachineGearDetail());
        dto.setSheavePulleyDetail(model.getSheavePulleyDetail());
        dto.setSheavePulleyDiameter(model.getSheavePulleyDiameter());
        dto.setDiverterPulleyDetail(model.getDiverterPulleyDetail());
        dto.setRopingSize(model.getRopingSize());
        dto.setNoOfRope(model.getNoOfRope());
        dto.setSupportCableDesc(model.getSupportCableDesc());
        dto.setOverSpeedGovernor(model.getOverSpeedGovernor());
        dto.setSafetyGearDetail(model.getSafetyGearDetail());
        dto.setIsRetiringCam(HelperUtil.fromBoolean(model.getIsRetiringCam()));
        dto.setRetiringCamDetail(model.getRetiringCamDetail());
        dto.setConstructionDetail(model.getConstructionDetail());
        dto.setCallIndicatorDetail(model.getCallIndicatorDetail());
        dto.setEmergencyStopSwitchDetail(model.getEmergencyStopSwitchDetail());
        dto.setFloorLevelDetail(model.getFloorLevelDetail());
        dto.setFloorSelectorDetail(model.getFloorSelectorDetail());
        dto.setSlackRopeDetail(model.getSlackRopeDetail());
        dto.setTerminalSlowDetail(model.getTerminalSlowDetail());
        dto.setTerminalStopNormalDetail(model.getTerminalStopNormalDetail());
        dto.setTerminalStopFinalDetail(model.getTerminalStopFinalDetail());
        dto.setIsFiremanSwitch(HelperUtil.fromBoolean(model.getIsFiremanSwitch()));
        dto.setFiremanSwitchDetail(model.getFiremanSwitchDetail());
        dto.setMainSwitchDetails(model.getMainSwitchDetails());
        dto.setWiringMachineDetail(model.getWiringMachineDetail());
        dto.setWiringLiftPitDetail(model.getWiringLiftPitDetail());
        dto.setControlSwitchDetail(model.getControlSwitchDetail());
        dto.setOverCurrentProtection(model.getOverCurrentProtection());
        dto.setAlarmSystemDetail(model.getAlarmSystemDetail());
        dto.setEarthingDetail(model.getEarthingDetail());
        dto.setEmergencySignalDetail(model.getEmergencySignalDetail());
        dto.setPowerDetail(model.getPowerDetail());
        dto.setMainCablesDetail(model.getMainCablesDetail());
        dto.setCommencementWork(AllUtil.formatNGDate(model.getCommencementWork()));
        dto.setCompletionWork(AllUtil.formatNGDate(model.getCompletionWork()));
        dto.setCarBufferDetail(bufferDetailHelper.toNGDTO(model.getBufferDetailFor(BufferType.CAR_BUFFER)));
        dto.setCounterWeightBufferDetail(bufferDetailHelper.toNGDTO(model.getBufferDetailFor(BufferType.COUNTER_WEIGHT_BUFFER)));
        dto.setApplicantAddress(addressHelper.toNGDTO(model.getForm().getAddressFor(AddressType.APPLICANT)));
        dto.setLocalAgentAddress(addressHelper.toNGDTO(model.getForm().getAddressFor(AddressType.AGENT)));
        dto.setLiftSiteAddress(addressHelper.toLINGDTO(model.getForm().getAddressFor(AddressType.LIFTSITE)));
        dto.setPersonAddress(addressHelper.toNGDTO(model.getForm().getAddressFor(AddressType.PERSON)));
        dto.setAttachments(attachmentHelper.fromModelNG(model.getForm().getAttachments()));

        return dto;
    }


}