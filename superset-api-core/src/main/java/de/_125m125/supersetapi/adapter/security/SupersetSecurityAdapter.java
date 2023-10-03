package de._125m125.supersetapi.adapter.security;

import de._125m125.supersetapi.model.common.Result;
import de._125m125.supersetapi.model.security.AccessAndRefreshToken;
import de._125m125.supersetapi.model.security.AccessToken;
import de._125m125.supersetapi.model.security.GuestTokenRequestData;
import de._125m125.supersetapi.model.security.LoginRequestData;
import de._125m125.supersetapi.model.security.Token;

public interface SupersetSecurityAdapter {
  Result getCsrfToken();

  Token createGuestToken(GuestTokenRequestData guestTokenRequestData);

  AccessAndRefreshToken login(LoginRequestData loginRequestData);

  AccessToken refreshAccessToken(String refreshToken);
}
