//package org.ipn.mx.galeriaarte.infrastructure.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                // Deshabilitar CSRF para APIs REST
//                .csrf(AbstractHttpConfigurer::disable)
//
//                // Configurar autorización de peticiones
//                .authorizeHttpRequests(auth -> auth
//                        // Permitir acceso público a Swagger UI y API Docs
//                        .requestMatchers(
//                                "/swagger-ui/**",
//                                "/v3/api-docs/**",
//                                "/swagger-ui.html",
//                                "/api-docs/**"
//                        ).permitAll()
//
//                        // Permitir acceso público a todas las APIs (puedes modificar esto después)
//                        .requestMatchers("/api/**").permitAll()
//
//                        // Cualquier otra petición requiere autenticación
//                        .anyRequest().authenticated()
//                )
//
//                // Configurar política de sesiones (sin estado para REST API)
//                .sessionManagement(session ->
//                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                );
//
//        return http.build();
//    }
//}