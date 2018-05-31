package nst.app.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import nst.app.dto.OrganizationDetailsDTO;
import nst.app.dto.newgen.NGOrganizationDetailsDTO;
import nst.app.model.PortalUser;
import nst.app.model.forms.OrganizationDetails;
import nst.common.Helper;
import nst.util.AllUtil;
import nst.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class OrganizationDetailsHelper implements Helper {

  @Autowired
  AttachmentHelper attachmentHelper;

  public OrganizationDetails toModel(OrganizationDetailsDTO dto) {
    OrganizationDetails model = new OrganizationDetails();
    return toModel(model, dto);
  }


  public OrganizationDetails toModel(OrganizationDetails model, OrganizationDetailsDTO dto) {
    model.setId(dto.getId());
    model.setNameOfPartner(dto.getNameOfPartner());
    model.setHomeAddress(dto.getHomeAddress());
    model.setDateOfBirth(AllUtil.toUIDate(dto.getBirthDate()));
    return model;
  }
  public List<NGOrganizationDetailsDTO> fromModelNG(Iterable<OrganizationDetails> ms) {
    if (ms == null) {
      return Collections.emptyList();
    }
    return StreamSupport.stream(ms.spliterator(), false).map(this::toNGDTO)
            .collect(Collectors.toList());
  }

  public NGOrganizationDetailsDTO toNGDTO(OrganizationDetails model) {
    NGOrganizationDetailsDTO dto = new NGOrganizationDetailsDTO();
    dto.setId(model.getId());
    dto.setBirthDate(AllUtil.formatNGDate(model.getDateOfBirth()));
    dto.setHomeAddress(model.getHomeAddress());
    dto.setNameOfPartner(model.getNameOfPartner());
    return dto;
  }

  public OrganizationDetails blankModel(Object portaluser) {
    OrganizationDetails ownerDetail = new OrganizationDetails();
    LoginUserUtil.getLoginUser();
    ownerDetail.getForm().setUser((PortalUser) portaluser);
    return ownerDetail;
  }


  public List<OrganizationDetailsDTO> fromModel(Iterable<OrganizationDetails> ms) {
    if (ms == null) {
      return Collections.emptyList();
    }
    return StreamSupport.stream(ms.spliterator(), false).map(this::fromModel)
            .collect(Collectors.toList());
  }

  public OrganizationDetailsDTO fromModel(OrganizationDetails model) {
    if (model == null) {
      return null;
    }
    OrganizationDetailsDTO dto = new OrganizationDetailsDTO();
    dto.setId(model.getId());
    dto.setBirthDate(AllUtil.formatUIDate(model.getDateOfBirth()));
    dto.setHomeAddress(model.getHomeAddress());
    dto.setNameOfPartner(model.getNameOfPartner());

    dto.setAttachments( new ArrayList<>());

    dto.getAttachments().add(attachmentHelper.fromModel(model.getForm().getAttachmentFor("5.1."+ model.getId())));
    dto.getAttachments().add(attachmentHelper.fromModel(model.getForm().getAttachmentFor("5.2."+ model.getId())));
    dto.getAttachments().add(attachmentHelper.fromModel(model.getForm().getAttachmentFor("5.3."+ model.getId())));

    return dto;
  }
}