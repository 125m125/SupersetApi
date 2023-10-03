package de._125m125.supersetapi.spring.web._6.adapter.security;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import de._125m125.supersetapi.adapter.security.SupersetSecurityAdapter;
import de._125m125.supersetapi.model.common.CommonModelFactory;
import de._125m125.supersetapi.model.common.Result;
import de._125m125.supersetapi.model.security.AccessAndRefreshToken;
import de._125m125.supersetapi.model.security.AccessToken;
import de._125m125.supersetapi.model.security.GuestTokenRequestData;
import de._125m125.supersetapi.model.security.LoginRequestData;
import de._125m125.supersetapi.model.security.SecurityModelFactory;
import de._125m125.supersetapi.model.security.SecurityModelMapper;
import de._125m125.supersetapi.model.security.Token;
import de._125m125.supersetapi.spring.web._6.AuthenticatedSupersetRestTemplate;
import de._125m125.supersetapi.spring.web._6.BasicSupersetRestTemplate;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ResttemplateSupersetSecurityAdapter implements SupersetSecurityAdapter {

  private final AuthenticatedSupersetRestTemplate authenticatedRestTemplate;
  private final BasicSupersetRestTemplate basicRestTemplate;
  private final CommonModelFactory commonModelFactory;
  private final SecurityModelFactory securityModelFactory;
  private final SecurityModelMapper securityModelMapper;

  @Override
  public Result getCsrfToken() {
    return authenticatedRestTemplate.getRestTemplate().getForObject("/security/csrf_token/",
        commonModelFactory.getResultClass());
  }

  @Override
  public Token createGuestToken(GuestTokenRequestData guestTokenRequestData) {
    return authenticatedRestTemplate.getRestTemplate().postForObject("/security/guest_token/",
        securityModelMapper.mapGuestTokenRequestData(guestTokenRequestData),
        securityModelFactory.getTokenClass());
  }

  @Override
  public AccessAndRefreshToken login(LoginRequestData loginRequestData) {
    return basicRestTemplate.getRestTemplate().postForObject("/security/login",
        securityModelMapper.mapLoginRequestData(loginRequestData),
        securityModelFactory.getAccessAndRefreshTokenClass());
  }

  @Override
  public AccessToken refreshAccessToken(String refreshToken) {
    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(refreshToken);

    return basicRestTemplate.getRestTemplate().postForObject("/security/refresh",
        new HttpEntity<>(null, headers), securityModelFactory.getAccessTokenClass());
  }

}
