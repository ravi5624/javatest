package nst.common.base;

public interface ChildModel<M extends BaseModel, P> {

  M create(P p);

  Long getId();
}
