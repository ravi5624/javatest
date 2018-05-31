package nst.app.model.forms.le;

import lombok.Data;
import lombok.ToString;
import nst.app.enums.ApplicationType;
import nst.app.enums.FileStatus;
import nst.app.model.AgencyDetail;
import nst.app.model.PortalUser;
import nst.app.model.forms.Form;
import nst.common.base.BaseModel;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
@Table(name = "operating_escalator")
@BatchSize(size = 50)
@ToString(callSuper = true)
public class OperatingEscalator extends BaseModel implements Form {

  @JoinColumn(name = "form_id")
  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  CommonForm form = new CommonForm(ApplicationType.OESCL);

  @JoinColumn(name = "agency_id")
  @OneToOne
  AgencyDetail agency;

  //===== Part 1 ============================
  // Applicant details
  @Column(name = "applicant_name")
  private String oeApplicantName;

  @Column(name = "applicant_email")
  private String applicantEmail;

  @Column(name = "applicant_mobile")
  private String applicantMobile;

  // Local agent details
  @Column(name = "is_local_agent_appointed")
  private String isLocalAgentAppointed;

  @Column(name = "local_agent_name")
  private String localAgentName;

  @Column(name = "local_agent_email")
  private String localAgentEmail;

  @Column(name = "local_agent_contact_no")
  private String localAgentContactNo;

  @Column(name = "escalator_site_name")
  private String escalatorSiteName;

  // Escalator maintainer details
  @Column(name = "escalator_town_planning_no")
  private String escalatorTownPlanningNo;

  @Column(name = "escalator_fp_no")
  private String escalatorFpNo;

  @Column(name = "escalator_rs_no")
  private String escalatorRsNo;

  @Column(name = "escalator_sub_plot_no")
  private String escalatorSubPlotNo;

  @Column(name = "escalator_block_tenament_no")
  private String escalatorBlockTenamentNo;

  @Column(name = "escalator_city_survey_no")
  private String escalatorCitySurveyNo;

  @Column(name = "person_name")
  private String personName;

  @Column(name = "person_email")
  private String personEmail;

  @Column(name = "person_mobile")
  private String personMobile;

  @Column(name = "maker_name")
  private String makerName;

  @Column(name = "maker_email")
  private String makerEmail;

  @Column(name = "maker_mobile")
  private String makerMobile;

  @Column(name = "escalator_identification")
  private String escalatorIdentification;
  // Escalator properties
  @Column(name = "from_floor")
  private Integer fromFloor;

  @Column(name = "to_floor")
  private Integer toFloor;

  @Column(name = "owner_submitted_on")
  private Date ownerSubmittedOn;

  //===== Part 1 ============================
// ===== Part 2 ============================

  @Column(name = "escalator_type")
  private String escalatorType;

  // The rated load of the Escalator (W * A)in Kilograms.
  @Column(name = "rated_load")
  private Double ratedLoad;

  // The rated speed of the Escalator (meters/second).
  @Column(name = "rated_speed")
  private Double ratedSpeed;

  @Column(name = "escalator_passenger_capacity")
  private Integer escalatorPassengerCapacity;

  @Column(name = "escalator_angle")
  private Double escalatorAngle;

  @Column(name = "escalator_width")
  private Double escalatorWidth;

  @Column(name = "vertical_rise")
  private Double verticalRise;

  @Column(name = "description")
  private String description;

  @Column(name = "total_head_room")
  private String totalHeadRoom;

  @Column(name = "construction_details")
  private String constructionDetails;

  @Column(name = "approx_reaction")
  private String approxReaction;

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