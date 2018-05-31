package nst.app.model.forms;

import lombok.Data;
import lombok.ToString;
import nst.app.enums.AddressType;
import nst.app.model.forms.le.CommonForm;
import nst.common.base.BaseAuditableModel;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;

@Data
@Entity
@Table(name = "form_address")
@BatchSize(size = 50)
@ToString(exclude = "form", callSuper = true)
public class Address extends BaseAuditableModel {

  public Address() {
  }

  public Address(CommonForm form) {
    this.form = form;
  }

  @JoinColumn(name = "form_id")
  @OneToOne
  CommonForm form;

  @Column(name = "type")
  @Enumerated(EnumType.STRING)
  AddressType addressType;

  @Column(name = "premises_name")
  String premisesName;

  /*It is BlockNo, HouseNo etc...*/
  @Column(name = "house_no")
  String houseNo;

  @Column(name = "user_key")
  Long userKey;

  @Column(name = "tenament_no")
  String tenamentNo;

  @Column(name = "building")
  String building;

  @Column(name = "taluka")
  String taluka;

  @Column(name = "district")
  String district;

  @Column(name = "state")
  String state;

  @Column(name = "pincode")
  String pincode;

  @Column(name = "addrLine1")
  String addrLine1;

  @Column(name = "addrLine2")
  String addrLine2;

  @Column(name = "address_line3")
  String addressLine3;

  @Column(name = "village")
  String village;

  @Column(name = "site_name")
  String siteName;

  @Column(name = "scheme_no")
  String schemeNo;

  @Column(name = "fp_no")
  String fpNo;

  @Column(name = "rs_no")
  String rsNo;
  
  @Column(name = "sub_plot_no")
  String subPlotNo;

  @Column(name = "city_survey_no")
  String citySurveyNo;

  @Column(name = "country")
  String country;

  @Column(name="branch_email")
  String branchEmail;

  @Column(name="branch_contact_no")
  String branchContactNo;

  @Column(name="branch_website")
  String branchWebsite;

  @Column(name="business_email")
  String businessEmail;

  @Column(name="business_contact_no")
  String businessContactNo;

  @Column(name="business_website")
  String businessWebsite;

  public boolean isFor(AddressType type) {
    return getAddressType() == type;
  }

  public boolean isFor(AddressType type, Long id) {
    return (getAddressType() == type) && ((userKey == null && id == null) || userKey != null && userKey.equals(id));
  }
}
