package nst.app.repo;

import nst.app.model.forms.le.ApplicationAttachment;
import nst.common.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationAttachmentRepository extends BaseRepository<ApplicationAttachment> {

  ApplicationAttachment findByIdAndForm_Id(Long fileId, Long form_id);

  ApplicationAttachment findByIdAndForm_IdAndFileId(Long fileId, Long form_id, String uuid);

  List<ApplicationAttachment> findByFormIdAndFieldIdentifierAndFormPart(Long applicationId, String fieldIdentifier, String formPart);
}