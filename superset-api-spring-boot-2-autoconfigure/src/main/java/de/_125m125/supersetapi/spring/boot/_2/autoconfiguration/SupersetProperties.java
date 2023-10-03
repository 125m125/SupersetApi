package de._125m125.supersetapi.spring.boot._2.autoconfiguration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Data;

@Data
@Configuration
@ConfigurationProperties
public class SupersetProperties {
  private String rootUrl;
  private ApiProperties api;

  @Data
  public static class ApiProperties {
    private String apiPath = "/api/v1";
    private String username;
    private String password;
    private String provider = "db";
  }
}
