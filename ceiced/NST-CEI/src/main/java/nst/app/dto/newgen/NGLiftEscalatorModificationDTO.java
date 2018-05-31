package nst.app.dto.newgen;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.Data;
@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class NGLiftEscalatorModificationDTO extends NGBaseDTO {

    String ownerName;

    private NGAddressDTO ownerAddress;


    String ownerEmail;

    String ownerContactNo;

    String changeBuildingName;

    String isChangeAgency;

    String installerPersonName;

    private NGAddressDTO installerPersonAddress;

    String installerPersonEmail;

    String installerPersonContactNo;

    public List<NGAttachmentDTO> attachments;
}
