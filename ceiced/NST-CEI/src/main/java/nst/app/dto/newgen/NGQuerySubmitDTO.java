package nst.app.dto.newgen;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import nst.common.base.BaseDTO;
import nst.util.JSONUtil;

@Data
public class NGQuerySubmitDTO extends BaseDTO {

  public static void main(String[] args) {
    System.out.println("FromJson(); = " + FromJson());
  }

  public static NGQuerySubmitDTO FromJson() {
    return JSONUtil.fromJSON(
        "{\n"
            + "  \"Status\": \"Success\",\n"
            + "  \"Remarks\": \"Query Answered Successfully\"\n"
            + "}",
        NGQuerySubmitDTO.class);
  }

  @JsonProperty("Remarks")
  String remarks;

  @JsonProperty("Status")
  String status;

  @JsonIgnore
  public boolean isSuccess() {
    return "Success".equalsIgnoreCase(getStatus());
  }
}