package nst.app.helper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import nst.app.dto.SafetyGadgetDTO;
import nst.app.model.PortalUser;
import nst.app.model.forms.SafetyGadget;
import nst.common.Helper;
import nst.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class SafetyGadgetHelper implements Helper {

  @Autowired
  AttachmentHelper attachmentHelper;

  public SafetyGadget toModel(SafetyGadgetDTO dto) {
    SafetyGadget model = new SafetyGadget();
    return toModel(model, dto);
  }


  public SafetyGadget toModel(SafetyGadget model, SafetyGadgetDTO dto) {
    model.setId(dto.getId());
    model.setMake(dto.getMake());
    model.setName(dto.getName());
    model.setRemarks(dto.getRemarks());
    model.setNumber(dto.getNumber());
    return model;
  }

  /*public List<NGOrganizationDetailsDTO> fromModelNG(Iterable<LESafetyGadget> ms) {
    if (ms == null) {
      return Collections.emptyList();
    }
*//*    return StreamSupport.stream(ms.spliterator(), false).map(this::toNGDTO)
            .collect(Collectors.toList());*//*
  return null;
  }*/


/*
/public NGOrganizationDetailsDTO toNGDTO(OrganizationDetails model) {
    NGOrganizationDetailsDTO dto = new NGOrganizationDetailsDTO();
    dto.setId(model.getId());
    dto.setBirthDate(AllUtil.formatUIDate(model.getDateOfBirth()));
    dto.setHomeAddress(model.getHomeAddress());
    dto.setNameOfPartner(model.getNameOfPartner());
    return dto;
  }
*/

  public SafetyGadget blankModel(Object portaluser) {
    SafetyGadget ownerDetail = new SafetyGadget();
    LoginUserUtil.getLoginUser();
    ownerDetail.getForm().setUser((PortalUser) portaluser);
    return ownerDetail;
  }


  public List<SafetyGadgetDTO> fromModel(Iterable<SafetyGadget> ms) {
    if (ms == null) {
      return Collections.emptyList();
    }
    return StreamSupport.stream(ms.spliterator(), false).map(this::fromModel)
            .collect(Collectors.toList());
  }

  public SafetyGadgetDTO fromModel(SafetyGadget model) {
    if (model == null) {
      return null;
    }
    SafetyGadgetDTO dto = new SafetyGadgetDTO();
    dto.setId(model.getId());
    dto.setMake(model.getMake());
    dto.setName(model.getName());
    dto.setRemarks(model.getRemarks());
    dto.setNumber(model.getNumber());
    return dto;
  }
}