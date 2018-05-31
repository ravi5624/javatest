package nst.app.helper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import nst.app.dto.TestingInstrumentDTO;
import nst.app.dto.newgen.NGOrganizationDetailsDTO;
import nst.app.model.PortalUser;
import nst.app.model.forms.TestingInstrument;
import nst.common.Helper;
import nst.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class TestingInstrumentHelper implements Helper {

  @Autowired
  AttachmentHelper attachmentHelper;

  public TestingInstrument toModel(TestingInstrumentDTO dto) {
    TestingInstrument model = new TestingInstrument();
    return toModel(model, dto);
  }


  public TestingInstrument toModel(TestingInstrument model, TestingInstrumentDTO dto) {
    model.setId(dto.getId());
    model.setMake(dto.getMake());
    model.setSerialNumber(dto.getSerialNumber());
    model.setRemarks(dto.getRemarks());
    model.setNumber(dto.getNumber());
    return model;
  }

  public List<NGOrganizationDetailsDTO> fromModelNG(Iterable<TestingInstrument> ms) {
    if (ms == null) {
      return Collections.emptyList();
    }
/*    return StreamSupport.stream(ms.spliterator(), false).map(this::toNGDTO)
            .collect(Collectors.toList());*/
  return null;
  }


/*
/public NGOrganizationDetailsDTO toNGDTO(OrganizationDetails model) {
    NGOrganizationDetailsDTO dto = new NGOrganizationDetailsDTO();
    dto.setId(model.getId());
    dto.setBirthDate(AllUtil.formatNGDate(model.getDateOfBirth()));
    dto.setHomeAddress(model.getHomeAddress());
    dto.setNameOfPartner(model.getNameOfPartner());
    return dto;
  }
*/

  public TestingInstrument blankModel(Object portaluser) {
    TestingInstrument ownerDetail = new TestingInstrument();
    LoginUserUtil.getLoginUser();
    ownerDetail.getForm().setUser((PortalUser) portaluser);
    return ownerDetail;
  }


  public List<TestingInstrumentDTO> fromModel(Iterable<TestingInstrument> ms) {
    if (ms == null) {
      return Collections.emptyList();
    }
    return StreamSupport.stream(ms.spliterator(), false).map(this::fromModel)
            .collect(Collectors.toList());
  }

  public TestingInstrumentDTO fromModel(TestingInstrument model) {
    if (model == null) {
      return null;
    }
    TestingInstrumentDTO dto = new TestingInstrumentDTO();
    dto.setId(model.getId());
    dto.setMake(model.getMake());
    dto.setSerialNumber(model.getSerialNumber());
    dto.setRemarks(model.getRemarks());
    dto.setNumber(model.getNumber());
    return dto;
  }
}