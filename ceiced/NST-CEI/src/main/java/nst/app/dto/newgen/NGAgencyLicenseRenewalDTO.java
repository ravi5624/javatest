package nst.app.dto.newgen;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import nst.app.enums.AgencyType;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class NGAgencyLicenseRenewalDTO extends NGBaseDTO {

  private AgencyType agencyType;
  private String agencyAuthNo;
  private String issueDate;
  private String expiryDate;
  private String nameOfAgency;
  private NGAddressDTO officeAddress;
  private String email;
  private String contactNo;
  private String websiteUrl;
  private String isChangeAnyDetails;
  private List<NGAttachmentDTO> attachments;
}