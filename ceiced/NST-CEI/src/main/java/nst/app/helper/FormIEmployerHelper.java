package nst.app.helper;

import nst.app.common.HelperUtil;
import nst.app.dto.FormIEmployerDTO;
import nst.app.dto.newgen.NGFormIEmployerDTO;
import nst.app.model.PortalUser;
import nst.app.model.forms.FormIEmployer;
import nst.util.AllUtil;
import nst.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class FormIEmployerHelper {

  @Autowired
  AttachmentHelper attachmentHelper;

  public FormIEmployer toModel(FormIEmployerDTO dto) {
    FormIEmployer model = new FormIEmployer();
    return toModel(model, dto);
  }


  public FormIEmployer toModel(FormIEmployer model, FormIEmployerDTO dto) {

    model.setLeavingDate(AllUtil.toUIDate(dto.getLeavingDate()));
    model.setIsInactive(HelperUtil.toBoolean(dto.getIsInactive()));

    if (!model.getIsNewEmployer()) {
      return model;
    }

    model.setName(dto.getName());
    model.setPermitNo(dto.getPermitNo());
    model.setDesignation(dto.getDesignation());
    model.setJoiningDate(AllUtil.toUIDate(dto.getJoiningDate()));
    model.setIsNewEmployer(HelperUtil.toBoolean(dto.getIsNew()));
    model.setOtherAttachmentName(dto.getOtherAttachmentName());
//    dto.setAttachments(attachmentHelper.fromModel(model.getForm().getAttachments()));
    return model;
  }

  public FormIEmployer blankModel(Object portaluser) {
    FormIEmployer employerDetail = new FormIEmployer();
    LoginUserUtil.getLoginUser();
    employerDetail.getForm().setUser((PortalUser) portaluser);
    return employerDetail;
  }


  public List<FormIEmployerDTO> fromModel(Iterable<FormIEmployer> ms) {
    if (ms == null) {
      return Collections.emptyList();
    }
    return StreamSupport.stream(ms.spliterator(), false).map(this::fromModel)
        .collect(Collectors.toList());
  }

  public FormIEmployerDTO fromModel(FormIEmployer model) {
    if (model == null) {
      return null;
    }
    FormIEmployerDTO dto = new FormIEmployerDTO();
    dto.setId(model.getId());
    dto.setName(model.getName());
    dto.setPermitNo(model.getPermitNo());
    dto.setDesignation(model.getDesignation());
    dto.setJoiningDate(AllUtil.formatUIDate(model.getJoiningDate()));
    dto.setLeavingDate(AllUtil.formatUIDate(model.getLeavingDate()));
    dto.setIsInactive(HelperUtil.fromBoolean(model.getIsInactive()));
    dto.setIsNew(HelperUtil.fromBoolean(model.getIsNewEmployer()));
    dto.setOtherAttachmentName(model.getOtherAttachmentName());
    return dto;
  }

  public List<NGFormIEmployerDTO> fromModelNG(Iterable<FormIEmployer> ms) {
    if (ms == null) {
      return Collections.emptyList();
    }

    return StreamSupport.stream(ms.spliterator(), false).map(this::toNGDTO)
        .collect(Collectors.toList());

  }

  public NGFormIEmployerDTO toNGDTO(FormIEmployer model) {
    NGFormIEmployerDTO dto = new NGFormIEmployerDTO();
    dto.setId(model.getId());
    dto.setName(model.getName());
    dto.setPermitNo(model.getPermitNo());
    dto.setDesignation(model.getDesignation());
    dto.setJoiningDate(AllUtil.formatNGDate(model.getJoiningDate()));
    dto.setLeavingDate(AllUtil.formatNGDate(model.getLeavingDate()));
    dto.setIsInactive(model.getIsInactive());
    dto.setOtherAttachmentName(model.getOtherAttachmentName());
//    dto.setAttachments(attachmentHelper.fromModelNG(model.getForm().getAttachments()));
    return dto;
  }

}
