package com.sprintplanner.planner.impl.listeners;

import java.util.List;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.event.TransactionalEventListener;

import com.sprintplanner.planner.domain.listeners.MemberListener;
import com.sprintplanner.planner.domain.model.Member;
import com.sprintplanner.planner.domain.providers.sso.SSOProvider;
import com.sprintplanner.planner.impl.providers.sso.KeycloakProvider;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;


public class MemberAuditListener implements MemberListener {
    @Value("${keycloak.realm}")
    private String realm;

    private SSOProvider<Keycloak> sso;

    public MemberAuditListener(KeycloakProvider sso) {
        this.sso = sso;
    }

    @TransactionalEventListener
    @PreRemove
    public void beforeDelete(Member member) {
        RealmResource realmResource = getRealmResource();
        UsersResource usersResource = realmResource.users();

        UserRepresentation user = usersResource.searchByEmail(member.getEmail(), true).getFirst();
    
        UserResource userResource = usersResource.get(user.getId());

        userResource.remove();
    }

    @TransactionalEventListener
    @PreUpdate
    public void beforeUpdate(Member member) {
        RealmResource realmResource = getRealmResource();
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
    public void beforePersist(Member member) {
        RealmResource realmResource = getRealmResource();
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

    private RealmResource getRealmResource() {
        var instance = sso.getSSOInstanceWithoutCredentials();

        return instance.realm(realm);
    }
}
