package com.testing.springboot;

import com.testing.springboot.application.ApplicationListeners;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Application Main serves as the primary Configuration with the @EnableAutoConfiguration configured.
 * Additional Configuration can be added via
 * 1. Other @Configuration annotated classes
 * 2. @Import(Configuration.class)
 * 3. @ImportResources(configuration.xml)
 * To Exclude certain Auto-Configuration
 * 1. exclude=({Configuration.class ...})
 * 2. excludeName=fully qualified names.
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    /**
     * self runnable
     */
    public static void main(String[] args) {

	    // Bypass the default so that we can configure the application more
        // SpringApplication.run(ApplicationConfiguration.class, args);

        // Pass in Configurations, so that SpringBoot knows how to initiate ApplicationContext
        SpringApplication app = new SpringApplication(Application.class);

        // setup listeners
        app.addListeners(new ApplicationListeners());

        // set ApplicationContext forcefully in case auto-configuration doesn't work
        // app.setApplicationContextClass(new AnnotationConfigApplicationContext());

        // enable webEnvironment forcefully in case auto-configuration doesn't work
        // app.setWebEnvironment(boolean webEnvironment)
        // app.setApplicationContextClass(new AnnotationConfigEmbeddedWebApplicationContext ());

        // disable command line arguments when needed
        // app.setAddCommandLineProperties(false).

        // any properties to be configured
        // app.setDefaultProperties(properties);
        // Other settings
        app.setBannerMode(Banner.Mode.OFF);

        app.run(args);

	}

    /**
     * In order to be invoked from classic servlet container (needs to be 3.0 above)
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
}
