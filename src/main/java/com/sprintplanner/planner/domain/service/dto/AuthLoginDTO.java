package com.sprintplanner.planner.domain.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthLoginDTO {
    @NotBlank(message = "the username must not the blank")
    @NotNull(message = "the username must not the null")
    String username;

    @NotBlank(message = "the password must not the blank")
    @NotNull(message = "the password must not the null")
    String password;
}
