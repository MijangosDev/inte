package org.ti.inte.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("inte")
                        .version("1.0")
                        .description("inte")
                        .termsOfService("https://example.com/terms")
                        .license(new License().name("Apache 2.0").url("https://apache.org/licenses/LICENSE-2.0"))
                );
    }
}
