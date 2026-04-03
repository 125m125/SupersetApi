package de._125m125.supersetapi.jackson3.model.security;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import de._125m125.supersetapi.model.security.GuestUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(Include.NON_NULL)
public class JacksonGuestUser implements GuestUser {
  private String username;

  private String firstName;

  private String lastName;

  public JacksonGuestUser(String username) {
    super();
    this.username = username;
  }

}
