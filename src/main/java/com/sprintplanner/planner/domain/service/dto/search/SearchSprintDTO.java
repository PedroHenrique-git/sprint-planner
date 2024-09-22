package com.sprintplanner.planner.domain.service.dto.search;

import java.time.LocalDateTime;
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
    String startDate;

    @JsonProperty
    String endDate;

    @JsonProperty
    String description;

    @JsonProperty
    String teamId;

    @JsonProperty
    List<String> tasks;

    @JsonProperty
    List<String> members;
}
