package com.codegym.songapp.config;

import com.codegym.songapp.converters.StringToArtistConverter;
import com.codegym.songapp.models.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.codegym.songapp")
@EnableSpringDataWebSupport //
public class WebConfig implements WebMvcConfigurer {

    // Thư viện xử lý chức năng upload
    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

    // Cấu hình URL phục vụ request bài hát (download file về ntn).

    //Bước 8: Override phương thức addResourceHandlers để cấu hình đường dẫn đến file bài hát trong chức năng nghe nhạc.
    public static final String SONG_FOLDER = System.getProperty("user.dir") + "/songs-upload";
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/songs/**")
                .addResourceLocations("file:" + SONG_FOLDER + "/");
    }

    @Autowired
    StringToArtistConverter artistConverter;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(artistConverter);
    }
}