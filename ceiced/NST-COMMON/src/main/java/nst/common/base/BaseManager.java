package nst.common.base;

import nst.common.Manager;
import nst.common.error.AppException;
import nst.kernal.SystemConstants.Model;

import java.util.Collection;

public abstract class BaseManager<T extends BaseModel> implements Manager {

  public T save(T t) {
    return getRepository().save(t);
  }

  public Iterable<T> save(Collection<T> t) {
    return getRepository().save(t);
  }

  public T update(T t) {
    return getRepository().save(t);
  }

  public void delete(T t) {
    getRepository().delete(t);
  }

  public void delete(Long t) {
    getRepository().delete(t);
  }

  public Iterable<T> getAll() {
    return getRepository().findAll();
  }

  public T loadById(Long id, Integer version) {
    if (id == null || version == null) {
      throw AppException.createWithMessageCode(Model.INVALID_REQUEST, Model.INVALID_APPLICATION);
    }
    T t = getRepository().findOne(id);
    if (t == null) {
      throw AppException.createWithMessageCode(Model.INVALID_REQUEST, Model.INVALID_APPLICATION);
    }

//    if (t.getVersion().equals(version) == false) {
//      throw AppException.createWithMessageCode(Model.INVALID_VERSION, Model.INVALID_APPLICATION);
//    }
    return t;
  }

  public T loadById(Long id) {
    if (id == null) {
      throw AppException.createWithMessageCode(Model.INVALID_REQUEST, Model.INVALID_APPLICATION);
    }
    T t = getRepository().findOne(id);
    if (t == null) {
      throw AppException.createWithMessageCode(Model.INVALID_REQUEST, Model.INVALID_APPLICATION);
    }
    return t;
  }

  public T getById(Long id, Integer version) {
    if (id == null || version == null) {
      throw AppException.createWithMessageCode(Model.INVALID_REQUEST, Model.INVALID_APPLICATION);
    }
    T t = getRepository().findOne(id);
    if (t.getVersion().equals(version) == false) {
      throw AppException.createWithMessageCode(Model.INVALID_VERSION, Model.INVALID_APPLICATION);
    }
    return t;
  }

  public T getById(Long id) {
    if (id == null) {
      throw AppException.createWithMessageCode(Model.INVALID_REQUEST, Model.INVALID_APPLICATION);
    }
    return getRepository().findOne(id);
  }

  public T get(String identifier1, String identifier2) {
    return null;
  }

  public T findForm(Long formId) {
    return null;
  }

  public abstract BaseRepository<T> getRepository();

  public T submitForm(T t){
    return t;
  }
}