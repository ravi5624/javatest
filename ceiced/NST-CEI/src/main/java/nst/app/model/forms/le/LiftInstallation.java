package nst.app.model.forms.le;

import lombok.Data;
import lombok.ToString;
import nst.app.enums.ApplicationType;
import nst.app.enums.BufferType;
import nst.app.enums.FileStatus;
import nst.app.model.AgencyDetail;
import nst.app.model.PortalUser;
import nst.app.model.forms.Form;
import nst.common.base.BaseAuditableModel;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "lift_installation")
@BatchSize(size = 50)
@ToString(callSuper = true)
public class LiftInstallation extends BaseAuditableModel implements Form {

  @JoinColumn(name = "form_id")
  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  CommonForm form = new CommonForm(ApplicationType.LIL);

  @JoinColumn(name = "agency_id")
  @OneToOne
  AgencyDetail agency;

  @Column(name = "lift_application")
  private String liftApplication;

  @Column(name = "lift_license_number")
  private String liftLicenseNumber;

  @Column(name = "applicant_name")
  private String leApplicantName;

  @Column(name = "applicant_email")
  private String applicantEmail;

  @Column(name = "applicant_contact_no")
  private String applicantMobile;

  @Column(name = "is_local_agent")
  private Boolean isLocalAgent;

  @Column(name = "local_agent_name")
  private String localAgentName;

  @Column(name = "local_agent_email")
  private String localAgentEmail;

  @Column(name = "local_agent_contact_no")
  private String localAgentContactNo;

  @Column(name = "person_name")
  private String personName;

  @Column(name = "person_email")
  private String personEmail;

  @Column(name = "person_mobile")
  private String personMobile;

  @Column(name = "owner_submitted_on")
  private Date ownerSubmittedOn;
//===== Part 1 ============================
// ===== Part 2 ============================

  @Column(name = "lift_type")
  private String liftType;

  @Column(name = "sub_category_lift")
  private String subCategoryLift;

  @Column(name = "make_of_lift")
  private String makeOfLift;

  @Column(name = "number_of_lift")
  private Integer numberOfLift;

  @Column(name = "number_of_passengers")
  private Integer numberOfPassengers;

  @Column(name = "rated_load")
  private Integer ratedLoad;

  @Column(name = "rated_speed")
  private Integer ratedSpeed;

  @Column(name = "travel_meters")
  private Integer travelMeters;

  @Column(name = "travel_time")
  private Integer travelTime;

  @Column(name = "basement_floors")
  private Integer basementFloors;

  @Column(name = "ground_floors")
  private Integer groundFloors;

  @Column(name = "mezzaine_floors")
  private Integer mezzaineFloors;

  @Column(name = "served_floors")
  private Integer servedFloors;

  @Column(name = "number_of_entrances")
  private Integer numberOfEntrances;

  @Column(name = "closed_floors")
  private String closedFloors;

  @Column(name = "dummy_floors")
  private String dummyFloors;

  @Column(name = "control_method_for_lift")
  private String controlMethodForLift;

  @Column(name = "lift_door_operation_type")
  private String liftDoorOperationType;

  @Column(name = "car_length")
  private Double carLength;

  @Column(name = "car_breadth")
  private Double carBreadth;

  @Column(name = "internal_car_size")
  private Double internalCarSize;

  @Column(name = "platform_details")
  private String platformDetails;

  @Column(name = "car_frame_detail")
  private String carFrameDetails;

  @Column(name = "car_door_details")
  private String carDoorDetails;

  @Column(name = "landing_door_details")
  private String landingDoorDetails;

  @Column(name = "locking_arrangement_details")
  private String lockingArrangementDetails;

  @Column(name = "counterweight_position")
  private String counterweightPosition;

  @Column(name = "counter_weight_approx_kg")
  private String counterweightApproxKg;

  @Column(name = "lift_pit_length")
  private Double liftPitLength;

  @Column(name = "lift_pit_width")
  private Double liftPitWidth;

  @Column(name = "lift_pit_breadth")
  private Double liftPitBreadth;

  @Column(name = "lift_well_length")
  private Double liftWellLength;

  @Column(name = "lift_well_breadth")
  private Double liftWellBreadth;

  @Column(name = "lift_well_height")
  private Double liftWellHeight;

  @Column(name = "lift_well_details")
  private String liftWellAndBeamDetails;

  @Column(name = "head_room_distance")
  private Integer headRoomDistance;

  @JoinColumn(name = "form_id", referencedColumnName = "form_id")
  @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
  @BatchSize(size = 50)
  @OrderBy("id")
  List<BufferDetail> bufferDetails;

  @Column(name = "bottom_car_run_by")
  private Integer bottomCarRunBy;

  @Column(name = "bottom_counter_weight_run_by")
  private Integer bottomCounterWeightRunBy;

  @Column(name = "bottom_counter_weight_clearance")
  private Integer bottomCounterWeightClearance;

  @Column(name = "top_counter_weight_clearance")
  private Integer topCounterWeightClearance;

  @Column(name = "bottom_car_clearance")
  private Integer bottomCarClearance;

  @Column(name = "top_car_clearance")
  private Integer topCarClearance;

  @Column(name = "lift_well_dim_length")
  private Double liftWellDimLength;

