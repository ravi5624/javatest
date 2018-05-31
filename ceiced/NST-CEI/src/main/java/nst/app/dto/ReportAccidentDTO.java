package nst.app.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import nst.common.base.BaseModelDTO;
import nst.dto.AttachmentDTO;

import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
public class ReportAccidentDTO extends BaseModelDTO {


  String accidentFor;

  String accidentDate;

  String accidentTime;

  String ownerName;

  List<AccidentVictimDTO> victims;

  AddressDTO accidentPlace;


  String accidentType;

  String isPostmortemperformed;

  String isPersonAuthorized;

  String personDesgination;

  String jobDescription;

  String isPersonAllowed;

  String licenceNo;

  String agencyName;

  //AddressDTO parmanentAddress;

  AddressDTO businessAddress;

  String maintainerContactNo;

  String maintainerWebsite;

  String maintainerEmail;

  String injuriesDescription;

  @Size(max = 6000)
  String detailedAccidentCauses;

  @Size(max = 6000)
  String takenAction;

  String isPoliceConcerned;

  String policeConcernedDetails;

  @Size(max = 6000)
  String accidentEvidene;

  String assistingDescription;

  String witnessDescription;

  @Size(max = 6000)
  String remarks;

  String isAuthorized;

  public List<AttachmentDTO> attachments;

  @Override
  public String getApplicantName() {
    return String.format("%s", nst.app.common.HelperUtil.fromString(ownerName)).trim();
  }
}