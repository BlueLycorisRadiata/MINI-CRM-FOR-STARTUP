package com.example.crm.controller;

import com.example.crm.dto.CreateInteractionRequest;
import com.example.crm.dto.InteractionResponse;
import com.example.crm.service.InteractionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users/{id}/interactions")
public class InteractionController {

  private final InteractionService interactionService;

  public InteractionController(InteractionService interactionService) {
    this.interactionService = interactionService;
  }

  // POST /users/:id/interactions
  @PostMapping
  public ResponseEntity<InteractionResponse> create(
      @PathVariable("id") UUID userId,
      @Valid @RequestBody CreateInteractionRequest req
  ) {
    return ResponseEntity.status(HttpStatus.CREATED).body(interactionService.create(userId, req));
  }

  // GET /users/:id/interactions
  @GetMapping
  public List<InteractionResponse> list(@PathVariable("id") UUID userId) {
    return interactionService.listByUser(userId);
  }
}