  @Column(name = "lift_well_dim_breadth")
  private Double liftWellDimBreadth;

  @Column(name = "lift_well_dim_height")
  private Double liftWellDimHeight;

  @Column(name = "car_counter_detail")
  private String carCounterDetail;

  @Column(name = "car_body_work")
  private String carBodyWork;

  @Column(name = "front_distance")
  private String frontDistance;

  @Column(name = "rear_distance")
  private String rearDistance;

  @Column(name = "left_distance")
  private String leftDistance;

  @Column(name = "right_distance")
  private String rightDistance;

  @Column(name = "machine_room_details")
  private String machineRoomDetails;

  @Column(name = "machine_room_length")
  private Double machineRoomLength;

  @Column(name = "machine_room_breadth")
  private Double machineRoomBreadth;

  @Column(name = "machine_room_dim_height")
  private Double machineRoomDimHeight;

  @Column(name = "machine_gear_detail")
  private String machineGearDetail;

  @Column(name = "sheave_pulley_detail")
  private String sheavePulleyDetail;

  @Column(name = "sheave_pulley_diameter")
  private Integer sheavePulleyDiameter;

  @Column(name = "diverter_pulley_detail")
  private String diverterPulleyDetail;

  @Column(name = "roping_size")
  private Integer ropingSize;

  @Column(name = "no_of_rope")
  private Integer noOfRope;

  @Column(name = "support_cable_desc")
  private String supportCableDesc;

  @Column(name = "over_speed_governor")
  private String overSpeedGovernor;

  @Column(name = "safety_gear_detail")
  private String safetyGearDetail;

  @Column(name = "is_retiring_cam")
  private Boolean isRetiringCam;

  @Column(name = "retiring_cam_detail")
  private String retiringCamDetail;

  @Column(name = "construction_detail")
  private String constructionDetail;

  @Column(name = "other_category_lift")
  private String otherCategoryLift;
  // ===== Part 2 ============================
  // ===== Part 3 ============================

  @Column(name = "call_indicator_detail")
  private String callIndicatorDetail;

  @Column(name = "emergency_stop_switch_detail")
  private String emergencyStopSwitchDetail;

  @Column(name = "floor_leveling_detail")
  private String floorLevelDetail;

  @Column(name = "floor_selector_detail")
  private String floorSelectorDetail;

  @Column(name = "slack_rope_detail")
  private String slackRopeDetail;

  @Column(name = "terminal_slow_detail")
  private String terminalSlowDetail;

  @Column(name = "terminal_stop_normal_detail")
  private String terminalStopNormalDetail;

  @Column(name = "terminal_stop_final_detail")
  private String terminalStopFinalDetail;

  @Column(name = "is_fireman_switch")
  private Boolean isFiremanSwitch;

  @Column(name = "fireman_switch_detail")
  private String firemanSwitchDetail;

  @Column(name = "main_switch_details")
  private String mainSwitchDetails;

  @Column(name = "wiring_machine_detail")
  private String wiringMachineDetail;

  @Column(name = "wiring_lift_pit_detail")
  private String wiringLiftPitDetail;

  @Column(name = "control_switch_detail")
  private String controlSwitchDetail;

  @Column(name = "over_current_protection")
  private String overCurrentProtection;

  @Column(name = "alarm_system_detail")
  private String alarmSystemDetail;

  @Column(name = "earthing_detail")
  private String earthingDetail;

  @Column(name = "emergencyS_signal_detail")
  private String emergencySignalDetail;

  @Column(name = "power_detail")
  private String powerDetail;

  @Column(name = "main_cables_detail")
  private String mainCablesDetail;

  @Column(name = "commencement_work")
  private Date commencementWork;

  @Column(name = "completion_work")
  private Date completionWork;

// ===== Part 3 ============================
/*
    public BufferDetail bufferDetail(Long id) {
        if (id != null) {
            Optional<BufferDetail> first = getBufferDetail().stream().filter(e -> id.equals(e.getId()))
                    .findFirst();
            if (first.isPresent()) {
                return first.get();
            }
        }
        BufferDetail bufferDetail = new BufferDetail(form);
        getBufferDetail().add(bufferDetail);
        return bufferDetail;
    }*/

  @Override
  public ApplicationType getApplicationType() {
    return getForm().getApplicationType();
  }

  @Override
  public PortalUser getOwner() {
    return getForm().getUser();
  }

  @Transient
  public Long getApplicationId() {
    return getForm().getId();
  }

  @Override
  public FileStatus getFileStatus() {
    return getForm().getFileStatus();
  }

  @Override
  public String getUniqueId() {
    return getForm().getUniqueId();
  }

  @Override
  public PortalUser getAssignedTo(){
    return Objects.isNull(getAgency()) ? null : getAgency().getUser();
  }

  public BufferDetail getBufferDetailFor(BufferType type) {
    if (getBufferDetails() == null) {
      return null;
    }
    return getBufferDetails().stream().filter(a -> a.isFor(type)).findFirst().orElse(null);
  }

  public void addBufferDetails(BufferDetail bufferDetail) {
    if (bufferDetails == null) {
      bufferDetails = new ArrayList<>();
    }
    bufferDetails.add(bufferDetail);
  }
}