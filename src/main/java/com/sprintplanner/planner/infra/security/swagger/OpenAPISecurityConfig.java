package com.sprintplanner.planner.infra.security.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.Scopes;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;

@Configuration
public class OpenAPISecurityConfig {
    private static final String OAUTH_SCHEME_NAME = "planner-oauth";

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    String authURL;

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
            .addSecurityItem(new SecurityRequirement().addList(OAUTH_SCHEME_NAME))
            .components(new Components().addSecuritySchemes(OAUTH_SCHEME_NAME, createOAuthScheme()))
            .addSecurityItem(new SecurityRequirement().addList(OAUTH_SCHEME_NAME));
    }

    private SecurityScheme createOAuthScheme() {
        return new SecurityScheme().type(Type.OAUTH2).flows(createOauthFlows());
    }

    private OAuthFlows createOauthFlows() {
        final var oauthFlow = new OAuthFlow()
            .authorizationUrl(authURL + "/protocol/openid-connect/auth")
            .refreshUrl(authURL + "/protocol/openid-connect/token")
            .tokenUrl(authURL + "/protocol/openid-connect/token")
            .scopes(new Scopes());

        return new OAuthFlows().authorizationCode(oauthFlow);
    }
}
