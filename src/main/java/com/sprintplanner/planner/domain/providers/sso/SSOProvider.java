package com.sprintplanner.planner.domain.providers.sso;

import java.util.Map;

public interface SSOProvider<T> {
    T getSSOInstanceWithCredentials(Map<String, String> credentials);
    T getSSOInstanceWithoutCredentials();
}
