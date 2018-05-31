package nst.app.dto.newgen;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import lombok.Data;
import nst.common.base.BaseDTO;
import nst.util.JSONUtil;

@Data
public class NGSubmitDTO extends BaseDTO {

  public static void main(String[] args) {
    System.out.println("FromJson(); = " + FromJson());
  }

  public static NGSubmitDTO FromJson() {
    return JSONUtil.fromJSON(
        "{\"id\":\"20204\",\"status\":\"Success\",\"fileStatus\":\"File in Processing\",\"applicationId\":\"20180119-WIRREN-GGRDU\",\"remarks\":\"New WI Created\",\"fileNo\":\"20180119-WIRREN-R5TC5\",\"pid\":\"ExamInterstate-0000000015-process\",\"comments\":[]}",
        NGSubmitDTO.class);
  }

  String fileStatus;
  String id;
  String applicationId;
  String pid;
  String fileNo;
  List<String> comments;
  String remarks;
  String status;

  @JsonIgnore
  public boolean isSuccess() {
    return "Success".equalsIgnoreCase(getStatus());
  }
}