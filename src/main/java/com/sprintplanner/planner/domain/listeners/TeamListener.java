package com.sprintplanner.planner.domain.listeners;

import com.sprintplanner.planner.domain.model.Task;
import com.sprintplanner.planner.domain.model.Team;

public interface TeamListener {
    void afterDelete(Team team);
    void afterUpdate(Team team);
    void afterPersist(Team team);
}
