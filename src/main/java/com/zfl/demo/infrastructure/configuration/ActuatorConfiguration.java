package com.zfl.demo.infrastructure.configuration;

import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActuatorConfiguration {

    @Bean
    public HttpTraceRepository HttpTraceRepository() {
        return new InMemoryHttpTraceRepository();
    }
}