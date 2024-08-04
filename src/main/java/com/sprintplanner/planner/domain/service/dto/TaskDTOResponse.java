package com.sprintplanner.planner.domain.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sprintplanner.planner.domain.enumeration.Complexity;
import com.sprintplanner.planner.domain.enumeration.Priority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaskDTOResponse {
    @JsonProperty
    String id;

    @JsonProperty
    String name;

    @JsonProperty
    String description;

    @JsonProperty
    Complexity complexity;

    @JsonProperty
    Priority priority;

    @JsonProperty
    int punctuation;

    @JsonProperty
    String sprintId;

    @JsonProperty
    String memberId;
}
