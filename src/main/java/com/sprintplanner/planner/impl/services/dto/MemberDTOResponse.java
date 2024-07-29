package com.sprintplanner.planner.impl.services.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberDTOResponse {
    @JsonProperty
    String id;

    @JsonProperty
    String username;

    @JsonProperty
    String firstName;

    @JsonProperty
    String lastName;

    @JsonProperty
    String avatar;

    @JsonProperty
    String email;

    @JsonProperty
    List<String> teams;

    @JsonProperty
    List<String> tasks;

    @JsonProperty
    List<String> sprints;
}
