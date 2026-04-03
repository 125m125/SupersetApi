package de._125m125.supersetapi.spring.boot._4.autoconfiguration.adapter;

import java.util.Optional;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.restclient.RestTemplateBuilder;
import org.springframework.boot.restclient.autoconfigure.RestTemplateAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import de._125m125.supersetapi.adapter.security.SupersetSecurityAdapter;
import de._125m125.supersetapi.model.common.CommonModelFactory;
import de._125m125.supersetapi.model.security.SecurityModelFactory;
import de._125m125.supersetapi.model.security.SecurityModelMapper;
import de._125m125.supersetapi.spring.boot._4.autoconfiguration.SupersetProperties;
import de._125m125.supersetapi.spring.boot._4.autoconfiguration.SupersetapiAutoconfiguration;
import de._125m125.supersetapi.spring.boot._4.autoconfiguration.SupersetapiAutoconfigurationProperties;
import de._125m125.supersetapi.spring.boot._4.autoconfiguration.SupersetapiFinalizerAutoconfiguration;
import de._125m125.supersetapi.spring.web._7.AuthenticatedSupersetRestTemplate;
import de._125m125.supersetapi.spring.web._7.BasicSupersetRestTemplate;
import de._125m125.supersetapi.spring.web._7.adapter.security.ResttemplateSupersetSecurityAdapter;
import de._125m125.supersetapi.spring.web._7.adapter.security.SupersetAuthenticationInterceptor;
import lombok.AllArgsConstructor;

@AutoConfiguration(
    after = {RestTemplateAutoConfiguration.class, SupersetapiAutoconfiguration.class},
    before = SupersetapiFinalizerAutoconfiguration.class)
@ConditionalOnBean(RestTemplateBuilder.class)
@ConditionalOnProperty(
    prefix = SupersetapiAutoconfigurationProperties.AUTOCONFIGURATION_CONFIGURATION_PREFIX,
    name = "adapter-library", havingValue = "resttemplate", matchIfMissing = true)
@AllArgsConstructor
public class SupersetapiResttemplateAutoconfiguration {

  private final SupersetProperties supersetProperties;

  @Bean
  @ConditionalOnMissingBean
  BasicSupersetRestTemplate basicSupersetRestTemplate(RestTemplateBuilder builder) {
    return new BasicSupersetRestTemplate(
        builder.rootUri(supersetProperties.getRootUrl() + "/api/v1").build());
  }

  @Bean
  @ConditionalOnMissingBean
  SupersetAuthenticationInterceptor supersetAuthenticationInterceptor(
      SecurityModelFactory securityModelFactory) {
    SupersetAuthenticationInterceptor supersetAuthenticationInterceptor =
        new SupersetAuthenticationInterceptor(securityModelFactory.createLoginRequestData(
            supersetProperties.getApi().getUsername(), supersetProperties.getApi().getPassword(),
            supersetProperties.getApi().getProvider(), true));
    return supersetAuthenticationInterceptor;
  }

  @Bean
  @ConditionalOnMissingBean
  AuthenticatedSupersetRestTemplate authenticatedSupersetRestTemplate(RestTemplateBuilder builder,
      SecurityModelFactory securityModelFactory,
      SupersetAuthenticationInterceptor supersetAuthenticationInterceptor) {
    return new AuthenticatedSupersetRestTemplate(
        builder.rootUri(supersetProperties.getRootUrl() + "/api/v1")

            .requestFactory(
                () -> new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()))
            .additionalInterceptors(supersetAuthenticationInterceptor)

            .build());
  }

  @Bean
  @ConditionalOnMissingBean
  SupersetSecurityAdapter supersetSecurityAdapter(BasicSupersetRestTemplate basicRestTemplate,
      AuthenticatedSupersetRestTemplate authenticatedRestTemplate,
      CommonModelFactory commonModelFactory, SecurityModelFactory securityModelFactory,
      SecurityModelMapper securityModelMapper,
      Optional<SupersetAuthenticationInterceptor> supersetAuthenticationInterceptor) {
    SupersetSecurityAdapter securityAdapter =
        new ResttemplateSupersetSecurityAdapter(authenticatedRestTemplate, basicRestTemplate,
            commonModelFactory, securityModelFactory, securityModelMapper);
    supersetAuthenticationInterceptor.ifPresent(i -> i.setSupersetSecurityAdapter(securityAdapter));
    return securityAdapter;
  }

}
