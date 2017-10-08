package com.testing.springboot.application;

import org.springframework.boot.context.event.*;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class ApplicationListeners implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {

        // triggered at the start of a run, but before any processing except the registration of
        // listeners and initializers
        if (applicationEvent instanceof ApplicationStartingEvent) {
            return;
        }

        // triggered when the Environment to be used in the context is known, but before the context
        // is created.
        if (applicationEvent instanceof ApplicationEnvironmentPreparedEvent) {
            return;
        }

        // just before the refresh is started, but after bean definitions have been loaded.
        if (applicationEvent instanceof ApplicationPreparedEvent) {
            return;
        }

        // after the refresh and any related callbacks have been processed to indicate the application
        // is ready to service requests.
        if (applicationEvent instanceof ApplicationReadyEvent) {
            return;
        }

        // if there is an exception on startup.
        if (applicationEvent instanceof ApplicationFailedEvent) {
            return;
        }

        return;
    }
}
