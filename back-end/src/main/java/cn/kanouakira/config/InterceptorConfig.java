package cn.kanouakira.config;

import cn.kanouakira.common.inteceptor.AuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getAuthenticationInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public AuthenticationInterceptor getAuthenticationInterceptor(){
        return new AuthenticationInterceptor();
    }
}
