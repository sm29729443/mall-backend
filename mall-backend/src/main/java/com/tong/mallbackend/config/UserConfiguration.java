package com.tong.mallbackend.config;

import com.tong.mallbackend.interceptor.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ClassName: UserConfiguration
 * Package: com.tong.mallbackend.config
 */
@Configuration
public class UserConfiguration implements WebMvcConfigurer {
    @Autowired
    private UserInterceptor userInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor)
                .addPathPatterns("/users/**")
                .addPathPatterns("/products/**")
                .addPathPatterns("/productCategorys")
                .addPathPatterns("/productCategorys/**");
    }
}
