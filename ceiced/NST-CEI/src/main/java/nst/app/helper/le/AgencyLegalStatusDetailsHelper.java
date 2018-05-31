package nst.app.helper.le;

import nst.app.dto.le.AgencyLegalStatusDetailsDTO;
import nst.app.dto.newgen.NGAgencyLegalStatusDetailsDTO;
import nst.app.helper.AttachmentHelper;
import nst.app.model.PortalUser;
import nst.app.model.forms.le.AgencyLegalStatusDetails;
import nst.common.Helper;
import nst.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
@Component
public class AgencyLegalStatusDetailsHelper implements Helper {

    @Autowired
    AttachmentHelper attachmentHelper;
    public AgencyLegalStatusDetails toModel(AgencyLegalStatusDetailsDTO dto) {
        AgencyLegalStatusDetails model = new AgencyLegalStatusDetails();
        return toModel(model, dto);
    }


    public AgencyLegalStatusDetails toModel(AgencyLegalStatusDetails model, AgencyLegalStatusDetailsDTO dto) {

        model.setId(dto.getId());
        model.setPartnerName(dto.getPartnerName());
        return model;
    }

    public List<NGAgencyLegalStatusDetailsDTO> fromModelNG(Iterable<AgencyLegalStatusDetails> ms) {
        if (ms == null) {
            return Collections.emptyList();
        }
    return StreamSupport.stream(ms.spliterator(), false).map(this::toNGDTO)
            .collect(Collectors.toList());
    }


    public AgencyLegalStatusDetails blankModel(Object portaluser) {
        AgencyLegalStatusDetails ownerDetail = new AgencyLegalStatusDetails();
        LoginUserUtil.getLoginUser();
        ownerDetail.getForm().setUser((PortalUser) portaluser);
        return ownerDetail;
    }


    public List<AgencyLegalStatusDetailsDTO> fromModel(Iterable<AgencyLegalStatusDetails> ms) {
        if (ms == null) {
            return Collections.emptyList();
        }
        return StreamSupport.stream(ms.spliterator(), false).map(this::fromModel)
                .collect(Collectors.toList());
    }

    public AgencyLegalStatusDetailsDTO fromModel(AgencyLegalStatusDetails model) {
        if (model == null) {
            return null;
        }
        AgencyLegalStatusDetailsDTO dto = new AgencyLegalStatusDetailsDTO();
        dto.setId(model.getId());
        dto.setPartnerName(model.getPartnerName());
        dto.setAttachments(attachmentHelper.fromModel(model.getForm().getAttachments()));
        return dto;
    }

    public NGAgencyLegalStatusDetailsDTO toNGDTO(AgencyLegalStatusDetails model) {
        NGAgencyLegalStatusDetailsDTO dto = new NGAgencyLegalStatusDetailsDTO();
        dto.setId(model.getId());
        dto.setPartnerName(model.getPartnerName());
        dto.setAttachments(attachmentHelper.fromModelNG(model.getForm().getAttachments()));
        return dto;


    }

}