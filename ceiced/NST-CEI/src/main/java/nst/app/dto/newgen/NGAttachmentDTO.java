package nst.app.dto.newgen;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import nst.common.base.BaseDTO;

@Data
@NoArgsConstructor
public class NGAttachmentDTO extends BaseDTO {

  String id;
  String applicationId;
  String relatedFieldName;
  String doc_type;
  String doc_name;
  String absolute_path;
  String realFileName;
  String form_part;
  String related_field_name;
  String field_identifier;
  String portal_variable_name;
  String dmsUrl;

  @JsonProperty("DOCTYPEMAPPING")
  String docTypeMapping;
}