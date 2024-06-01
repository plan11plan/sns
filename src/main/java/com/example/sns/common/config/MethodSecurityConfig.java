package com.example.sns.common.config;


import com.example.sns.core.post.domain.service.port.PostReadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class MethodSecurityConfig {
    private final PostReadRepository postReadRepository;
    @Bean
    public MethodSecurityExpressionHandler methodSecurityExpressionHandler(){
        var handler = new DefaultMethodSecurityExpressionHandler();
        handler.setPermissionEvaluator(new SnsPermissionEvaluator(postReadRepository));
        return handler;
    }
}
