package com.example.crm.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record UpdateUserRequest(
    @Size(max = 200) String name,
    @Email @Size(max = 320) String email,
    Set<@Size(max = 50) String> tags
) {}
