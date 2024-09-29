package br.com.danielamaral.demoapibanco.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfiguration {
    @Value("${spring.application.name}")
    private String appName;

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title(appName)
                        .description(appName)
                        .version("ver2024-001")
                        .contact(new Contact()
                                .name("Daniel Amaral")
                                .email("amaraldaniel@google.com"))
                        .termsOfService("TOC")
                );
    }
}
