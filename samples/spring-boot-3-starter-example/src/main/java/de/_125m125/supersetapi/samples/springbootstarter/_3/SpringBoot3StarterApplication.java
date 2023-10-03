package de._125m125.supersetapi.samples.springbootstarter._3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class SpringBoot3StarterApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBoot3StarterApplication.class, args);
  }
}
