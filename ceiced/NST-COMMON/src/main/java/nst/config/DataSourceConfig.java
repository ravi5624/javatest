package nst.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.time.ZonedDateTime;
import java.util.GregorianCalendar;
import java.util.Properties;
import javax.sql.DataSource;
import nst.common.security.LoginUser;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaAuditing(dateTimeProviderRef = "auditingDateTimeProvider")
@EnableTransactionManagement
@DependsOn({"systemConfiguration"})
public class DataSourceConfig {

  private static final String BASE_PACKAGE = "nst";
  //public static final String HIBERNATE_HBM2DDL_AUTO = "update";
//  public static final String HIBERNATE_HBM2DDL_AUTO = "create";
  public static final String HIBERNATE_HBM2DDL_AUTO = "none";

  @Value("${db.url}")
  public String url;

  @Value("${db.user}")
  public String user;

  @Value("${db.password}")
  public String password;

  @Value("${hibernate.show_sql:false}")
  public boolean show_sql;

  private DataSource createDataSourcePooled() {
    MyLogger.log("DataSourceConfig", "DB Connection to : %s", url);
    try {
      ComboPooledDataSource ds = new ComboPooledDataSource();
      ds.setJdbcUrl(url);
      ds.setDriverClass("org.postgresql.Driver");
      ds.setUser(user);
      ds.setPassword(password);
      ds.setMaxPoolSize(10);
      ds.setInitialPoolSize(2);
      ds.setAcquireIncrement(2);
      ds.setPreferredTestQuery("SELECT 1");
      ds.setMaxIdleTime(10);
      ds.setUnreturnedConnectionTimeout(100);
      ds.setDebugUnreturnedConnectionStackTraces(Boolean.TRUE);
      ds.setAutoCommitOnClose(Boolean.TRUE);
      return ds;
    } catch (Exception e) {
      MyLogger.log("DataSourceConfig:createDataSourcePooled", e.getMessage());
      MyLogger.logError(e);
      System.exit(0);
    }
    return null;
  }

  @Bean
  public DataSource createDataSource() {
    return createDataSourcePooled();
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
    entityManagerFactoryBean.setDataSource(createDataSource());
    entityManagerFactoryBean
        .setPersistenceProviderClass(HibernatePersistenceProvider.class);
    entityManagerFactoryBean.setPackagesToScan(BASE_PACKAGE);
    entityManagerFactoryBean.setPersistenceUnitName("erp");
    entityManagerFactoryBean.setJpaProperties(jpaProperties());

    return entityManagerFactoryBean;
  }

  private Properties jpaProperties() {
    Properties jpaProperties = new Properties();
    jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");
    jpaProperties.put("hibernate.show_sql", show_sql);
    jpaProperties.put("hibernate.jdbc.batch_size", 80);
    jpaProperties.put("hibernate.hbm2ddl.auto", SystemConfiguration.IS_DEV_ENV ? HIBERNATE_HBM2DDL_AUTO : "none");
    jpaProperties.put("hibernate.order_inserts", "true");
    jpaProperties.put("hibernate.order_updates", "true");
    return jpaProperties;
  }

  @Bean
  public JpaTransactionManager transactionManager() {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(entityManagerFactory()
        .getObject());
    return transactionManager;
  }

  @Bean
  public JdbcTemplate mysqlJdbcTemplate() {
    return new JdbcTemplate(createDataSource());
  }

  @Bean
  public NamedParameterJdbcTemplate mysqlNamedParameterJdbcTemplate() {
    return new NamedParameterJdbcTemplate(createDataSource());
  }

  @Bean
  public DateTimeProvider auditingDateTimeProvider() {
    return () -> GregorianCalendar.from(ZonedDateTime.now());
  }

  @Bean
  public AuditorAware<String> auditingAuditorProvider() {
    return () -> {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

      if (authentication == null || !authentication.isAuthenticated()) {
        return "No User";
      }
      if (authentication.getPrincipal() instanceof LoginUser) {
        return ((LoginUser) authentication.getPrincipal()).getUsername();
      }
      return "No User";
    };
  }
}