package com.example.test.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(

                "/img/**",
                "/static/css/**",
                "/js/**",
                "/js/**/*" ,
                "ckeditor-add/**")
                .addResourceLocations(
                        
                        "classpath:/static/img/",
                        "classpath:/static/css/",
                        "classpath:/static/js/",
                        "classpath:/static/js/**/",
                        "classpath:/static/ckeditor-add/");
    }
}