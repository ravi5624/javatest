package nst.app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import nst.common.base.BaseModelDTO;
import nst.dto.AttachmentDTO;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@JsonInclude(Include.ALWAYS)
public class LiftInstallationDTO extends BaseModelDTO {

  private String liftApplication;

  @NotNull
  private Long agencyId;

  private String agencyName;

  private String liftLicenseNumber;

  @JsonProperty("applicantName")
  private String leApplicantName;

  private AddressDTO applicantAddress;

  private String applicantEmail;

  private String applicantMobile;

  private String isLocalAgent;

  private String localAgentName;

  private AddressDTO localAgentAddress;

  private String localAgentEmail;

  private String localAgentContactNo;

  private LEAddressDTO liftSiteAddress;

  private String personName;

  private AddressDTO personAddress;

  private String personEmail;

  private String personMobile;

  private String liftType;

  private String subCategoryLift;

  private String makeOfLift;

  private String ownerSubmittedOn;

  @Max(100)
  private Integer numberOfLift;

  private Integer numberOfPassengers;

  private Integer ratedLoad;

  @Max(20)
  private Integer ratedSpeed;

  @Max(60)
  private Integer travelMeters;

  private Integer travelTime;

  @Max(5)
  private Integer basementFloors;

  @Max(5)
  private Integer groundFloors;

  @Max(10)
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

  @Min(600)
  private Integer topCounterWeightClearance;

  @Min(600)
  private Integer bottomCarClearance;

  private Integer topCarClearance;

  private Double liftWellDimLength;

  private Double liftWellDimBreadth;

  private Double liftWellDimHeight;

  private String carCounterDetail;

  private String carBodyWork;

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

  @Size(max = 6000)
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

  private String otherCategoryLift;

  @Valid
  @NotNull
  private BufferDetailDTO carBufferDetail;

  @Valid
  @NotNull
  private BufferDetailDTO counterWeightBufferDetail;

      public List<AttachmentDTO> attachments;

  @Override
  public String getApplicantName() {
    return String.format("%s", nst.app.common.HelperUtil.fromString(leApplicantName)).trim();
  }
}