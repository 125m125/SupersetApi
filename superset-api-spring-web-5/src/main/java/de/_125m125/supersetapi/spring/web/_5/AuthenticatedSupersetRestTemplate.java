package de._125m125.supersetapi.spring.web._5;

import org.springframework.web.client.RestTemplate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthenticatedSupersetRestTemplate {
  private final RestTemplate restTemplate;
}
