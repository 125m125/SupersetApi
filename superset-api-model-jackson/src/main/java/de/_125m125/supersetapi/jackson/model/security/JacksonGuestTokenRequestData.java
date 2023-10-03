package de._125m125.supersetapi.jackson.model.security;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import de._125m125.supersetapi.model.security.GuestTokenRequestData;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

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
