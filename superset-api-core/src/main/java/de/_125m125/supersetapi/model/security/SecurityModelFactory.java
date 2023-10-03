package de._125m125.supersetapi.model.security;

public interface SecurityModelFactory {

  GuestUser createGuestUser();

  GuestUser createGuestUser(String username);

  GuestUser createGuestUser(String username, String firstname, String lastname);

  Class<? extends GuestUser> getGuestUserClass();

  Resource createResource(String id, String type);

  Class<? extends Resource> getResourceClass();

  RlsRule createRlsRule(String clause);

  RlsRule createRlsRule(String clause, int dataset);

  Class<? extends RlsRule> getRlsRuleClass();

  LoginRequestData createLoginRequestData(String username, String password, String provider,
      boolean refresh);

  Class<? extends LoginRequestData> getLoginRequestClass();

  Token createToken(String token);

  Class<? extends Token> getTokenClass();

  AccessAndRefreshToken createAccessAndRefreshToken(String accessToken);

  AccessAndRefreshToken createAccessAndRefreshToken(String accessToken, String refreshToken);

  Class<? extends AccessAndRefreshToken> getAccessAndRefreshTokenClass();

  AccessToken createAccessToken(String accessToken);

  Class<? extends AccessToken> getAccessTokenClass();

  GuestTokenRequestDataBuilder<? extends GuestTokenRequestData, ? extends Resource, ? extends RlsRule, ? extends GuestUser> createGuestTokenRequestDataBuilder();

  GuestTokenRequestDataBuilder<? extends GuestTokenRequestData, ? extends Resource, ? extends RlsRule, ? extends GuestUser> createGuestTokenRequestDataBuilder(
      String username);

  GuestTokenRequestDataBuilder<? extends GuestTokenRequestData, ? extends Resource, ? extends RlsRule, ? extends GuestUser> createGuestTokenRequestDataBuilder(
      String username, String firstname, String lastname);

  Class<? extends GuestTokenRequestData> getGuestTokenRequestData();

  static interface GuestTokenRequestDataBuilder<T extends GuestTokenRequestData, U extends Resource, V extends RlsRule, W extends GuestUser> {
    GuestTokenRequestDataBuilder<T, U, V, W> setGuestUser();

    GuestTokenRequestDataBuilder<T, U, V, W> setGuestUser(String username);

    GuestTokenRequestDataBuilder<T, U, V, W> setGuestUser(String username, String firstname,
        String lastname);

    GuestTokenRequestDataBuilder<T, U, V, W> addResource(String id, String type);

    GuestTokenRequestDataBuilder<T, U, V, W> addRlsRule(String clause);

    GuestTokenRequestDataBuilder<T, U, V, W> addRlsRule(String clause, int dataset);

    T build();
  }
}
