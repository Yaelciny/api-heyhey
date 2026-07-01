package com.heyhey.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // configuracion globales del servidor
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**") // Aplica el permiso a todas tus rutas
                .allowedOrigins("http://localhost:3000") // Aquí le damos acceso
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Permisos de acciones
                .allowedHeaders("*") // Permite enviar cualquier tipo de dato
                .allowCredentials(true);
    }
}
