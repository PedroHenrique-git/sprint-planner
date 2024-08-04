package com.sprintplanner.planner.domain.listeners;

import com.sprintplanner.planner.domain.model.Member;

public interface MemberListener {
    void beforeDelete(Member member);
    void beforeUpdate(Member member);
    void beforePersist(Member member);
}
