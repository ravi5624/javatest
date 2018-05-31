package nst.common.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageableDOT extends BaseDTO {

  private static final long serialVersionUID = 1L;

  private long count;
  private List list;

  public static PageableDOT create(long count, List list) {
    return new PageableDOT(count, list);
  }
}