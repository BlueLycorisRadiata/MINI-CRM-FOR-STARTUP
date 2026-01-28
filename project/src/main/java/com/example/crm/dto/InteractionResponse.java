package com.example.crm.dto;

import java.time.Instant;
import java.util.UUID;

public record InteractionResponse(
    UUID id,
    UUID userId,
    String type,
    String note,
    Instant createdAt
) {}
