package com.zfl.demo.infrastructure.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;


@ConfigurationProperties(prefix = "custom")
@Data
public class CustomConfiguration {

    private List<String> ignores;
}
