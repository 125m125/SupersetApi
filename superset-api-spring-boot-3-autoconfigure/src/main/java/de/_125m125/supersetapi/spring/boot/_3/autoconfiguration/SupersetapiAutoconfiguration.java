package de._125m125.supersetapi.spring.boot._3.autoconfiguration;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class SupersetapiAutoconfiguration {

  @Bean
  @ConditionalOnMissingBean
  @ConfigurationProperties("superset")
  SupersetProperties supersetProperties() {
    return new SupersetProperties();
  }

}
