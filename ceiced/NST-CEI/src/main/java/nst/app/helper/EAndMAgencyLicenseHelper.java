package nst.app.helper;

import nst.app.common.HelperUtil;
import nst.app.dto.EAndMAgencyLicenseDTO;
import nst.app.dto.newgen.le.NGEAndMAgencyLicenseDTO;
import nst.app.enums.AddressType;
import nst.app.enums.LiftEscalatorType;
import nst.app.helper.le.AgencyLegalStatusDetailsHelper;
import nst.app.helper.le.LESafetyGadgetHelper;
import nst.app.helper.le.LETestingInstrumentHelper;
import nst.app.model.PortalUser;
import nst.app.model.forms.le.EAndMAgencyLicense;
import nst.common.base.BaseHelper;
import nst.util.AllUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class EAndMAgencyLicenseHelper extends
        BaseHelper<EAndMAgencyLicense, EAndMAgencyLicenseDTO> {

  @Autowired AttachmentHelper attachmentHelper;

  @Autowired AddressHelper addressHelper;

  @Autowired
  LETestingInstrumentHelper letestingInstrumentHelper;

  @Autowired
  LESafetyGadgetHelper lesafetyGadgetHelper;

  @Autowired
  AgencyLegalStatusDetailsHelper agencyLegalStatusDetailsHelper;

  @Autowired
  StaffEmployeeHelper staffEmployeeHelper;


  public EAndMAgencyLicense toModel(EAndMAgencyLicenseDTO EAndMAgencyLicenseDTO) {
    EAndMAgencyLicense portalUser = new EAndMAgencyLicense();
    return toModel(portalUser, EAndMAgencyLicenseDTO);
  }

  @Override
  public EAndMAgencyLicense toModel(EAndMAgencyLicense model,
                                    EAndMAgencyLicenseDTO dto) {
    HelperUtil.toModel(model.getForm(), dto);
    model.setLiftEscalatorType(LiftEscalatorType.fromType(dto.getLiftEscalatorType()));
    model.setAgencyName(dto.getAgencyName());
    model.setAgencyLegalStatus(dto.getAgencyLegalStatus());
    model.setEstablishmentYear(dto.getEstablishmentYear());
    model.getForm().setApplicantName(dto.getApplicantName());
    if (CollectionUtils.isEmpty(dto.getStaffEmployees())) {
      model.getStaffEmployees().clear();
    } else {
      dto.getStaffEmployees().forEach(staff -> {
        staffEmployeeHelper.toModel(model.myStaff(staff.getId()), staff);
      });
    }
    if (CollectionUtils.isEmpty(dto.getAgencyLegalStatusDetails())) {
    model.getAgencyLegalStatusDetails().clear();
  } else {
      dto.getAgencyLegalStatusDetails().forEach(testIn -> {
        agencyLegalStatusDetailsHelper.toModel(model.getAgencyLegalStatusDetails(testIn.getId()), testIn);
      });
    }

    addressHelper.manageBranchAddress(model.getForm(), dto.getBranchAddress());
    addressHelper.manageBusinessAddress(model.getForm(), dto.getBusinessAddress());
    model.setContactEmail(dto.getContactEmail());
    model.setContactNo(dto.getContactNo());
    model.setIsAuthorizationCertificateIssued(dto.getIsAuthorizationCertificateIssued());
    model.setAuthorizationNo(dto.getAuthorizationNo());
    model.setAuthorizationCertificateDate(AllUtil.toUIDate(dto.getAuthorizationCertificateDate()));
    model.setRegistrationNumberElectricalContractor(dto.getRegistrationNumberElectricalContractor());
    model.setBankName(dto.getBankName());
    model.setBranchName(dto.getBranchName());
    model.setIssueDate(AllUtil.toUIDate(dto.getIssueDate()));
    model.setAmount(dto.getAmount());
    model.setBankAddress(dto.getBankAddress());
    /* Part 1 end*/
    /* Part 2 start*/
    model.setManufacturingUnitDetails(dto.getManufacturingUnitDetails());
    addressHelper.manageLEAddress(model.getForm(), dto.getWorkshopAddress());
    model.setIsRegistrationGranted(HelperUtil.toBoolean(dto.getIsRegistrationGranted()));
    model.setOccupacyRights(dto.getOccupacyRights());
    model.setElectricityBillNo(dto.getElectricityBillNo());
    model.setCommunicationFacilitiesDetails(dto.getCommunicationFacilitiesDetails());

    if (CollectionUtils.isEmpty(dto.getTestingInstruments())) {
    model.getLETestingInstruments().clear();
  } else {
      dto.getTestingInstruments().forEach(testIn -> {
        letestingInstrumentHelper.toModel(model.getTestingInstruments(testIn.getId()), testIn);
      });
    }


    if (CollectionUtils.isEmpty(dto.getSafetyGadgets())) {
    model.getLESafetyGadgets().clear();
  } else {
      dto.getSafetyGadgets().forEach(testIn -> {
        lesafetyGadgetHelper.toModel(model.getSafetyGadgets(testIn.getId()), testIn);
      });
    }
    //private List<LESafetyGadget> LESafetyGadgets;
    model.setVehiclePossession(dto.getVehiclePossession());
    model.setRemarks(dto.getRemarks());
    /* Part 2 end*/
    return model;
  }

  public EAndMAgencyLicense blankModel(Object portalUser) {
    EAndMAgencyLicense model = new EAndMAgencyLicense();
    model.getForm().setUser((PortalUser) portalUser);
    return model;
  }

  public EAndMAgencyLicenseDTO fromModel(EAndMAgencyLicense model) {
    EAndMAgencyLicenseDTO dto = new EAndMAgencyLicenseDTO();
    HelperUtil.toDTO(dto, model);

    dto.setLiftEscalatorType(HelperUtil.toEnumName(model.getLiftEscalatorType()));
    dto.setAgencyName(model.getAgencyName());
    dto.setAgencyLegalStatus(model.getAgencyLegalStatus());
    dto.setEstablishmentYear(model.getEstablishmentYear());
    dto.setAgencyLegalStatusDetails(agencyLegalStatusDetailsHelper.fromModel(model.getAgencyLegalStatusDetails()));
    dto.setBusinessAddress(addressHelper.fromBMModel(model.getForm().getAddressFor(AddressType.BUSINESS)));
    dto.setBranchAddress(addressHelper.fromAMModel(model.getForm().getAddressesFor(AddressType.BRANCHOFFICE)));
    dto.setContactEmail(model.getContactEmail());
    dto.setContactNo(model.getContactNo());
    dto.setIsAuthorizationCertificateIssued(model.getIsAuthorizationCertificateIssued());
    dto.setAuthorizationNo(model.getAuthorizationNo());
    dto.setAuthorizationCertificateDate(AllUtil.formatUIDate(model.getAuthorizationCertificateDate()));
    dto.setRegistrationNumberElectricalContractor(model.getRegistrationNumberElectricalContractor());
    dto.setBankName(model.getBankName());
    dto.setBranchName(model.getBranchName());
    dto.setIssueDate(AllUtil.formatUIDate(model.getIssueDate()));
    dto.setAmount(model.getAmount());
    dto.setBankAddress(model.getBankAddress());
    /* Part 1 end*/

    /* Part 2 start*/
    dto.setManufacturingUnitDetails(model.getManufacturingUnitDetails());
    dto.setWorkshopAddress(addressHelper.leAddressDTO(model.getForm().getAddressFor(AddressType.WORKSHOP)));

    dto.setIsRegistrationGranted(HelperUtil.fromBoolean(model.getIsRegistrationGranted()));
    dto.setOccupacyRights(model.getOccupacyRights());
    dto.setElectricityBillNo(model.getElectricityBillNo());
    dto.setCommunicationFacilitiesDetails(model.getCommunicationFacilitiesDetails());

    dto.setTestingInstruments(letestingInstrumentHelper.fromModel(model.getLETestingInstruments()));
    dto.setSafetyGadgets(lesafetyGadgetHelper.fromModel(model.getLESafetyGadgets()));
    dto.setVehiclePossession(model.getVehiclePossession());
    dto.setRemarks(model.getRemarks());
    dto.setStaffEmployees(staffEmployeeHelper.fromModel(model.getStaffEmployees()));
    /* Part 2 end*/

    dto.setAttachments(attachmentHelper.fromModel(model.getForm().getAttachments()));

    return dto;
  }

  public NGEAndMAgencyLicenseDTO toNGDTO(EAndMAgencyLicense model) {
    NGEAndMAgencyLicenseDTO dto = new NGEAndMAgencyLicenseDTO();
    HelperUtil.getNGDTO(model, dto);

    dto.setLiftEscalatorType(HelperUtil.toEnumName(model.getLiftEscalatorType()));
    dto.setAgencyName(model.getAgencyName());
    dto.setAgencyLegalStatus(model.getAgencyLegalStatus());
    dto.setEstablishmentYear(model.getEstablishmentYear());

    dto.setAgencyLegalStatusDetails(agencyLegalStatusDetailsHelper.fromModelNG(model.getAgencyLegalStatusDetails()));

    dto.setBusinessAddress(addressHelper.toBMGDTO(model.getForm().getAddressFor(AddressType.BUSINESS)));

    dto.setBranchAddress(addressHelper.toBranchNGDTO(model.getForm().getAddressesFor(AddressType.BRANCHOFFICE)));

    dto.setContactEmail(model.getContactEmail());
    dto.setContactNo(model.getContactNo());
    dto.setIsAuthorizationCertificateIssued(model.getIsAuthorizationCertificateIssued());
    dto.setAuthorizationNo(model.getAuthorizationNo());
    dto.setAuthorizationCertificateDate(AllUtil.formatNGDate(model.getAuthorizationCertificateDate()));
    dto.setRegistrationNumberElectricalContractor(model.getRegistrationNumberElectricalContractor());
    dto.setBankName(model.getBankName());
    dto.setBranchName(model.getBranchName());
    dto.setIssueDate(model.getIssueDate());
    dto.setAmount(model.getAmount());
    dto.setBankAddress(model.getBankAddress());

    /* Part 1 end*/


    /* Part 2 start*/
    dto.setManufacturingUnitDetails(model.getManufacturingUnitDetails());
    dto.setWorkshopAddress(addressHelper.toLENGDTO(model.getForm().getAddressFor(AddressType.WORKSHOP)));

    dto.setIsRegistrationGranted(model.getIsRegistrationGranted());
    dto.setOccupacyRights(model.getOccupacyRights());
    dto.setElectricityBillNo(model.getElectricityBillNo());
    dto.setCommunicationFacilitiesDetails(model.getCommunicationFacilitiesDetails());

    dto.setTestingInstruments(letestingInstrumentHelper.fromModelNG(model.getLETestingInstruments()));
    dto.setSafetyGadgets(lesafetyGadgetHelper.fromModelNG(model.getLESafetyGadgets()));
    dto.setVehiclePossession(model.getVehiclePossession());
    dto.setRemarks(model.getRemarks());

    /* Part 2 end*/
    dto.setStaffEmployees(staffEmployeeHelper.fromModelNG(model.getStaffEmployees()));
    dto.setAttachments(attachmentHelper.fromModelNG(model.getForm().getAttachments()));

    return dto;

  }
}