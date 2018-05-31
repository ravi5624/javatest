package nst.app.model.forms.le;

import java.io.File;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Data;
import lombok.ToString;
import nst.app.enums.AttachmentType;
import nst.common.base.BaseAuditableModel;
import nst.util.AllUtil;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Data
@Entity
@Table(name = "attachments")
@ToString(exclude = "form", callSuper = true)
@BatchSize(size = 50)
public class ApplicationAttachment extends BaseAuditableModel {

  @JoinColumn(name = "form_id")
  @OneToOne
  @Fetch(FetchMode.JOIN)
  CommonForm form;

  @Column(name = "file_id")
  String fileId = AllUtil.getFileName();

  @Column(name = "related_field_name")
  String relatedFieldName;

  @Column(name = "doc_type")
  String docType;

  @Column(name = "mime_type")
  String mimeType;

  @Column(name = "label_name")
  String labelName;

  @Column(name = "doc_name")
  String docName;

  @Column(name = "absolute_path")
  String absolutePath;

  @Column(name = "real_file_name")
  String realFileName;

  @Column(name = "form_part")
  String formPart;

  @Column(name = "field_identifier")
  String fieldIdentifier;

  @Column(name = "portal_variable_name")
  String portalVariableName;

  @Column(name = "dms_url")
  String dmsUrl;

  @Column(name = "attachment_type")
  @Enumerated(value = EnumType.STRING)
  AttachmentType type;

  @Column(name = "user_key")
  Long userKey;

  @Transient
  File file;

  public ApplicationAttachment() {
  }

  public ApplicationAttachment(CommonForm commonForm) {
    this.form = commonForm;
  }

  public String getSystemFile() {
    return fileId + "." + AllUtil.getDocType(realFileName);
  }

  public boolean isForField(String fieldIdentifier) {
    return fieldIdentifier.equalsIgnoreCase(this.fieldIdentifier);
  }

  public boolean isForField(String fieldIdentifier, String formPart) {
    return fieldIdentifier.equalsIgnoreCase(this.fieldIdentifier) && formPart.equalsIgnoreCase(this.formPart);
  }
}