package nst.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import lombok.NoArgsConstructor;
import nst.common.base.BaseModelDTO;

@Data
@NoArgsConstructor
@JsonInclude(Include.ALWAYS)
public class AttachmentDTO extends BaseModelDTO {

  Long applicationId;
  String fileId;
  String relatedFieldName;
  String docType;
  String docName;
  String absolutePath;
  String realFileName;
  String formPart;
  String labelName;
  String fieldIdentifier;
  String portalVariableName;
  String mimeType;
  String dmsUrl;
  String uuid;
}