package com.bernardo.me.seniorproject_backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

        @Autowired
        JwtAuthFilter jwtAuthFilter;

        @Bean
        SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .csrf(csrf -> csrf.disable())
                                .sessionManagement(management -> management
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authorizeHttpRequests(authorize -> authorize
                                                /*
                                                 * .requestMatchers(HttpMethod.POST, "/users",
                                                 * "/users/login").permitAll()
                                                 * .requestMatchers(HttpMethod.GET, "/orders").permitAll()
                                                 */
                                                .anyRequest().permitAll())
                                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
                return http.build();
        }

}
