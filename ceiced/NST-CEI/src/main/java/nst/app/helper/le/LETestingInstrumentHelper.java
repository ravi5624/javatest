package nst.app.helper.le;

import nst.app.dto.le.LETestingInstrumentDTO;
import nst.app.dto.newgen.le.NGLETestingInstrumentDTO;
import nst.app.model.PortalUser;
import nst.app.model.forms.le.LETestingInstrument;
import nst.common.Helper;
import nst.util.LoginUserUtil;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Component("letestingInstrumentHelper")
public class LETestingInstrumentHelper implements Helper {


  public LETestingInstrument toModel(LETestingInstrumentDTO dto) {
    LETestingInstrument model = new LETestingInstrument();
    return toModel(model, dto);
  }


  public LETestingInstrument toModel(LETestingInstrument model, LETestingInstrumentDTO dto) {
    model.setId(dto.getId());
    model.setMake(dto.getMake());
    model.setSerialNumber(dto.getSerialNumber());
    model.setMachineName(dto.getMachineName());
    model.setRemarks(dto.getRemarks());
    model.setNumber(dto.getNumber());
    model.setIsNew(dto.getIsNew());
    return model;
  }

  public List<NGLETestingInstrumentDTO> fromModelNG(Iterable<LETestingInstrument> ms) {
    if (ms == null) {
      return Collections.emptyList();
    }
   return StreamSupport.stream(ms.spliterator(), false).map(this::toNGDTO)
            .collect(Collectors.toList());

  }


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

  public LETestingInstrument blankModel(Object portaluser) {
    LETestingInstrument ownerDetail = new LETestingInstrument();
    LoginUserUtil.getLoginUser();
    ownerDetail.getForm().setUser((PortalUser) portaluser);
    return ownerDetail;
  }


  public List<LETestingInstrumentDTO> fromModel(Iterable<LETestingInstrument> ms) {
    if (ms == null) {
      return Collections.emptyList();
    }
    return StreamSupport.stream(ms.spliterator(), false).map(this::fromModel)
            .collect(Collectors.toList());
  }

  public LETestingInstrumentDTO fromModel(LETestingInstrument model) {
    if (model == null) {
      return null;
    }
    LETestingInstrumentDTO dto = new LETestingInstrumentDTO();
    dto.setId(model.getId());
    dto.setMake(model.getMake());
    dto.setSerialNumber(model.getSerialNumber());
    dto.setMachineName(model.getMachineName());
    dto.setRemarks(model.getRemarks());
    dto.setNumber(model.getNumber());
    dto.setIsNew(model.getIsNew());
    return dto;
  }


  public NGLETestingInstrumentDTO toNGDTO(LETestingInstrument model) {
    NGLETestingInstrumentDTO dto = new NGLETestingInstrumentDTO();
    dto.setId(model.getId());
    dto.setMake(model.getMake());
    dto.setSerialNumber(model.getSerialNumber());
    dto.setMachineName(model.getMachineName());
    dto.setRemarks(model.getRemarks());
    dto.setNumber(model.getNumber());

    return dto;
  }
}