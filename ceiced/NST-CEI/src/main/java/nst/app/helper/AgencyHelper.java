package nst.app.helper;

import nst.app.dto.*;
import nst.app.enums.AgencyType;
import nst.app.manager.PortalUserManager;
import nst.app.model.AgencyDetail;
import nst.app.model.EAndMAgency;
import nst.app.model.IAndTAgency;
import nst.app.model.OAndMAgency;
import nst.common.Helper;
import nst.util.AllUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class AgencyHelper implements Helper {

  @Autowired
  PortalUserManager portalUserManager;

  public EAndMAgency toModel(EAndMAgencyDTO dto) {
    EAndMAgency agency = new EAndMAgency();
    agency.getAgencyDetail().setUser(portalUserManager.get(dto.getUserId()));
    agency.setName(dto.getAgencyName());
    agency.getAgencyDetail().setAgencyType(AgencyType.E_M_AGENCY);
    setAgencyDetails(agency.getAgencyDetail(), dto);
    return agency;
  }

  public List<EAndMAgencyDTO> fromEMModel(Iterable<EAndMAgency> ms) {
    if (ms == null) {
      return Collections.emptyList();
    }
    return StreamSupport.stream(ms.spliterator(), false).map(this::fromModel)
        .collect(Collectors.toList());
  }

  public List<AgencyDetailDTO> fromAgencyDetail(Iterable<AgencyDetail> ms) {
    if (ms == null) {
      return Collections.emptyList();
    }
    return StreamSupport.stream(ms.spliterator(), false).map(this::fromModel)
        .collect(Collectors.toList());
  }

  public List<IAndTAgencyDTO> fromITModel(Iterable<IAndTAgency> ms) {
    if (ms == null) {
      return Collections.emptyList();
    }
    return StreamSupport.stream(ms.spliterator(), false).map(this::fromModel)
        .collect(Collectors.toList());
  }

  public List<OAndMAgencyDTO> fromOMModel(Iterable<OAndMAgency> ms) {
    if (ms == null) {
      return Collections.emptyList();
    }
    return StreamSupport.stream(ms.spliterator(), false).map(this::fromModel)
        .collect(Collectors.toList());
  }

  public List<LicenseSearchDTO> fromAgencyModel(Iterable<AgencyDetail> ms) {
    if (ms == null) {
      return Collections.emptyList();
    }
    return StreamSupport.stream(ms.spliterator(), false).map(this::toLicenseDTO)
        .collect(Collectors.toList());
  }

  public EAndMAgencyDTO fromModel(EAndMAgency agency) {
    EAndMAgencyDTO dto = new EAndMAgencyDTO();
    dto.setAgencyName(agency.getName());
    dto.setUserId(agency.getAgencyDetail().getUser().getId());
    dto.setId(agency.getAgencyDetail().getId());
    return dto;
  }

  public OAndMAgency toModel(OAndMAgencyDTO dto) {
    OAndMAgency agency = new OAndMAgency();
    agency.getAgencyDetail().setUser(portalUserManager.get(dto.getUserId()));
    agency.setName(dto.getAgencyName());
    agency.getAgencyDetail().setAgencyType(AgencyType.O_M_AGENCY);

    setAgencyDetails(agency.getAgencyDetail(), dto);
    return agency;
  }

  public OAndMAgencyDTO fromModel(OAndMAgency agency) {
    OAndMAgencyDTO dto = new OAndMAgencyDTO();
    dto.setAgencyName(agency.getName());
    dto.setUserId(agency.getAgencyDetail().getUser().getId());
    dto.setId(agency.getAgencyDetail().getId());
    return dto;

  }

  public IAndTAgency toModel(IAndTAgencyDTO dto) {
    IAndTAgency agency = new IAndTAgency();
    agency.getAgencyDetail().setUser(portalUserManager.get(dto.getUserId()));
    agency.setName(dto.getAgencyName());
    agency.getAgencyDetail().setAgencyType(AgencyType.I_T_AGENCY);
    setAgencyDetails(agency.getAgencyDetail(), dto);
    return agency;
  }

  public IAndTAgencyDTO fromModel(IAndTAgency agency) {
    IAndTAgencyDTO dto = new IAndTAgencyDTO();
    dto.setAgencyName(agency.getName());
    dto.setUserId(agency.getAgencyDetail().getUser().getId());
    dto.setId(agency.getAgencyDetail().getId());
    return dto;
  }
  public AgencyDetailDTO fromModel(AgencyDetail model) {
    AgencyDetailDTO dto = new AgencyDetailDTO();
    dto.setId(model.getId());
    dto.setAgencyName(model.getName());
    return dto;
  }

  public LicenseSearchDTO toLicenseDTO(AgencyDetail model) {
    LicenseSearchDTO dto = new LicenseSearchDTO();
    dto.setLicenseNumber(model.getAgencyAuthNo());
    dto.setExpiryDate(AllUtil.formatUIDate(model.getExpiryDate()));
    dto.setIssueDate(AllUtil.formatUIDate(model.getIssueDate()));
    return dto;
  }

  private void setAgencyDetails(AgencyDetail model, AgencyDetailDTO dto) {
    model.setName(dto.getAgencyName());
    model.setFormId(dto.getFormId());
    model.setAgencyAuthNo(dto.getLicenseNumber());
    model.setIssueDate(AllUtil.toUIDate(dto.getIssueDate()));
    model.setExpiryDate(AllUtil.toUIDate(dto.getExpiryDate()));
    model.setMobileNumber(dto.getMobileNumber());
    model.setEmail(dto.getEmail());
    if(!Objects.isNull(dto.getAgencyAddress())) {
      model.setHouseNo(dto.getAgencyAddress().getHouseNo());
      model.setBuilding(dto.getAgencyAddress().getBuildingName());
      model.setTaluka(dto.getAgencyAddress().getTalukaName());
      model.setDistrict(dto.getAgencyAddress().getDistrict());
      model.setState(dto.getAgencyAddress().getState());
      model.setPincode(dto.getAgencyAddress().getPincode());
      model.setAddrLine1(dto.getAgencyAddress().getAddrLine1());
      model.setAddrLine2(dto.getAgencyAddress().getAddrLine2());
      model.setVillage(dto.getAgencyAddress().getVillage());
    }
  }
}