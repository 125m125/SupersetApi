package de._125m125.supersetapi.jackson.model.security;

import org.mapstruct.Mapper;
import de._125m125.supersetapi.model.security.GuestTokenRequestData;
import de._125m125.supersetapi.model.security.GuestUser;
import de._125m125.supersetapi.model.security.LoginRequestData;
import de._125m125.supersetapi.model.security.Resource;
import de._125m125.supersetapi.model.security.RlsRule;
import de._125m125.supersetapi.model.security.SecurityModelMapper;

@Mapper
public interface JacksonSecurityModelMapper extends SecurityModelMapper {

  @Override
  JacksonGuestUser mapGuestUser(GuestUser guestUser);

  @Override
  JacksonResource mapResource(Resource resource);

  @Override
  JacksonRlsRule mapRlsRule(RlsRule rlsRule);

  @Override
  JacksonLoginRequestData mapLoginRequestData(LoginRequestData loginRequestData);

  @Override
  JacksonGuestTokenRequestData mapGuestTokenRequestData(
      GuestTokenRequestData guestTokenRequestData);
}
