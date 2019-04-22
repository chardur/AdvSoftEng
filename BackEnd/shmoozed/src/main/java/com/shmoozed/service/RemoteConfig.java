package com.shmoozed.service;

import java.util.stream.Stream;

import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.util.stream.Collectors.toList;

@Configuration
public class RemoteConfig {

  @Bean
  public RestTemplateCustomizer globalInterceptorCustomizer(LoggingRequestInterceptor loggingRequestInterceptor) {
    return restTemplate -> restTemplate.getInterceptors()
      .addAll(Stream.of(loggingRequestInterceptor)
                .collect(toList()));
  }

}
