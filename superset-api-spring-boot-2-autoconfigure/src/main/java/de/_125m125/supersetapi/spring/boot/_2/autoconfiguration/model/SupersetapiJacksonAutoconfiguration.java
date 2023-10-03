package de._125m125.supersetapi.spring.boot._2.autoconfiguration.model;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import com.fasterxml.jackson.databind.ObjectMapper;
import de._125m125.supersetapi.jackson.model.common.JacksonCommonModelFactory;
import de._125m125.supersetapi.jackson.model.security.JacksonSecurityModelFactory;
import de._125m125.supersetapi.jackson.model.security.JacksonSecurityModelMapperImpl;
import de._125m125.supersetapi.model.common.CommonModelFactory;
import de._125m125.supersetapi.model.security.SecurityModelFactory;
import de._125m125.supersetapi.model.security.SecurityModelMapper;
import de._125m125.supersetapi.spring.boot._2.autoconfiguration.SupersetapiAutoconfiguration;
import de._125m125.supersetapi.spring.boot._2.autoconfiguration.SupersetapiAutoconfigurationProperties;
import de._125m125.supersetapi.spring.boot._2.autoconfiguration.SupersetapiFinalizerAutoconfiguration;

@AutoConfiguration(after = SupersetapiAutoconfiguration.class,
    before = SupersetapiFinalizerAutoconfiguration.class)
@ConditionalOnClass({ObjectMapper.class})
@ConditionalOnProperty(
    prefix = SupersetapiAutoconfigurationProperties.AUTOCONFIGURATION_CONFIGURATION_PREFIX,
    name = "json-library", havingValue = "jackson", matchIfMissing = true)
public class SupersetapiJacksonAutoconfiguration {

  @Bean
  @ConditionalOnMissingBean
  SecurityModelFactory jacksonSecurityModelFactory() {
    return new JacksonSecurityModelFactory();
  }

  @Bean
  @ConditionalOnMissingBean
  CommonModelFactory jacksonCommonModelFactory() {
    return new JacksonCommonModelFactory();
  }

  @Bean
  @ConditionalOnMissingBean
  SecurityModelMapper jacksonSecurityModelMapper() {
    return new JacksonSecurityModelMapperImpl();
  }
}
