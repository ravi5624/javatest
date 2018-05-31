package nst.app.repo;

import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Query;
import nst.app.model.Locale;
import nst.common.base.BaseCustomRepository;
import org.springframework.stereotype.Repository;

@Repository
public class LocaleRepositoryImpl extends BaseCustomRepository implements LocaleRepository {

  @Override
  public List<Locale> findAll() {
    Query query = entityManager.createNativeQuery("SELECT id, key, lang, screen, value FROM locale_message");
    List resultList = query.getResultList();
    return (List<Locale>) resultList.stream().map(result -> {
      Object[] obj = (Object[]) result;
      Locale locale = new Locale();
      locale.setId(((Number)obj[0]).longValue());
      locale.setKey(((String)obj[1]));
      locale.setLang(((String)obj[2]));
      locale.setScreen(((String)obj[3]));
      locale.setValue(((String)obj[4]));
      return locale;
    }).collect(Collectors.toList());
  }
}