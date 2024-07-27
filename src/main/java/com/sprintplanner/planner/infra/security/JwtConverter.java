package com.sprintplanner.planner.infra.security;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component

public class JwtConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    @Value("${jwt.converter.resource-id}")
    private String resourceId;

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        Map<String, Map<String, Collection<String>>> resource = jwt.getClaim("resource_access");
        Collection<String> roles = resource.get(resourceId).get("roles");

        var grants = roles
            .stream()
            .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
            .toList();

        return new JwtAuthenticationToken(jwt, grants);
    }
}
