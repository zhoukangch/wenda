package com.nowcoder.configuration;

import com.nowcoder.intercepter.LoginIntercepter;
import com.nowcoder.intercepter.PassportIntercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
//WebMvcConfigurerAdapter已经过时，官方推荐下面的方式
public class WendaWebConfiguration implements WebMvcConfigurer{
     @Autowired
     PassportIntercepter passportIntercepter;
     @Autowired
     LoginIntercepter loginIntercepter;
     @Override
     public void addInterceptors(InterceptorRegistry registry) {
         registry.addInterceptor(passportIntercepter);
         registry.addInterceptor(loginIntercepter).addPathPatterns("/user/*");
     }
}
