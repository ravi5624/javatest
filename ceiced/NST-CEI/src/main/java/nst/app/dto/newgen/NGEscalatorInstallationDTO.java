package nst.app.dto.newgen;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import nst.app.dto.AddressDTO;

import java.util.List;

/**
 * Created by bhargav on 16/1/18.
 */

@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class NGEscalatorInstallationDTO extends NGBaseDTO{

    //===== Part 1 ============================

    private Long agencyId;

    private String agencyName;

    private String applicationFor;

    private String applicantName;

    private String escalatorLicenseNumber;

    private String applicantEmail;

    private String applicantMobile;

    public AddressDTO applicantAddress;

    private String isLocalAgentAppointed;

    private String localAgentName;

    private String localAgentEmail;

    private String localAgentContactNo;

    public AddressDTO localAgentAddress;

    private String escalatorSiteName;

    private NGLIAddressDTO liftSiteAddress;

    private String personName;

    private String personEmail;

    private String personMobile;

    public AddressDTO personAddress;

    private String makerName;

    private String makerEmail;

    private String makerMobile;

    public AddressDTO makerAddress;

    private String escalatorIdentification;

    private Integer fromFloor;

    private Integer toFloor;

//===== Part 1 ============================
// ===== Part 2 ============================

    private Double ratedSpeed;

    private Double balusmadesWidth;

    private Double horizontalDistance;

    private Double ratedLoad;

    private Integer escalatorPersonCapacity;

    private Double escalatorAngle;

    private Double escalatorWidth;

    private Double verticalRise;

    private String description;

    private String constructionDetails;

    private String commencementWork;

    private String completionWork;

    public List<NGAttachmentDTO> attachments;
}
