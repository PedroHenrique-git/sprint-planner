package com.sprintplanner.planner.impl.services.dto;

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
    String description;

    @JsonProperty
    List<String> tasks;

    @JsonProperty
    List<String> members;
}

