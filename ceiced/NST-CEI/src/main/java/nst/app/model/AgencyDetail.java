package nst.app.model;

import lombok.Data;
import nst.app.enums.AgencyType;
import nst.common.base.BaseModel;
import nst.dto.LookupDataDTO;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "agency_detail")
public class AgencyDetail extends BaseModel {

  @JoinColumn(name = "user_id")
  @OneToOne
  private PortalUser user;

  @Column(name = "agency_type")
  @Enumerated(value = EnumType.STRING)
  private AgencyType agencyType;

  @Column(name = "name")
  private String name;

  @Column(name = "code")
  private String code;

  @Column(name = "form_id")
  private Long formId;

  @Column(name = "agency_auth_no")
  private String agencyAuthNo;

  @Column(name = "issue_date")
  private Date issueDate;

  @Column(name = "expiry_date")
  private Date expiryDate;

  @Column(name = "mobile_number")
  private String mobileNumber;

  @Column(name = "email")
  private String email;

  @Column(name = "house_no")
  private String houseNo;

  @Column(name = "building")
  private String building;

  @Column(name = "taluka")
  private String taluka;

  @Column(name = "district")
  private String district;

  @Column(name = "state")
  private String state;

  @Column(name = "pincode")
  private String pincode;

  @Column(name = "addrLine1")
  private String addrLine1;

  @Column(name = "addrLine2")
  private String addrLine2;

  @Column(name = "village")
  private String village;

  public LookupDataDTO toLookup(){
    return LookupDataDTO.create(getId(), getCode(), getName());
  }
}