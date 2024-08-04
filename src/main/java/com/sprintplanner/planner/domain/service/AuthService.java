package com.sprintplanner.planner.domain.service;

import com.sprintplanner.planner.domain.exception.AuthException;
import com.sprintplanner.planner.domain.service.dto.AuthLoginDTO;
import com.sprintplanner.planner.domain.service.dto.AuthLoginDTOResponse;

public interface AuthService {
    AuthLoginDTOResponse makeKeycloakLogin(AuthLoginDTO dto) throws AuthException;
}
