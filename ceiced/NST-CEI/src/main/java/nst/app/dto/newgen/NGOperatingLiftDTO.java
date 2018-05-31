package nst.app.dto.newgen;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import nst.app.dto.AddressDTO;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class NGOperatingLiftDTO extends CEINGBaseDto{


    //===== Part 1 ============================
    private Long agencyId;
    private String agencyName;
    private String applicantName;
    private String applicantEmail;
    private String applicantMobile;
    private AddressDTO applicantAddress;
    private String localAgentAppointed;
    private String localAgentName;
    private String localAgentEmail;
    private String localAgentContactNo;
    private AddressDTO localAgentAddress;
    private String liftSiteName;
    private NGLIAddressDTO liftSiteAddress;
   /* private String liftTownPlanningNo;
    private String liftFpNo;
    private String liftRsNo;
    private String liftSubPlotNo;
    private String liftBlockTenamentNo;
    private String liftCitySurveyNo;*/
    private String personName;
    private AddressDTO personAddress;
    private String personEmail;
    private String personMobile;

    //===== Part 1 ============================
// ===== Part 2 ============================

    private String liftType;
    private String liftSubCategory;
    private String otherCategoryLift;
    private String makeLift;
    private Integer liftPassengerCapacity;
    private Double ratedLoad;
    private Double ratedSpeed;
    private String totalLiftWeight;
    private String totalCounterWeight;
    private String suspensionRopeSize;
    private String pitDepth;
    private Double travelMeters;
    private Double travelTime;
    private Integer basementFloors;
    private Integer groundFloors;
    private Integer mezzaineFloors;
    private Integer servedFloors;
    private Integer entrances;
    private String floorClosed;
    private Integer dummyFloors;
    private String headRoomDetail;

    // ===== Part 2 ============================

    public List<NGAttachmentDTO> attachments;
}
