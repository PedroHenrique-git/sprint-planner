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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Nonnull
    private String name;

    @Nonnull
    private String description;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<Sprint> sprints;

    @ManyToMany
    @JoinTable(name = "team_member", joinColumns = @JoinColumn(name = "team_id"), inverseJoinColumns = @JoinColumn(name = "member_id"))
    private List<Member> members;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdOn;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant lastUpdatedOn;
}