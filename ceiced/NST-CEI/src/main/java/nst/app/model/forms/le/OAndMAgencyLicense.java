package nst.app.model.forms.le;

import lombok.Data;
import nst.app.enums.ApplicationType;
import nst.app.enums.FileStatus;
import nst.app.enums.LiftEscalatorType;
import nst.app.model.PortalUser;
import nst.app.model.forms.Form;
import nst.common.base.BaseModel;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Data
@Entity
@Table(name = "om_agency_license")
@BatchSize(size = 50)
public class OAndMAgencyLicense extends BaseModel implements Form {
  @JoinColumn(name = "form_id")
  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  CommonForm form = new CommonForm(ApplicationType.OMAL);

  /* Part 1 start*/
  @Column(name = "type")
  @Enumerated(EnumType.STRING)
  LiftEscalatorType liftEscalatorType;

  @Column(name = "agency_name")
  private String agencyName;

  @Column(name = "agency_legal_status")
  private String agencyLegalStatus;

  @Column(name = "establishment_year")
  private Integer establishmentYear;

  @JoinColumn(name = "form_id", referencedColumnName = "form_id")
  @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
  @BatchSize(size = 50)
  List<AgencyLegalStatusDetails> agencyLegalStatusDetails = new ArrayList<>();

  /*@Column(name="business_email")
  private String businessEmail;

  @Column(name="business_contact_no")
  private String businessContactNo;

  @Column(name="business_website")
  private String businessWebsite;

  @Column(name="branch_email")
  private String branchEmail;

  @Column(name="branch_contact_no")
  private String branchContactNo;

  @Column(name="branch_website")
  private String branchWebsite;*/

  @Column(name="contact_email")
  private String contactEmail;

  @Column(name="contact_no")
  private String contactNo;

  @Column(name="is_authorization_certificate_issued")
  private String isAuthorizationCertificateIssued;

  @Column(name="authorization_no")
  String authorizationNo;

  @Column(name="authorization_certificate_date")
  Date authorizationCertificateDate;

  @Column(name="registration_number_electrical_contractor")
  String registrationNumberElectricalContractor;

  @Column(name="bank_name")
  String bankName;

  @Column(name="branch_name")
  String branchName;

  @Column(name="issue_date")
  Date issueDate;

  @Column(name="amount")
  Double amount;

  @Column(name="bank_address")
  String bankAddress;

  @JoinColumn(name = "form_id", referencedColumnName = "form_id")
  @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
  @BatchSize(size = 50)
  @OrderBy("id")
  private List<StaffEmployee> staffEmployees = new ArrayList<>();

  /* Part 1 end*/

  /* Part 2 start*/

  @JoinColumn(name = "form_id", referencedColumnName = "form_id")
  @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
  @BatchSize(size = 50)
  private List<LETestingInstrument> LETestingInstruments;


  @JoinColumn(name = "form_id", referencedColumnName = "form_id")
  @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
  @BatchSize(size = 50)
  private List<LESafetyGadget> LESafetyGadgets;

  @Column(name="vehicle_possession")
  private String vehiclePossession;

  @Column(name="remarks")
  private String remarks;

  /* Part 2 end*/
  public StaffEmployee myStaff(Long expId) {
    if (expId != null) {
      Optional<StaffEmployee> first = getStaffEmployees().stream()
              .filter(e -> expId.equals(e.getId()))
              .findFirst();
      if (first.isPresent()) {
        return first.get();
      }
    }
    StaffEmployee staffEmployee = new StaffEmployee(form);
    getStaffEmployees().add(staffEmployee);
    return staffEmployee;
  }


  public LETestingInstrument getTestingInstruments(Long testId) {
    if (testId != null) {
      Optional<LETestingInstrument> first = getLETestingInstruments().stream().filter(t -> testId.equals(t.getId()))
              .findFirst();
      if (first.isPresent()) {
        return first.get();
      }
    }
    LETestingInstrument LETestingInstrument = new LETestingInstrument(form);
    getLETestingInstruments().add(LETestingInstrument);
    return LETestingInstrument;
  }


  public LESafetyGadget getSafetyGadgets(Long gadgetId) {
    if (gadgetId != null) {
      Optional<LESafetyGadget> first = getLESafetyGadgets().stream().filter(g -> gadgetId.equals(g.getId()))
              .findFirst();
      if (first.isPresent()) {
        return first.get();
      }
    }
    LESafetyGadget LESafetyGadget = new LESafetyGadget(form);
    getLESafetyGadgets().add(LESafetyGadget);
    return LESafetyGadget;
  }



  public AgencyLegalStatusDetails getAgencyLegalStatusDetails(Long statusId) {
    if (statusId != null) {
      Optional<AgencyLegalStatusDetails> first = getAgencyLegalStatusDetails().stream().filter(t -> statusId.equals(t.getId()))
              .findFirst();
      if (first.isPresent()) {
        return first.get();
      }
    }
    AgencyLegalStatusDetails agencyLegalStatusDetails = new AgencyLegalStatusDetails(form);
    getAgencyLegalStatusDetails().add(agencyLegalStatusDetails);
    return agencyLegalStatusDetails;
  }




  @Override
  public ApplicationType getApplicationType() {
    return getForm().getApplicationType();
  }

  @Override
  public PortalUser getOwner() {
    return getForm().getUser();
  }

  @Override
  public FileStatus getFileStatus() {
    return getForm().getFileStatus();
  }

  @Transient
  public Long getApplicationId() {
    return getForm().getId();
  }

  @Override
  public String getUniqueId() {
    return getForm().getUniqueId();
  }


}