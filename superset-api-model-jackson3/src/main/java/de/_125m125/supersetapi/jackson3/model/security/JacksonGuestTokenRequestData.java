package de._125m125.supersetapi.jackson3.model.security;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import de._125m125.supersetapi.model.security.GuestTokenRequestData;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(Include.NON_NULL)
public class JacksonGuestTokenRequestData implements GuestTokenRequestData {
  @NonNull
  private JacksonResource[] resources;
  @NonNull
  private JacksonRlsRule[] rls;
  @NonNull
  private JacksonGuestUser user;
}
