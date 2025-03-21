package com.lima.portifolio.shared.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Portifolio",
                version = "1.0",
                description = "API para portifolio",
                contact = @Contact(
                        name = "Gabriel Lima de Souza",
                        email = "gabriellima2803@gmail.com"
                )
        ),
        servers = {
                @Server(
                        description = "Ambiente Local",
                        url = "http://localhost:8080"
                )
        }
)
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
    }
}