package de._125m125.supersetapi.jackson.model.security;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import de._125m125.supersetapi.jackson.util.Default;
import de._125m125.supersetapi.model.security.RlsRule;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(Include.NON_NULL)
public class JacksonRlsRule implements RlsRule {
  @NonNull
  private String clause;

  private Integer dataset;

  @Default
  public JacksonRlsRule(@NonNull String clause, Integer dataset) {
    this.clause = clause;
    this.dataset = dataset;
  }

}
