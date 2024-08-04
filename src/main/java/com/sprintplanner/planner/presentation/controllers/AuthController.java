package com.sprintplanner.planner.presentation.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprintplanner.planner.domain.exception.AuthException;
import com.sprintplanner.planner.domain.service.AuthService;
import com.sprintplanner.planner.domain.service.dto.AuthLoginDTO;
import com.sprintplanner.planner.domain.service.dto.AuthLoginDTOResponse;
import com.sprintplanner.planner.impl.services.AuthServiceImpl;

import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Auth", description = "auth API")
public class AuthController {
    private AuthService service;

    public AuthController(AuthServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/authenticated-user")
    public ResponseEntity<Map<String, String>> getAUthenticatedUser(@AuthenticationPrincipal Jwt token) {
        return ResponseEntity.ok(extractUserInfoFromToken(token));
    }
    
    @PostMapping("/login")
    public ResponseEntity<AuthLoginDTOResponse> login(@RequestBody AuthLoginDTO dto) throws AuthException {
        var response = service.makeKeycloakLogin(dto);
        
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<Map<String, String>> handler(AuthException exception) {
        Map<String, String> body = new HashMap<>();

        body.put("message", exception.getMessage());

        return ResponseEntity.status(exception.getStatus()).body(body);
    }

    private Map<String, String> extractUserInfoFromToken(Jwt token) {
        String userEmail = token.getClaim("email");
        String userCompleteName = token.getClaim("name");
        String userFirstName = token.getClaim("given_name");
        String userLastName = token.getClaim("family_name");

        return Map.of("email", userEmail, "name", userCompleteName, "firstName", userFirstName, "lastName", userLastName);
    }
}
