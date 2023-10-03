package de._125m125.supersetapi.spring.boot._2.autoconfiguration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import lombok.Data;

@Data
@ConfigurationProperties(
    prefix = SupersetapiAutoconfigurationProperties.AUTOCONFIGURATION_CONFIGURATION_PREFIX)
public class SupersetapiAutoconfigurationProperties {
  public static final String AUTOCONFIGURATION_CONFIGURATION_PREFIX = "superset.api";

  private String jsonLibrary;
  private String adapterLibrary;
}
