package com.sprintplanner.planner.impl.listeners;

import com.sprintplanner.planner.domain.listeners.ProjectListener;
import com.sprintplanner.planner.domain.model.Project;
import com.sprintplanner.planner.domain.model.Team;
import com.sprintplanner.planner.domain.model.search.SearchProject;
import com.sprintplanner.planner.domain.service.search.SearchProjectService;
import com.sprintplanner.planner.impl.services.search.SearchProjectServiceImpl;
import jakarta.persistence.PostRemove;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.event.TransactionalEventListener;

@Log
public class ProjectAuditListener implements ProjectListener {
    private final SearchProjectService searchService;

    public ProjectAuditListener(@Lazy SearchProjectServiceImpl searchService) {
        this.searchService = searchService;
    }

    @TransactionalEventListener
    @PostRemove
    @Override
    public void afterDelete(Project project) {
        try {
            searchService.delete(project.getId());
        } catch (Exception err) {
            log.info(String.format("SOMETHING WENT WRONG | LISTENER: Project | METHOD: DELETE | ERROR MESSAGE: %s", err.getMessage()));
        }
    }

    @Override
    public void afterUpdate(Project project) {
        try {
            searchService.update(project.getId(), mapProjectToSearchProject(project));
        } catch (Exception err) {
            log.info(String.format("SOMETHING WENT WRONG | LISTENER: Project | METHOD: UPDATE | ERROR MESSAGE: %s", err.getMessage()));
        }
    }

    @Override
    public void afterPersist(Project project) {
        try {
            searchService.create(mapProjectToSearchProject(project));
        } catch (Exception err) {
            log.info(String.format("SOMETHING WENT WRONG | LISTENER: Project | METHOD: CREATE | ERROR MESSAGE: %s", err.getMessage()));
        }
    }

    private SearchProject mapProjectToSearchProject(Project project) {
        SearchProject searchProject = new SearchProject();

        searchProject.setId(project.getId());
        searchProject.setName(project.getName());
        searchProject.setDescription(project.getDescription());
        searchProject.setTeams(project.getTeams().stream().map(Team::getId).toList());

        return searchProject;
    }
}
