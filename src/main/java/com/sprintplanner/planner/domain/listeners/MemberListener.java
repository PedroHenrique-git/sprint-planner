package com.sprintplanner.planner.domain.listeners;

import com.sprintplanner.planner.domain.model.Member;
import com.sprintplanner.planner.domain.model.Sprint;

public interface MemberListener {
    void beforeDelete(Member member);
    void beforeUpdate(Member member);
    void beforePersist(Member member);
    void afterDelete(Member member);
    void afterUpdate(Member member);
    void afterPersist(Member member);
}
