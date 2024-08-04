package com.sprintplanner.planner.domain.service.dto;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Pattern.Flag;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberDTO {
    @NotBlank(message = "the member username must not the blank")
    @NotNull(message = "the member username must not the null")
    @Size(min = 1, max = 255, message = "The username must be between 1 and 255 characters")
    String username;

    @NotBlank(message = "the member first name must not the blank")
    @NotNull(message = "the member first name must not the null")
    @Size(min = 1, max = 255, message = "The first name must be between 1 and 255 characters")
    String firstName;

    @NotBlank(message = "the member last name must not the blank")
    @NotNull(message = "the member last name must not the null")
    @Size(min = 1, max = 255, message = "The last name must be between 1 and 255 characters")
    String lastName;

    @NotBlank(message = "the member avatar must not the blank")
    @NotNull(message = "the member avatar must not the null")
    @Size(max = 255, message = "The email must not have a maximum of 255 characters")
    String avatar;

    @NotBlank(message = "the member email must not the blank")
    @NotNull(message = "the member email must not the null")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
    flags = Flag.CASE_INSENSITIVE, message = "Invalid email")
    @Size(max = 255, message = "The email must not have a maximum of 255 characters")
    String email;

    @NotBlank(message = "the member password must not the blank")
    @NotNull(message = "the member password must not the null")
    @Pattern(
        regexp = "^(?=.*[A-Z].*[A-Z])(?=.*[!@#$&*])(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z].*[a-z]).{8}$", 
        message = "Invalid password, your password must follow the rules: at least two capital letters, at least one uppercase letter, at least two digits, at least two lowercase letters and at least eight characters."
    )
    @Size(min = 8, max = 255, message = "The password must be between 8 and 255 characters")
    String password;

    @NotNull(message = "the member teams must not the null")
    List<String> teams;

    @NotNull(message = "the member tasks must not the null")
    List<String> tasks;

    @NotNull(message = "the member sprints must not the null")
    List<String> sprints;
}
