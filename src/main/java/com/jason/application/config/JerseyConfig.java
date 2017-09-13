package com.jason.application.config;

import com.jason.application.controller.AccountController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created by chien on 9/13/17.
 */
@Configuration
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        registerEndpoints();
    }

    private void registerEndpoints() {
         register(AccountController.class);
    }
}