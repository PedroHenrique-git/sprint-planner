package com.sprintplanner.planner.impl.services.dto;

import java.util.List;

import org.springframework.data.annotation.ReadOnlyProperty;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SprintDTO {
    @JsonProperty("id")
    @ReadOnlyProperty
    String id;

    @NotBlank(message = "the sprint name must not the blank")
    @NotNull(message = "the sprint name must not the null")
    @Size(min = 1, max = 255, message = "The name must be between 1 and 255 characters")
    String name;

    @NotBlank(message = "the sprint description must not the blank")
    @NotNull(message = "the sprint description must not the null")
    @Size(max = 255, message = "The description must not have a maximum of 255 characters")
    String description;

    @NotNull(message = "the sprint tasks must not the null")
    List<String> tasks;

    @NotNull(message = "the sprint members must not the null")
    List<String> members;
}
