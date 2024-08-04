package com.sprintplanner.planner.infra.security;

import org.springframework.context.annotation.Configuration;

@Configuration
public class RoutesSecurityConfig {
    public final String[] API_GET_BLACK_LIST = {
        "/api/v1/sprints/**",
        "/api/v1/tasks/**",
        "/api/v1/teams/**",
        "/api/v1/members/**",
        "/api/v1/auth/authenticated-user",
    };

    public final String[] API_POST_BLACK_LIST = {
        "/api/v1/sprints",
        "/api/v1/tasks",
        "/api/v1/teams",
    };

    public final String[] API_PUT_BLACK_LIST = {
        "/api/v1/sprints/**",
        "/api/v1/tasks/**",
        "/api/v1/teams/**",
        "/api/v1/members/**",
    };

    public final String[] API_DELETE_BLACK_LIST = {
        "/api/v1/sprints/**",
        "/api/v1/tasks/**",
        "/api/v1/teams/**",
        "/api/v1/members/**",
    };
}
