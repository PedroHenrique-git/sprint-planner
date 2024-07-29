package com.sprintplanner.planner.impl.listeners;

import java.util.List;

import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import com.sprintplanner.planner.domain.model.Member;
import com.sprintplanner.planner.infra.sso.SSO;

import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MemberAuditListener {
    private SSO sso;

    @PrePersist
    private void afterPersist(Member member) {
        RealmResource realmResource = sso.getRealmResource();
        UsersResource usersResource = realmResource.users();

        UserRepresentation userRepresentation = new UserRepresentation();

        userRepresentation.setEnabled(true);
        userRepresentation.setEmail(member.getEmail());
        userRepresentation.setUsername(member.getUsername());
        userRepresentation.setFirstName(member.getFirstName());
        userRepresentation.setLastName(member.getLastName());

        CredentialRepresentation credentials = new CredentialRepresentation();

        credentials.setTemporary(false);
        credentials.setValue(member.getPassword());

        userRepresentation.setCredentials(List.of(credentials));

        usersResource.create(userRepresentation);
    }
}
