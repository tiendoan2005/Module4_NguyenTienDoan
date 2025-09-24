package com.example.demo;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() { //nơi khai báo beans dành cho data/service...
        return new Class<?>[]{};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() { //nơi khai báo beans dành cho web layer (controller, views)
        return new Class<?>[]{
                WebConfig.class
        };
    }

    @Override
    protected String[] getServletMappings() { //đường dẫn gốc mapping "/"
        return new String[]{"/"};
    }
}
