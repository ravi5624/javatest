package nst.app.service;

import java.io.File;
import java.util.List;

import java.util.Objects;
import nst.app.common.AppConfig;
import nst.app.common.CEIBaseService;
import nst.app.config.CEIConstants;
import nst.app.enums.ApplicationType;
import nst.app.enums.AttachmentType;
import nst.app.helper.AttachmentHelper;
import nst.app.manager.ApplicationAttachmentManager;
import nst.app.manager.ApplicationQueryManager;
import nst.app.model.forms.le.ApplicationAttachment;
import nst.app.model.forms.le.ApplicationQuery;
import nst.app.model.forms.le.CommonForm;
import nst.common.base.BaseHelper;
import nst.common.base.BaseManager;
import nst.common.error.AppException;
import nst.dto.AttachmentDTO;
import nst.util.AllUtil;
import nst.util.IOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
@Transactional
public class ApplicationAttachmentService extends
    CEIBaseService<ApplicationAttachment, AttachmentDTO> {

  @Autowired
  ApplicationAttachmentManager manager;

  @Autowired
  AttachmentHelper helper;

  @Autowired
  ApplicationQueryManager queryManager;

  @Autowired
  AppConfig appConfig;

  public AttachmentDTO add(long applicationId, MultipartFile multipartFile, String fieldIdentifier,
      String variableName) {
    return add(applicationId, multipartFile, fieldIdentifier, variableName, variableName, "1", null);
  }

  public AttachmentDTO add(long applicationId, MultipartFile multipartFile, String fieldIdentifier,
      String variableName, String labelName, String formPart, Long queryId) {
    CommonForm form = getCommonForm(applicationId);
    deleteAttachment(applicationId, fieldIdentifier, formPart);
    ApplicationAttachment attachment = new ApplicationAttachment(form);
    attachment.setRealFileName(multipartFile.getOriginalFilename());
    attachment.setMimeType(multipartFile.getContentType());
    attachment.setDocName(attachment.getSystemFile());
    attachment.setDocType(AllUtil.getDocType(multipartFile.getOriginalFilename()));
    attachment.setAbsolutePath(appConfig
        .getRelativePath(CEIConstants.CEI_PATH, form.getUniqueId(), attachment.getSystemFile()));
    File pathRoot = appConfig.getPathRoot(CEIConstants.CEI_PATH, form.getUniqueId());
    attachment.setFieldIdentifier(fieldIdentifier);
    attachment.setType(fieldIdentifier.startsWith("QUE.") ? AttachmentType.QUERY : AttachmentType.APPLICATION);

    if (AttachmentType.QUERY == attachment.getType()) {
      if (Objects.isNull(queryId)) {
        throw AppException.create("FIELD_ERROR", "queryId", CEIConstants.REQUIRED);
      }
      ApplicationQuery forApplication = queryManager.getForApplication(queryId, applicationId);
      attachment.setUserKey(forApplication.getId());
    }

    attachment.setPortalVariableName(variableName);
    attachment.setLabelName(labelName);
    attachment.setFormPart(formPart);

    IOUtil.storeInputFileTo(multipartFile,
        pathRoot,
        attachment.getSystemFile(),variableName);
    return helper.fromModel(manager.save(attachment));
  }

  @Override
  public BaseManager<ApplicationAttachment> getManager() {
    return manager;
  }

  @Override
  public BaseHelper getHelper() {
    return helper;
  }

  public ApplicationAttachment getFile(Long applicationId, Long fileId, String uuid) {
    return loadAttachment(applicationId, fileId, uuid);
  }

  private ApplicationAttachment loadAttachment(Long applicationId, Long fileId, String uuid) {
    ApplicationAttachment attachment = null;
    if (StringUtils.isEmpty(uuid)) {
      attachment = manager.getForApplication(fileId, applicationId);
    } else {
      attachment = manager.getForApplication(fileId, applicationId, uuid);
    }

    File pathRoot = appConfig
        .getPathRoot(CEIConstants.CEI_PATH, attachment.getForm().getUniqueId());
    attachment.setFile(IOUtil.getFile(pathRoot, attachment.getSystemFile()));
    return attachment;
  }

  public ApplicationType applicationType() {
    return null;
  }

  public void delete(Long applicationId, Long fileId) {
    ApplicationAttachment attachment = loadAttachment(applicationId, fileId, null);
    IOUtil.deleteFile(attachment.getFile());
    manager.delete(attachment);
  }

  private void deleteAttachment(Long applicationId, String fieldIdentifier, String formPart) {
    List<ApplicationAttachment> attachments = manager.getAttachment(applicationId, fieldIdentifier, formPart);
    attachments.forEach(attachment ->{
      delete(attachment.getForm().getId(), attachment.getId());
    });
  }
}