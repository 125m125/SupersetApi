package de._125m125.supersetapi.samples.springbootstarter._2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class SpringBoot2StarterApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBoot2StarterApplication.class, args);
  }
}
