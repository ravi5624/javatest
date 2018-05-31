package nst.util;

import java.util.Optional;
import nst.kernal.SystemConstants;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class RepoUtil {

  public static final int PAGE_SIZE = 20;

  public static Pageable pagination() {
    return pagination(SystemConstants.ONE_VALUE, PAGE_SIZE);
  }

  public static Pageable pagination(Integer pageNo, Integer pageSize) {
    if (pageNo != null && pageNo < 1) {
      pageNo = 1;
    }

    return new PageRequest(
        Optional.ofNullable(pageNo).orElse(SystemConstants.ONE_VALUE) - SystemConstants.ONE_VALUE,
        Optional.ofNullable(pageSize).orElse(PAGE_SIZE));
  }
}
