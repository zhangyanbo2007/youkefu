package com.ukefu.webim.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.ukefu.webim.web.interceptor.CrossInterceptorHandler;
import com.ukefu.webim.web.interceptor.LogIntercreptorHandler;
import com.ukefu.webim.web.interceptor.UserInterceptorHandler;

@Configuration
public class UKWebAppConfigurer 
        extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(new UserInterceptorHandler()).addPathPatterns("/**").excludePathPatterns(new String[] {"/login.html","/im/**","/res/image*","/res/file*","/cs/**"});
        registry.addInterceptor(new CrossInterceptorHandler()).addPathPatterns(new String[] {"/**"});
        registry.addInterceptor(new LogIntercreptorHandler()).addPathPatterns(new String[] {"/**"});
        super.addInterceptors(registry);
    }
}