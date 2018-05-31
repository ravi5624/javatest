package nst.common.base;

import java.util.Collection;
import nst.common.Service;

public abstract class BaseService<T extends BaseModel> implements Service {

  public T save(T t) {
    return getManager().save(t);
  }

  public Iterable<T> save(Collection<T> t) {
    return getManager().save(t);
  }

  public T update(T t) {
    return getManager().save(t);
  }

  public void delete(T t) {
    getManager().delete(t);
  }

  public void delete(Long t) {
    getManager().delete(t);
  }

  public Iterable<T> getAll() {
    return getManager().getAll();
  }

  public T loadById(Long id, Integer version) {
    return getManager().loadById(id, version);
  }

  public T loadById(Long id) {
    return getManager().loadById(id);
  }

  public T getById(Long id, Integer version) {
    return getManager().getById(id, version);
  }

  public T getById(Long id) {
    return getManager().getById(id);
  }

  public T get(String identifier1,String identifier2) {
    return getManager().get(identifier1, identifier2);
  }

  public abstract BaseManager<T> getManager();
}