package com.sprintplanner.planner.domain.enumeration;

public enum Priority {
    HIGH("HIGH"),
    MEDIUM("MEDIUM"),
    LOW("LOW");

    private final String value;

    Priority(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
