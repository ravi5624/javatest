package nst.app.helper;

import nst.app.common.HelperUtil;
import nst.app.dto.AgencyLicenseModificationDTO;
import nst.app.dto.FormIDTO;
import nst.app.dto.newgen.NGAgencyLicenseModificationDTO;
import nst.app.enums.AddressType;
import nst.app.helper.le.LESafetyGadgetHelper;
import nst.app.helper.le.LETestingInstrumentHelper;
import nst.app.model.PortalUser;
import nst.app.model.forms.lb.FormI;
import nst.app.model.forms.le.AgencyLicenseModification;
import nst.common.base.BaseHelper;
import nst.util.AllUtil;
import nst.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class AgencyLicenseModificationHelper extends
    BaseHelper<AgencyLicenseModification, AgencyLicenseModificationDTO> {

    @Autowired
    AttachmentHelper attachmentHelper;
  @Autowired
  AddressHelper addressHelper;
  @Autowired
  LESafetyGadgetHelper leSafetyGadgetHelper;

  @Autowired
  LETestingInstrumentHelper leTestingInstrumentHelper;

  @Autowired
  StaffEmployeeHelper staffEmployeeHelper;

  public AgencyLicenseModification toModel(
      AgencyLicenseModificationDTO AgencyLicenseModificationDTO) {
    AgencyLicenseModification portalUser = new AgencyLicenseModification();
    return toModel(portalUser, AgencyLicenseModificationDTO);
  }

  @Override
  public AgencyLicenseModification toModel(AgencyLicenseModification model,
      AgencyLicenseModificationDTO dto) {
    HelperUtil.toModel(model.getForm(), dto);
    addressHelper.manageAddress(model.getForm(), dto.getBranchAddress());
    addressHelper.manageAddress(model.getForm(), dto.getOfficeAddress());
    addressHelper.manageLEAddress(model.getForm(), dto.getWorkshopAddress());
    model.setBranchEmail(dto.getBranchEmail());
    model.setBranchContactNo(dto.getBranchContactNo());
    model.setMainOfficeEmail(dto.getMainOfficeEmail());
    model.setMainOfficeContactNo(dto.getMainOfficeContactNo());
    model.setCommunicationFacilityChangeRequest(dto.getCommunicationFacilityChangeRequest());
    model.setVehiclePossessionDetail(dto.getVehiclePossessionDetail());
    model.setAgencyLegalStatus(dto.getAgencyLegalStatus());

    if (CollectionUtils.isEmpty(dto.getSafetyGadgets())) {
      model.getSafetyGadgets().clear();
    } else {
      dto.getSafetyGadgets().forEach(gadgets -> {
        leSafetyGadgetHelper.toModel(model.mySaftyGadget(gadgets.getId()), gadgets);
      });
    }
    if (CollectionUtils.isEmpty(dto.getTestingInstruments())) {
      model.getTestingInstruments().clear();
    } else {
      dto.getTestingInstruments().forEach(org -> {
        leTestingInstrumentHelper.toModel(model.myTestInstru(org.getId()), org);
      });
    }

    if (CollectionUtils.isEmpty(dto.getStaffEmployees())) {
      model.getStaffEmployees().clear();
    } else {
      dto.getStaffEmployees().forEach(staff -> {
        staffEmployeeHelper.toModel(model.myStaff(staff.getId()), staff);
      });
    }

    model.setIsChangeBranchOffice(dto.getIsChangeBranchOffice());
    model.setIsChangeCommunicationDetails(dto.getIsChangeCommunicationDetails());
    model.setIsChangeLegalStatus(dto.getIsChangeLegalStatus());
    model.setIsChangeMainOffice(dto.getIsChangeMainOffice());
    model.setIsChangeSaftyguards(dto.getIsChangeSaftyguards());
    model.setIsChangeVehicleDetails(dto.getIsChangeVehicleDetails());
    model.setIsChangeWorkshopOffice(dto.getIsChangeWorkshopOffice());
    model.setIsChangetestInstrument(dto.getIsChangetestInstrument());
    model.setIsStaffDetailsChange(dto.getIsStaffDetailsChange());
    return model;
  }

  public AgencyLicenseModificationDTO cloneDTO(AgencyLicenseModification model, AgencyLicenseModificationDTO dto) {
    HelperUtil.toModel(model.getForm(), dto);
    addressHelper.manageAddress(model.getForm(), dto.getBranchAddress());
    addressHelper.manageAddress(model.getForm(), dto.getOfficeAddress());
    addressHelper.manageLEAddress(model.getForm(), dto.getWorkshopAddress());
    model.setBranchEmail(dto.getBranchEmail());
    model.setBranchContactNo(dto.getBranchContactNo());
    model.setMainOfficeEmail(dto.getMainOfficeEmail());
    model.setMainOfficeContactNo(dto.getMainOfficeContactNo());
    model.setCommunicationFacilityChangeRequest(dto.getCommunicationFacilityChangeRequest());
    model.setVehiclePossessionDetail(dto.getVehiclePossessionDetail());
    model.setAgencyLegalStatus(dto.getAgencyLegalStatus());

    if (CollectionUtils.isEmpty(dto.getSafetyGadgets())) {
      model.getSafetyGadgets().clear();
    } else {
      dto.getSafetyGadgets().forEach(gadgets -> {
        leSafetyGadgetHelper.toModel(model.mySaftyGadget(gadgets.getId()), gadgets);
      });
    }
    if (CollectionUtils.isEmpty(dto.getTestingInstruments())) {
      model.getTestingInstruments().clear();
    } else {
      dto.getTestingInstruments().forEach(org -> {
        leTestingInstrumentHelper.toModel(model.myTestInstru(org.getId()), org);
      });
    }

    if (CollectionUtils.isEmpty(dto.getStaffEmployees())) {
      model.getStaffEmployees().clear();
    } else {
      dto.getStaffEmployees().forEach(staff -> {
        staffEmployeeHelper.toModel(model.myStaff(staff.getId()), staff);
      });
    }

    model.setIsChangeBranchOffice(dto.getIsChangeBranchOffice());
    model.setIsChangeCommunicationDetails(dto.getIsChangeCommunicationDetails());
    model.setIsChangeLegalStatus(dto.getIsChangeLegalStatus());
    model.setIsChangeMainOffice(dto.getIsChangeMainOffice());
    model.setIsChangeSaftyguards(dto.getIsChangeSaftyguards());
    model.setIsChangeVehicleDetails(dto.getIsChangeVehicleDetails());
    model.setIsChangeWorkshopOffice(dto.getIsChangeWorkshopOffice());
    model.setIsChangetestInstrument(dto.getIsChangetestInstrument());
    model.setIsStaffDetailsChange(dto.getIsStaffDetailsChange());
    return dto;
  }


  public AgencyLicenseModification blankModel(Object portalUser) {
    AgencyLicenseModification ownerDetail = new AgencyLicenseModification();
    LoginUserUtil.getLoginUser();
    ownerDetail.getForm().setUser((PortalUser) portalUser);
    return ownerDetail;
  }

  public AgencyLicenseModificationDTO fromModel(AgencyLicenseModification model) {
    AgencyLicenseModificationDTO dto = new AgencyLicenseModificationDTO();
    HelperUtil.toDTO(dto, model);
    dto.setBranchAddress(addressHelper.fromModel(model.getForm().getAddressFor(AddressType.BRANCHOFFICE)));
    dto.setOfficeAddress(addressHelper.fromModel(model.getForm().getAddressFor(AddressType.OFFICE)));
    dto.setWorkshopAddress(addressHelper.leAddressDTO(model.getForm().getAddressFor(AddressType.WORKSHOP)));
    dto.setId(model.getApplicationId());
    dto.setType(model.getApplicationType().getType());
    dto.setApplicationName(model.getApplicationType().getName());
    dto.setBranchEmail(model.getBranchEmail());
    dto.setMainOfficeContactNo(model.getMainOfficeContactNo());
    dto.setBranchContactNo(model.getBranchContactNo());
    dto.setMainOfficeEmail(model.getMainOfficeEmail());
    dto.setBranchContactNo(model.getBranchContactNo());
    dto.setCommunicationFacilityChangeRequest(model.getCommunicationFacilityChangeRequest());
    dto.setVehiclePossessionDetail(model.getVehiclePossessionDetail());
    dto.setAgencyLegalStatus(model.getAgencyLegalStatus());
    dto.setSafetyGadgets(leSafetyGadgetHelper.fromModel(model.getSafetyGadgets()));
    dto.setTestingInstruments(leTestingInstrumentHelper.fromModel(model.getTestingInstruments()));
    dto.setStaffEmployees(staffEmployeeHelper.fromModel(model.getStaffEmployees()));
    dto.setAttachments(attachmentHelper.fromModel(model.getForm().getAttachments()));
    dto.setIsChangeBranchOffice(model.getIsChangeBranchOffice());
    dto.setIsChangeCommunicationDetails(model.getIsChangeCommunicationDetails());
    dto.setIsChangeLegalStatus(model.getIsChangeLegalStatus());
    dto.setIsChangeMainOffice(model.getIsChangeMainOffice());
    dto.setIsChangeSaftyguards(model.getIsChangeSaftyguards());
    dto.setIsChangeVehicleDetails(model.getIsChangeVehicleDetails());
    dto.setIsChangeWorkshopOffice(model.getIsChangeWorkshopOffice());
    dto.setIsChangetestInstrument(model.getIsChangetestInstrument());
    dto.setIsStaffDetailsChange(model.getIsStaffDetailsChange());
    return dto;
  }
  public NGAgencyLicenseModificationDTO toNGDTO(AgencyLicenseModification model) {
    NGAgencyLicenseModificationDTO dto = new NGAgencyLicenseModificationDTO();

    HelperUtil.getNGDTO(model, dto);
    dto.setWorkshopAddress(addressHelper.toLINGDTO(model.getForm().getAddressFor(AddressType.WORKSHOP)));
    dto.setBranchAddress(addressHelper.toNGDTO(model.getForm().getAddressFor(AddressType.BRANCHOFFICE)));
    dto.setMainOfficeAddress(addressHelper.toNGDTO(model.getForm().getAddressFor(AddressType.OFFICE)));
    dto.setBranchEmail(model.getBranchEmail());
    dto.setBranchContactNo(model.getBranchContactNo());
    dto.setMainOfficeEmail(model.getMainOfficeEmail());
    dto.setMainOfficeContactNo(model.getMainOfficeContactNo());
    dto.setCommunicationFacilityChangeRequestDetails(model.getCommunicationFacilityChangeRequest());
    dto.setVehiclePossessionDetail(model.getVehiclePossessionDetail());
    dto.setLegalStatusChangeRequest(model.getAgencyLegalStatus());
    dto.setSafetyGadgets(leSafetyGadgetHelper.fromModelNG(model.getSafetyGadgets()));
    dto.setTestingInstruments(leTestingInstrumentHelper.fromModelNG(model.getTestingInstruments()));
    dto.setStaffEmployees(staffEmployeeHelper.fromModelNG(model.getStaffEmployees()));
    dto.setAttachments(attachmentHelper.fromModelNG(model.getForm().getAttachments()));

    return dto;
  }


}