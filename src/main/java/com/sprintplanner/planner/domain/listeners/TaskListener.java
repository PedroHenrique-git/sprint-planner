package com.sprintplanner.planner.domain.listeners;

import com.sprintplanner.planner.domain.model.Sprint;
import com.sprintplanner.planner.domain.model.Task;

public interface TaskListener {
    void afterDelete(Task task);
    void afterUpdate(Task task);
    void afterPersist(Task task);
}
