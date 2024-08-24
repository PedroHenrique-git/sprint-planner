package com.sprintplanner.planner.domain.enumeration;

public enum UserAction {
    CREATE("CREATE"),
    DELETE("DELETE"),
    UPDATE("UPDATE");

    private final String value;

    UserAction(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
