package nst.app.dto.newgen;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import nst.app.dto.AccidentVictimDTO;
import nst.app.dto.AddressDTO;
import nst.app.model.forms.le.AccidentVictim;
import nst.common.base.BaseModelDTO;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class NGReportAccidentDTO extends NGBaseDTO {

        String accidentDate;

        String accidentFor;

        String accidentTime;

        String ownerName;

        List<NGAccidentVictimDTO> victims;


        String accidentType;

        String isPostmortemperformed;

        String isPersonAuthorized;

        String personDesgination;

        String jobDescription;

        String isPersonAllowed;

        String licenceNo;

        String agencyName;

        /*NGAddressDTO agencyAddress;*/

        NGAddressDTO businessAddress;

        NGAddressDTO parmanentAddress;

        NGAddressDTO accidentPlace;

        String maintainerContactNo;

        String maintainerWebsite;

        String maintainerEmail;

        String injuriesDescription;

        String detailedAccidentCauses;

        String takenAction;

        String isPoliceConcerned;

        String policeConcernedDetails;

        String accidentEvidene;

        String assistingDescription;

        String witnessDescription;

        String remarks;

        String isAuthorized;

        public List<NGAttachmentDTO> attachments;

}