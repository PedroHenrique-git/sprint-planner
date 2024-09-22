package com.sprintplanner.planner.impl.listeners;

import com.sprintplanner.planner.domain.listeners.TaskListener;
import com.sprintplanner.planner.domain.model.Task;
import com.sprintplanner.planner.domain.model.search.SearchTask;
import com.sprintplanner.planner.domain.service.search.SearchTaskService;
import com.sprintplanner.planner.impl.services.search.SearchTaskServiceImpl;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import lombok.extern.java.Log;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.event.TransactionalEventListener;

@Log
public class TaskAuditListener implements TaskListener {
    private final SearchTaskService searchService;

    public TaskAuditListener(@Lazy SearchTaskServiceImpl searchService) {
        this.searchService = searchService;
    }

    @TransactionalEventListener
    @PostRemove
    @Override
    public void afterDelete(Task task) {
        try {
            searchService.delete(task.getId());
        } catch (Exception err) {
            log.info(String.format("SOMETHING WENT WRONG | LISTENER: Task | METHOD: DELETE | ERROR MESSAGE: %s", err.getMessage()));
        }
    }

    @TransactionalEventListener
    @PostPersist
    @Override
    public void afterPersist(Task task) {
        try {
            searchService.create(mapTaskToSearchTask(task));
        } catch (Exception err) {
            log.info(String.format("SOMETHING WENT WRONG | LISTENER: Task | METHOD: CREATE | ERROR MESSAGE: %s", err.getMessage()));
        }
    }

    @TransactionalEventListener
    @PostUpdate
    @Override
    public void afterUpdate(Task task) {
        try {
            searchService.update(task.getId(), mapTaskToSearchTask(task));
        } catch (Exception err) {
            log.info(String.format("SOMETHING WENT WRONG | LISTENER: Task | METHOD: UPDATE | ERROR MESSAGE: %s", err.getMessage()));
        }
    }

    private SearchTask mapTaskToSearchTask(Task task) {
        SearchTask searchTask = new SearchTask();

        searchTask.setId(task.getId());
        searchTask.setName(task.getName());
        searchTask.setComplexity(task.getComplexity());
        searchTask.setPriority(task.getPriority());
        searchTask.setDescription(task.getDescription());
        searchTask.setMemberId(task.getMember().getId());
        searchTask.setPunctuation(task.getPunctuation());
        searchTask.setSprintId(task.getSprint().getId());

        return searchTask;
    }
}
