package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.example.demo")
//Load beans --> controllers là các object (beans)
public class WebConfig implements WebMvcConfigurer {

    @Bean
    //bean dành cho view
    public InternalResourceViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();

        //đường dẫn đến view
        resolver.setPrefix("/WEB-INF/view/");

        //đuôi file view là gì (.jsp)
        resolver.setSuffix(".jsp");

        //font hiển thị tiếng Việt
        resolver.setContentType("text/html;charset=UTF-8");

        return resolver;
    }
}
