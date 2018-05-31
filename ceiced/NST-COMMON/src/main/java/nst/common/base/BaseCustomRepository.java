package nst.common.base;

import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class BaseCustomRepository {

  @Autowired
  protected EntityManager entityManager;

  @Autowired
  protected JdbcTemplate jdbcTemplate;
}
