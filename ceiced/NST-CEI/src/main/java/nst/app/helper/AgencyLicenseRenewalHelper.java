package nst.app.helper;

import nst.app.common.HelperUtil;
import nst.app.dto.AgencyLicenseRenewalDTO;
import nst.app.dto.newgen.NGAgencyLicenseRenewalDTO;
import nst.app.enums.AddressType;
import nst.app.model.AgencyDetail;
import nst.app.model.PortalUser;
import nst.app.model.forms.le.AgencyLicenseRenewal;
import nst.app.repo.AgencyDetailRepository;
import nst.common.base.BaseHelper;
import nst.util.AllUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AgencyLicenseRenewalHelper extends
    BaseHelper<AgencyLicenseRenewal, AgencyLicenseRenewalDTO> {

  @Autowired
  AddressHelper addressHelper;

  @Autowired
  AttachmentHelper attachmentHelper;

  @Autowired
  AgencyDetailRepository agencyDetailRepository;

  public AgencyLicenseRenewal toModel(AgencyLicenseRenewalDTO agencyLicenseRenewalDTO) {
    AgencyLicenseRenewal agencyLicenseRenewal = new AgencyLicenseRenewal();
    return toModel(agencyLicenseRenewal, agencyLicenseRenewalDTO);
  }

  @Override
  public AgencyLicenseRenewal toModel(AgencyLicenseRenewal model,
      AgencyLicenseRenewalDTO dto) {
    HelperUtil.toModel(model.getForm(), dto);

    model.setAgencyType(dto.getAgencyType());
    model.setAgencyAuthNo(dto.getAgencyAuthNo());
    model.setIssueDate(AllUtil.toUIDate(dto.getIssueDate()));
    model.setExpiryDate(AllUtil.toUIDate(dto.getExpiryDate()));
    model.setNameOfAgency(dto.getNameOfAgency());
    model.setEmail(dto.getEmail());
    model.setContactNo(dto.getContactNo());
    model.setWebsiteUrl(dto.getWebsiteUrl());
    model.setIsChangeAnyDetails(HelperUtil.toBoolean(dto.getIsChangeAnyDetails()));
    model.getForm().setApplicantName(dto.getApplicantName());
    addressHelper.manageAddress(model.getForm(), dto.getOfficeAddress());

    return model;
  }

  public AgencyLicenseRenewal blankModel(Object o) {
    AgencyLicenseRenewal agencyLicenseRenewal = new AgencyLicenseRenewal();
    PortalUser portalUser = (PortalUser) o;
    AgencyDetail agencyDetail = agencyDetailRepository.getByUser(portalUser);
    if (agencyDetail != null) {
      agencyLicenseRenewal.setExpiryDate(agencyDetail.getExpiryDate());
      agencyLicenseRenewal.setAgencyAuthNo(agencyDetail.getAgencyAuthNo());
      agencyLicenseRenewal.setIssueDate(agencyDetail.getIssueDate());
      agencyLicenseRenewal.setAgencyType(agencyDetail.getAgencyType());
    }
    agencyLicenseRenewal.getForm().setUser(portalUser);
    return agencyLicenseRenewal;
  }

  public AgencyLicenseRenewalDTO fromModel(AgencyLicenseRenewal model) {
    AgencyLicenseRenewalDTO dto = new AgencyLicenseRenewalDTO();
    dto.setId(model.getApplicationId());
    dto.setType(model.getApplicationType().getType());
    dto.setApplicationName(model.getApplicationType().getName());

    dto.setAgencyType(model.getAgencyType());
    dto.setAgencyAuthNo(model.getAgencyAuthNo());
    dto.setIssueDate(AllUtil.formatUIDate(model.getIssueDate()));
    dto.setExpiryDate(AllUtil.formatUIDate(model.getExpiryDate()));
    dto.setNameOfAgency(model.getNameOfAgency());
    dto.setEmail(model.getEmail());
    dto.setContactNo(model.getContactNo());
    dto.setWebsiteUrl(model.getWebsiteUrl());
    dto.setIsChangeAnyDetails(HelperUtil.fromBoolean(model.getIsChangeAnyDetails()));
    dto.setOfficeAddress(
        addressHelper.fromModel(model.getForm().getAddressFor(AddressType.OFFICE)));
    dto.setAttachments(attachmentHelper.fromModel(model.getForm().getAttachments()));

    return dto;
  }

  public NGAgencyLicenseRenewalDTO toNGDTO(AgencyLicenseRenewal model) {
    NGAgencyLicenseRenewalDTO dto = new NGAgencyLicenseRenewalDTO();

    HelperUtil.getNGDTO(model, dto);
    dto.setAgencyType(model.getAgencyType());
    dto.setAgencyAuthNo(model.getAgencyAuthNo());
    dto.setIssueDate(AllUtil.formatNGDate(model.getIssueDate()));
    dto.setExpiryDate(AllUtil.formatNGDate(model.getExpiryDate()));
    dto.setNameOfAgency(model.getNameOfAgency());
    dto.setEmail(model.getEmail());
    dto.setContactNo(model.getContactNo());
    dto.setWebsiteUrl(model.getWebsiteUrl());
    dto.setIsChangeAnyDetails(HelperUtil.fromBoolean(model.getIsChangeAnyDetails()));
    dto.setOfficeAddress(
        addressHelper.toNGDTO(model.getForm().getAddressFor(AddressType.OFFICE)));
    dto.setAttachments(attachmentHelper.fromModelNG(model.getForm().getAttachments()));

    return dto;
  }

}