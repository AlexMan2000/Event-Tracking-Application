package com.example.emsbackend.config;

//import com.example.emsbackend.jwt.JwtInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

//
//@Configuration
//public class InterceptorConfig extends WebMvcConfigurationSupport {
//
//
//    @Override
//    protected void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(getJwtInterceptor())
//                .addPathPatterns("/home");
//
//    }
//
//    @Bean
//    public JwtInterceptor getJwtInterceptor() {
//        return new JwtInterceptor();
//    }
//}
