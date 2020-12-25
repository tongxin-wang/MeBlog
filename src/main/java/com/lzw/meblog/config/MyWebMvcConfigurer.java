package com.lzw.meblog.config;

import com.lzw.meblog.interceptor.BackInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置
 * @author TongxinWang
 */
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        //添加拦截规则（拦截除/admin/login以外的后台管理请求）
        registry.addInterceptor(new BackInterceptor()).addPathPatterns("/admin/**").excludePathPatterns("/admin/login").excludePathPatterns("/admin/state");
    }
}
