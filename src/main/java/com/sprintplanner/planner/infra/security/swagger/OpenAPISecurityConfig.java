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

    @Value("${external.auth.url}")
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
        String authorizationUrl = authURL + "/protocol/openid-connect/auth";
        String refreshUrl = authURL + "/protocol/openid-connect/token";
        String tokenUrl = authURL + "/protocol/openid-connect/token";

        final var oauthFlow = new OAuthFlow()
                .scopes(new Scopes());

        oauthFlow.setAuthorizationUrl(authorizationUrl);
        oauthFlow.setRefreshUrl(refreshUrl);
        oauthFlow.setTokenUrl(tokenUrl);

        return new OAuthFlows().authorizationCode(oauthFlow);
    }
}
