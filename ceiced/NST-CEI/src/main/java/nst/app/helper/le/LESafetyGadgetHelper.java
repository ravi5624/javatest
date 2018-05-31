package nst.app.helper.le;


import nst.app.dto.le.LESafetyGadgetDTO;
import nst.app.dto.newgen.le.NGLESafetyGadgetDTO;
import nst.app.helper.AttachmentHelper;
import nst.app.model.PortalUser;
import nst.app.model.forms.le.LESafetyGadget;
import nst.common.Helper;
import nst.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Component("lesafetyGadgetHelper")
public class LESafetyGadgetHelper implements Helper {
  @Autowired
  AttachmentHelper attachmentHelper;

  public LESafetyGadget toModel(LESafetyGadgetDTO dto) {
    LESafetyGadget model = new LESafetyGadget();
    return toModel(model, dto);
  }


  public LESafetyGadget toModel(LESafetyGadget model, LESafetyGadgetDTO dto) {

    model.setMake(dto.getMake());
    model.setName(dto.getName());
    model.setRemarks(dto.getRemarks());
    model.setNumber(dto.getNumber());
    model.setIsNew(dto.getIsNew());
    return model;
  }

  public List<NGLESafetyGadgetDTO> fromModelNG(Iterable<LESafetyGadget> ms) {
    if (ms == null) {
      return Collections.emptyList();
    }
   return StreamSupport.stream(ms.spliterator(), false).map(this::toNGDTO).collect(Collectors.toList());

  }


  public LESafetyGadget blankModel(Object portaluser) {
    LESafetyGadget ownerDetail = new LESafetyGadget();
    LoginUserUtil.getLoginUser();
    ownerDetail.getForm().setUser((PortalUser) portaluser);
    return ownerDetail;
  }


  public List<LESafetyGadgetDTO> fromModel(Iterable<LESafetyGadget> ms) {
    if (ms == null) {
      return Collections.emptyList();
    }
    return StreamSupport.stream(ms.spliterator(), false).map(this::fromModel)
            .collect(Collectors.toList());
  }

  public LESafetyGadgetDTO fromModel(LESafetyGadget model) {
    if (model == null) {
      return null;
    }
    LESafetyGadgetDTO dto = new LESafetyGadgetDTO();
    dto.setId(model.getId());
    dto.setMake(model.getMake());
    dto.setName(model.getName());
    dto.setRemarks(model.getRemarks());
    dto.setNumber(model.getNumber());
    dto.setIsNew(model.getIsNew());
    return dto;
  }

  public NGLESafetyGadgetDTO toNGDTO(LESafetyGadget model) {
    NGLESafetyGadgetDTO dto = new NGLESafetyGadgetDTO();
    dto.setId(model.getId());
    dto.setMake(model.getMake());
    dto.setName(model.getName());
    dto.setRemarks(model.getRemarks());
    dto.setNumber(model.getNumber());
    return dto;

  }

}