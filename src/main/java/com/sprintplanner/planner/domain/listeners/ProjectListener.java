package com.sprintplanner.planner.domain.listeners;

import com.sprintplanner.planner.domain.model.Project;
import com.sprintplanner.planner.domain.model.Sprint;

public interface ProjectListener {
    void afterDelete(Project project);
    void afterUpdate(Project project);
    void afterPersist(Project project);
}
