package com.testing.springboot.application.configuration.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationProperties {

    @Value("${application.name}")
    private String applicationName;

    @Value("${application.mode}")
    private String applicationMode;

    @Value("${application.secret}")
    private int applicationSecret;
}
