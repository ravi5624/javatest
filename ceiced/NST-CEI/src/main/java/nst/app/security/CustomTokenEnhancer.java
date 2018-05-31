package nst.app.security;

import java.util.HashMap;
import java.util.Map;
import nst.app.service.PortalUserService;
import nst.common.security.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

@Component
public class CustomTokenEnhancer implements TokenEnhancer {

  @Autowired
  PortalUserService portalUserService;

  @Override
  public OAuth2AccessToken enhance(OAuth2AccessToken accessToken,
      OAuth2Authentication authentication) {
    LoginUser user = (LoginUser) authentication.getPrincipal();
    final Map<String, Object> additionalInfo = new HashMap<>();
    additionalInfo.put("detail", portalUserService.getDetail(user.getUserId()));

    ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
    return accessToken;
  }
}