package com.sprintplanner.planner.domain.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.sprintplanner.planner.impl.listeners.TeamAuditListener;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "team")
@Entity(name = "team")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EntityListeners({ TeamAuditListener.class })
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Nonnull
    private String name;

    @Nonnull
    private String description;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Sprint> sprints = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "team_member", joinColumns = @JoinColumn(name = "team_id"), inverseJoinColumns = @JoinColumn(name = "member_id"))
    private List<Member> members = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdOn;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant lastUpdatedOn;
}