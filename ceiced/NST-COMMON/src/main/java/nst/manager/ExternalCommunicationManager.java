package nst.manager;

import nst.common.base.BaseManager;
import nst.common.base.BaseRepository;
import nst.kernal.ExternalCommunication;
import nst.repo.ExternalCommunicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExternalCommunicationManager extends BaseManager<ExternalCommunication> {

  @Autowired
  ExternalCommunicationRepository repository;

  @Override
  public BaseRepository<ExternalCommunication> getRepository() {
    return repository;
  }
}