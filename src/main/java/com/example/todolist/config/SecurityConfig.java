package com.example.todolist.config;

import com.example.todolist.jwt.JWTAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    @Configuration
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public static class apiConfig{

        @Autowired
        private JWTAuthenticationFilter jwtAuthenticationFilter;

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                    .csrf()
                    .disable()
                    .requestMatchers().antMatchers("/v1/**")
                    .and().authorizeRequests().anyRequest().authenticated()//.authenticated() // Secure API endpoints
                    .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

            http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
            return http.build();
        }
    }

    @Configuration
    @Order(2)
    public static class oauth2Config{

        @Autowired
        private CustomOAuth2LoginSuccessHandler customOAuth2LoginSuccessHandler;

        @Bean
        public SecurityFilterChain oauth2FilterChain(HttpSecurity http) throws Exception {
            http
                    .csrf()
                    .disable()
                    .authorizeRequests()
                    .antMatchers( "/","/authenticated").permitAll()
                    .and()
                    .logout()
                    .and()
                    .oauth2Login()
                    .successHandler(customOAuth2LoginSuccessHandler);
            return http.build();
        }
    }
}
