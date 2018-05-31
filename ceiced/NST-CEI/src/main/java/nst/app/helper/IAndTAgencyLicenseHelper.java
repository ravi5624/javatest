package nst.app.helper;

import nst.app.common.HelperUtil;
import nst.app.dto.IAndTAgencyLicenseDTO;
import nst.app.dto.newgen.NGIAndTAgencyLicenseDTO;
import nst.app.enums.AddressType;
import nst.app.enums.LiftEscalatorType;
import nst.app.helper.le.AgencyLegalStatusDetailsHelper;
import nst.app.helper.le.LESafetyGadgetHelper;
import nst.app.helper.le.LETestingInstrumentHelper;
import nst.app.model.PortalUser;
import nst.app.model.forms.le.IAndTAgencyLicense;
import nst.common.base.BaseHelper;
import nst.util.AllUtil;
import nst.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class IAndTAgencyLicenseHelper extends
    BaseHelper<IAndTAgencyLicense, IAndTAgencyLicenseDTO> {

  @Autowired  AttachmentHelper attachmentHelper;

  @Autowired AddressHelper addressHelper;

  @Autowired LETestingInstrumentHelper letestingInstrumentHelper;

  @Autowired  LESafetyGadgetHelper lesafetyGadgetHelper;

  @Autowired AgencyLegalStatusDetailsHelper agencyLegalStatusDetailsHelper;

  @Autowired StaffEmployeeHelper staffEmployeeHelper;

  public IAndTAgencyLicense toModel(IAndTAgencyLicenseDTO IAndTAgencyLicenseDTO) {
    IAndTAgencyLicense portalUser = new IAndTAgencyLicense();
    return toModel(portalUser, IAndTAgencyLicenseDTO);
  }

  @Override
  public IAndTAgencyLicense toModel(IAndTAgencyLicense model,
      IAndTAgencyLicenseDTO dto) {

    HelperUtil.toModel(model.getForm(), dto);
    model.setLiftEscalatorType(LiftEscalatorType.fromType(dto.getLiftEscalatorType()));
    model.setAgencyName(dto.getAgencyName());
    model.setAgencyLegalStatus(dto.getAgencyLegalStatus());
    model.setEstablishmentYear(dto.getEstablishmentYear());
    model.getForm().setApplicantName(dto.getApplicantName());

    if (CollectionUtils.isEmpty(dto.getAgencyLegalStatusDetails())) {
      model.getAgencyLegalStatusDetails().clear();
    } else {
      dto.getAgencyLegalStatusDetails().forEach(testIn -> {
        agencyLegalStatusDetailsHelper.toModel(model.getAgencyLegalStatusDetails(testIn.getId()), testIn);
      });
    }


    addressHelper.manageBusinessAddress(model.getForm(), dto.getBusinessAddress());
    /*model.setBusinessEmail(dto.getBusinessEmail());
    model.setBusinessContactNo(dto.getBusinessContactNo());
    model.setBusinessWebsite(dto.getBusinessWebsite());*/
    addressHelper.manageBranchAddress(model.getForm(), dto.getBranchAddress());
    /*model.setBranchEmail(dto.getBranchEmail());
    model.setBranchContactNo(dto.getBranchContactNo());
    model.setBranchWebsite(dto.getBranchWebsite());*/
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

    if (CollectionUtils.isEmpty(dto.getStaffEmployees())) {
      model.getStaffEmployees().clear();
    } else {
      dto.getStaffEmployees().forEach(staff -> {
        staffEmployeeHelper.toModel(model.myStaff(staff.getId()), staff);
      });
    }
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
        lesafetyGadgetHelper.toModel(model.getLESafetyGadgets(testIn.getId()), testIn);

      });
    }


    model.setRemarks(dto.getRemarks());

    /* Part 2 end*/

    return model;
  }

  public IAndTAgencyLicense blankModel(Object portalUser) {
    IAndTAgencyLicense ownerDetail = new IAndTAgencyLicense();
    LoginUserUtil.getLoginUser();
    ownerDetail.getForm().setUser((PortalUser) portalUser);
    return ownerDetail;
  }

  public IAndTAgencyLicenseDTO fromModel(IAndTAgencyLicense model) {
    IAndTAgencyLicenseDTO dto = new IAndTAgencyLicenseDTO();
    HelperUtil.toDTO(dto, model);

    dto.setLiftEscalatorType(HelperUtil.toEnumType(model.getLiftEscalatorType()));
    dto.setAgencyName(model.getAgencyName());
    dto.setAgencyLegalStatus(model.getAgencyLegalStatus());
    dto.setEstablishmentYear(model.getEstablishmentYear());

    dto.setAgencyLegalStatusDetails(agencyLegalStatusDetailsHelper.fromModel(model.getAgencyLegalStatusDetails()));

    dto.setBusinessAddress(addressHelper.fromBMModel(model.getForm().getAddressFor(AddressType.BUSINESS)));

    /*dto.setBusinessEmail(model.getBusinessEmail());
    dto.setBusinessContactNo(model.getBusinessContactNo());
    dto.setBusinessWebsite(model.getBusinessWebsite());*/

    dto.setBranchAddress(addressHelper.fromAMModel(model.getForm().getAddressesFor(AddressType.BRANCHOFFICE)));

    /*dto.setBranchEmail(model.getBranchEmail());
    dto.setBranchContactNo(model.getBranchContactNo());
    dto.setBranchWebsite(model.getBranchWebsite());*/
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
    dto.setStaffEmployees(staffEmployeeHelper.fromModel(model.getStaffEmployees()));

    /* Part 1 end*/


    /* Part 2 start*/
    dto.setCommunicationFacilitiesDetails(model.getCommunicationFacilitiesDetails());


    dto.setTestingInstruments(letestingInstrumentHelper.fromModel(model.getLETestingInstruments()));
    dto.setSafetyGadgets(lesafetyGadgetHelper.fromModel(model.getLESafetyGadgets()));
    dto.setRemarks(model.getRemarks());

    /* Part 2 end*/

    dto.setAttachments(attachmentHelper.fromModel(model.getForm().getAttachments()));

    return dto;

  }

  public NGIAndTAgencyLicenseDTO toNGDTO(IAndTAgencyLicense model) {
    NGIAndTAgencyLicenseDTO dto = new NGIAndTAgencyLicenseDTO();
    HelperUtil.getNGDTO(model, dto);

    dto.setLiftEscalatorType(HelperUtil.toEnumType(model.getLiftEscalatorType()));
    dto.setAgencyName(model.getAgencyName());
    dto.setAgencyLegalStatus(model.getAgencyLegalStatus());
    dto.setEstablishmentYear(model.getEstablishmentYear());


    dto.setAgencyLegalStatusDetails(agencyLegalStatusDetailsHelper.fromModelNG(model.getAgencyLegalStatusDetails()));


    dto.setBusinessAddress(addressHelper.toBMGDTO(model.getForm().getAddressFor(AddressType.BUSINESS)));

    /*dto.setBusinessEmail(model.getBusinessEmail());
    dto.setBusinessContactNo(model.getBusinessContactNo());
    dto.setBusinessWebsite(model.getBusinessWebsite());*/

    dto.setBranchAddress(addressHelper.toBranchNGDTO(model.getForm().getAddressesFor(AddressType.BRANCHOFFICE)));

    /*dto.setBranchEmail(model.getBranchEmail());
    dto.setBranchContactNo(model.getBranchContactNo());
    dto.setBranchWebsite(model.getBranchWebsite());*/
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
    dto.setCommunicationFacilitiesDetails(model.getCommunicationFacilitiesDetails());

    dto.setTestingInstruments(letestingInstrumentHelper.fromModelNG(model.getLETestingInstruments()));
    dto.setSafetyGadgets(lesafetyGadgetHelper.fromModelNG(model.getLESafetyGadgets()));

    dto.setRemarks(model.getRemarks());

    /* Part 2 end*/

    dto.setStaffEmployees(staffEmployeeHelper.fromModelNG(model.getStaffEmployees()));
    dto.setAttachments(attachmentHelper.fromModelNG(model.getForm().getAttachments()));

    return dto;

  }
}