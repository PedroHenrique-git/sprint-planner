package com.sprintplanner.planner.domain.model;

import java.time.Instant;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.sprintplanner.planner.domain.enumeration.Complexity;
import com.sprintplanner.planner.domain.enumeration.Priority;
import com.sprintplanner.planner.impl.listeners.TaskAuditListener;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "task")
@Entity(name = "task")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EntityListeners({ TaskAuditListener.class })
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Nonnull
    private String name;

    @Nonnull
    private String description;

    @Nonnull
    private Complexity complexity;

    @Nonnull
    private Priority priority;

    @Nonnull
    private int punctuation;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "sprint_id")
    private Sprint sprint;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdOn;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant lastUpdatedOn;
}