package com.sprintplanner.planner.impl.services;

import java.util.Map;

import org.keycloak.admin.client.Keycloak;
import org.springframework.stereotype.Service;

import com.sprintplanner.planner.domain.exception.AuthException;
import com.sprintplanner.planner.domain.providers.sso.SSOProvider;
import com.sprintplanner.planner.domain.service.AuthService;
import com.sprintplanner.planner.domain.service.dto.AuthLoginDTO;
import com.sprintplanner.planner.domain.service.dto.AuthLoginDTOResponse;
import com.sprintplanner.planner.impl.providers.sso.KeycloakProvider;

import jakarta.ws.rs.NotAuthorizedException;

@Service
public class AuthServiceImpl implements AuthService {
    private SSOProvider<Keycloak> sso;

    public AuthServiceImpl(KeycloakProvider sso) {
        this.sso = sso;
    }

    @Override
    public AuthLoginDTOResponse makeKeycloakLogin(AuthLoginDTO dto) throws AuthException {
        try {
            var instance = sso.getSSOInstanceWithCredentials(Map.of("user", dto.getUsername(), "password", dto.getPassword()));
            var token = instance.tokenManager().getAccessToken();
    
            AuthLoginDTOResponse response = new AuthLoginDTOResponse();
    
            response.setAccessToken(token.getToken());
            response.setExpiresIn(token.getExpiresIn());
            response.setRefreshToken(token.getRefreshToken());
            response.setRefreshExpiresIn(token.getRefreshExpiresIn());
            response.setTokenType(token.getTokenType());
    
            return response;
        } catch(NotAuthorizedException err) {
            throw new AuthException("Invalid credentials", 401);
        } catch(Exception err) {
            throw new AuthException("Something went wrong", 500);
        }
    }
}
