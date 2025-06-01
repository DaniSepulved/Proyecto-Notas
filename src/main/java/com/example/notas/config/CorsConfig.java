    package com.example.notas.config;

    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.web.servlet.config.annotation.CorsRegistry;
    import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

    @Configuration
    public class CorsConfig {
        @Bean
        public WebMvcConfigurer corsConfigurer() {
            return new WebMvcConfigurer() {
                @Override
                public void addCorsMappings(CorsRegistry registry) {
                    registry.addMapping("/**") // Permite CORS en todos los endpoints
                            .allowedOrigins("https://b2pi2-876585927226.us-central1.run.app", "http://localhost:8502", "http://127.0.0.1:8501", "http://localhost:5173/") // Origen permitido
                            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos HTTP permitidos
                            .allowedHeaders("*") // Todos los encabezados permitidos
                            .allowCredentials(true); // Si necesitas enviar cookies o autenticación
                }
            };
        }
    }
