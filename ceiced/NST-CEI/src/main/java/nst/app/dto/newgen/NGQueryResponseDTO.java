package nst.app.dto.newgen;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import nst.common.base.BaseDTO;

@Data
@JsonInclude(Include.ALWAYS)
public class NGQueryResponseDTO extends BaseDTO {

  List<NGQueryResponseDetailDTO> queries = new ArrayList<>();

  @Data
  public static class NGQueryResponseDetailDTO extends BaseDTO {

    String fileNo;
    String packid;
    String pid;
    String q_id;
    String q_raise;
    String querySubmitDate;
    String raiseby;
    String raiseon;
    String qcomment;
    List<NGAttachmentDTO> document;
  }
}