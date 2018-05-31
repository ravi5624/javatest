package nst.app.model.forms.le;

import lombok.Data;
import lombok.ToString;
import nst.app.enums.ApplicationType;
import nst.app.enums.FileStatus;
import nst.app.model.AgencyDetail;
import nst.app.model.PortalUser;
import nst.app.model.forms.Form;
import nst.common.base.BaseAuditableModel;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
@Table(name = "operating_lift")
@BatchSize(size = 50)
@ToString(callSuper = true)
public class OperatingLift extends BaseAuditableModel implements Form {

  @JoinColumn(name = "form_id")
  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  CommonForm form = new CommonForm(ApplicationType.OLIFT);

  @JoinColumn(name = "agency_id")
  @OneToOne
  AgencyDetail agency;

  //===== Part 1 ===========================

  @Column(name="applicant_name")
  private String applicantName;

  @Column(name="applicant_email")
  private String applicantEmail;

  @Column(name="applicant_contact_no")
  private String applicantMobile;

  @Column(name = "local_agent_appointed")
  private Boolean localAgentAppointed;

  @Column(name = "local_agent_name")
  private String localAgentName;

  @Column(name = "local_agent_email")
  private String localAgentEmail;

  @Column(name = "local_agent_contact_no")
  private String localAgentContactNo;

  @Column(name = "lift_site_name")
  private String liftSiteName;

  /*@Column(name = "lift_town_planning_no")
  private String liftTownPlanningNo;


  @Column(name = "lift_fp_no")
  private String liftFpNo;

  @Column(name = "lift_rs_no")
  private String liftRsNo;

  @Column(name = "lift_sub_plot_no")
  private String liftSubPlotNo;

  @Column(name = "lift_block_tenament_no")
  private String liftBlockTenamentNo;

  @Column(name = "lift_ciry_survey_no")
  private String liftCitySurveyNo;*/

  @Column(name = "person_name")
  private String personName;

  @Column(name = "person_email")
  private String personEmail;

  @Column(name = "person_mobile")
  private String personMobile;

  @Column(name = "owner_submitted_on")
  private Date ownerSubmittedOn;

    //===== Part 1 ===========================
// ===== Part 2 ============================

  @Column(name="lift_type")
  private String liftType;

  @Column(name="lift_sub_category")
  private String liftSubCategory;

  @Column(name = "other_category_lift")
  private String otherCategoryLift;

  @Column(name="make_lift")
  private String makeLift;

  @Column (name="lift_passenger_capacity")
  private Integer liftPassengerCapacity;

  @Column(name = "rated_load")
  private Double ratedLoad;

  @Column(name = "rated_speed")
  private Double ratedSpeed;

  @Column(name = "total_lift_weight")
  private String totalLiftWeight;

  @Column(name = "total_counter_weight")
  private String totalCounterWeight;

  @Column(name = "suspension_rope_size")
  private String suspensionRopeSize;

  @Column(name = "pit_depth")
  private String pitDepth;

  @Column(name = "travel_meters")
  private Double travelMeters;

  @Column(name = "travel_time")
  private Double travelTime;

  @Column(name = "basement_floors")
  private Integer basementFloors;

  @Column(name = "ground_floors")
  private Integer groundFloors;

  @Column(name = "mezzanine_floors")
  private Integer mezzaineFloors;

  @Column(name = "served_floors")
  private Integer servedFloors;

  @Column(name = "entrances")
  private Integer entrances;

  @Column(name = "floor_closed")
  private String floorClosed;

  @Column(name = "dummy_floors")
  private Integer dummyFloors;

  @Column(name = "head_room_details")
  private String headRoomDetail;

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
}