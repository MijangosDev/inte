package org.ti.inte.config;

import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("inte")
                        .title("inte")
                        .version("1.0")
                        .description("inte")
                        .termsOfService("")
                        .license(new License().name("Apache 2.0")));
    }
}