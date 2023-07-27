package com.resume.config;

import com.resume.exception.ExceptionResolver;
import com.resume.exception.PostNotFountException;
import com.resume.interceptor.LogInCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInCheckInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**","/error","/login","/js/**"
                        ,"/register/**","/login/search","/verify",
                        "/login/search/detail","/notices/**","/board/**");

    }

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        resolvers.add(new ExceptionResolver());
    }
}
