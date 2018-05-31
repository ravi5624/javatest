package nst.app.dto.newgen;

import lombok.Data;
import nst.app.dto.AddressDTO;
import nst.app.enums.FileStatus;
import nst.common.base.BaseDTO;
import nst.util.JSONUtil;
import org.codehaus.jackson.annotate.JsonIgnore;

@Data
public class NGRequestDTO extends BaseDTO {

  public static NGRequestDTO fromJSON(String body) {
    return JSONUtil.fromJSON(body, NGRequestDTO.class);
  }

  String id;
  String applicationId;
  String fileNo;
  String fileStatus;
  String counter;
  String remarks;
  String outwardNo;
  String activityName;
  String licenseNumber;
  String issueDate;
  String expiryDate;
  String mobileNumber;
  String email;
  Object comments;
  //TODO: Confirm which address class to use (NGAddressDTO / AddressDTO )?
  AddressDTO agencyAddress;

  /*public FileStatus getStatusEnum(){
    return FileStatus.getByType(fileStatus.toUpperCase());
  }*/
  @JsonIgnore
  public FileStatus getStatusEnum(){
    if(fileStatus.equals("Not Approved")){
      return FileStatus.NOT_APPROVED;
    };
    return FileStatus.getByType(fileStatus.toUpperCase());
  }
}