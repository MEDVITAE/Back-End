package org.example.Configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Cors {

    @Value("${allowed.origin}")
    private String allowedOrigin;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/Api/**")
                        .allowedOrigins(allowedOrigin)
                        .allowedMethods(
                                HttpMethod.GET.name(),
                                HttpMethod.POST.name(),
                                HttpMethod.PUT.name(),
                                HttpMethod.PATCH.name(),
                                HttpMethod.DELETE.name(),
                                HttpMethod.OPTIONS.name(),
                                HttpMethod.HEAD.name(),
                                HttpMethod.TRACE.name())
                        .allowedHeaders("*");
            }
        };

//    @Bean
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedHeaders("*")
//                .allowedOrigins(allowedOrigin)
//                .allowedMethods(
//                    HttpMethod.GET.name(),
//                    HttpMethod.POST.name(),
//                    HttpMethod.PUT.name(),
//                    HttpMethod.PATCH.name(),
//                    HttpMethod.DELETE.name(),
//                    HttpMethod.OPTIONS.name(),
//                    HttpMethod.HEAD.name(),
//                    HttpMethod.TRACE.name());// Isso permite CORS para todos os caminhos
//
//    }
    }
}


