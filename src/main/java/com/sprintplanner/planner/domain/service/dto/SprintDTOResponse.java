package com.sprintplanner.planner.domain.service.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SprintDTOResponse {
    @JsonProperty
    String id;

    @JsonProperty
    String name;

    @JsonProperty
    LocalDateTime startDate;

    @JsonProperty
    LocalDateTime endDate;

    @JsonProperty
    String description;

    @JsonProperty
    String teamId;

    @JsonProperty
    List<String> tasks;

    @JsonProperty
    List<String> members;
}
