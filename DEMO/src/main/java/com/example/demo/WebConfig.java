package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.nio.file.Path;

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

    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver(); //Sử dụng cơ chế xử lý của multipart của Servlet 3.0
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String uploadDir = Path.of(System.getProperty("user.dir"), "uploads").toString();

        //hãy chuyển tới thư mục upload để lấy hình ảnh/file tĩnh (css, js...)
        registry.addResourceHandler("/uploads/**") //Bất kỳ url nào bắt đầu bằng uploads
                .addResourceLocations("file:" + uploadDir + "/");
    }
}
