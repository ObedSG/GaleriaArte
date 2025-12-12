package org.ipn.mx.galeriaarte.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("prod")
public class OpenApiConfig {

    @Bean
    public OpenAPI OpenApiConfigProd() {

        Server serverProd = new Server()
                .url("https://galeriaarte-gujm.onrender.com")
                .description("Servidor en Producción");

        Server serverLocal = new Server()
                .url("http://localhost:10000")
                .description("Servidor Local");

        return new OpenAPI().servers(
                List.of(serverProd, serverLocal)
        );
    }
}
