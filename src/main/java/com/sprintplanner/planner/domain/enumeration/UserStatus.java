package com.sprintplanner.planner.domain.enumeration;

public enum UserStatus {
    OK("OK"),
    PENDING("PENDING");

    private final String value;

    UserStatus(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
