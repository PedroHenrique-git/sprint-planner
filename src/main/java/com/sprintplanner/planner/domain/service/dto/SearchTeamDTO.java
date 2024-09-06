package com.sprintplanner.planner.domain.service.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SearchTeamDTO {
    @JsonProperty
    String id;

    @JsonProperty
    String name;

    @JsonProperty
    String description;

    @JsonProperty
    List<String> sprints;

    @JsonProperty
    List<String> members;
}
