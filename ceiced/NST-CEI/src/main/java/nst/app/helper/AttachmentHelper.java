package nst.app.helper;


import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.Data;
import nst.app.dto.newgen.NGAttachmentDTO;
import nst.app.enums.ApplicationType;
import nst.app.model.PortalUser;
import nst.app.model.forms.le.ApplicationAttachment;
import nst.app.model.forms.le.CommonForm;
import nst.common.base.BaseHelper;
import nst.dto.AttachmentDTO;
import nst.util.LoginUserUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class AttachmentHelper extends
    BaseHelper<ApplicationAttachment, AttachmentDTO> {

  public ApplicationAttachment toModel(AttachmentDTO dto) {
    ApplicationAttachment model = new ApplicationAttachment();
    return toModel(model, dto);
  }

  public ApplicationAttachment toModel(ApplicationAttachment model, AttachmentDTO dto) {
    model.setDocName(dto.getDocName());
    model.setFileId(dto.getFileId());
    model.setDocType(dto.getDocType());
    model.setFieldIdentifier(dto.getFieldIdentifier());
    model.setPortalVariableName(dto.getPortalVariableName());
    model.setRealFileName(dto.getRealFileName());
    return model;
  }

  public ApplicationAttachment blankModel(Object portaluser) {
    ApplicationAttachment ownerDetail = new ApplicationAttachment();
    LoginUserUtil.getLoginUser();
    ownerDetail.getForm().setUser((PortalUser) portaluser);
    return ownerDetail;
  }


  public AttachmentDTO fromModel(ApplicationAttachment model) {
    if (model == null) {
      return null;
    }
    AttachmentDTO dto = new AttachmentDTO();
    dto.setId(model.getId());
    dto.setVersion(model.getVersion());
    dto.setDocName(model.getDocName());
    dto.setFileId(model.getFileId());
    dto.setMimeType(model.getMimeType());
    dto.setDocType(model.getDocType());
    dto.setAbsolutePath(model.getAbsolutePath());
    dto.setFieldIdentifier(model.getFieldIdentifier());
    dto.setPortalVariableName(model.getPortalVariableName());
    dto.setRealFileName(model.getRealFileName());
    dto.setApplicationId(model.getForm().getId());
    dto.setFormPart(model.getFormPart());
    dto.setLabelName(model.getLabelName());
    dto.setUuid(model.getFileId());
    return dto;
  }

  public List<NGAttachmentDTO> fromModelNG(Iterable<ApplicationAttachment> ms) {
    if (ms == null) {
      return Collections.emptyList();
    }
    return StreamSupport.stream(ms.spliterator(), false).map(this::toNGDTO)
        .collect(Collectors.toList());
  }

  public static void main(String[] args) {
    ApplicationAttachment attachment = new ApplicationAttachment();
    CommonForm form = new CommonForm(ApplicationType.WIREXM);
    attachment.setForm(form);
    attachment.setFormPart("1");
    attachment.setFieldIdentifier("14.1");
    attachment.setPortalVariableName("attach_electrical_jointing_certi");

    AttachmentHelper helper = new AttachmentHelper();
    NGAttachmentDTO ngAttachmentDTO = helper.toNGDTO(attachment);
    System.out.println(ngAttachmentDTO.toJSON());
  }

  public NGAttachmentDTO toNGDTO(ApplicationAttachment model) {
    if (model == null) {
      return null;
    }
    NGAttachmentDTO dto = new NGAttachmentDTO();

    dto.setDoc_name(model.getDocName());
    dto.setId(model.getFileId());
    dto.setDoc_type(model.getDocType());
    dto.setField_identifier(model.getFieldIdentifier());
    dto.setPortal_variable_name(model.getPortalVariableName());
    dto.setRealFileName(model.getRealFileName());
    dto.setRelated_field_name(model.getLabelName());
    dto.setForm_part(model.getFormPart());
    dto.setAbsolute_path(model.getAbsolutePath());
    dto.setDocTypeMapping(DocTypeMapping.DOC_TYPE_MAPPING.stream().filter(dt -> {
      return dt.getApplicationType().equalsIgnoreCase(model.getForm().getApplicationType().getType());
    }).filter(dt -> {
      System.out.println(dt.getApplicationType() + " =>"+ dt.getFormPart() + " => " + dt.getFieldIdentifier() + " => " + dt.getPortalVariableName());
      return (dt.getFieldIdentifier().equalsIgnoreCase(model.getFieldIdentifier()) || model.getFieldIdentifier().startsWith(dt.getFieldIdentifier()))
              && (StringUtils.isEmpty(dt.getPortalVariableName()) ||  (!StringUtils.isEmpty(dt.getPortalVariableName()) && dt.getPortalVariableName().equalsIgnoreCase(model.getPortalVariableName())))
              && dt.getFormPart().equalsIgnoreCase(model.getFormPart());
    }).findFirst().orElse(DocTypeMapping.UNKNOWN).getDocTypeMapping());
    System.out.println("dto = " + dto.getDocTypeMapping());
    return dto;
  }

  @Data
  public static class DocTypeMapping implements Serializable {

    String applicationType;
    String fieldIdentifier;
    String formPart;
    String docTypeMapping;
    String portalVariableName;

    static DocTypeMapping UNKNOWN = DocTypeMapping.create("", "", "", "UNKNOWN", "");

    static Set<DocTypeMapping> DOC_TYPE_MAPPING = new HashSet<>();

    static {
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.WIREXM.getType(), "4.1", "1", "schoolleavingcerti", "School leaving certificate"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.WIREXM.getType(), "6.7", "1", "permanentaddressproof", "Attach electricty bill for address pfroof or any Govt. valid ID proof"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.WIREXM.getType(), "7.7", "1", "temporarytaddressproof", "attach electricity bill for address proof or any other govt. valid id proof"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.WIREXM.getType(), "form_k_attachment", "12.1", "1", "formk", "Form K ( K in One PDF)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.WIREXM.getType(), "certificate", "12.1", "1", "certificate", "Certificate"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.WIREXM.getType(), "12.2", "1", "forml", "Copy of Form L Verified by electrical Inspector(All Pages)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.WIREXM.getType(), "15.1", "1", "photographwireman", "Attach a Passport size photograph"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.WIREXM.getType(), "16.1", "1", "signaturewireman", "Attach a scan copy of Signature"));

      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.WIREXMP.getType(), "4.1", "1", "schoolleavingcertificate", "School leaving certificate"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.WIREXMP.getType(), "6.7", "1", "permanentaddressproof", "Attach electricty bill for address pfroof or any Govt. valid ID proof"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.WIREXMP.getType(), "7.7", "1", "temporaryaddressproof", "Attach electricity bill for address proof or any Govt. Id"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.WIREXMP.getType(), "attach_national_apprenticeship_certi", "11.2", "1", "nationalapprenticeshipcerti", "National apprenticeship certificate (ELE)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.WIREXMP.getType(), "attach_national_trade_certi", "11.2", "1", "nationaltradecertificate", "National trade certificate (ELE)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.WIREXMP.getType(), "attach_electrical_service_technician_certi", "11.2", "1", "electricalservicetechniciancerti", "Electrical service technician certificate"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.WIREXMP.getType(), "attach_electrical_jointing_certi", "11.2", "1", "electricalinstallationwiringjointing", "Electrical installation,wiring and jointing certificate"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.WIREXMP.getType(), "attach_all_semester_marksheet", "11.2", "1", "allsemestermarkssheet", "All semester marks sheet"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.WIREXMP.getType(), "11.3", "1", "certificate", "Certificate"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.WIREXMP.getType(), "12.2", "1", "affidavit", "Affidavit if not from Gujarat"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.WIREXMP.getType(), "13.1", "1", "photograph", "Attach a Passport size photograph"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.WIREXMP.getType(), "14.1", "1", "signature", "Attach a scan copy of Signature"));

      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.WIRMODI.getType(), "2.1", "1", "???", "Technical Qualification Document"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.WIRMODI.getType(), "6.2", "1", "???", "School leaving certificate or matriculation certificate"));



      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.CONMODI.getType(), "6.7", "1", "electricitybill", "Attach electricty bill for address proof or any Govt. valid ID proof"));

      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.CONLIC.getType(), "3.7", "1", "officeaddressproof", "Attach electricity bill for address proof or any Govt. Id"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.CONLIC.getType(), "4.1", "1", "partnershipdeed", "Partnership deed (in case of change in partners)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.CONLIC.getType(), "5.1", "1", "signaturepartner", "Signature of partner"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.CONLIC.getType(), "5.2", "1", "birthproof", "Date of birth proof"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.CONLIC.getType(), "5.3", "1", "orgaddressproof", "Address proof"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.CONLIC.getType(), "7.6", "1", "supervisoraddress", "Attach address proof of supervisor"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.CONLIC.getType(), "7.7", "1", "medicalcertificate", "Attach medical certificate of civil surgeon"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.CONLIC.getType(), "8.1", "1", "contrformi", "Form I verfied by electrical inspector or his representative"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.CONLIC.getType(), "8.2", "1", "signaturesupervisor", "Attach supervisor Signature"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.CONLIC.getType(), "8.3", "1", "signaturesupforpartners", "Attache Supervisor Singnature for partners/director/propreitor"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.CONLIC.getType(), "9.1", "1", "banksolvencycertificate" , "Bank solvency certificate"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.CONLIC.getType(), "9.2", "1", "detailsofinstrument", "Details of instruments"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.CONLIC.getType(), "9.3", "1", "invoiceofinstrument", "Invoice of instruments"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.CONLIC.getType(), "9.4", "1", "ownersdeclaration", "Owners Declaration"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.CONLIC.getType(), "9.5", "1", "supercisordeclaration", "Declaration Of Supervisor"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.CONLIC.getType(), "9.6", "1", "appointmentletter", "Appointment Letter to be given"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.CONLIC.getType(), "9.7", "1", "declarationletter", "Letter of declaration"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.CONLIC.getType(), "9.8", "1", "contractoraffidavit", "Affidavit"));

      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.CONREN.getType(), "5", "1", "renformi", "Copy of Form “I” verified by authority or his representative"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.CONREN.getType(), "8.7", "1", "renaddressproof", "Attach electricty bill for address pfroof or any Govt. valid ID proof"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.CONREN.getType(), "9.1", "1", "ownerssignature", "Attach Owners Signature"));

      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.SUPEXM.getType(), "4.1", "1", "schoolleavingcerti", "School leaving certificate"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.SUPEXM.getType(), "6.7", "1", "permanentaddressproof", "Attach electricty bill for address proof or any Govt. valid ID proof"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.SUPEXM.getType(), "7.7", "1", "temporarytaddressproof", "Attach electricity bill for address proof or any Govt. Id"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.SUPEXM.getType(), "12.1", "1", "wiremanpermit", "attach wireman permit"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.SUPEXM.getType(), "wireman_certificate_and_permit", "15.1", "1", "wiremancertiandpermit", "Wireman Certificate And Permit"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.SUPEXM.getType(), "supervisor_certificate_and_permit", "15.1", "1", "supervisorcertiandpermit", "Supervisor Certificate And Permit"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.SUPEXM.getType(), "form_k_attachment", "15.2", "1", "formk", "form k ( k in one pdf)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.SUPEXM.getType(), "attach_apprenticeship_certificate", "15.2", "1", "apprenticeshipcertificate", "Apprenticeship Certificate"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.SUPEXM.getType(), "attach_copy_formI_proof", "15.3", "1", "forml", "copy of form l verified by electrical inspector(all pages)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.SUPEXM.getType(), "attach_experience_certificate", "15.3", "1", "experiencecerti", "Experience Certificate"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.SUPEXM.getType(), "15.4", "1", "forml", "copy of form l verified by electrical inspector(all pages)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.SUPEXM.getType(), "18.1", "1", "photographsupervisor", "Attach a Passport size photograph"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.SUPEXM.getType(), "19.1", "1", "signaturesupervisor", "Attach a scan copy of Signature"));

      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.SUPEXMP.getType(), "4.1", "1", "schoolleavingcertificate", "School leaving certificate"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.SUPEXMP.getType(), "6.7", "1", "permanentaddressproof", "Attach electricty bill for address pfroof or any Govt. valid ID proof"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.SUPEXMP.getType(), "7.7", "1", "temporaryaddressproof", "attach electricity bill for address proof or any other govt. valid id proof"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.SUPEXMP.getType(), "attach_marksheet_last_year", "11.3", "1", "lastyearmarkssheet", "Marks sheet of last year"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.SUPEXMP.getType(), "attach_national_trade_certificate", "11.3", "1", "nationaltradecertificate", "National trade certificate (ELE)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.SUPEXMP.getType(), "attach_technical_exam_certificate", "11.3", "1", "techniacalexaminationboardcerti", "Technical examination board certificate"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.SUPEXMP.getType(), "attach_degree_certificate", "11.3", "1", "degreecertificate", "Degree certificate"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.SUPEXMP.getType(), "attach_certificate", "11.3", "1", "certificate", "Certificate"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.SUPEXMP.getType(), "attach_diploma_certificate", "11.4", "1", "diplomacertificate", "Diploma certificate"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.SUPEXMP.getType(), "attach_degree_certificate", "11.4", "1", "degreecertificate", "Degree certificate"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.SUPEXMP.getType(), "attach_form_k", "11.4", "1", "formk", "Form “K”"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.SUPEXMP.getType(), "11.5", "1", "formi", "Copy of Form “I” verified by authority"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.SUPEXMP.getType(), "11.6", "1", "nationalapprenticeshipcerti", "National apprenticeship certificate (ELE)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.SUPEXMP.getType(), "12.3", "1", "affidavit", "Affidavit if not from Gujarat"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.SUPEXMP.getType(), "12.4", "1", "otherstateaddressproof", "Permanent address proof of the state from which you belong if not from Gujarat"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.SUPEXMP.getType(), "13.2", "1", "otherstatemarkssheet", " Marks sheet of all sem if from Other state"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.SUPEXMP.getType(), "14.1", "1", "photograph", "Attach a Passport size photograph"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.SUPEXMP.getType(), "15.1", "1", "signature", "Attach a scan copy of Signature"));

      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.ISPERMIT.getType(), "4.1", "1", "schoolleavingcerti", "School leaving certificate"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.ISPERMIT.getType(), "6.7", "1", "permanentaddressproof", "Attach electricty bill for address pfroof or any Govt. valid ID proof"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.ISPERMIT.getType(), "7.7", "1", "temporarytaddressproof", "Attach electricity bill for address proof or any Govt. Id"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.ISPERMIT.getType(), "11.4", "1", "permitcopy", "Attach Orignal Permit Copy"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.ISPERMIT.getType(), "11.5", "1", "exemptioncerti", "Attach Exemption Certificate copy If Taken Exemption"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.ISPERMIT.getType(), "11.6", "1", "affidavit", "Attach affidavit if Interstate"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.ISPERMIT.getType(), "12.2", "1", "appointmentletter", "attach appointment letter"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.ISPERMIT.getType(), "13.1", "1", "photographinterstate", "Attach a Passport size photograph"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.ISPERMIT.getType(), "14.1", "1", "signatureinterstate", "Attach a scan copy of Signature"));


      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.SUPMODI.getType(), "2.1", "1", "???", "Technical Qualification Document"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.SUPMODI.getType(), "6.7", "1", "electricitybill", "Attach electricty bill for address proof or any Govt. valid ID proof"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.SUPMODI.getType(), "7.2", "1", "???", "School Leaving Certificate"));



      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.REPEATER.getType(), "4.1", "1", "schoolleavingcertirepeter", "School leaving certificate or matriculation certificate"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.REPEATER.getType(), "12.1", "1", "photographrepeater", "Attach a Passport size photograph"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.REPEATER.getType(), "13.1", "1", "signaturerepeater", "Attach a scan copy of signature"));

      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.DUPPER.getType(), "4.1", "1", "photocopyofpermit", "Attach Photocopy of Permit"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.DUPPER.getType(), "8.7", "1", "dupaddressproof", "Attach electricty bill for address pfroof or any Govt. valid ID proof"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.DUPPER.getType(), "9.1", "1", "dupphotograph", "Attach a Passport size photograph"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.DUPPER.getType(), "9.2", "1", "attachsignature", "Attach signature"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.DUPPER.getType(), "9.3", "1", "dupaffidavit", "Affidavit"));


      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.CONPARTMODI.getType(), "attach_partnership_proof", "4.1", "1", "partnershipdeed1", "Partnership deed (in case of change in partners)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.CONPARTMODI.getType(), "attach_death_certificate", "4.1", "1", "??", "Death Certificate"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.CONPARTMODI.getType(), "5.1", "1", "dateofbirth", "Date of birth proof"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.CONPARTMODI.getType(), "5.2", "1", "addressproof1", "Address proof"));


      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.FORMI.getType(), "1.1", "1", "supervisorpermitletter", "Supervisor Permit  Letter"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.FORMI.getType(), "1.2", "1", "declaration", "Declaration"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.FORMI.getType(), "1.3", "1", "supervisorappointmentletter", "Supervisor appointment letter"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.FORMI.getType(), "1.4", "1", "schoolleavingcertificateformi", "School Leaving Certificate"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.FORMI.getType(), "1.5", "1", "supervisorpermit", "Supervisor Permit"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.FORMI.getType(), "1.6", "1", "supervisorcertificate", "Supervisor Certificate"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.FORMI.getType(), "1.7", "1", "supervisorexpletter", "Supervisor experience letter (if experienced)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.FORMI.getType(), "1.8", "1", "supervisorprevcontformi", "Supervisor's previous contractor's  Form I certified copy (if experienced)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.FORMI.getType(), "1.9", "1", "otherformi", "Other"));



      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.ALRENE.getType(), "7", "14", "certiofauthoriginal", "Certificate of Authorization in original"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.ALRENE.getType(), "8", "14", "certifromnationalisedbank", "Certificate from nationalised bank to effect that the applicant continues to be solvent to the extent of Rupees Invested at time of Authorization"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.ALRENE.getType(), "9", "14", "lastupdatedelectricalcontlicense", "Last updated electrical contractor license"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.ALRENE.getType(), "10", "14", "statementindicatingliftescalator", "A statement indicating the numbers of lift/escalator erected and maintain during the period of 3 year before renewal."));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.ALRENE.getType(), "11", "14", "attachreldocren", "Attach relevant documents for renewal"));


      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.ALDUPL.getType(), "6.1", "15", "notarizedaffidavitagencydup", "Notarized Affidavit"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.ALDUPL.getType(), "6.2", "15", "attachreldocdup", "Attach other relevant documents for Duplicate"));

      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LEML.getType(), "3.1", "13", "owneraddressownershipproof", "Proof of Ownership"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LEML.getType(), "3.2", "13", "attachreldocowneraddress", "Attach Relevant documents"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LEML.getType(), "8", "13", "attach_relevant_documents_agancy", "Attach Relevant documents"));


      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LIL.getType(), "A", "10", "proofoffromlocal", "Proof of from Local"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LIL.getType(), "A1", "10", "rajachittidevelopment", "Rajachitti development"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LIL.getType(), "A2", "10", "approvalofcivilplanfromauthority", "Approval of civilplan from authority"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LIL.getType(), "A2(i)", "10", "keyplan", "Key plan"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LIL.getType(), "A2(ii)", "10", "elevation", "Elevation"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LIL.getType(), "A2(iii)", "10", "floorplanbottomtopandother", "floor plan bottom  , top and other (indivual)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LIL.getType(), "B", "10", "proofofownership", "Proof of ownership"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LIL.getType(), "B1", "10", "partnershipdeedmemorandum", "Partnership Deed / Memorandum of undertaking / articles of association"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LIL.getType(), "B2", "10", "saledeed", "Sale Deed (if any)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LIL.getType(), "B3", "10", "developmentaggreement", "Development aggreement"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LIL.getType(), "B4", "10", "assignmentdeed", "Assignment deed (if any)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LIL.getType(), "B5", "10", "societyregistrationcertificate", "Society registration certificate (if any)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LIL.getType(), "B6", "10", "transferdeed", "Transfer deed (if any)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LIL.getType(), "B7", "10", "index-2", "Index-2 , 7/12 Index, Template 8(A) , Template 6 & Other"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LIL.getType(), "C", "10", "proofofsign", "Proof of Sign"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LIL.getType(), "D", "10", "drawingoflift", "Drawing of Lift"));



      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.EIL.getType(), "A", "10", "proofoffromlocal", "Proof of from Local"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.EIL.getType(), "A1", "10", "rajachittidevelopment", "Rajachitti development"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.EIL.getType(), "A2", "10", "approvalofcivilplanfromauthority", "Approval of civilplan from authority"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.EIL.getType(), "A2(i)", "10", "keyplan", "Key plan"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.EIL.getType(), "A2(ii)", "10", "elevation", "Elevation"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.EIL.getType(), "A2(iii)", "10", "floorplanbottomtopandother", "floor plan bottom  , top and other (indivual)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.EIL.getType(), "B", "10", "proofofownership", "Proof of ownership"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.EIL.getType(), "B1", "10", "partnershipdeedmemorandum", "Partnership Deed / Memorandum of undertaking / articles of association"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.EIL.getType(), "B2", "10", "saledeed", "Sale Deed (if any)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.EIL.getType(), "B3", "10", "developmentaggreement", "Development aggreement"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.EIL.getType(), "B4", "10", "assignmentdeed", "Assignment deed (if any)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.EIL.getType(), "B5", "10", "societyregistrationcertificate", "Society registration certificate (if any)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.EIL.getType(), "B6", "10", "transferdeed", "Transfer deed (if any)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.EIL.getType(), "B7", "10", "index-2", "Index-2 , 7/12 Index, Template 8(A) , Template 6 & Other"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.EIL.getType(), "C", "10", "proofofsign", "Proof of Sign"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.EIL.getType(), "D", "10", "drawingoflift", "Drawing of Lift"));


      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.OLIFT.getType(), "A", "10", "proofoffromlocal", "Proof of from Local"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.OLIFT.getType(), "A1", "10", "rajachittidevelopment", "Rajachitti development"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.OLIFT.getType(), "A2", "10", "approvalofcivilplanfromauthority", "Approval of civilplan from authority"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.OLIFT.getType(), "A2(i)", "10", "keyplan", "Key plan"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.OLIFT.getType(), "A2(ii)", "10", "elevation", "Elevation"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.OLIFT.getType(), "A2(iii)", "10", "floorplanbottomtopandother", "floor plan bottom  , top and other (indivual)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.OLIFT.getType(), "B", "10", "proofofownership", "Proof of ownership"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.OLIFT.getType(), "B1", "10", "partnershipdeedmemorandum", "Partnership Deed / Memorandum of undertaking / articles of association"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.OLIFT.getType(), "B2", "10", "saledeed", "Sale Deed (if any)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.OLIFT.getType(), "B3", "10", "developmentaggreement", "Development aggreement"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.OLIFT.getType(), "B4", "10", "assignmentdeed", "Assignment deed (if any)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.OLIFT.getType(), "B5", "10", "societyregistrationcertificate", "Society registration certificate (if any)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.OLIFT.getType(), "B6", "10", "transferdeed", "Transfer deed (if any)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.OLIFT.getType(), "B7", "10", "index-2", "Index-2 , 7/12 Index, Template 8(A) , Template 6 & Other"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.OLIFT.getType(), "C", "10", "proofofsign", "Proof of Sign"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.OLIFT.getType(), "D", "10", "drawingoflift", "Drawing of Lift"));


      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.OESCL.getType(), "A", "10", "proofoffromlocal", "Proof of from Local"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.OESCL.getType(), "A1", "10", "rajachittidevelopment", "Rajachitti development"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.OESCL.getType(), "A2", "10", "approvalofcivilplanfromauthority", "Approval of civilplan from authority"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.OESCL.getType(), "A2(i)", "10", "keyplan", "Key plan"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.OESCL.getType(), "A2(ii)", "10", "elevation", "Elevation"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.OESCL.getType(), "A2(iii)", "10", "floorplanbottomtopandother", "floor plan bottom  , top and other (indivual)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.OESCL.getType(), "B", "10", "proofofownership", "Proof of ownership"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.OESCL.getType(), "B1", "10", "partnershipdeedmemorandum", "Partnership Deed / Memorandum of undertaking / articles of association"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.OESCL.getType(), "B2", "10", "saledeed", "Sale Deed (if any)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.OESCL.getType(), "B3", "10", "developmentaggreement", "Development aggreement"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.OESCL.getType(), "B4", "10", "assignmentdeed", "Assignment deed (if any)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.OESCL.getType(), "B5", "10", "societyregistrationcertificate", "Society registration certificate (if any)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.OESCL.getType(), "B6", "10", "transferdeed", "Transfer deed (if any)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.OESCL.getType(), "B7", "10", "index-2", "Index-2 , 7/12 Index, Template 8(A) , Template 6 & Other"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.OESCL.getType(), "C", "10", "proofofsign", "Proof of Sign"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.OESCL.getType(), "D", "10", "drawingoflift", "Drawing of Lift"));


      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LEDL.getType(), "6.1", "8", "notarizedaffidavit", "Notarized Affidavit"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LEDL.getType(), "6.2", "8", "attachotherrelevantdocumentsforduplicate", "Attach other relevant documents for Duplicate (If more than two attach in zip)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LEDL.getType(), "6.15", "8", "attachrelevantdocumentsforrenewal", "Attach relevant documents for renewal (If more than two attach in zip)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LEDL.getType(), "6.14", "8", "attachoriginallicenseoflift", "Attach Original License of Lift/Escalator"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LEDL.getType(), "A", "10", "proofoffromlocal", "Proof of from Local"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LEDL.getType(), "A1", "10", "rajachittidevelopment", "Rajachitti development"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LEDL.getType(), "A2", "10", "approvalofcivilplanfromauthority", "Approval of civilplan from authority"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LEDL.getType(), "A2(i)", "10", "keyplan", "Key plan"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LEDL.getType(), "A2(ii)", "10", "elevation", "Elevation"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LEDL.getType(), "A2(iii)", "10", "floorplanbottomtopandother", "floor plan bottom  , top and other (indivual)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LEDL.getType(), "B", "10", "proofofownership", "Proof of ownership"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LEDL.getType(), "B1", "10", "partnershipdeedmemorandum", "Partnership Deed / Memorandum of undertaking / articles of association"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LEDL.getType(), "B2", "10", "saledeed", "Sale Deed (if any)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LEDL.getType(), "B3", "10", "developmentaggreement", "Development aggreement"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LEDL.getType(), "B4", "10", "assignmentdeed", "Assignment deed (if any)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LEDL.getType(), "B5", "10", "societyregistrationcertificate", "Society registration certificate (if any)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LEDL.getType(), "B6", "10", "transferdeed", "Transfer deed (if any)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LEDL.getType(), "B7", "10", "index-2", "Index-2 , 7/12 Index, Template 8(A) , Template 6 & Other"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LEDL.getType(), "C", "10", "proofofsign", "Proof of Sign"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LEDL.getType(), "D", "10", "drawingoflift", "Drawing of Lift"));


      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LERL.getType(), "6.1", "8", "notarizedaffidavit", "Notarized Affidavit"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LERL.getType(), "6.2", "8", "attachotherrelevantdocumentsforduplicate", "Attach other relevant documents for Duplicate (If more than two attach in zip)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LERL.getType(), "6.15", "8", "attachrelevantdocumentsforrenewal", "Attach relevant documents for renewal (If more than two attach in zip)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LERL.getType(), "6.14", "8", "attachoriginallicenseoflift", "Attach Original License of Lift/Escalator"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LERL.getType(), "A", "10", "proofoffromlocal", "Proof of from Local"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LERL.getType(), "A1", "10", "rajachittidevelopment", "Rajachitti development"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LERL.getType(), "A2", "10", "approvalofcivilplanfromauthority", "Approval of civilplan from authority"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LERL.getType(), "A2(i)", "10", "keyplan", "Key plan"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LERL.getType(), "A2(ii)", "10", "elevation", "Elevation"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LERL.getType(), "A2(iii)", "10", "floorplanbottomtopandother", "floor plan bottom  , top and other (indivual)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LERL.getType(), "B", "10", "proofofownership", "Proof of ownership"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LERL.getType(), "B1", "10", "partnershipdeedmemorandum", "Partnership Deed / Memorandum of undertaking / articles of association"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LERL.getType(), "B2", "10", "saledeed", "Sale Deed (if any)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LERL.getType(), "B3", "10", "developmentaggreement", "Development aggreement"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LERL.getType(), "B4", "10", "assignmentdeed", "Assignment deed (if any)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LERL.getType(), "B5", "10", "societyregistrationcertificate", "Society registration certificate (if any)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LERL.getType(), "B6", "10", "transferdeed", "Transfer deed (if any)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LERL.getType(), "B7", "10", "index-2", "Index-2 , 7/12 Index, Template 8(A) , Template 6 & Other"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LERL.getType(), "C", "10", "proofofsign", "Proof of Sign"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.LERL.getType(), "D", "10", "drawingoflift", "Drawing of Lift"));


      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.ACCIDENT.getType(), "A", "9", "attachment", "Attachment"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.ACCIDENT.getType(), "A", "9", "attachment1", "Attachment"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.ACCIDENT.getType(), "A1", "10", "rajachittidevelopment", "Rajachitti development"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.ACCIDENT.getType(), "A2", "10", "approvalofcivilplanfromauthority", "Approval of civilplan from authority"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.ACCIDENT.getType(), "A2(i)", "10", "keyplan", "Key plan"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.ACCIDENT.getType(), "A2(ii)", "10", "elevation", "Elevation"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.ACCIDENT.getType(), "A2(iii)", "10", "floorplanbottomtopandother", "floor plan bottom  , top and other (indivual)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.ACCIDENT.getType(), "B", "10", "proofofownership", "Proof of ownership"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.ACCIDENT.getType(), "B1", "10", "partnershipdeedmemorandum", "Partnership Deed / Memorandum of undertaking / articles of association"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.ACCIDENT.getType(), "B2", "10", "saledeed", "Sale Deed (if any)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.ACCIDENT.getType(), "B3", "10", "developmentaggreement", "Development aggreement"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.ACCIDENT.getType(), "B4", "10", "assignmentdeed", "Assignment deed (if any)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.ACCIDENT.getType(), "B5", "10", "societyregistrationcertificate", "Society registration certificate (if any)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.ACCIDENT.getType(), "B6", "10", "transferdeed", "Transfer deed (if any)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.ACCIDENT.getType(), "B7", "10", "index-2", "Index-2 , 7/12 Index, Template 8(A) , Template 6 & Other"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.ACCIDENT.getType(), "C", "10", "proofofsign", "Proof of Sign"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.ACCIDENT.getType(), "D", "10", "drawingoflift", "Drawing of Lift"));



      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.EMAL.getType(), "government_id_proof", "3.3", "1", "????", "Government ID Proof"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.EMAL.getType(), "partner_deed", "3.3", "1", "????", "Partnership deed"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.EMAL.getType(), "artical_memorandum_association", "3.3", "1", "????", "Article of Association, Memorandum of Association With resolution for signing authority"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.EMAL.getType(), "4.11", "1", "ownershipoccupancyrightsproof", "Ownership or Occupancy rights proof"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.EMAL.getType(), "5.11", "1", "branchownershipoccupancyrightsproof", "Ownership or Occupancy rights proof"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.EMAL.getType(), "7.3", "1", "copyofauthorizationcertificate", "Copy of authorization certificate"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.EMAL.getType(), "attach_list_of_lifts_escalator_erected_maintained_with_details_of_license_no_lift_location_address_districtwise", "7.4", "1", "listofmaintainedlift", "Attach List of lifts/escalator erected/ maintained with details of license no. lift location, address, districtwise"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.EMAL.getType(), "attach_details_of_lifts_escalator_erected_maintained_or_major_project_undertaken_and_authorisation_held_by_the_firm_or_company_in_other_states", "7.4", "1", "projectundertakenauthorisation", "Attach details of lifts/escalator erected /maintained or major project undertaken and authorisation held by the firm or company in other states"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.EMAL.getType(), "7.5", "1", "companycatalogue", "Attach company catalogue"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.EMAL.getType(), "8.1", "1", "electricalcontractorlicense", "Copy of electrical contractor license"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.EMAL.getType(), "8.2", "1", "iformverifiedbyauthority", "Copy of Form “I” verified by authority (All Pages)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.EMAL.getType(), "9.1", "1", "solvencycertificate5lakhs", "Attach solvency certificate of 5 lakhs"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.EMAL.getType(), "10.1", "1", "??", "employeeAttachment"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.EMAL.getType(), "12.1", "2", "registrationfactoryact", "Copy of registration factory act"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.EMAL.getType(), "13.1", "2", "ownedownershiptaxbill", "Proof of ownership tax bill (attach one or more agreement if any in Zip form)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.EMAL.getType(), "13.2", "2", "ownedelectricitybill", "Electricity bill"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.EMAL.getType(), "14.2", "2", "recentcopybill", "Attach recent copy of bill"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.EMAL.getType(), "14.3", "2", "taxbillcopyforproof", "Tax bill copy for proof of area"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.EMAL.getType(), "rc_book_proof", "17.1", "2", "ownedrcbookproof", "RC book proof"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.EMAL.getType(), "rc_book_proof_agreement","17.1", "2", "??", "RC book proof Agreement"));


      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.EMAL.getType(), "government_id_proof", "3.3", "1", "????", "Government ID Proof"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.EMAL.getType(), "partner_deed", "3.3", "1", "????", "Partnership deed"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.EMAL.getType(), "artical_memorandum_association", "3.3", "1", "????", "Article of Association, Memorandum of Association With resolution for signing authority"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.OMAL.getType(), "4.11", "1", "ownershipoccupancyrightsproof", "Ownership or Occupancy rights proof"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.OMAL.getType(), "5.11", "1", "branchownershipoccupancyrightsproof", "Ownership or Occupancy rights proof"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.OMAL.getType(), "7.3", "1", "copyofauthorizationcertificate", "Copy of authorization certificate"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.OMAL.getType(), "attach_list_of_lifts_escalator_erected_maintained_with_details_of_license_no_lift_location_address_districtwise", "7.4", "1", "listofmaintainedlift", "Attach List of lifts/escalator erected/ maintained with details of license no. lift location, address, districtwise"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.OMAL.getType(), "attach_details_of_lifts_escalator_erected_maintained_or_major_project_undertaken_and_authorisation_held_by_the_firm_or_company_in_other_states", "7.4", "1", "projectundertakenauthorisation", "Attach details of lifts/escalator erected /maintained or major project undertaken and authorisation held by the firm or company in other states"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.OMAL.getType(), "7.5", "1", "companycatalogue", "Attach company catalogue"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.OMAL.getType(), "8.1", "1", "electricalcontractorlicense", "Copy of electrical contractor license"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.OMAL.getType(), "8.2", "1", "iformverifiedbyauthority", "Copy of Form “I” verified by authority (All Pages)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.OMAL.getType(), "9.1", "1", "solvencycertificate5lakhs", "Attach solvency certificate of 5 lakhs"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.OMAL.getType(), "10.1", "1", "??", "employeeAttachment"));


      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.EMAL.getType(), "government_id_proof", "3.3", "1", "????", "Government ID Proof"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.EMAL.getType(), "partner_deed", "3.3", "1", "????", "Partnership deed"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.EMAL.getType(), "artical_memorandum_association", "3.3", "1", "????", "Article of Association, Memorandum of Association With resolution for signing authority"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.ITAL.getType(), "4.11", "1", "ownershipoccupancyrightsproof", "Ownership or Occupancy rights proof"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.ITAL.getType(), "5.11", "1", "branchownershipoccupancyrightsproof", "Ownership or Occupancy rights proof"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.ITAL.getType(), "7.3", "1", "copyofauthorizationcertificate", "Copy of authorization certificate"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.ITAL.getType(), "attach_list_of_lifts_escalator_erected_maintained_with_details_of_license_no_lift_location_address_districtwise", "7.4", "1", "listofmaintainedlift", "Attach List of lifts/escalator erected/ maintained with details of license no. lift location, address, districtwise"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.ITAL.getType(), "attach_details_of_lifts_escalator_erected_maintained_or_major_project_undertaken_and_authorisation_held_by_the_firm_or_company_in_other_states", "7.4", "1", "projectundertakenauthorisation", "Attach details of lifts/escalator erected /maintained or major project undertaken and authorisation held by the firm or company in other states"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.ITAL.getType(), "7.5", "1", "companycatalogue", "Attach company catalogue"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.ITAL.getType(), "8.1", "1", "electricalcontractorlicense", "Copy of electrical contractor license"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.ITAL.getType(), "8.2", "1", "iformverifiedbyauthority", "Copy of Form “I” verified by authority (All Pages)"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.ITAL.getType(), "9.1", "1", "solvencycertificate5lakhs", "Attach solvency certificate of 5 lakhs"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.ITAL.getType(), "10.1", "1", "??", "employeeAttachment"));

      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.ALMODI.getType(), "1.1", "13", "addresswhoinstalllift", "Attach Relevant documents"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.ALMODI.getType(), "2.10", "13", "branchofficeaddresschange", "Attach Relevant documents"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.ALMODI.getType(), "3.10", "13", "mainofficeaddresschange", "Attach Relevant documents"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.ALMODI.getType(), "4.11", "13", "workshopdetailschange", "Attach Relevant documents"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.ALMODI.getType(), "rc_book_proof","8.2", "13", "ownedrcbookproof", "RC book proof"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.ALMODI.getType(), "rc_book_proof_agreement","8.2", "13", "??", "RC book proof Agreement"));
      DOC_TYPE_MAPPING.add(DocTypeMapping.create(ApplicationType.ALMODI.getType(), "9.2", "13", "attachreldoclegalstatus", "Attach Relevant documents of Legal Status"));

    }

    private DocTypeMapping(String applicationType, String fieldIdentifier, String formPart,
        String docTypeMapping, String labelName, String portalVariableName) {
      this.applicationType = applicationType;
      this.fieldIdentifier = fieldIdentifier;
      this.formPart = formPart;
      this.docTypeMapping = docTypeMapping;
      this.portalVariableName=portalVariableName;
    }

    public static DocTypeMapping create(String applicationType, String fieldIdentifier,
        String formPart, String docType, String labelName) {
      return new DocTypeMapping(applicationType, fieldIdentifier, formPart, docType, labelName,null);
    }

    public static DocTypeMapping create(String applicationType, String portalVariableName, String fieldIdentifier,
                                        String formPart, String docType, String labelName) {
      return new DocTypeMapping(applicationType, fieldIdentifier, formPart, docType, labelName, portalVariableName);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      if (!super.equals(o)) {
        return false;
      }

      DocTypeMapping that = (DocTypeMapping) o;

      if (!applicationType.equals(that.applicationType)) {
        return false;
      }
      if (!fieldIdentifier.equals(that.fieldIdentifier)) {
        return false;
      }
      if (!formPart.equals(that.formPart)) {
        return false;
      }
      if (!docTypeMapping.equals(that.docTypeMapping)) {
        return false;
      }
      return portalVariableName != null ? portalVariableName.equals(that.portalVariableName)
          : that.portalVariableName == null;
    }

    @Override
    public int hashCode() {
      int result = super.hashCode();
      result = 31 * result + applicationType.hashCode();
      result = 31 * result + fieldIdentifier.hashCode();
      result = 31 * result + formPart.hashCode();
      result = 31 * result + docTypeMapping.hashCode();
      result = 31 * result + (portalVariableName != null ? portalVariableName.hashCode() : 0);
      return result;
    }
  }
}























