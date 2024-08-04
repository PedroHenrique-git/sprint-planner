package com.sprintplanner.planner.domain.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthLoginDTOResponse {
    @JsonProperty
    String accessToken;

    @JsonProperty
    long expiresIn;

    @JsonProperty
    long refreshExpiresIn;

    @JsonProperty
    String refreshToken;

    @JsonProperty
    String tokenType;
}
