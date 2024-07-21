package com.sprintplanner.planner.domain.enumeration;

public enum Complexity {
    HIGH("HIGH"),
    MEDIUM("MEDIUM"),
    LOW("LOW");

    private final String value;

    Complexity(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
