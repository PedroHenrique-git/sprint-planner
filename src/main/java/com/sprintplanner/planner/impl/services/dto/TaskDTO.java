package com.sprintplanner.planner.impl.services.dto;

import org.springframework.data.annotation.ReadOnlyProperty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sprintplanner.planner.domain.enumeration.Complexity;
import com.sprintplanner.planner.domain.enumeration.Priority;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaskDTO {
    @JsonProperty("id")
    @ReadOnlyProperty
    String id;

    @NotBlank(message = "the task name must not the blank")
    @NotNull(message = "the task name must not the null")
    @Size(min = 1, max = 255, message = "The name must be between 1 and 255 characters")
    String name;

    @NotBlank(message = "the task description must not the blank")
    @NotNull(message = "the task description must not the null")
    @Size(max = 255, message = "The description must not have a maximum of 255 characters")
    String description;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "the task complexity must not the null")
    Complexity complexity;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "the task priority must not the null")
    Priority priority;

    @NotNull(message = "the task punctuation must not the null")
    int punctuation;

    @NotBlank(message = "the task sprint must not the blank")
    @NotNull(message = "the task sprint must not the null")
    String sprintId;

    @NotBlank(message = "the task member must not the blank")
    @NotNull(message = "the task member must not the null")
    String memberId;
}
