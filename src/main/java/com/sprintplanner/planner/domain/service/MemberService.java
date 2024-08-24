package com.sprintplanner.planner.domain.service;

import java.util.List;
import java.util.Optional;

import com.sprintplanner.planner.domain.enumeration.UserStatus;
import com.sprintplanner.planner.domain.model.Member;

public interface MemberService extends CrudService<Member> {
    Optional<Member> getByEmail(String email);

    List<Member> findByStatus(UserStatus status);
}
