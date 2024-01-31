package com.example.neilassignment2af;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(HttpMethod.GET, "/properties/**").permitAll()
                            .requestMatchers(HttpMethod.DELETE, "/properties/**").hasRole("MANAGER")
                            .requestMatchers(HttpMethod.POST, "/properties/**").hasRole("MANAGER")
                            .requestMatchers(HttpMethod.PATCH, "/properties/**").hasRole("MANAGER")
                            .requestMatchers(HttpMethod.GET, "/users/**").hasRole("MANAGER")
                            .requestMatchers(HttpMethod.POST, "/users/**").hasRole("MANAGER")
                            .requestMatchers(HttpMethod.PATCH, "/users/**").hasRole("MANAGER")
                            .requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("MANAGER")
                            .requestMatchers(HttpMethod.DELETE, "/tenants/**").hasAnyRole("MANAGER", "OFFICE_STAFF")
                            .requestMatchers(HttpMethod.GET, "/tenants/**").hasAnyRole("MANAGER", "OFFICE_STAFF")
                            .requestMatchers(HttpMethod.POST, "/tenants/**").hasAnyRole("MANAGER", "OFFICE_STAFF")
                            .requestMatchers(HttpMethod.PATCH, "/tenants/**").hasAnyRole("MANAGER", "OFFICE_STAFF")
                            .requestMatchers("/graphql").permitAll()
                            .anyRequest().authenticated();
                })
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }
}
