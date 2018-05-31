package nst.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import nst.app.common.CEIBaseDTO;
import nst.dto.AttachmentDTO;
import org.hibernate.validator.constraints.Email;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
public class OperatingLiftDTO extends CEIBaseDTO {

  //===== Part 1 ============================

  @NotNull
  private Long agencyId;

  private String agencyName;

  @NotNull
  @Pattern(regexp = "[a-zA-Z]")
  @Size(max = 50)
  @JsonProperty("applicantName")
  private String olApplicantName;

  @Email
  private String applicantEmail;

  @Pattern(regexp = "(^$|[0-9]{10})")
  private String applicantMobile;

  @Valid
  @NotNull
  private AddressDTO applicantAddress;

  private String localAgentAppointed;

  @Pattern(regexp = "[a-zA-Z]")
  @Size(max = 50)
  private String localAgentName;

  @Email
  private String localAgentEmail;

  @Pattern(regexp = "(^$|[0-9]{10})")
  private String localAgentContactNo;

  @Valid
//  @NotNull
  private AddressDTO localAgentAddress;


  private String liftSiteName;


  private LEAddressDTO liftSiteAddress;

  /*@NotNull
  private String liftTownPlanningNo;

  @NotNull
  @Size(max = 15)
  private String liftFpNo;

  @NotNull
  @Size(max = 15)
  private String liftRsNo;

  @NotNull
  @Size(max = 15)
  private String liftSubPlotNo;

  @NotNull
  @Size(max = 15)
  private String liftBlockTenamentNo;

  @NotNull
  @Size(max = 15)
  private String liftCitySurveyNo;*/


  @NotNull
  @Size(max = 100)
  private String personName;

  @Valid
  @NotNull
  private AddressDTO personAddress;

  @NotNull
  @Size(max = 100)
  @Email
  private String personEmail;

  @NotNull
  @Pattern(regexp = "(^$|[0-9]{10})")
  private String personMobile;

  //===== Part 1 ============================
// ===== Part 2 ============================

  @Size(max = 30)
  private String liftType;

  @Size(max = 50)
  private String liftSubCategory;


  private String otherCategoryLift;

  @Size(max = 20)
  private String makeLift;

  private Integer liftPassengerCapacity;

  private Double ratedLoad;

  private Double ratedSpeed;

  private String totalLiftWeight;

  private String totalCounterWeight;

  @Size(max = 100)
  private String suspensionRopeSize;

  private String pitDepth;

  private Double travelMeters;

  private Double travelTime;

  private Integer basementFloors;

  private Integer groundFloors;

  private Integer mezzaineFloors;

  private Integer servedFloors;

  private Integer entrances;

  @Size(max = 50)
  private String floorClosed;

  private Integer dummyFloors;

  @Size(max = 100)
  private String headRoomDetail;

  // ===== Part 2 ============================
  public List<AttachmentDTO> attachments;

  @Override
  public String getApplicantName() {
    return String.format("%s", nst.app.common.HelperUtil.fromString(olApplicantName)).trim();
  }
}