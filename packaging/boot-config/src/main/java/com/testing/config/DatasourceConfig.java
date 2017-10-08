package com.testing.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class DatasourceConfig {
    @Bean
    public Datasource Datasource() {
        return new Datasource();
    }
}