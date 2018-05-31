package nst.app.helper;


import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import nst.app.common.HelperUtil;
import nst.app.dto.ExperienceDTO;
import nst.app.dto.newgen.NGExperienceDTO;
import nst.app.enums.UserType;
import nst.app.model.forms.Experience;
import nst.common.Helper;
import nst.util.AllUtil;
import org.springframework.stereotype.Component;

@Component
public class ExperienceHelper implements Helper {

  public Experience toModel(ExperienceDTO dto) {
    Experience model = new Experience();
    return toModel(model, dto);
  }

  public Experience toModel(Experience model, ExperienceDTO dto) {
    model.setFromDate(AllUtil.toUIDate(dto.getFromDate()));
    model.setToDate(AllUtil.toUIDate(dto.getToDate()));
    model.setSupervisorName(dto.getExam());
    model.setLicenseNo(dto.getWmanContractorLicenceNo());
    model.setFirmName(dto.getWmanOrgSupFirmName());
    model.setSupervisorPermitNo(dto.getWmanSupSupervisorPermitNo());
    if(model.getForm().getUser().getUserType() == UserType.SUPERVISOR){
        model.setIsCurrent(HelperUtil.toBoolean(dto.getIsCurrent()));
    }
    return model;
  }

  public List<ExperienceDTO> fromModel(Iterable<Experience> ms) {
    if (ms == null) {
      return Collections.emptyList();
    }
    return StreamSupport.stream(ms.spliterator(), false).map(this::fromModel)
        .collect(Collectors.toList());
  }
  public List<NGExperienceDTO> fromModelNG(Iterable<Experience> ms) {
    if (ms == null) {
      return Collections.emptyList();
    }
    return StreamSupport.stream(ms.spliterator(), false).map(this::toNGDTO)
            .collect(Collectors.toList());
  }



  public ExperienceDTO fromModel(Experience model) {
    if (model == null) {
      return null;
    }
    ExperienceDTO dto = new ExperienceDTO();
    dto.setId(model.getId());
    dto.setExam(model.getSupervisorName());
    dto.setFromDate(AllUtil.formatUIDate(model.getFromDate()));
    dto.setToDate(AllUtil.formatUIDate(model.getToDate()));
    dto.setWmanContractorLicenceNo(model.getLicenseNo());
    dto.setWmanOrgSupFirmName(model.getFirmName());
    dto.setWmanSupSupervisorPermitNo(model.getSupervisorPermitNo());
      if(model.getForm().getUser().getUserType() == UserType.SUPERVISOR){
          dto.setIsCurrent(HelperUtil.fromBoolean(model.getIsCurrent()));
      }
    return dto;
  }

  public NGExperienceDTO toNGDTO(Experience model) {
    NGExperienceDTO dto = new NGExperienceDTO();

    dto.setId(model.getId());
    dto.setSupervisorName(model.getSupervisorName());
    dto.setFromDate(AllUtil.formatNGDate(model.getFromDate()));
    dto.setToDate(AllUtil.formatNGDate(model.getToDate()));
    dto.setContractorLicenceNo(model.getLicenseNo());
    dto.setFirmName(model.getFirmName());
    dto.setSupervisorPermitNo(model.getSupervisorPermitNo());
    dto.setIsCurrent(model.getIsCurrent());

    return dto;
  }
}