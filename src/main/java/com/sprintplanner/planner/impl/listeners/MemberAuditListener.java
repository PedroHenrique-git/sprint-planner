package com.sprintplanner.planner.impl.listeners;

import java.util.List;

import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.transaction.event.TransactionalEventListener;

import com.sprintplanner.planner.domain.model.Member;
import com.sprintplanner.planner.infra.sso.SSO;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MemberAuditListener {
    private SSO sso;

    @TransactionalEventListener
    @PreRemove
    private void beforeDelete(Member member) {
        RealmResource realmResource = sso.getRealmResource();
        UsersResource usersResource = realmResource.users();

        UserRepresentation user = usersResource.searchByEmail(member.getEmail(), true).getFirst();
    
        UserResource userResource = usersResource.get(user.getId());

        userResource.remove();
    }

    @TransactionalEventListener
    @PreUpdate
    private void beforeUpdate(Member member) {
        RealmResource realmResource = sso.getRealmResource();
        UsersResource usersResource = realmResource.users();

        UserRepresentation user = usersResource.searchByEmail(member.getEmail(), true).getFirst();
    
        UserResource userResource = usersResource.get(user.getId());

        user.setEmail(member.getEmail());
        user.setUsername(member.getUsername());
        user.setFirstName(member.getFirstName());
        user.setLastName(member.getLastName());

        CredentialRepresentation credential = new CredentialRepresentation();

        credential.setTemporary(false);
        credential.setValue(member.getPassword());

        user.setCredentials(List.of(credential));

        userResource.update(user);
    }
    
    @TransactionalEventListener
    @PrePersist
    private void beforePersist(Member member) {
        RealmResource realmResource = sso.getRealmResource();
        UsersResource usersResource = realmResource.users();

        UserRepresentation userRepresentation = new UserRepresentation();

        userRepresentation.setEnabled(true);
        userRepresentation.setEmail(member.getEmail());
        userRepresentation.setUsername(member.getUsername());
        userRepresentation.setFirstName(member.getFirstName());
        userRepresentation.setLastName(member.getLastName());

        CredentialRepresentation credential = new CredentialRepresentation();

        credential.setTemporary(false);
        credential.setValue(member.getPassword());

        userRepresentation.setCredentials(List.of(credential));

        usersResource.create(userRepresentation);
    }
}
