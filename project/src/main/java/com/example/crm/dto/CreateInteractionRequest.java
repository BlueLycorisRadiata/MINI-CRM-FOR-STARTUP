package com.example.crm.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateInteractionRequest(
    @Pattern(regexp = "call|email|chat") String type,
    @NotBlank @Size(max = 2000) String note
) {}
