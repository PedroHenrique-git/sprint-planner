package com.sprintplanner.planner.infra.sso;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SSO {
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

    @Bean
    public Keycloak getKeycloakInstance() {
        return Keycloak.getInstance(keycloakServer, keycloakRealm, keycloakUser, keycloakPassword, keycloakClientId, keycloakSecret);
    }

    @Bean
    public RealmResource getRealmResource() {
        var instance = getKeycloakInstance();

        return instance.realm(keycloakRealm);
    }
}
