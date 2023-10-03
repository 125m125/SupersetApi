package de._125m125.supersetapi.spring.web._6;

import org.springframework.web.client.RestTemplate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BasicSupersetRestTemplate {
  private final RestTemplate restTemplate;
}
