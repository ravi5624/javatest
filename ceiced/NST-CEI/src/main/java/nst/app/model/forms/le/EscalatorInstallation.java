package nst.app.model.forms.le;

import lombok.Data;
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
@Table(name = "escalator_installation")
@BatchSize(size = 50)
public class EscalatorInstallation extends BaseAuditableModel implements Form {

  @JoinColumn(name = "form_id")
  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  CommonForm form = new CommonForm(ApplicationType.EIL);

  @JoinColumn(name = "agency_id")
  @OneToOne
  AgencyDetail agency;

  //===== Part 1 ============================
  @Column(name = "escalator_license_number")
  private String escalatorLicenseNumber;

  @Column(name = "application_for")
  private String applicationFor;


  @Column(name = "applicant_name")
  String eiApplicantName;

  @Column(name = "applicant_email")
  String applicantEmail;

  @Column(name = "applicant_contact_no")
  String applicantMobile;

  @Column(name = "local_agent_name")
  String localAgentName;

  @Column(name = "is_local_agent_appointed")
  String isLocalAgentAppointed;

  @Column(name = "local_agent_email")
  String localAgentEmail;

  @Column(name = "local_agent_contact_no")
  String localAgentContactNo;

  @Column(name = "escalator_site_name")
  String escalatorSiteName;

  @Column(name = "installer_person_name")
  String personName;

    @Column(name = "installer_person_email")
  String personEmail;

  @Column(name = "installer_person_mobile")
  String personMobile;

  @Column (name="maker_name")
  private String makerName;

  @Column (name="maker_email")
  private String makerEmail;

  @Column (name="maker_mobile")
  private String makerMobile;

  @Column (name="escalator_identification")
  private String escalatorIdentification;

  @Column (name="from_floor")
  private Integer fromFloor;

  @Column (name="to_floor")
  private Integer toFloor;

  @Column(name = "owner_submitted_on")
  private Date ownerSubmittedOn;

  //===== Part 1 ============================
// ===== Part 2 ============================

  @Column (name="rated_speed")
  private Double ratedSpeed;

  @Column(name = "balusmades_width")
  private Double balusmadesWidth;


  @Column(name = "horizontal_distance")
  private Double horizontalDistance;

  @Column(name = "rated_load")
  private Double ratedLoad;

  @Column(name = "maximum_persons")
  private Integer maximumPersons;

  @Column(name = "escalator_person_capacity")
  private Integer escalatorPersonCapacity;

  @Column(name = "escalator_angle")
  private Double escalatorAngle;

  @Column(name = "escalator_width")
  private Double escalatorWidth;

  @Column(name = "vertical_rise")
  private Double verticalRise;

  @Column(name = "description" , columnDefinition = "Text")
  private String description;

  @Column(name = "construction_details" , columnDefinition = "Text")
  private String constructionDetails;

  @Column(name = "commencement_work")
  private Date commencementWork;

  @Column(name = "completion_work")
  private Date completionWork;

  @Override
  public PortalUser getAssignedTo(){
    return Objects.isNull(getAgency()) ? null : getAgency().getUser();
  }

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
}