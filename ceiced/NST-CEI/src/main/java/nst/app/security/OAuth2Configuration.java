package nst.app.security;

import javax.sql.DataSource;
import nst.common.security.CustomAuthenticationEntryPoint;
import nst.common.security.CustomLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@Configuration
public class OAuth2Configuration {

  @Configuration
  @EnableResourceServer
  protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Autowired
    private CustomLogoutSuccessHandler customLogoutSuccessHandler;

    @Override
    public void configure(HttpSecurity http) throws Exception {
      http
          .exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint)
          .and()
          .logout().logoutUrl("/oauth/logout").logoutSuccessHandler(customLogoutSuccessHandler)
          .and()
          .csrf().disable()
          .headers().frameOptions().disable()
          .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
          .and()
          .authorizeRequests()
          .antMatchers("/public/**").permitAll()
          .antMatchers("/open/**").permitAll()
          .antMatchers("/api/**").authenticated();
    }
  }

  @Configuration
  @EnableAuthorizationServer
  protected static class AuthorizationServerConfiguration extends
      AuthorizationServerConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Bean
    public TokenStore tokenStore() {
      return new JdbcTokenStore(dataSource);
      //return new InMemoryTokenStore();
    }

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints)
        throws Exception {
      endpoints
          .pathMapping("/oauth/token", "/authorize")//To change Token URL
          .tokenStore(tokenStore())
          //.tokenEnhancer(tokenEnhancer())
          .authenticationManager(authenticationManager);
    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
      return new CustomTokenEnhancer();
    }

    /*
    //To make Client In-Line
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
      clients
          .inMemory()
          .withClient(propertyResolver.getProperty(PROP_CLIENTID))
          .scopes("read", "write")
          .authorities(Authorities.ROLE_ADMIN.name(), Authorities.ROLE_USER.name())
          .authorizedGrantTypes("password", "refresh_token")
          .secret(propertyResolver.getProperty(PROP_SECRET))
          .accessTokenValiditySeconds(
              propertyResolver.getProperty(PROP_TOKEN_VALIDITY_SECONDS, Integer.class, 1800));
    }
    */

    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
      clients.withClientDetails(clientDetailsService);
    }

    @Autowired
    private MyClientDetailsService clientDetailsService;
  }

  @Configuration
  public static class MyClientDetailsService implements ClientDetailsService {

    @Override
    public ClientDetails loadClientByClientId(String clientId)
        throws ClientRegistrationException {
      BaseClientDetails details = new BaseClientDetails(clientId, "resourceId", "write,read",
          "password,refresh_token", "ROLE_ADMIN,ROLE_USER", null);
      details.setClientSecret("secret");
      details.setAccessTokenValiditySeconds(1000000);
      details.setRefreshTokenValiditySeconds(1000000);
      return details;
    }
  }
}