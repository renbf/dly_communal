package com.project.framework.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.project.appinterface.interceptor.AppLoginInterceptor;

@Configuration
public class IntercpetorConfig implements WebMvcConfigurer {
	
	@Autowired
	private AppLoginInterceptor appLoginInterceptor;
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(appLoginInterceptor).addPathPatterns("/gift/accountNumberController/**","/gift/commonInterface/**","/gift/giftMachine/**");
    }
}
