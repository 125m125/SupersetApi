package de._125m125.supersetapi.spring.web._5.adapter.security;

import java.io.IOException;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import de._125m125.supersetapi.adapter.security.SupersetSecurityAdapter;
import de._125m125.supersetapi.model.security.AccessAndRefreshToken;
import de._125m125.supersetapi.model.security.LoginRequestData;

public class SupersetAuthenticationInterceptor implements ClientHttpRequestInterceptor {
  private static final Pattern EXPIRATION_PATTERN = Pattern.compile("\"exp\":([0-9]+)");

  private final LoginRequestData loginRequestData;
  private SupersetSecurityAdapter supersetSecurityAdapter;

  private String accessToken;
  private long accessTokenExpiration;
  private String refreshToken;
  private long refreshTokenExpiration;
  private String csrfToken;
  private String cookie;

  public SupersetAuthenticationInterceptor(LoginRequestData loginRequestData) {
    this.loginRequestData = loginRequestData;
  }

  public SupersetAuthenticationInterceptor(SupersetSecurityAdapter supersetSecurityAdapter,
      LoginRequestData loginRequestData) {
    this.supersetSecurityAdapter = supersetSecurityAdapter;
    this.loginRequestData = loginRequestData;
  }

  public void setSupersetSecurityAdapter(SupersetSecurityAdapter supersetSecurityAdapter) {
    this.supersetSecurityAdapter = supersetSecurityAdapter;
  }

  @Override
  public ClientHttpResponse intercept(HttpRequest request, byte[] body,
      ClientHttpRequestExecution execution) throws IOException {
    refreshAccessToken();
    request.getHeaders().setBearerAuth(accessToken);
    if (csrfToken != null) {
      request.getHeaders().set("X-CSRFTOKEN", csrfToken);
    }
    if (cookie != null) {
      request.getHeaders().set(HttpHeaders.COOKIE, cookie);
    }

    ClientHttpResponse response = execution.execute(request, body);
    if (response.getHeaders().containsKey(HttpHeaders.SET_COOKIE)) {
      cookie = response.getHeaders().get(HttpHeaders.SET_COOKIE).get(0).split(";", 2)[0];
    }
    return response;
  }

  private synchronized void refreshAccessToken() {
    if (System.currentTimeMillis() >= accessTokenExpiration) {
      refreshAccessTokenNow();
      refreshCsrfToken();
    }
  }

  private void refreshCsrfToken() {
    this.csrfToken = supersetSecurityAdapter.getCsrfToken().getResult();
  }

  private void refreshAccessTokenNow() {
    if (System.currentTimeMillis() >= refreshTokenExpiration) {
      login();
      return;
    }
    this.accessToken = supersetSecurityAdapter.refreshAccessToken(refreshToken).getAccessToken();
    this.accessTokenExpiration = getExpiryTimestampFromJwt(this.accessToken);
  }

  private void login() {
    AccessAndRefreshToken token = supersetSecurityAdapter.login(loginRequestData);

    this.accessToken = token.getAccessToken();
    this.accessTokenExpiration = getExpiryTimestampFromJwt(this.accessToken);
    this.refreshToken = token.getRefreshToken();
    if (this.refreshToken != null) {
      this.refreshTokenExpiration = getExpiryTimestampFromJwt(this.refreshToken);
    }
  }

  private long getExpiryTimestampFromJwt(String jwt) {
    if (jwt == null) {
      return 0;
    }
    String payload = jwt.split("\\.")[1];
    Matcher matcher =
        EXPIRATION_PATTERN.matcher(new String(Base64.getUrlDecoder().decode(payload)));
    if (matcher.find()) {
      return Long.parseLong(matcher.group(1)) * 1000;
    }
    return 0L;
  }

}
