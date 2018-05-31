package nst.app.dto.newgen;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import nst.common.base.BaseDTO;
import nst.util.JSONUtil;

@Data
public class NGQueryRequestDTO extends BaseDTO {


  public static NGQueryRequestDTO fromJSON(String body) {
    return JSONUtil.fromJSON(body, NGQueryRequestDTO.class);
  }

  public static void main(String[] args) {
    String body = "{\"data\":{\"file_status\":\"Query_Raised\",\"fileno\":\"20180313-CONREN-2RMHU\",\"packId\":\"180313141048377\",\"applicationId\":\"20180313-CONREN-5HVUA\",\"queries\":[{\"raiseOn\":\"13/03/2018\",\"raiseBy\":\"aei\",\"qid\":\"1\",\"qraise\":\"new query\"}],\"pid\":\"execon-0000000324-process\",\"outwardNo\":\"\",\"activityName\":\"Clerk\"}}";

    NGQueryRequestDTO dto1 = JSONUtil.fromJSON(body, NGQueryRequestDTO.class);
    System.out.println("dto1 = " + dto1);
  }

  NGQueryDTO data = new NGQueryDTO();

  @Data
  public static class NGQueryDTO extends BaseDTO {

    String applicationId;
    String pid;
    String packId;
    List<NGQueryDetailDTO> queries;
    List<NGAttachmentDTO> attachments;
  }

  @Data
  public static class NGQueryDetailDTO extends BaseDTO {

    String qId;
    String raiseOn;
    String raiseBy;
    String qRaise;
    String qReply;
  }
}