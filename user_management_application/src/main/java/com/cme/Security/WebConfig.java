package com.cme.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private RoleInterceptor roleInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Apply the interceptor to role-restricted paths
        registry.addInterceptor(roleInterceptor)
                .addPathPatterns("/admin/**", "/mentor/**", "/employee/**")
                .excludePathPatterns("/register", "/sign-in", "/error/**", "/index");
    }
}
