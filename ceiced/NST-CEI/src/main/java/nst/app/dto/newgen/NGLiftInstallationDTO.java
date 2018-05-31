package nst.app.dto.newgen;

/**
 * Created by bhargav on 12/1/18.
 */

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class NGLiftInstallationDTO extends NGBaseDTO{

    private Long agencyId;
    private String agencyName;
    private String liftApplication;
    private String liftLicenseNumber;
    private String applicantName;
    private NGAddressDTO applicantAddress;
    private String applicantEmail;
    private String applicantMobile;
    private String isLocalAgent;
    private String localAgentName;
    private NGAddressDTO localAgentAddress;
    private String localAgentEmail;
    private String localAgentContactNo;
    private NGLIAddressDTO liftSiteAddress;
    private String personName;
    private NGAddressDTO personAddress;
    private String personEmail;
    private String personMobile;
    private String liftType;
    private String subCategoryLift;
    private String otherCategoryLift;
    private String makeOfLift;
    private Integer numberOfLift;
    private Integer numberOfPassengers;
    private Integer ratedLoad;
    private Integer ratedSpeed;
    private Integer travelMeters;
    private Integer travelTime;
    private Integer basementFloors;
    private Integer groundFloors;
    private Integer mezzaineFloors;
    private Integer servedFloors;
    private Integer numberOfEntrances;
    private String closedFloors;
    private String dummyFloors;
    private String controlMethodForLift;
    private String liftDoorOperationType;
    private Double carLength;
    private Double carBreadth;
    private Double internalCarSize;
    private String platformDetails;
    private String carFrameDetails;
    private String carDoorDetails;
    private String landingDoorDetails;
    private String lockingArrangementDetails;
    private String counterweightPosition;
    private String counterweightApproxKg;
    private Double liftPitLength;
    private Double liftPitWidth;
    private Double liftPitBreadth;
    private Double liftWellLength;
    private Double liftWellBreadth;
    private Double liftWellHeight;
    private String liftWellAndBeamDetails;
    private Integer headRoomDistance;
    private Integer bottomCarRunBy;
    private Integer bottomCounterWeightRunBy;
    private Integer bottomCounterWeightClearance;
    private Integer topCounterWeightClearance;
    private Integer bottomCarClearance;
    private Integer topCarClearance;
    private Double liftWellDimLength;
    private Double liftWellDimBreadth;
    private Double liftWellDimHeight;
    private String carCounterDetail;
    private String carBodyWork;
   @Valid
   @NotNull
   private NGBufferDetailDTO carBufferDetail ;

   @Valid
   @NotNull
   private NGBufferDetailDTO counterWeightBufferDetail;


    private String frontDistance;
    private String rearDistance;
    private String leftDistance;
    private String rightDistance;
    private String machineRoomDetails;
    private Double machineRoomLength;
    private Double machineRoomBreadth;
    private Double machineRoomDimHeight;
    private String machineGearDetail;
    private String sheavePulleyDetail;
    private Integer sheavePulleyDiameter;
    private String diverterPulleyDetail;
    private Integer ropingSize;
    private Integer noOfRope;
    private String supportCableDesc;
    private String overSpeedGovernor;
    private String safetyGearDetail;
    private String isRetiringCam;
    private String retiringCamDetail;
    private String constructionDetail;
    private String callIndicatorDetail;
    private String emergencyStopSwitchDetail;
    private String floorLevelDetail;
    private String floorSelectorDetail;
    private String slackRopeDetail;
    private String terminalSlowDetail;
    private String terminalStopNormalDetail;
    private String terminalStopFinalDetail;
    private String isFiremanSwitch;
    private String firemanSwitchDetail;
    private String mainSwitchDetails;
    private String wiringMachineDetail;
    private String wiringLiftPitDetail;
    private String controlSwitchDetail;
    private String overCurrentProtection;
    private String alarmSystemDetail;
    private String earthingDetail;
    private String emergencySignalDetail;
    private String powerDetail;
    private String mainCablesDetail;
    private String commencementWork;
    private String completionWork;

    public List<NGAttachmentDTO> attachments;
}