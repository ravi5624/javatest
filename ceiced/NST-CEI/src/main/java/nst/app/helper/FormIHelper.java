package nst.app.helper;

import nst.app.common.HelperUtil;
import nst.app.dto.FormIDTO;
import nst.app.dto.newgen.NGFormIDTO;
import nst.app.enums.AddressType;
import nst.app.model.PortalUser;
import nst.app.model.forms.lb.FormI;
import nst.common.base.BaseHelper;
import nst.util.AllUtil;
import nst.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class FormIHelper extends BaseHelper<FormI, FormIDTO> {

  @Autowired
  FormIEmployerHelper formIEmployerHelper;

  @Autowired
  AttachmentHelper attachmentHelper;

  @Autowired
  AddressHelper addressHelper;

  public FormI toModel(FormIDTO FormIDTO) {
    FormI portalUser = new FormI();
    return toModel(portalUser, FormIDTO);
  }

  @Override
  public FormI toModel(FormI model, FormIDTO dto) {
    HelperUtil.toModel(model.getForm(), dto);
    model.setTechnicalContractorName(dto.getTechnicalContractorName());
    model.setIssueDate(AllUtil.toUIDate(dto.getIssueDate()));
    model.setContractorLicNo(dto.getContractorLicNo());
    addressHelper.manageAddress(model.getForm(), dto.getOfficeAddress());

    model.getForm().setApplicantName(dto.getApplicantName());

    if (CollectionUtils.isEmpty(dto.getEmployer())) {
      model.getEmployer().clear();
    } else {
      dto.getEmployer().forEach(org -> {
        formIEmployerHelper.toModel(model.myEmp(org.getId()), org);
      });
    }
    return model;
  }

  public FormI blankModel(Object portalUser) {
    FormI ownerDetail = new FormI();
    LoginUserUtil.getLoginUser();
    ownerDetail.getForm().setUser((PortalUser) portalUser);
    return ownerDetail;
  }

  public FormIDTO fromModel(FormI model) {
    FormIDTO dto = new FormIDTO();
    HelperUtil.toDTO(dto, model);
    dto.setTechnicalContractorName(model.getTechnicalContractorName());
    dto.setIssueDate(AllUtil.formatUIDate(model.getIssueDate()));
    dto.setEmployer(formIEmployerHelper.fromModel(model.getEmployer()));
    dto.setContractorLicNo(model.getContractorLicNo());
    dto.setOfficeAddress(addressHelper.fromModel(model.getForm().getAddressFor(AddressType.OFFICE)));
    dto.setAttachments(attachmentHelper.fromModel(model.getForm().getAttachments()));
      return dto;
  }

  public FormIDTO cloneDTO(FormI model, FormIDTO dto) {
    dto.setTechnicalContractorName(model.getTechnicalContractorName());
    dto.setIssueDate(AllUtil.formatUIDate(model.getIssueDate()));
    dto.setEmployer(formIEmployerHelper.fromModel(model.getEmployer()));
    dto.setContractorLicNo(model.getContractorLicNo());
    dto.setOfficeAddress(addressHelper.fromModel(model.getForm().getAddressFor(AddressType.OFFICE)));

    dto.getEmployer().forEach(d -> {
      d.setId(null);
      d.setIsNew(HelperUtil.fromBoolean(Boolean.FALSE));
    });
    if(dto.getOfficeAddress() != null){
      dto.getOfficeAddress().setId(null);
    }
    return dto;
  }

  public NGFormIDTO toNGDTO(FormI model) {
    NGFormIDTO dto = new NGFormIDTO();

    HelperUtil.getNGDTO(model, dto);

    dto.setTechnicalContractorName(model.getTechnicalContractorName());
    dto.setIssueDate(AllUtil.formatNGDate(model.getIssueDate()));
    dto.setEmployer(formIEmployerHelper.fromModelNG(model.getEmployer()));
    dto.setContractorLicNo(model.getContractorLicNo());
    dto.setOfficeAddress(addressHelper.toNGDTO(model.getForm().getAddressFor(AddressType.OFFICE)));
    dto.setAttachments(attachmentHelper.fromModelNG(model.getForm().getAttachments()));
      return dto;
  }
}