package com.meli.challenge.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Value("${spring.application.name:meli-item-detail}")
    private String applicationName;

    @Value("${server.port:8080}")
    private String serverPort;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("MercadoLibre Challenge API")
                        .description("""
                                REST API for MercadoLibre technical challenge.
                                
                                This API provides endpoints to get detailed product information,
                                implementing Redis cache to optimize performance.
                                
                                ## Features
                                - 🚀 **Spring Boot 3.3.0** with Java 17
                                - 💾 **Redis Cache** for better performance
                                - 🏗️ **Hexagonal Architecture** for maintainability
                                - 🧪 **Test coverage > 80%**
                                - 🐳 **Complete containerization** with Docker
                                
                                ## Authentication
                                Currently does not require authentication. In a production environment 
                                JWT/OAuth2 would be implemented.
                                """)
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Sebastian Cardozo")
                                .email("sebastian@example.com")
                                .url("https://github.com/your-username"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:" + serverPort)
                                .description("Local Development"),
                        new Server()
                                .url("http://localhost:8080")
                                .description("Docker Local")
                ))
                .tags(List.of(
                        new Tag()
                                .name("Products")
                                .description("Product-related operations"),
                        new Tag()
                                .name("Health")
                                .description("System monitoring and health endpoints")
                ));
    }
} 