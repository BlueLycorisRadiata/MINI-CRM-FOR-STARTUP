package com.example.crm.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record CreateUserRequest(
    @NotBlank @Size(max = 200) String name,
    @NotBlank @Email @Size(max = 320) String email,
    Set<@Size(max = 50) String> tags
) {}
