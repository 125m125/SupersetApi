package de._125m125.supersetapi.jackson.model.security;

import de._125m125.supersetapi.model.security.SecurityModelFactory;
import de._125m125.supersetapi.model.security.SimpleGuestTokenRequestDataBuilder;
import de._125m125.supersetapi.model.security.SimpleGuestTokenRequestDataBuilder.GuestUserBuilder;

public class JacksonSecurityModelFactory implements SecurityModelFactory {

  @Override
  public JacksonGuestUser createGuestUser() {
    return new JacksonGuestUser();
  }

  @Override
  public JacksonGuestUser createGuestUser(String username) {
    return new JacksonGuestUser(username);
  }

  @Override
  public JacksonGuestUser createGuestUser(String username, String firstname, String lastname) {
    return new JacksonGuestUser(username, firstname, lastname);
  }

  @Override
  public Class<? extends JacksonGuestUser> getGuestUserClass() {
    return JacksonGuestUser.class;
  }

  @Override
  public JacksonResource createResource(String id, String type) {
    return new JacksonResource(id, type);
  }

  @Override
  public Class<? extends JacksonResource> getResourceClass() {
    return JacksonResource.class;
  }

  @Override
  public JacksonRlsRule createRlsRule(String clause) {
    return new JacksonRlsRule(clause);
  }

  @Override
  public JacksonRlsRule createRlsRule(String clause, int dataset) {
    return new JacksonRlsRule(clause, dataset);
  }

  @Override
  public Class<? extends JacksonRlsRule> getRlsRuleClass() {
    return JacksonRlsRule.class;
  }

  @Override
  public JacksonLoginRequestData createLoginRequestData(String username, String password,
      String provider, boolean refresh) {
    return new JacksonLoginRequestData(username, password, provider, refresh);
  }

  @Override
  public Class<? extends JacksonLoginRequestData> getLoginRequestClass() {
    return JacksonLoginRequestData.class;
  }

  @Override
  public JacksonToken createToken(String token) {
    return new JacksonToken(token);
  }

  @Override
  public Class<? extends JacksonToken> getTokenClass() {
    return JacksonToken.class;
  }

  @Override
  public JacksonAccessAndRefreshToken createAccessAndRefreshToken(String accessToken) {
    return new JacksonAccessAndRefreshToken(accessToken);
  }

  @Override
  public JacksonAccessAndRefreshToken createAccessAndRefreshToken(String accessToken,
      String refreshToken) {
    return new JacksonAccessAndRefreshToken(accessToken, refreshToken);
  }

  @Override
  public Class<? extends JacksonAccessAndRefreshToken> getAccessAndRefreshTokenClass() {
    return JacksonAccessAndRefreshToken.class;
  }

  @Override
  public JacksonAccessToken createAccessToken(String accessToken) {
    return new JacksonAccessToken(accessToken);
  }

  @Override
  public Class<? extends JacksonAccessToken> getAccessTokenClass() {
    return JacksonAccessToken.class;
  }

  public JacksonGuestTokenRequestData createGuestTokenRequestData(JacksonResource[] resources,
      JacksonRlsRule[] rlsRules, JacksonGuestUser guestUser) {
    return new JacksonGuestTokenRequestData(resources, rlsRules, guestUser);
  }

  @Override
  public GuestTokenRequestDataBuilder<? extends JacksonGuestTokenRequestData, ? extends JacksonResource, ? extends JacksonRlsRule, ? extends JacksonGuestUser> createGuestTokenRequestDataBuilder() {
    return createGuestTokenRequestDataBuilder(null);
  }

  @Override
  public GuestTokenRequestDataBuilder<? extends JacksonGuestTokenRequestData, ? extends JacksonResource, ? extends JacksonRlsRule, ? extends JacksonGuestUser> createGuestTokenRequestDataBuilder(
      String username) {
    return createGuestTokenRequestDataBuilder(username, null, null);
  }

  @Override
  public GuestTokenRequestDataBuilder<? extends JacksonGuestTokenRequestData, ? extends JacksonResource, ? extends JacksonRlsRule, ? extends JacksonGuestUser> createGuestTokenRequestDataBuilder(
      String firstname, String lastname, String username) {
    SimpleGuestTokenRequestDataBuilder<JacksonGuestTokenRequestData, JacksonResource, JacksonRlsRule, JacksonGuestUser> builder =
        new SimpleGuestTokenRequestDataBuilder<JacksonGuestTokenRequestData, JacksonResource, JacksonRlsRule, JacksonGuestUser>(
            new GuestUserBuilder<JacksonGuestUser>() {
              @Override
              public JacksonGuestUser createGuestUser() {
                return JacksonSecurityModelFactory.this.createGuestUser();
              }

              @Override
              public JacksonGuestUser createGuestUser(String username) {
                return JacksonSecurityModelFactory.this.createGuestUser(username);
              }

              @Override
              public JacksonGuestUser createGuestUser(String username, String firstname,
                  String lastname) {
                return JacksonSecurityModelFactory.this.createGuestUser(username, firstname,
                    lastname);
              }
            }, this::createResource, this::createRlsRule, this::createRlsRule,
            this::createGuestTokenRequestData, new JacksonResource[0], new JacksonRlsRule[0]);
    builder.setGuestUser(firstname, lastname, username);
    return builder;
  }

  @Override
  public Class<? extends JacksonGuestTokenRequestData> getGuestTokenRequestData() {
    return JacksonGuestTokenRequestData.class;
  }

}
