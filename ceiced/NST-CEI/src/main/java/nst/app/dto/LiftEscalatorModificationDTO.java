package nst.app.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import nst.common.base.BaseModelDTO;
import nst.dto.AttachmentDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class LiftEscalatorModificationDTO extends BaseModelDTO {

  @NotNull
  String ownerName;

  @Valid
  @NotNull
  private AddressDTO ownerAddress;

  @NotNull
  String ownerEmail;

  @NotNull
  String ownerContactNo;

  @NotNull
  String changeBuildingName;

  @NotNull
  String isChangeAgency;

  @NotNull
  String installerPersonName;

  @Valid
  @NotNull
  private AddressDTO installerPesonAddress;

  @NotNull
  String installerPersonEmail;

  @NotNull
  String installerPersonContactNo;

  public List<AttachmentDTO> attachments;

  @Override
  public String getApplicantName() {
    return String.format("%s", nst.app.common.HelperUtil.fromString(ownerName)).trim();
  }
}