package nst.app.helper;

import nst.app.common.HelperUtil;
import nst.app.dto.ReportAccidentDTO;
import nst.app.dto.newgen.NGReportAccidentDTO;
import nst.app.enums.AddressType;
import nst.app.model.PortalUser;
import nst.app.model.forms.le.ReportAccident;
import nst.common.base.BaseHelper;
import nst.util.AllUtil;
import nst.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class ReportAccidentHelper extends
    BaseHelper<ReportAccident, ReportAccidentDTO> {

  @Autowired AccidentVictimHelper accidentVictimHelper;
  @Autowired AddressHelper addressHelper;
  @Autowired AttachmentHelper attachmentHelper;


  public ReportAccident toModel(ReportAccidentDTO ReportAccidentDTO) {
    ReportAccident portalUser = new ReportAccident();
    return toModel(portalUser, ReportAccidentDTO);
  }

  @Override
  public ReportAccident toModel(ReportAccident model,
      ReportAccidentDTO dto) {
    HelperUtil.toModel(model.getForm(), dto);
    model.getForm().setApplicantName(dto.getApplicantName());
    model.setAccidentFor(dto.getAccidentFor());
    model.setAccidentDate(AllUtil.toUIDate(dto.getAccidentDate()));
    model.setAccidentTime(dto.getAccidentTime());
    model.setOwnerName(dto.getOwnerName());
    model.setAccidentType(dto.getAccidentType());
    model.setIsPostmortemperformed(HelperUtil.toBoolean(dto.getIsPostmortemperformed()));
    model.setIsPersonAuthorized(HelperUtil.toBoolean(dto.getIsPersonAuthorized()));
    model.setPersonDesgination(dto.getPersonDesgination());
    model.setJobDescription(dto.getJobDescription());
    model.setIsPersonAllowed(HelperUtil.toBoolean(dto.getIsPersonAllowed()));
    model.setLicenceNo(dto.getLicenceNo());
    model.setMaintainerContactNo(dto.getMaintainerContactNo());
    model.setMaintainerWebsite(dto.getMaintainerWebsite());
    model.setMaintainerEmail(dto.getMaintainerEmail());
    model.setInjuriesDescription(dto.getInjuriesDescription());
    model.setDetailedAccidentCauses(dto.getDetailedAccidentCauses());
    model.setTakenAction(dto.getTakenAction());
    model.setIsPoliceConcerned(HelperUtil.toBoolean(dto.getIsPoliceConcerned()));
    model.setPoliceConcernedDetails(dto.getPoliceConcernedDetails());
    model.setAccidentEvidene(dto.getAccidentEvidene());
    model.setAssistingDescription(dto.getAssistingDescription());
    model.setWitnessDescription(dto.getWitnessDescription());
    model.setRemarks(dto.getRemarks());
    model.setIsAuthorized(HelperUtil.toBoolean(dto.getIsAuthorized()));
    model.setAgencyName(dto.getAgencyName());
    addressHelper.manageAddress(model.getForm(), dto.getBusinessAddress());
    addressHelper.manageAddress(model.getForm(), dto.getAccidentPlace());
    //addressHelper.manageAddress(model.getForm(), dto.getParmanentAddress());
    if (CollectionUtils.isEmpty(dto.getVictims())) {
      model.getVictims().clear();
    } else {
      dto.getVictims().forEach(exp -> {
        accidentVictimHelper.toModel(model.myReport(exp.getId()), exp);
      });
    }

    return model;

  }

  public ReportAccident blankModel(Object portalUser) {
    ReportAccident ownerDetail = new ReportAccident();
    LoginUserUtil.getLoginUser();
    ownerDetail.getForm().setUser((PortalUser) portalUser);
    return ownerDetail;
  }

  public ReportAccidentDTO fromModel(ReportAccident model) {
    ReportAccidentDTO dto = new ReportAccidentDTO();
    HelperUtil.toDTO(dto, model);
    dto.setAccidentFor(model.getAccidentFor());
    dto.setAccidentDate(AllUtil.formatUIDate(model.getAccidentDate()));
    dto.setAccidentTime(model.getAccidentTime());
    dto.setOwnerName(model.getOwnerName());
    dto.setAccidentType(model.getAccidentType());
    dto.setIsPostmortemperformed(HelperUtil.fromBoolean(model.getIsPostmortemperformed()));
    dto.setIsPersonAuthorized(HelperUtil.fromBoolean(model.getIsPersonAuthorized()));
    dto.setPersonDesgination(model.getPersonDesgination());
    dto.setJobDescription(model.getJobDescription());
    dto.setIsPersonAllowed(HelperUtil.fromBoolean(model.getIsPersonAllowed()));
    dto.setLicenceNo(model.getLicenceNo());
    dto.setMaintainerContactNo(model.getMaintainerContactNo());
    dto.setMaintainerWebsite(model.getMaintainerWebsite());
    dto.setMaintainerEmail(model.getMaintainerEmail());
    dto.setInjuriesDescription(model.getInjuriesDescription());
    dto.setDetailedAccidentCauses(model.getDetailedAccidentCauses());
    dto.setTakenAction(model.getTakenAction());
    dto.setIsPoliceConcerned(HelperUtil.fromBoolean(model.getIsPoliceConcerned()));
    dto.setPoliceConcernedDetails(model.getPoliceConcernedDetails());
    dto.setAccidentEvidene(model.getAccidentEvidene());
    dto.setAssistingDescription(model.getAssistingDescription());
    dto.setWitnessDescription(model.getWitnessDescription());
    dto.setRemarks(model.getRemarks());
    dto.setAgencyName(model.getAgencyName());
    dto.setIsAuthorized(HelperUtil.fromBoolean(model.getIsAuthorized()));

//    dto.setAgencyAddress(addressHelper.fromModel(model.getForm().getAddressFor(AddressType.AGENCY)));
    dto.setBusinessAddress(addressHelper.fromModel(model.getForm().getAddressFor(AddressType.BUSINESS)));
    dto.setAccidentPlace(addressHelper.fromModel(model.getForm().getAddressFor(AddressType.ACCIDENT)));
   // dto.setParmanentAddress(addressHelper.fromModel(model.getForm().getAddressFor(AddressType.PERMANENT)));
    dto.setVictims(accidentVictimHelper.fromModel(model.getVictims()));
    dto.setAttachments(attachmentHelper.fromModel(model.getForm().getAttachments()));
    return dto;
  }

  @Override
  public NGReportAccidentDTO toNGDTO(ReportAccident model) {

    NGReportAccidentDTO dto = new NGReportAccidentDTO();

    HelperUtil.getNGDTO(model, dto);
    dto.setAccidentFor(model.getAccidentFor());
    dto.setAccidentDate(AllUtil.formatNGDate(model.getAccidentDate()));
    dto.setAccidentTime(model.getAccidentTime());
    dto.setOwnerName(model.getOwnerName());
    dto.setAccidentType(model.getAccidentType());
    dto.setIsPostmortemperformed(HelperUtil.fromBoolean(model.getIsPostmortemperformed()));
    dto.setIsPersonAuthorized(HelperUtil.fromBoolean(model.getIsPersonAuthorized()));
    dto.setPersonDesgination(model.getPersonDesgination());
    dto.setJobDescription(model.getJobDescription());
    dto.setIsPersonAllowed(HelperUtil.fromBoolean(model.getIsPersonAllowed()));
    dto.setLicenceNo(model.getLicenceNo());
    dto.setMaintainerContactNo(model.getMaintainerContactNo());
    dto.setMaintainerWebsite(model.getMaintainerWebsite());
    dto.setMaintainerEmail(model.getMaintainerEmail());
    dto.setInjuriesDescription(model.getInjuriesDescription());
    dto.setDetailedAccidentCauses(model.getDetailedAccidentCauses());
    dto.setTakenAction(model.getTakenAction());
    dto.setIsPoliceConcerned(HelperUtil.fromBoolean(model.getIsPoliceConcerned()));
    dto.setIsAuthorized(HelperUtil.fromBoolean(model.getIsAuthorized()));
    dto.setPoliceConcernedDetails(model.getPoliceConcernedDetails());
    dto.setAccidentEvidene(model.getAccidentEvidene());
    dto.setAssistingDescription(model.getAssistingDescription());
    dto.setWitnessDescription(model.getWitnessDescription());
    dto.setRemarks(model.getRemarks());
    dto.setAgencyName(model.getAgencyName());
//    dto.setAgencyAddress(addressHelper.toNGDTO(model.getForm().getAddressFor(AddressType.AGENCY)));
    dto.setBusinessAddress(addressHelper.toNGDTO(model.getForm().getAddressFor(AddressType.BUSINESS)));
    dto.setAccidentPlace(addressHelper.toNGDTO(model.getForm().getAddressFor(AddressType.ACCIDENT)));
   // dto.setParmanentAddress(addressHelper.toNGDTO(model.getForm().getAddressFor(AddressType.PERMANENT)));

    dto.setVictims(accidentVictimHelper.fromModelNG(model.getVictims()));
    dto.setAttachments(attachmentHelper.fromModelNG(model.getForm().getAttachments()));
    return dto;
  }
}