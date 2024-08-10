package com.sprintplanner.planner.infra.security.filters;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.keycloak.TokenVerifier;
import org.keycloak.common.VerificationException;
import org.keycloak.representations.AccessToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sprintplanner.planner.domain.model.Member;
import com.sprintplanner.planner.domain.service.MemberService;
import com.sprintplanner.planner.impl.services.MemberServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.java.Log;

@Log
@Component
public class UserResourceFilter extends OncePerRequestFilter {
    private final MemberService memberService;

    public UserResourceFilter(MemberServiceImpl memberService) {
        this.memberService = memberService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String uri = request.getRequestURI();
        String method = request.getMethod();

        if (shouldVerifyRequest(uri, method)) {
            String authenticationHeader = request.getHeader("Authorization");
            String token = authenticationHeader.split("Bearer ")[1];

            Map<String, String> userInfo = getUserInfoFromToken(token);
            String userEmail = userInfo.get("email");

            if (userEmail == null) {
                sendAccessDeniedResponse(response);

                return;
            }

            Optional<Member> maybeMember = memberService.getByEmail(userEmail);

            if (!maybeMember.isPresent()) {
                sendAccessDeniedResponse(response);

                return;
            }

            Member member = maybeMember.get();
            String userId = getLastParamFromUri(uri);

            if (!member.getId().equals(userId)) {
                sendAccessDeniedResponse(response);

                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private String getLastParamFromUri(String uri) {
        List<String> segments = Arrays.asList(uri.split("/"));

        return segments.get(segments.size() - 1);
    }

    private boolean shouldVerifyRequest(String uri, String method) {
        String lastParam = getLastParamFromUri(uri);

        List<String> methods = List.of("GET", "PUT", "DELETE");

        return isUUID(lastParam) && methods.contains(method);
    }

    private Map<String, String> getUserInfoFromToken(String token) {
        try {
            AccessToken accessToken = TokenVerifier.create(token, AccessToken.class).getToken();

            return Map.of("email", accessToken.getEmail());
        } catch (VerificationException e) {
            return Map.of();
        }
    }

    private boolean isUUID(String str) {
        try {
            UUID.fromString(str);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void sendAccessDeniedResponse(HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper body = new ObjectMapper();

        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(body.writeValueAsString(Map.of("message",
                "Access denied")));
    }
}
