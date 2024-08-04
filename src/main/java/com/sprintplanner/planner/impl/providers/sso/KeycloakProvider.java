package com.sprintplanner.planner.impl.providers.sso;

import java.util.Map;

import org.keycloak.admin.client.Keycloak;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sprintplanner.planner.domain.providers.sso.SSOProvider;

@Component
public class KeycloakProvider implements SSOProvider<Keycloak> {
    @Value("${keycloak.server}")
    private String keycloakServer;

    @Value("${keycloak.user}")
    private String keycloakUser;

    @Value("${keycloak.password}")
    private String keycloakPassword;

    @Value("${keycloak.client-id}")
    private String keycloakClientId;

    @Value("${keycloak.secret}")
    private String keycloakSecret;

    @Value("${keycloak.realm}")
    private String keycloakRealm;

    @Override
    public Keycloak getSSOInstanceWithCredentials(Map<String, String> credentials) {
        String user = credentials.get("user");
        String password = credentials.get("password");

        return Keycloak.getInstance(keycloakServer, keycloakRealm, user, password, keycloakClientId, keycloakSecret);
    }

    @Override
    public Keycloak getSSOInstanceWithoutCredentials() {
        return Keycloak.getInstance(keycloakServer, keycloakRealm, keycloakUser, keycloakPassword, keycloakClientId, keycloakSecret);
    }
}
