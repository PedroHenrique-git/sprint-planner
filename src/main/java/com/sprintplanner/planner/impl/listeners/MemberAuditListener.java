package com.sprintplanner.planner.impl.listeners;

import org.springframework.transaction.event.TransactionalEventListener;

import com.sprintplanner.planner.domain.enumeration.UserAction;
import com.sprintplanner.planner.domain.enumeration.UserStatus;
import com.sprintplanner.planner.domain.listeners.MemberListener;
import com.sprintplanner.planner.domain.model.Member;
import com.sprintplanner.planner.domain.service.KeycloakService;
import com.sprintplanner.planner.impl.services.KeycloakServiceImpl;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;

public class MemberAuditListener implements MemberListener {
    private KeycloakService service;

    public MemberAuditListener(KeycloakServiceImpl service) {
        this.service = service;
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
}
