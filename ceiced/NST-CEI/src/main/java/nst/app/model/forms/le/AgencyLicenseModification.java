package nst.app.model.forms.le;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Data;
import nst.app.enums.ApplicationType;
import nst.app.enums.FileStatus;
import nst.app.model.PortalUser;
import nst.app.model.forms.Form;
import nst.common.base.BaseAuditableModel;
import org.hibernate.annotations.BatchSize;

/**
 * Modification_Request_Form.xlsx
 */
@Data
@Entity
@Table(name = "agency_license_modification")
@BatchSize(size = 50)
public class AgencyLicenseModification extends BaseAuditableModel implements Form {

  @JoinColumn(name = "form_id")
  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  CommonForm form = new CommonForm(ApplicationType.ALMODI);

  @JoinColumn(name = "form_id", referencedColumnName = "form_id")
  @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
  @BatchSize(size = 50)
  @OrderBy("id")
  private List<StaffEmployee> staffEmployees = new ArrayList<>();

  @Column(name = "branch_email")
  String branchEmail;

  @Column(name = "branch_contact_no")
  String branchContactNo;

  @Column(name = "main_office_contact_no")
  String mainOfficeContactNo;

  @Column(name = "main_office_email")
  String mainOfficeEmail;

  @JoinColumn(name = "form_id", referencedColumnName = "form_id")
  @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
  @BatchSize(size = 50)
  @OrderBy("id")
  private List<LETestingInstrument> testingInstruments = new ArrayList<>();

  @JoinColumn(name = "form_id", referencedColumnName = "form_id")
  @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
  @BatchSize(size = 50)
  @OrderBy("id")
  private List<LESafetyGadget> safetyGadgets = new ArrayList<>();

  @Column(name = "communication_facility_change_request")
  private String communicationFacilityChangeRequest;

  //dropdown
  @Column(name = "vehicle_possession_detail")
  private String vehiclePossessionDetail;

  //dropdown
  @Column(name = "legal_status_change_request")
  private String agencyLegalStatus;


  @Column(name = "is_change_branch_office")
  private Boolean isChangeBranchOffice;

  @Column(name = "is_change_communication_details")
  private Boolean isChangeCommunicationDetails;

  @Column(name = "is_change_legal_status")
  private Boolean isChangeLegalStatus;

  @Column(name = "is_change_main_office")
  private Boolean isChangeMainOffice;

  @Column(name = "is_change_saftyguards")
  private Boolean isChangeSaftyguards;

  @Column(name = "is_change_vehicle_details")
  private Boolean isChangeVehicleDetails;

  @Column(name = "is_change_workshopoffice")
  private Boolean isChangeWorkshopOffice;

  @Column(name = "is_change_testinstrument")
  private Boolean isChangetestInstrument;

  @Column(name = "is_staff_details_change")
  private Boolean isStaffDetailsChange;

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

  public LESafetyGadget mySaftyGadget(Long expId) {
    if (expId != null) {
      Optional<LESafetyGadget> first = getSafetyGadgets().stream()
          .filter(e -> expId.equals(e.getId()))
          .findFirst();
      if (first.isPresent()) {
        return first.get();
      }
    }
    LESafetyGadget safetyGadget = new LESafetyGadget(form);
    getSafetyGadgets().add(safetyGadget);
    return safetyGadget;
  }

  public LETestingInstrument myTestInstru(Long expId) {
    if (expId != null) {
      Optional<LETestingInstrument> first = getTestingInstruments().stream()
          .filter(e -> expId.equals(e.getId()))
          .findFirst();
      if (first.isPresent()) {
        return first.get();
      }
    }
    LETestingInstrument testingInstrument = new LETestingInstrument(form);
    getTestingInstruments().add(testingInstrument);
    return testingInstrument;
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