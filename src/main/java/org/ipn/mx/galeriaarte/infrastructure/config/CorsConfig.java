//    package org.ipn.mx.galeriaarte.infrastructure.config;
//
//    import org.springframework.context.annotation.Bean;
//    import org.springframework.context.annotation.Configuration;
//    import org.springframework.web.cors.CorsConfiguration;
//    import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//    import org.springframework.web.filter.CorsFilter;
//
//    import java.util.Arrays;
//    import java.util.List;
//
//    @Configuration
//    public class CorsConfig {
//
//        @Bean
//        public CorsFilter corsFilter() {
//            CorsConfiguration corsConfiguration = new CorsConfiguration();
//
//            // Permitir credenciales
//            corsConfiguration.setAllowCredentials(true);
//
//            // Orígenes permitidos (modificar según tus necesidades)
//            corsConfiguration.setAllowedOriginPatterns(List.of("*"));
//
//            // Métodos HTTP permitidos
//            corsConfiguration.setAllowedMethods(Arrays.asList(
//                    "GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"
//            ));
//
//            // Encabezados permitidos
//            corsConfiguration.setAllowedHeaders(Arrays.asList(
//                    "Origin",
//                    "Content-Type",
//                    "Accept",
//                    "Authorization",
//                    "Access-Control-Request-Method",
//                    "Access-Control-Request-Headers"
//            ));
//
//            // Encabezados expuestos
//            corsConfiguration.setExposedHeaders(Arrays.asList(
//                    "Access-Control-Allow-Origin",
//                    "Access-Control-Allow-Credentials",
//                    "Content-Disposition"
//            ));
//
//            // Tiempo máximo de cacheo de la configuración CORS
//            corsConfiguration.setMaxAge(3600L);
//
//            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//            source.registerCorsConfiguration("/**", corsConfiguration);
//
//            return new CorsFilter(source);
//        }
//    }