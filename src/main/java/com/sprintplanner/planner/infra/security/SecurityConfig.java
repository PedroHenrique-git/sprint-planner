package com.sprintplanner.planner.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;

import com.sprintplanner.planner.infra.security.filters.UserResourceFilter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final UserResourceFilter userResourceFilter;
    private final RoutesSecurityConfig config;
    private final JwtConverter jwtConverter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, config.API_GET_BLACK_LIST).authenticated()
                        .requestMatchers(HttpMethod.POST, config.API_POST_BLACK_LIST).authenticated()
                        .requestMatchers(HttpMethod.PUT, config.API_PUT_BLACK_LIST).authenticated()
                        .requestMatchers(HttpMethod.DELETE, config.API_DELETE_BLACK_LIST).authenticated()
                        //.requestMatchers("/room/*").authenticated()
                        .anyRequest().permitAll());

        http.addFilterBefore(userResourceFilter, BearerTokenAuthenticationFilter.class);

        http
                .sessionManagement(session -> session.sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS));

        http
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtConverter)));

        return http.build();
    }
}
