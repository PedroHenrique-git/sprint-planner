package com.sprintplanner.planner.domain.model;

import com.sprintplanner.planner.impl.listeners.MemberAuditListener;
import com.sprintplanner.planner.impl.listeners.ProjectAuditListener;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Table(name = "project")
@Entity(name = "project")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EntityListeners({ ProjectAuditListener.class })
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Nonnull
    private String name;

    @Nonnull
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "project_team", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "team_id"))
    private List<Team> teams = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdOn;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant lastUpdatedOn;
}
