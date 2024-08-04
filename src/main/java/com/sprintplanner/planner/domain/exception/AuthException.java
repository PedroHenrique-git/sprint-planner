package com.sprintplanner.planner.domain.exception;

import lombok.Getter;

@Getter
public class AuthException extends Exception {
    private final int status;

    public AuthException(String message, int status) {
        super(message);

        this.status = status;
    }
}
