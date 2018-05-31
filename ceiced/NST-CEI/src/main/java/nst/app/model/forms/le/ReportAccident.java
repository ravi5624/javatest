package nst.app.model.forms.le;

import lombok.Data;
import nst.app.enums.ApplicationType;
import nst.app.enums.FileStatus;
import nst.app.model.PortalUser;
import nst.app.model.forms.Experience;
import nst.app.model.forms.Form;
import nst.common.base.BaseAuditableModel;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Data
@Entity
@Table(name = "report_accident")
@BatchSize(size = 50)
public class ReportAccident extends BaseAuditableModel implements Form {

  @JoinColumn(name = "form_id")
  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  CommonForm form = new CommonForm(ApplicationType.ACCIDENT);

  @Column(name = "accident_date")
  Date accidentDate;

  @Column(name = "accident_time")
  String accidentTime;

  @Column(name = "owner_name")
  String ownerName;


  @JoinColumn(name = "form_id", referencedColumnName = "form_id")
  @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
  @BatchSize(size = 50)
  @OrderBy("id")
  List<AccidentVictim> victims = new ArrayList<>();

  @Column(name = "accident_type")
  String accidentType;


  @Column(name = "accident_for")
  String accidentFor;

  @Column(name = "postmortem_performed")
  Boolean isPostmortemperformed;


  @Column(name = "is_person_authorized")
  Boolean isPersonAuthorized;

  @Column(name = "person_designation")
  String personDesgination;

  @Column(name = "job_description")
  String jobDescription;

  @Column(name = "is_person_allowed")
  Boolean isPersonAllowed;

  @Column(name = "licence_no")
  String licenceNo;

  @Column(name = "agency_name")
  String agencyName;

  @Column(name = "maintainer_contact_no")
  String maintainerContactNo;

  @Column(name = "maintainer_email")
  String maintainerEmail;

  @Column(name = "maintainer_website")
  String maintainerWebsite;

  @Column(name = "injuries_description")
  String injuriesDescription;

  @Column(name = "detail_accident_causes", columnDefinition = "TEXT")
  String detailedAccidentCauses;

  @Column(name = "taken_action", columnDefinition = "TEXT")
  String takenAction;

  @Column(name = "is_police_concerned")
  Boolean isPoliceConcerned;


  @Column(name = "police_concerned_details")
  String policeConcernedDetails;

  @Column(name = "accident_evidence"  , columnDefinition = "TEXT")
  String accidentEvidene;

  @Column(name = "assisting_description")
  String assistingDescription;

  @Column(name = "witness_description")
  String witnessDescription;

  @Column(name = "remarks", columnDefinition = "TEXT")
  String remarks;

  @Column(name = "is_authorized")
  Boolean isAuthorized;


  public AccidentVictim myReport(Long expId) {
    if (expId != null) {
      Optional<AccidentVictim> first = getVictims().stream().filter(e -> expId.equals(e.getId()))
              .findFirst();
      if (first.isPresent()) {
        return first.get();
      }
    }
    AccidentVictim accidentVictim = new AccidentVictim(form);
    getVictims().add(accidentVictim);
    return accidentVictim;
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