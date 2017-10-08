package com.testing.example;

import com.testing.config.Datasource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class BootWeb {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(BootWeb.class);
        ConfigurableApplicationContext context = app.run();
        Datasource datasource = context.getBean(Datasource.class);

        System.out.println(datasource);
    }
}