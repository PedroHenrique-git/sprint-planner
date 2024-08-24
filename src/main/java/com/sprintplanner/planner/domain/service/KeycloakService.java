package com.sprintplanner.planner.domain.service;

import com.sprintplanner.planner.domain.model.Member;

public interface KeycloakService {
    void createUser(Member member);

    void updateUser(Member member);

    void deleteUser(Member member);

    void sendUpdatePasswordEmail(Member member);

    void sendUpdateProfileEmail(Member member);
}
