package nst.app.manager;

import nst.app.model.forms.le.ApplicationAttachment;
import nst.app.repo.ApplicationAttachmentRepository;
import nst.common.base.BaseManager;
import nst.common.base.BaseRepository;
import nst.common.error.AppException;
import nst.kernal.SystemConstants.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApplicationAttachmentManager extends BaseManager<ApplicationAttachment> {

  @Autowired
  ApplicationAttachmentRepository repository;

  @Override
  public BaseRepository<ApplicationAttachment> getRepository() {
    return repository;
  }

  public ApplicationAttachment getForApplication(Long fileId, Long applicationId) {
    ApplicationAttachment applicationAttachment = repository.findByIdAndForm_Id(fileId, applicationId);

    if (applicationAttachment == null) {
      throw AppException.createWithMessageCode(Model.INVALID_REQUEST, Model.INVALID_APPLICATION);
    }
    return applicationAttachment;
  }

  public ApplicationAttachment getForApplication(Long fileId, Long applicationId, String uuid) {
    ApplicationAttachment applicationAttachment = repository.findByIdAndForm_IdAndFileId(fileId, applicationId, uuid);

    if (applicationAttachment == null) {
      throw AppException.createWithMessageCode(Model.INVALID_REQUEST, Model.INVALID_APPLICATION);
    }
    return applicationAttachment;
  }

  public List<ApplicationAttachment> getAttachment(Long applicationId, String fieldIdentifier, String formPart) {
    return repository.findByFormIdAndFieldIdentifierAndFormPart(applicationId, fieldIdentifier, formPart);
  }
}