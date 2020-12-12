package co.com.cidenet.hulkStore.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class HostConfigurer {

    @Value("${HULK_STORE_HOST:http://localhost:4200}")
    private String hulkStoreHost;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            public void addCorsMappings(CorsRegistry registry) {
            	System.out.println("==============".concat(hulkStoreHost));
                registry.addMapping("/**").allowedOrigins(hulkStoreHost).allowedMethods("GET", "POST", "PUT", "DELETE",
                        "OPTIONS");
            }
        };
    }
}
