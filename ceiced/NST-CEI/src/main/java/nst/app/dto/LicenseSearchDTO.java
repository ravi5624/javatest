package nst.app.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import nst.app.enums.ApplicationType;
import nst.common.base.BaseDTO;
import nst.common.base.BaseResponse;
import nst.kernal.validation.CombiDate;
import nst.kernal.validation.PastDate;
import nst.util.AllUtil;
import nst.util.JSONUtil;
import org.springframework.util.StringUtils;

import java.util.Date;

@Data
@CombiDate(
        startField = "IssueDate",
        endField = "ExpiryDate",
        message = "Licence dates are invalid.")
@NoArgsConstructor
public class LicenseSearchDTO extends BaseDTO {

  String type;
  String licenseNumber;

  @PastDate
  String issueDate;
  String expiryDate;

  public Date issueDate() {
    return AllUtil.toDate(issueDate);
  }

  public Date expiryDate() {
    return AllUtil.toDate(expiryDate);
  }

  public ApplicationType getApplicationType() {
    if (StringUtils.isEmpty(type)) {
      return null;
    }
    return ApplicationType.valueOf(type.toUpperCase());
  }

  public static void main(String[] args) {
    LicenseSearchDTO dto = new LicenseSearchDTO();
    dto.setType(ApplicationType.EIL.getType());
    dto.setLicenseNumber("L-1");
    dto.setIssueDate(AllUtil.formatDate(new Date()));
    dto.setExpiryDate(AllUtil.formatDate(new Date()));
    System.out.println("dto = " + dto.toJSON());

    System.out.println("dto = " + BaseResponse.createSuccess(dto).toJSON());
    LicenseSearchDTO dto1 = JSONUtil.fromJSON(dto.toJSON(), LicenseSearchDTO.class);
    System.out.println("issueDate = " + dto1.issueDate());
    System.out.println("expiryDate = " + dto1.expiryDate());

    LicenseSearchDTO searchDTO = JSONUtil.getDataDTO("{\"data\":[{\"type\":\"OLIFT\",\"licenseNumber\":\"L-1\",\"issueDate\":\"02/12/2017\",\"expiryDate\":\"02/12/2017\",\"applicationType\":\"OLIFT\"}]}", LicenseSearchDTO.class);
    System.out.println("searchDTO = " + searchDTO);

  }
}