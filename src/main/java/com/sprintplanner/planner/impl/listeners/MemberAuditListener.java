package com.sprintplanner.planner.impl.listeners;

import com.sprintplanner.planner.domain.model.Sprint;
import com.sprintplanner.planner.domain.model.Task;
import com.sprintplanner.planner.domain.model.Team;
import com.sprintplanner.planner.domain.model.search.SearchMember;
import com.sprintplanner.planner.domain.service.search.SearchMemberService;
import com.sprintplanner.planner.impl.services.search.SearchMemberServiceImpl;
import jakarta.persistence.*;
import lombok.extern.java.Log;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.event.TransactionalEventListener;

import com.sprintplanner.planner.domain.enumeration.UserAction;
import com.sprintplanner.planner.domain.enumeration.UserStatus;
import com.sprintplanner.planner.domain.listeners.MemberListener;
import com.sprintplanner.planner.domain.model.Member;
import com.sprintplanner.planner.domain.service.KeycloakService;
import com.sprintplanner.planner.impl.services.KeycloakServiceImpl;

@Log
public class MemberAuditListener implements MemberListener {
    private final KeycloakService service;
    private final SearchMemberService searchService;

    public MemberAuditListener(KeycloakServiceImpl service, @Lazy SearchMemberServiceImpl searchService) {
        this.service = service;
        this.searchService = searchService;
    }

    @TransactionalEventListener
    @PreRemove
    public void beforeDelete(Member member) {
        try {
            service.deleteUser(member);
        } catch (Exception err) {
            member.setStatus(UserStatus.PENDING);
            member.setAction(UserAction.DELETE);
        }
    }

    @TransactionalEventListener
    @PreUpdate
    public void beforeUpdate(Member member) {
        try {
            service.updateUser(member);
        } catch (Exception err) {
            member.setStatus(UserStatus.PENDING);
            member.setAction(UserAction.UPDATE);
        }
    }

    @TransactionalEventListener
    @PrePersist
    public void beforePersist(Member member) {
        try {
            service.createUser(member);
        } catch (Exception err) {
            member.setStatus(UserStatus.PENDING);
            member.setAction(UserAction.CREATE);
        }
    }

    @TransactionalEventListener
    @PostRemove
    @Override
    public void afterDelete(Member member) {
        try {
            searchService.delete(member.getId());
        } catch (Exception err) {
            log.info(String.format("SOMETHING WENT WRONG | LISTENER: Member | METHOD: DELETE | ERROR MESSAGE: %s", err.getMessage()));
        }
    }

    @TransactionalEventListener
    @PostPersist
    @Override
    public void afterPersist(Member member) {
        try {
            searchService.create(mapMemberToSearchMember(member));
        } catch (Exception err) {
            log.info(String.format("SOMETHING WENT WRONG | LISTENER: Member | METHOD: CREATE | ERROR MESSAGE: %s", err.getMessage()));
        }
    }

    @TransactionalEventListener
    @PostUpdate
    @Override
    public void afterUpdate(Member member) {
        try {
            searchService.update(member.getId(), mapMemberToSearchMember(member));
        } catch (Exception err) {
            log.info(String.format("SOMETHING WENT WRONG | LISTENER: Member | METHOD: UPDATE | ERROR MESSAGE: %s", err.getMessage()));
        }
    }

    private SearchMember mapMemberToSearchMember(Member member) {
        SearchMember searchMember = new SearchMember();

        searchMember.setId(member.getId());
        searchMember.setAvatar(member.getAvatar());
        searchMember.setEmail(member.getEmail());
        searchMember.setFirstName(member.getFirstName());
        searchMember.setLastName(member.getFirstName());
        searchMember.setUsername(member.getUsername());
        searchMember.setSprints(member.getSprints().stream().map(Sprint::getId).toList());
        searchMember.setTasks(member.getTasks().stream().map(Task::getId).toList());
        searchMember.setTeams(member.getTeams().stream().map(Team::getId).toList());

        return searchMember;
    }
}
