package com.soft1851.music.admin.config;

import com.soft1851.music.admin.interceptor.JwtInterceptor;
import com.soft1851.music.admin.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author CRQ
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Resource
    private LoginInterceptor loginInterceptor;

    @Resource
    private JwtInterceptor jwtInterceptor;
    /**
     * 添加拦截器配置
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截路径可自行配置多个 可用, 分割开 放行特殊请求
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/**").excludePathPatterns("/login", "/captcha").excludePathPatterns("/static/**");

        //添加验证码的拦截器
        registry.addInterceptor(loginInterceptor).addPathPatterns("/login");
    }
}
