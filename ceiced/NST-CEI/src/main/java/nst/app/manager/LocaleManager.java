package nst.app.manager;

import java.util.List;
import nst.app.model.Locale;
import nst.app.repo.LocaleRepository;
import nst.common.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LocaleManager implements Manager {

  @Autowired
  LocaleRepository localeRepository;

  public List<Locale> findAll() {
    return localeRepository.findAll();
  }
}