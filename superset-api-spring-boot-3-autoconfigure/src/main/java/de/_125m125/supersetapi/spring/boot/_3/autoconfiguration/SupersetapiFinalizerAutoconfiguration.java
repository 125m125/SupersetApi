package de._125m125.supersetapi.spring.boot._3.autoconfiguration;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import de._125m125.supersetapi.adapter.DelegatingSupersetAdapter;
import de._125m125.supersetapi.adapter.SupersetAdapter;
import de._125m125.supersetapi.adapter.security.SupersetSecurityAdapter;
import de._125m125.supersetapi.model.DelegatingSupersetFactory;
import de._125m125.supersetapi.model.SupersetFactory;
import de._125m125.supersetapi.model.common.CommonModelFactory;
import de._125m125.supersetapi.model.security.SecurityModelFactory;

@AutoConfiguration
public class SupersetapiFinalizerAutoconfiguration {

  @Bean
  @Primary
  @ConditionalOnMissingBean
  SupersetAdapter supersetAdapter(SupersetSecurityAdapter security) {
    return new DelegatingSupersetAdapter(security);
  }

  @Bean
  @Primary
  @ConditionalOnMissingBean
  SupersetFactory supersetFactory(CommonModelFactory commonModelFactory,
      SecurityModelFactory securityModelFactory) {
    return new DelegatingSupersetFactory(commonModelFactory, securityModelFactory);
  }
}
