package com.example.crm.dto;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

public record UserResponse(
    UUID id,
    String name,
    String email,
    Set<String> tags,
    Instant createdAt
) {}
