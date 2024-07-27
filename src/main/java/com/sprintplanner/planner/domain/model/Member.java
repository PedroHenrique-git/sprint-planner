package com.sprintplanner.planner.domain.model;

import java.time.Instant;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "member")
@Entity(name = "member")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Nonnull
    private String name;

    @Nonnull
    private String avatar;

    @Nonnull
    private String email;

    @Nonnull
    private String password;

    @OneToMany(mappedBy = "member")
    private List<Task> tasks;

    @ManyToMany
    @JoinTable(name = "sprint_member", joinColumns = @JoinColumn(name = "member_id"), inverseJoinColumns = @JoinColumn(name = "sprint_id"))
    private List<Sprint> sprints;

    @ManyToMany
    @JoinTable(name = "team_member", joinColumns = @JoinColumn(name = "member_id"), inverseJoinColumns = @JoinColumn(name = "team_id"))
    private List<Team> teams;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdOn;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant lastUpdatedOn;
}
