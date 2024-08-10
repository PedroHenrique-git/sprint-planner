package com.sprintplanner.planner.domain.service;

import java.util.Optional;

import com.sprintplanner.planner.domain.model.Member;

public interface MemberService extends CrudService<Member> {
    Optional<Member> getByEmail(String email);
}
