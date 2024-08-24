package com.sprintplanner.planner.impl.schedulers;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sprintplanner.planner.domain.enumeration.UserStatus;
import com.sprintplanner.planner.domain.model.Member;
import com.sprintplanner.planner.domain.service.KeycloakService;
import com.sprintplanner.planner.domain.service.MemberService;
import com.sprintplanner.planner.impl.services.KeycloakServiceImpl;
import com.sprintplanner.planner.impl.services.MemberServiceImpl;

import lombok.extern.java.Log;

@Log
@Component
public class UserVerifySchedule {
    private MemberService memberService;
    private KeycloakService keycloakService;

    public UserVerifySchedule(MemberServiceImpl memberService, KeycloakServiceImpl keycloakService) {
        this.memberService = memberService;
        this.keycloakService = keycloakService;
    }

    @Scheduled(fixedDelay = 30 * 60 * 1000)
    public void verifyUser() {
        var members = memberService.findByStatus(UserStatus.PENDING);

        if (members.isEmpty()) {
            log.info("No users to verify");

            return;
        }

        for (Member m : members) {
            switch (m.getAction()) {
                case CREATE:
                    keycloakService.createUser(m);
                    keycloakService.sendUpdatePasswordEmail(m);

                    updateUserStatus(m);
                    break;
                case UPDATE:
                    keycloakService.updateUser(m);
                    keycloakService.sendUpdateProfileEmail(m);

                    updateUserStatus(m);
                    break;
                case DELETE:
                    keycloakService.deleteUser(m);

                    updateUserStatus(m);
                    break;
                default:
                    log.info(String.format("Action not found, ACTION: %s", m.getAction().toString()));
                    break;
            }
        }
    }

    private void updateUserStatus(Member m) {
        m.setStatus(UserStatus.OK);

        // prevent NullPointerException
        m.setPassword("");

        memberService.update(m.getId(), m);
    }
}
