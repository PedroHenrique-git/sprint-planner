package com.sprintplanner.planner.domain.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProjectDTO {
    @NotBlank(message = "the project name must not the blank")
    @NotNull(message = "the project name must not the null")
    @Size(min = 1, max = 255, message = "The project name must be between 1 and 255 characters")
    String name;

    @NotBlank(message = "the project description must not the blank")
    @NotNull(message = "the project description must not the null")
    @Size(max = 255, message = "The project description must not have a maximum of 255 characters")
    String description;

    @NotNull(message = "the project teams must not the null")
    List<String> teams;
}
