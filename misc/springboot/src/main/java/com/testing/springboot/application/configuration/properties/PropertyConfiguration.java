package com.testing.springboot.application.configuration.properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({ServiceProperties.class})
public class PropertyConfiguration {

}
