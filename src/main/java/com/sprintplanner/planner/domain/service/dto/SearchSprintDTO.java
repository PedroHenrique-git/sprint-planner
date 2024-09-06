package com.sprintplanner.planner.domain.service.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SearchSprintDTO {
    @JsonProperty
    String id;

    @JsonProperty
    String name;

    @JsonProperty
    String description;

    @JsonProperty
    String teamId;

    @JsonProperty
    List<String> tasks;

    @JsonProperty
    List<String> members;
}
