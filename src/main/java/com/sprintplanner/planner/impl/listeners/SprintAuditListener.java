package com.sprintplanner.planner.impl.listeners;

import com.sprintplanner.planner.domain.listeners.SprintListener;
import com.sprintplanner.planner.domain.model.Member;
import com.sprintplanner.planner.domain.model.Sprint;
import com.sprintplanner.planner.domain.model.Task;
import com.sprintplanner.planner.domain.model.search.SearchSprint;
import com.sprintplanner.planner.domain.service.search.SearchSprintService;
import com.sprintplanner.planner.impl.services.search.SearchSprintServiceImpl;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import lombok.extern.java.Log;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.event.TransactionalEventListener;

@Log
public class SprintAuditListener implements SprintListener {
    private final SearchSprintService searchService;

    public SprintAuditListener(@Lazy SearchSprintServiceImpl searchService) {
        this.searchService = searchService;
    }

    @TransactionalEventListener
    @PostRemove
    @Override
    public void afterDelete(Sprint sprint) {
        try {
            searchService.delete(sprint.getId());
        } catch (Exception err) {
            log.info(String.format("SOMETHING WENT WRONG | LISTENER: Sprint | METHOD: DELETE | ERROR MESSAGE: %s", err.getMessage()));
        }
    }

    @TransactionalEventListener
    @PostUpdate
    @Override
    public void afterUpdate(Sprint sprint) {
        try {
            searchService.update(sprint.getId(), mapSprintToSearchSprint(sprint));
        } catch (Exception err) {
            log.info(String.format("SOMETHING WENT WRONG | LISTENER: Sprint | METHOD: UPDATE | ERROR MESSAGE: %s", err.getMessage()));
        }
    }

    @TransactionalEventListener
    @PostPersist
    @Override
    public void afterPersist(Sprint sprint) {
        try {
            searchService.create(mapSprintToSearchSprint(sprint));
        } catch (Exception err) {
            log.info(String.format("SOMETHING WENT WRONG | LISTENER: Sprint | METHOD: CREATE | ERROR MESSAGE: %s", err.getMessage()));
        }
    }

    private SearchSprint mapSprintToSearchSprint(Sprint sprint) {
        SearchSprint searchSprint = new SearchSprint();

        searchSprint.setId(sprint.getId());
        searchSprint.setName(sprint.getName());
        searchSprint.setTasks(sprint.getTasks().stream().map(Task::getId).toList());
        searchSprint.setMembers(sprint.getMembers().stream().map(Member::getId).toList());
        searchSprint.setDescription(sprint.getDescription());
        searchSprint.setTeamId(sprint.getTeam().getId());

        return searchSprint;
    }
}
