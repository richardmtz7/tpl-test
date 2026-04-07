package com.terpel.test.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiDocConfig {
    @Bean
    public OpenAPI serviceOrdersOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Service Orders API")
                        .description("API for managing service orders at retail locations")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Backend")
                                .email("terpel@terpel.com")));
    }
}
