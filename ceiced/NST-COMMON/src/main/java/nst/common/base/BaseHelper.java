package nst.common.base;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public abstract class BaseHelper<M extends BaseModel, P extends BaseModelDTO> {

  public abstract M toModel(P p);

  public M toModel(M to, M from) {
    return to;
  }

  public abstract M toModel(M m, P p);

  public abstract M blankModel(Object o);

  public List<P> fromModel(Iterable<M> ms) {
    if (ms == null) {
      return Collections.emptyList();
    }
    return StreamSupport.stream(ms.spliterator(), false).map(this::fromModel)
        .collect(Collectors.toList());
  }

  public abstract P fromModel(M m);

  public BaseDTO toNGDTO(M m) {
    return null;
  }
}
