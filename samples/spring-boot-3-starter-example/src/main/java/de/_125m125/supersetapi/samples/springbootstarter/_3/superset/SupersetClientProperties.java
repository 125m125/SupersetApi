package de._125m125.supersetapi.samples.springbootstarter._3.superset;

import java.util.Optional;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Data;

@Data
@Configuration
@ConfigurationProperties("superset.client")
public class SupersetClientProperties {
  private String rootUrl;
  private String id;
  private String type;
  private Optional<String> rlsRule = Optional.empty();
}
