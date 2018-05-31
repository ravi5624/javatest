package nst.app.dto.newgen;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import nst.app.dto.AddressDTO;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class NGOperatingEscalatorDTO extends NGBaseDTO{

    //===== Part 1 ============================
    private Long agencyId;

    private String agencyName;

    private String applicantName;

    private String applicantEmail;

    private String applicantMobile;

    private AddressDTO applicantAddress;

    private String isLocalAgentAppointed;

    private String localAgentName;

    private String localAgentEmail;

    private String localAgentContactNo;

    private AddressDTO localAgentAddress;

    private String escalatorSiteName;

    private String escalatorTownPlanningNo;

    private String escalatorFpNo;

    private String escalatorRsNo;

    private String escalatorSubPlotNo;

    private String escalatorBlockTenamentNo;

    private String escalatorCitySurveyNo;

    private NGLIAddressDTO liftSiteAddress;

    private String personName;

    private AddressDTO personAddress;

    private String personEmail;

    private String personMobile;

    private String makerName;

    private String makerEmail;

    private String makerMobile;

    public AddressDTO makerAddress;

    private String escalatorIdentification;

    private Integer fromFloor;

    private Integer toFloor;

//===== Part 1 ============================
// ===== Part 2 ============================

    private String escalatorType;

    private Double ratedLoad;

    private Double ratedSpeed;

    private Integer escalatorPassengerCapacity;

    private Double escalatorAngle;

    private Double escalatorWidth;

    private Double verticalRise;

    private String description;

    private String totalHeadRoom;

    private String constructionDetails;

    private String approxReaction;
    public List<NGAttachmentDTO> attachments;
}
