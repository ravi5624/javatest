package nst.app.model.forms.le;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import nst.app.CEIUtil;
import nst.app.enums.*;
import nst.app.model.PortalUser;
import nst.app.model.forms.Address;
import nst.app.model.forms.OrganizationDetails;
import nst.common.base.BaseAuditableModel;
import nst.common.security.LoginUser;
import nst.util.AllUtil;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Where;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "form_detail")
@ToString(callSuper = true, exclude = {"transaction"})
@EqualsAndHashCode(callSuper = true, exclude = {"transaction"})
public class CommonForm extends BaseAuditableModel {

  public CommonForm() {
  }

  public CommonForm(ApplicationType applicationType) {
    this.applicationType = applicationType;
    this.uniqueId = AllUtil.getAppNo(applicationType.getType());
    this.fileNumber = CEIUtil.getFileNo(applicationType);
  }

  @JoinColumn(name = "user_id")
  @OneToOne(fetch = FetchType.EAGER)
  PortalUser user;

  @Column(name = "unique_id")
  String uniqueId = AllUtil.getAppNo();

  @Column(name = "applicant_name")
  String applicantName;

  @Column(name = "file_number")
  String fileNumber;

  @Column(name = "outward_no")
  String outwardNo;

  @Column(name = "pid")
  String pid;

  @Column(name = "remarks")
  String remarks;

  @Column(name = "license_number")
  String licenseNumber;

  @Column(name = "issue_date", columnDefinition = "DATE")
  Date issueDate;

  @Column(name = "expiry_date", columnDefinition = "DATE")
  Date expiryDate;

  @Column(name = "application_type")
  @Enumerated(value = EnumType.STRING)
  ApplicationType applicationType;

  @Column(name = "file_status")
  @Enumerated(value = EnumType.STRING)
  FileStatus fileStatus = FileStatus.DRAFT;

  @OneToMany(mappedBy = "form", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
  @BatchSize(size = 50)
  List<ApplicationQuery> queries;

  @OneToMany(mappedBy = "form", fetch = FetchType.LAZY, orphanRemoval = true)
  @Where(clause = "attachment_type = 'APPLICATION'")
  @BatchSize(size = 50)
  List<ApplicationAttachment> attachments;

  @OneToMany(mappedBy = "form", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
  @BatchSize(size = 50)
  List<Address> addresses;

  @OneToMany(mappedBy = "form", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
  @BatchSize(size = 50)
  List<OrganizationDetails> organizationDetail;

  @OneToOne(mappedBy = "form", orphanRemoval = true, cascade = CascadeType.ALL)
  @Fetch(FetchMode.JOIN)
  Transaction transaction;

  @Column(name = "submitted_on")
  Date submittedOn;

    //TODO [SAGAR]: need to update TYPO mistake
    @Column(name = "proved_on")
    Date approvedOn;

  @Column(name = "i_agree")
  Boolean iAgree = Boolean.FALSE;

  @Column(name = "other_comments" , columnDefinition = "TEXT")
  String otherComments;

  public PaymentStatus getTransactionStatus() {
    return transaction != null ? transaction.getStatus() : null;
  }

  public void addQuery(ApplicationQuery query) {
    if (queries == null) {
      queries = new ArrayList<>();
    }
    queries.add(query);
  }

  public void addAttachments(ApplicationAttachment attachment) {
    if (attachments == null) {
      attachments = new ArrayList<>();
    }
    attachments.add(attachment);
  }

  public ApplicationAttachment getAttachmentFor(String fieldIdentifier) {
    if (attachments == null || StringUtils.isEmpty(fieldIdentifier)) {
      return null;
    }
    return getAttachments().stream().filter(a -> a.isForField(fieldIdentifier)).findFirst()
            .orElse(null);
  }

  public ApplicationAttachment getAttachmentFor(String fieldIdentifier, String formPart) {
    if (attachments == null || StringUtils.isEmpty(fieldIdentifier) || StringUtils
            .isEmpty(formPart)) {
      return null;
    }
    return getAttachments().stream()
            .filter(a -> a.isForField(fieldIdentifier, formPart))
            .findFirst()
            .orElse(null);
  }

  public void addAddress(Address address) {
    if (addresses == null) {
      addresses = new ArrayList<>();
    }
    addresses.add(address);
  }

  public Address getAddressFor(AddressType type) {
    if (getAddresses() == null) {
      return null;
    }
    return getAddresses().stream().filter(a -> a.isFor(type)).findFirst().orElse(null);
  }
  public List<Address> getAddressesFor(AddressType type) {
    if (getAddresses() == null) {
      return null;
    }
    List<Address> list= new ArrayList<>();
    list.addAll(getAddresses().stream().filter(a -> a.isFor(type)).collect(Collectors.toList()));
    list.sort(Comparator.comparingLong(l->l.getId()));
    return list;
  }


  public void removeAddressFor(AddressType type) {
    if (getAddresses() == null) {
      return;
    }
    getAddresses().removeIf(a -> a.isFor(type));
  }

  public Address getAddressFor(AddressType type, Long id) {
    if (getAddresses() == null) {
      return null;
    }
    Address address = getAddresses().stream().filter(a -> a.isFor(type, id)).findFirst().orElse(null);
    return address;
  }

  public void removeAddressFor(AddressType type, Long id) {
    if (getAddresses() == null || id == null) {
      return;
    }
    getAddresses().removeIf(a -> a.isFor(type, id));
  }

  public void addOrganizationDetails(OrganizationDetails organizationDetails) {
    if (organizationDetail == null) {
      organizationDetail = new ArrayList<>();
    }
    organizationDetail.add(organizationDetails);
  }


  public OrganizationDetails getOrganizationDetailsFor(OrganizationDetails type) {
    if (getOrganizationDetail() == null) {
      return null;
    }
    return null;
    //getOrganizationDetail().stream().filter(a -> a.isFor(type)).findFirst().orElse(null);
  }


  public void setFileStatus(FileStatus fileStatus) {
    if (FileStatus.SUBMITTED == fileStatus) {
      submittedOn = new Date();
      /*ToDo: Once confirmed by DEPT, implement this*/
      //fileNumber = CEIUtil.getFileNo(applicationType);
    }
    if (FileStatus.APPROVED == fileStatus) {
      approvedOn = new Date();
    }

    this.fileStatus = fileStatus;
  }

  public boolean canSubmit(LoginUser loginUser) {
    ApplicationType appType = getApplicationType();
    if ((appType == ApplicationType.LIL || appType == ApplicationType.EIL || appType == ApplicationType.OESCL || appType == ApplicationType.OLIFT)) {
        return loginUser.hasAuthority(UserType.EM_AGENCY.getType()) && FileStatus.OWNER_SUBMITTED == getFileStatus() ||
                loginUser.hasAuthority(UserType.OWNER.getType()) && FileStatus.DRAFT == getFileStatus();
    }
    return FileStatus.DRAFT == getFileStatus();
  }
}