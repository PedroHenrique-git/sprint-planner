package com.sprintplanner.planner.domain.model;

import java.time.Instant;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "sprint")
@Entity(name = "sprint")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Sprint {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Nonnull
    private String name;

    @Nonnull
    private String description;

    @OneToMany(mappedBy = "sprint", cascade = CascadeType.ALL)
    private List<Task> tasks;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToMany
    @JoinTable(name = "sprint_member", joinColumns = @JoinColumn(name = "sprint_id"), inverseJoinColumns = @JoinColumn(name = "member_id"))
    private List<Member> members;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdOn;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant lastUpdatedOn;
}
