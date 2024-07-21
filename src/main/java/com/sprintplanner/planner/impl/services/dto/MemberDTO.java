package com.sprintplanner.planner.impl.services.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberDTO {
    @NotBlank(message = "the member name must not the blank")
    @NotNull(message = "the member name must not the null")
    @Size(min = 1, max = 255, message = "The name must be between 1 and 255 characters")
    String name;

    @NotBlank(message = "the member avatar must not the blank")
    @NotNull(message = "the member avatar must not the null")
    @Size(max = 255, message = "The email must not have a maximum of 255 characters")
    String avatar;

    @NotBlank(message = "the member email must not the blank")
    @NotNull(message = "the member email must not the null")
    @Size(max = 255, message = "The email must not have a maximum of 255 characters")
    String email;

    @NotBlank(message = "the member password must not the blank")
    @NotNull(message = "the member password must not the null")
    @Size(min = 8, max = 255, message = "The password must be between 8 and 255 characters")
    String password;

    @NotNull(message = "the member tasks must not the null")
    List<TaskDTO> tasks;

    @NotNull(message = "the member sprints must not the null")
    List<SprintDTO> sprints;
}
