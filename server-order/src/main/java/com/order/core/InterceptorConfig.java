package com.order.core;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.order.tx.RequestInterceptor;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter{
	@Autowired
    private RequestInterceptor requestInterceptor;

    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestInterceptor).addPathPatterns("/**");
    }
}