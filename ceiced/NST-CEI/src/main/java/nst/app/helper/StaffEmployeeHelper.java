package nst.app.helper;

import nst.app.dto.StaffEmployeeDTO;
import nst.app.dto.newgen.le.NGStaffEmployeeDTO;
import nst.app.model.PortalUser;
import nst.app.model.forms.le.StaffEmployee;
import nst.common.Helper;
import nst.util.AllUtil;
import nst.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Component
public class StaffEmployeeHelper implements Helper {

  @Autowired
  AttachmentHelper attachmentHelper;

  public StaffEmployee toModel(StaffEmployeeDTO dto) {
    StaffEmployee model = new StaffEmployee();System.err.println("Middle"+dto);

    return toModel(model, dto);
  }

  public StaffEmployee toModel(StaffEmployee model, StaffEmployeeDTO dto) {
    model.setQualification(dto.getQualification());
    model.setName(dto.getName());
    model.setDesignation(dto.getDesignation());
    model.setExitDate(AllUtil.toUIDate(dto.getExitDate()));
    model.setJoiningDate(AllUtil.toUIDate(dto.getJoiningDate()));
    model.setEmployee(dto.getEmployee());
    model.setSerialNumber(dto.getSerialNumber());
    model.setIsNew(dto.getIsNew());
    return model;
  }

  public StaffEmployee blankModel(Object portaluser) {
    StaffEmployee ownerDetail = new StaffEmployee();
    LoginUserUtil.getLoginUser();
    ownerDetail.getForm().setUser((PortalUser) portaluser);
    return ownerDetail;
  }

  public List<StaffEmployeeDTO> fromModel(Iterable<StaffEmployee> ms) {
    if (ms == null) {
      return Collections.emptyList();
    }
    return StreamSupport.stream(ms.spliterator(), false).map(this::fromModel)
            .collect(Collectors.toList());
  }

  public StaffEmployeeDTO fromModel(StaffEmployee model) {
    if (model == null) {
      return null;
    }
    StaffEmployeeDTO dto = new StaffEmployeeDTO();
    dto.setId(model.getId());
    dto.setQualification(model.getQualification());
    dto.setName(model.getName());
    dto.setDesignation(model.getDesignation());
    dto.setExitDate(AllUtil.formatUIDate(model.getExitDate()));
    dto.setJoiningDate(AllUtil.formatUIDate(model.getJoiningDate()));
    dto.setEmployee(model.getEmployee());
    dto.setSerialNumber(model.getSerialNumber());
    dto.setIsNew(model.getIsNew());
    dto.setAttachments(attachmentHelper.fromModel(model.getForm().getAttachments()));
    return dto;
  }


  public List<NGStaffEmployeeDTO> fromModelNG(Iterable<StaffEmployee> ms) {
    if (ms == null) {
      return Collections.emptyList();
    }
    return StreamSupport.stream(ms.spliterator(), false).map(this::toNGDTO)
            .collect(Collectors.toList());

  }

   public NGStaffEmployeeDTO toNGDTO(StaffEmployee model) {
    NGStaffEmployeeDTO dto = new NGStaffEmployeeDTO();
    dto.setId(model.getId());
    dto.setName(model.getName());
    dto.setQualification(model.getQualification());
    dto.setDesignation(model.getDesignation());
     dto.setSerialNumber(model.getSerialNumber());
    dto.setExitDate(AllUtil.formatNGDate(model.getExitDate()));
    dto.setJoiningDate(AllUtil.formatNGDate(model.getJoiningDate()));
    dto.setEmployee(model.getEmployee());
    dto.setAttachments(attachmentHelper.fromModelNG(model.getForm().getAttachments()));
    return dto;
  }

}