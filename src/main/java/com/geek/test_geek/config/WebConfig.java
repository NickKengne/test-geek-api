package com.geek.test_geek.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000","http://192.168.1.134:3000")
                .allowedMethods("PUT", "DELETE","POST","GET")
                .allowedHeaders("*", "*")
                .exposedHeaders("*", "*")
                .allowCredentials(true).maxAge(3600);
    }
}