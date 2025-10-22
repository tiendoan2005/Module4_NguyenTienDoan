package com.example.demo;

import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() { //nơi khai báo beans dành cho data/service...
        return new Class<?>[]{};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() { //nơi khai báo beans dành cho web layer (controller, views)
        return new Class<?>[]{
                WebConfig.class,
                ThymeleafConfig.class
        };
    }

    @Override
    protected String[] getServletMappings() { //đường dẫn gốc mapping "/"
        return new String[]{"/"};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        //Cấu hình thông tin file được upload
        //location (lưu vào thư mục trên webservlet)
        String location = System.getProperty("java.io.tmpdir");
        //max file size (5mb)
        long maxSize = 5 * 1024 * 1024; // = 5Mb
        //max request size (50mb)
        long maxRequestSize = 50 * 1024 * 1024;
        //file size threshold ~ Cached??? = 0; ghi ra đĩa
        int fileSizeThreshold = 0;
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(location, maxSize, maxRequestSize, fileSizeThreshold);
        registration.setMultipartConfig(multipartConfigElement);
    }
}
