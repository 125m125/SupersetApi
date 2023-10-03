package de._125m125.supersetapi.model.security;

public interface SecurityModelMapper {

  GuestUser mapGuestUser(GuestUser guestUser);

  Resource mapResource(Resource resource);

  RlsRule mapRlsRule(RlsRule rlsRule);

  LoginRequestData mapLoginRequestData(LoginRequestData loginRequestData);

  GuestTokenRequestData mapGuestTokenRequestData(GuestTokenRequestData guestTokenRequestData);
}
