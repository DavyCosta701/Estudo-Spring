package com.company.davyc.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


public class SpringDocConfig {


    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("users")
                .packagesToScan("com.company.davyc.api.controller")
                .pathsToMatch("/api/**")
                .build();
    }

}
