package nst.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import nst.common.base.BaseModelDTO;
import nst.dto.AttachmentDTO;
import org.hibernate.validator.constraints.Email;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
public class OperatingEscalatorDTO extends BaseModelDTO {

    //===== Part 1 ============================

    @NotNull
    private Long agencyId;

    private String agencyName;

    @NotNull
    @Pattern(regexp = "[a-zA-Z]")
    @Size(max = 50)
    @JsonProperty("applicantName")
    private String oeApplicantName;

    @Email
    private String applicantEmail;

    @Pattern(regexp = "(^$|[0-9]{10})")
    private String applicantMobile;

    @Valid
    @NotNull
    private AddressDTO applicantAddress;

    private String isLocalAgentAppointed;

    @Pattern(regexp = "[a-zA-Z]")
    @Size(max = 50)
    private String localAgentName;

    @Email
    private String localAgentEmail;

    @Pattern(regexp = "(^$|[0-9]{10})")
    private String localAgentContactNo;

    @Valid
    private AddressDTO localAgentAddress;

    @NotNull
    private String escalatorSiteName;

    @Valid
    @NotNull
    private LEAddressDTO liftSiteAddress;

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

    @NotNull
    private String makerName;

    @NotNull
    @Email
    private String makerEmail;

    @NotNull
    @Pattern(regexp = "(^$|[0-9]{10})")
    private String makerMobile;

    @Valid
    @NotNull
    public AddressDTO makerAddress;

    @NotNull
    private String escalatorIdentification;

    @NotNull
    private Integer fromFloor;

    @NotNull
    private Integer toFloor;

//===== Part 1 ============================
// ===== Part 2 ============================

//    @NotNull
//    @Size(max = 30)
    private String escalatorType;

//    @NotNull
//    @Size(max = 30)
    private Double ratedLoad;

    private Double ratedSpeed;

    private Integer escalatorPassengerCapacity;

//    @NotNull
//    @Size(max = 35)
    private Double escalatorAngle;

    private Double escalatorWidth;

//    @NotNull
//    @Size(max = 6000)
    private Double verticalRise;

    private String description;

    private String totalHeadRoom;

    private String constructionDetails;

    private String approxReaction;

    public List<AttachmentDTO> attachments;

    @Override
    public String getApplicantName() {
        return String.format("%s", nst.app.common.HelperUtil.fromString(oeApplicantName)).trim();
    }
}