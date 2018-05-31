package nst.app.helper;

import nst.app.dto.AddressDTO;
import nst.app.dto.BranchAddressDTO;
import nst.app.dto.BusinessAddressDTO;
import nst.app.dto.LEAddressDTO;
import nst.app.dto.newgen.*;
import nst.app.model.PortalUser;
import nst.app.model.forms.Address;
import nst.app.model.forms.le.CommonForm;
import nst.common.Helper;
import nst.util.LoginUserUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddressHelper implements Helper {

  public Address toModel(AddressDTO dto) {
    Address model = new Address();
    return toModel(model, dto);
  }

  public Address toModel(Address model, AddressDTO dto) {
    if(model.getId()!=null) {
      model.setId(model.getId());
    }
    model.setHouseNo(dto.getHouseNo());
    model.setBuilding(dto.getBuildingName());
    model.setState(dto.getState());
    model.setDistrict(dto.getDistrict());
    model.setTaluka(dto.getTalukaName());
    model.setPincode(dto.getPincode());
    model.setAddrLine1(dto.getAddrLine1());
    model.setAddrLine2(dto.getAddrLine2());
    model.setAddressLine3(dto.getAddressLine3());
    model.setVillage(dto.getVillage());
    model.setAddressType(dto.getAddressTypeEnum());
    return model;
  }

  public Address toLEModel(Address model, LEAddressDTO dto) {
    Address address = toModel(model, dto);
    address.setPremisesName(dto.getPremisesName());
    address.setCitySurveyNo(dto.getCitySurveyNo());
    address.setSiteName(dto.getSiteName());
    address.setSchemeNo(dto.getSchemeNo());
    address.setFpNo(dto.getFpNo());
    address.setRsNo(dto.getRsNo());
    address.setSubPlotNo(dto.getSubPlotNo());
    address.setCitySurveyNo(dto.getCitySurveyNo());
    address.setCountry(dto.getCountry());
    address.setTenamentNo(dto.getTenamentNo());
    return address;
  }
  public Address toBranchModel(Address model, BranchAddressDTO dto) {
    Address address = toModel(model, dto);
    address.setBranchEmail(dto.getBranchEmail());
    address.setBranchContactNo(dto.getBranchContactNo());
    address.setBranchWebsite(dto.getBranchWebsite());

    return address;
  }

  public Address toBusinessModel(Address model, BusinessAddressDTO dto) {
    Address address = toModel(model, dto);
    address.setBusinessEmail(dto.getBusinessEmail());
    address.setBusinessContactNo(dto.getBusinessContactNo());
    address.setBranchWebsite(dto.getBusinessWebsite());

    return address;
  }

  public Address blankModel(Object portaluser) {
    Address ownerDetail = new Address();
    LoginUserUtil.getLoginUser();
    ownerDetail.getForm().setUser((PortalUser) portaluser);
    return ownerDetail;
  }

  public AddressDTO fromModel(Address model) {
    if (model == null) {
      return null;
    }
    AddressDTO dto = new AddressDTO();
    dto.setId(model.getId());
    dto.setAddressType(model.getAddressType().name());
    dto.setHouseNo(model.getHouseNo());
    dto.setBuildingName(model.getBuilding());
    dto.setState(model.getState());
    dto.setDistrict(model.getDistrict());
    dto.setTalukaName(model.getTaluka());
    dto.setPincode(model.getPincode());
    dto.setAddrLine1(model.getAddrLine1());
    dto.setAddrLine2(model.getAddrLine2());
    dto.setAddressLine3(model.getAddressLine3());
    dto.setVillage(model.getVillage());
    return dto;
  }

  public BusinessAddressDTO fromBMModel(Address model) {
    if (model == null) {
      return null;
    }
    BusinessAddressDTO dto = new BusinessAddressDTO();
    dto.setId(model.getId());
    dto.setAddressType(model.getAddressType().name());
    dto.setHouseNo(model.getHouseNo());
    dto.setBuildingName(model.getBuilding());
    dto.setState(model.getState());
    dto.setDistrict(model.getDistrict());
    dto.setTalukaName(model.getTaluka());
    dto.setPincode(model.getPincode());
    dto.setAddrLine1(model.getAddrLine1());
    dto.setAddrLine2(model.getAddrLine2());
    dto.setAddressLine3(model.getAddressLine3());
    dto.setVillage(model.getVillage());
    dto.setBusinessContactNo(model.getBusinessContactNo());
    dto.setBusinessEmail(model.getBusinessEmail());
    dto.setBusinessWebsite(model.getBranchWebsite());
    return dto;
  }

  public List<AddressDTO> fromModel(List<Address> model) {
    if (model == null) {
      return null;
    }
    List<AddressDTO> list = new ArrayList<>();
    model.forEach(address -> {
      list.add(this.setAddressDTO(address));
    });
    return list;
  }

  public List<BranchAddressDTO> fromAMModel(List<Address> model) {
    if (model == null) {
      return null;
    }
    List<BranchAddressDTO> list = new ArrayList<>();
    model.forEach(address -> {
      list.add(this.setAMAddressDTO(address));
    });
    return list;
  }

  private AddressDTO setAddressDTO(Address model){
    AddressDTO dto = new AddressDTO();
    dto.setId(model.getId());
    dto.setAddressType(model.getAddressType().name());
    dto.setHouseNo(model.getHouseNo());
    dto.setBuildingName(model.getBuilding());
    dto.setState(model.getState());
    dto.setDistrict(model.getDistrict());
    dto.setTalukaName(model.getTaluka());
    dto.setPincode(model.getPincode());
    dto.setAddrLine1(model.getAddrLine1());
    dto.setAddrLine2(model.getAddrLine2());
    dto.setAddressLine3(model.getAddressLine3());
    dto.setVillage(model.getVillage());
    return dto;
  }

  private BranchAddressDTO setAMAddressDTO(Address model){
    BranchAddressDTO dto = new BranchAddressDTO();
    dto.setId(model.getId());
    dto.setAddressType(model.getAddressType().name());
    dto.setHouseNo(model.getHouseNo());
    dto.setBuildingName(model.getBuilding());
    dto.setState(model.getState());
    dto.setDistrict(model.getDistrict());
    dto.setTalukaName(model.getTaluka());
    dto.setPincode(model.getPincode());
    dto.setAddrLine1(model.getAddrLine1());
    dto.setAddrLine2(model.getAddrLine2());
    dto.setAddressLine3(model.getAddressLine3());
    dto.setVillage(model.getVillage());
    dto.setBranchContactNo(model.getBranchContactNo());
    dto.setBranchEmail(model.getBranchEmail());
    dto.setBranchWebsite(model.getBranchWebsite());
    return dto;
  }
  private NGAddressDTO setNGAddressDTO(Address model){
    NGAddressDTO dto = new NGAddressDTO();
    dto.setId(model.getId());
    dto.setAddressType(model.getAddressType().name());
    dto.setHouseNo(model.getHouseNo());
    dto.setBuildingName(model.getBuilding());
    dto.setState(model.getState());
    dto.setDistrict(model.getDistrict());
    dto.setTalukaName(model.getTaluka());
    dto.setPincode(model.getPincode());
    dto.setAddrLine1(model.getAddrLine1());
    dto.setAddrLine2(model.getAddrLine2());
    dto.setVillage(model.getVillage());
    return dto;
  }


  public LEAddressDTO leAddressDTO(Address model) {
    if (model == null) {
      return null;
    }
    LEAddressDTO dto = new LEAddressDTO();
    dto.setId(model.getId());
    dto.setAddressType(model.getAddressType().name());
    dto.setHouseNo(model.getHouseNo());
    dto.setBuildingName(model.getBuilding());
    dto.setState(model.getState());
    dto.setDistrict(model.getDistrict());
    dto.setTalukaName(model.getTaluka());
    dto.setPincode(model.getPincode());
    dto.setAddrLine1(model.getAddrLine1());
    dto.setAddrLine2(model.getAddrLine2());
    dto.setVillage(model.getVillage());
    dto.setPremisesName(model.getPremisesName());
    dto.setCitySurveyNo(model.getCitySurveyNo());
    dto.setSiteName(model.getSiteName());
    dto.setSchemeNo(model.getSchemeNo());
    dto.setFpNo(model.getFpNo());
    dto.setRsNo(model.getRsNo());
    dto.setSubPlotNo(model.getSubPlotNo());
    dto.setCitySurveyNo(model.getCitySurveyNo());
    dto.setTenamentNo(model.getTenamentNo());
    dto.setCountry(model.getCountry());
    return dto;
  }

  public NGAddressDTO toNGDTO(Address model) {
    if (model == null) {
      return null;
    }
    NGAddressDTO dto = new NGAddressDTO();

    dto.setId(model.getId());
    dto.setAddressType(model.getAddressType().name());
    dto.setHouseNo(model.getHouseNo());
    dto.setBuildingName(model.getBuilding());
    dto.setState(model.getState());
    dto.setDistrict(model.getDistrict());
    dto.setTalukaName(model.getTaluka());
    dto.setPincode(model.getPincode());
    dto.setAddrLine1(model.getAddrLine1());
    dto.setAddrLine2(model.getAddrLine2());
    dto.setAddrLine3(model.getAddressLine3());
    dto.setVillage(model.getVillage());
    return dto;
  }

  public List<NGAddressDTO> toNGDTO(List<Address> model) {
    if (model == null) {
      return null;
    }
    List<NGAddressDTO> list = new ArrayList<>();
    model.forEach(address -> {
      list.add(this.setNGAddressDTO(address));
    });
    return list;
  }

  public NGLiftEscalatorAddressDTO toLENGDTO(Address model) {
    if (model == null) {
      return null;
    }
    NGLiftEscalatorAddressDTO dto = new NGLiftEscalatorAddressDTO();

    dto.setId(model.getId());
    dto.setAddressType(model.getAddressType().name());
    dto.setHouseNo(model.getHouseNo());
    dto.setBuildingName(model.getBuilding());
    dto.setState(model.getState());
    dto.setDistrict(model.getDistrict());
    dto.setTalukaName(model.getTaluka());
    dto.setPincode(model.getPincode());
    dto.setAddrLine1(model.getAddrLine1());
    dto.setAddrLine2(model.getAddrLine2());
    dto.setAddrLine3(model.getAddressLine3());
    dto.setVillage(model.getVillage());
    dto.setCountry(model.getCountry());
    return dto;
  }

  public NGLIAddressDTO toLINGDTO(Address model) {
    if (model == null) {
      return null;
    }
    NGLIAddressDTO dto = new NGLIAddressDTO();

    dto.setId(model.getId());
    dto.setAddressType(model.getAddressType().name());
    dto.setHouseNo(model.getHouseNo());
    dto.setBuildingName(model.getBuilding());
    dto.setState(model.getState());
    dto.setDistrict(model.getDistrict());
    dto.setTalukaName(model.getTaluka());
    dto.setPincode(model.getPincode());
    dto.setPremisesName(model.getPremisesName());
    dto.setCitySurveyNo(model.getCitySurveyNo());
    dto.setSiteName(model.getSiteName());
    dto.setSchemeNo(model.getSchemeNo());
    dto.setFpNo(model.getFpNo());
    dto.setRsNo(model.getRsNo());
    dto.setSubPlotNo(model.getSubPlotNo());
    dto.setCitySurveyNo(model.getCitySurveyNo());
    dto.setTenamentNo(model.getTenamentNo());
    return dto;
  }

  public NGBusinessAddressDTO toBMGDTO(Address model) {
    if (model == null) {
      return null;
    }
    NGBusinessAddressDTO dto = new NGBusinessAddressDTO();

    dto.setId(model.getId());
    dto.setAddressType(model.getAddressType().name());
    dto.setHouseNo(model.getHouseNo());
    dto.setBuildingName(model.getBuilding());
    dto.setState(model.getState());
    dto.setDistrict(model.getDistrict());
    dto.setTalukaName(model.getTaluka());
    dto.setPincode(model.getPincode());
    dto.setBusinessContactNo(model.getBusinessContactNo());
    dto.setBusinessEmail(model.getBusinessEmail());
    dto.setBusinessWebsite(model.getBusinessWebsite());
    return dto;
  }

  public NGBranchAddressDTO setNGAMAddressDTO(Address model) {
    if (model == null) {
      return null;
    }
    NGBranchAddressDTO dto = new NGBranchAddressDTO();

    dto.setId(model.getId());
    dto.setAddressType(model.getAddressType().name());
    dto.setHouseNo(model.getHouseNo());
    dto.setBuildingName(model.getBuilding());
    dto.setState(model.getState());
    dto.setDistrict(model.getDistrict());
    dto.setTalukaName(model.getTaluka());
    dto.setPincode(model.getPincode());
    dto.setBranchContactNo(model.getBranchContactNo());
    dto.setBranchEmail(model.getBranchEmail());
    dto.setBranchWebsite(model.getBranchWebsite());
    return dto;
  }

  public List<NGBranchAddressDTO> toBranchNGDTO(List<Address> model) {
    if (model == null) {
      return null;
    }
    List<NGBranchAddressDTO> list = new ArrayList<>();
    model.forEach(address -> {
      list.add(this.setNGAMAddressDTO(address));
    });
    return list;
  }


  public void manageAddress(CommonForm form, AddressDTO dto) {
    if (dto == null || StringUtils.isEmpty(dto.getAddressType())) {
      return;
    }

    Address address = form.getAddressFor(dto.getAddressTypeEnum());
    if (address == null) {
      address = new Address(form);
      form.addAddress(address);
    }
   toModel(address, dto);
  }

  public void manageBusinessAddress(CommonForm form, BusinessAddressDTO dto) {
    if (dto == null || StringUtils.isEmpty(dto.getAddressType())) {
      return;
    }

    Address address = form.getAddressFor(dto.getAddressTypeEnum());
    if (address == null) {
      address = new Address(form);
      form.addAddress(address);
    }
    toBusinessModel(address, dto);
  }

  public void manageAddress(CommonForm form, List<AddressDTO> dto) {
    if (dto.size() == 0) {
      return;
    }
    dto.forEach(addressDTO-> {
      manageAddress(form, addressDTO, addressDTO.getId());
    });
  }

  public void manageBranchAddress(CommonForm form, List<BranchAddressDTO> dto) {
    if (dto.size() == 0) {
      return;
    }
    dto.forEach(addressDTO-> {
      manageBranchAddress(form, addressDTO, addressDTO.getId());
    });
  }

  public void manageBranchAddress(CommonForm form, BranchAddressDTO dto, Long id) {
    if (dto == null || StringUtils.isEmpty(dto.getAddressType())) {
      return;
    }
    Address address = form.getAddressFor(dto.getAddressTypeEnum(), id);
    if (address == null) {
      address = new Address(form);
      address.setUserKey(id);
      form.addAddress(address);

    }

    toBranchModel(address, dto);
  }


  public void manageAddress(CommonForm form, AddressDTO dto, Long id) {
    if (dto == null || StringUtils.isEmpty(dto.getAddressType())) {
      return;
    }
    Address address = form.getAddressFor(dto.getAddressTypeEnum(), id);
    if (address == null) {
      address = new Address(form);
      address.setUserKey(id);
      form.addAddress(address);

    }

   toModel(address, dto);


  }

  public void manageLEAddress(CommonForm form, LEAddressDTO dto) {
    if (dto == null || StringUtils.isEmpty(dto.getAddressType())) {
      return;
    }

    Address address = form.getAddressFor(dto.getAddressTypeEnum());
    if (address == null) {
      address = new Address(form);
      form.addAddress(address);
    }
    toLEModel(address, dto);
  }
}
