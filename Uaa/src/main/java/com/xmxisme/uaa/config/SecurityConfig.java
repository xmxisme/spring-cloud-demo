package com.xmxisme.uaa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/baseLogin").permitAll()  // 允许匿名访问
                                .requestMatchers("/secured/**").authenticated()  // 仅在特定路径启用OAuth2登录
                                .anyRequest().permitAll()  // 允许所有其他请求
                )
                .oauth2Login(oauth2 -> oauth2
                        .defaultSuccessUrl("/hello", true)  // 成功登录后的重定向URL
                )
                .csrf(AbstractHttpConfigurer::disable)
                .oauth2Login(withDefaults());  // 在需要时启用OAuth2登录

        return http.build();
    }
}


