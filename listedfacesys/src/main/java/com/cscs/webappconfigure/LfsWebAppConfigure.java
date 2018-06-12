package com.cscs.webappconfigure;

import com.cscs.webappconfigure.interceptor.LfsInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * wkm
 */
@Configuration
public class LfsWebAppConfigure extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成拦截链
        // addPathPatterns 添加拦截规则
        registry.addInterceptor(new LfsInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
