package nst.app.manager;

import nst.app.model.OwnerDetail;
import nst.app.repo.OwnerDetailRepository;
import nst.common.base.BaseManager;
import nst.common.base.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OwnerDetailManager extends BaseManager<OwnerDetail> {

  @Autowired
  OwnerDetailRepository repository;

  public Iterable<OwnerDetail> getAll() {
    return repository.findAll();
  }

  @Override
  public BaseRepository<OwnerDetail> getRepository() {
    return repository;
  }
}