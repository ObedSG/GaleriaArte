package org.ipn.mx.galeriaarte.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    @Profile("prod")
    public OpenAPI prodOpenAPI() {
        Server prodServer = new Server();
        prodServer.setUrl("https://galeriaarte-gujm.onrender.com");
        prodServer.setDescription("Servidor de Producción");

        return new OpenAPI()
                .servers(List.of(prodServer))
                .info(new Info()
                        .title("Galería de Arte API")
                        .description("API REST para gestión de galería de arte digital")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Tu Nombre")
                                .email("contacto@example.com")));
    }

    @Bean
    @Profile("dev")
    public OpenAPI devOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl("http://localhost:8082");
        devServer.setDescription("Servidor de Desarrollo");

        return new OpenAPI()
                .servers(List.of(devServer))
                .info(new Info()
                        .title("Galería de Arte API - DEV")
                        .description("API REST para gestión de galería de arte digital (Desarrollo)")
                        .version("1.0.0-DEV"));
    }
}