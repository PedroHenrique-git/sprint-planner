package com.sprintplanner.planner.domain.listeners;

import com.sprintplanner.planner.domain.model.Member;
import com.sprintplanner.planner.domain.model.Sprint;

public interface SprintListener {
    void afterDelete(Sprint sprint);
    void afterUpdate(Sprint sprint);
    void afterPersist(Sprint sprint);
}
