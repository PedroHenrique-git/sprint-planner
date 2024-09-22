package com.sprintplanner.planner.impl.listeners;

import com.sprintplanner.planner.domain.listeners.TeamListener;
import com.sprintplanner.planner.domain.model.Member;
import com.sprintplanner.planner.domain.model.Sprint;
import com.sprintplanner.planner.domain.model.Team;
import com.sprintplanner.planner.domain.model.search.SearchTeam;
import com.sprintplanner.planner.domain.service.search.SearchTeamService;
import com.sprintplanner.planner.impl.services.search.SearchTeamServiceImpl;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import lombok.extern.java.Log;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.event.TransactionalEventListener;

@Log
public class TeamAuditListener implements TeamListener {
    private final SearchTeamService searchService;

    public TeamAuditListener(@Lazy SearchTeamServiceImpl searchService) {
        this.searchService = searchService;
    }

    @TransactionalEventListener
    @PostRemove
    @Override
    public void afterDelete(Team team) {
        try {
            searchService.delete(team.getId());
        } catch (Exception err) {
            log.info(String.format("SOMETHING WENT WRONG | LISTENER: Team | METHOD: DELETE | ERROR MESSAGE: %s", err.getMessage()));
        }
    }

    @TransactionalEventListener
    @PostPersist
    @Override
    public void afterPersist(Team team) {
        try {
            searchService.create(mapTeamToSearchTeam(team));
        } catch (Exception err) {
            log.info(String.format("SOMETHING WENT WRONG | LISTENER: Team | METHOD: CREATE | ERROR MESSAGE: %s", err.getMessage()));
        }
    }

    @TransactionalEventListener
    @PostUpdate
    @Override
    public void afterUpdate(Team team) {
        try {
            searchService.update(team.getId(), mapTeamToSearchTeam(team));
        } catch (Exception err) {
            log.info(String.format("SOMETHING WENT WRONG | LISTENER: Team | METHOD: UPDATE | ERROR MESSAGE: %s", err.getMessage()));
        }
    }

    private SearchTeam mapTeamToSearchTeam(Team team) {
        SearchTeam searchTeam = new SearchTeam();

        searchTeam.setId(team.getId());
        searchTeam.setName(team.getName());
        searchTeam.setDescription(team.getDescription());
        searchTeam.setSprints(team.getSprints().stream().map(Sprint::getId).toList());
        searchTeam.setMembers(team.getMembers().stream().map(Member::getId).toList());

        return searchTeam;
    }
}
