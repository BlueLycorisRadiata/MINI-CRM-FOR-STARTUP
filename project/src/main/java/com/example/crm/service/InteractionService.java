package com.example.crm.service;

import com.example.crm.dto.CreateInteractionRequest;
import com.example.crm.dto.InteractionResponse;
import com.example.crm.entity.InteractionEntity;
import com.example.crm.entity.InteractionType;
import com.example.crm.entity.UserEntity;
import com.example.crm.exception.NotFoundException;
import com.example.crm.repository.InteractionRepository;
import com.example.crm.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InteractionService {

  private final InteractionRepository interactionRepository;
  private final UserRepository userRepository;

  public InteractionService(InteractionRepository interactionRepository, UserRepository userRepository) {
    this.interactionRepository = interactionRepository;
    this.userRepository = userRepository;
  }

  public InteractionResponse create(UUID userId, CreateInteractionRequest req) {
    UserEntity user = userRepository.findById(userId)
        .orElseThrow(() -> new NotFoundException("User not found: " + userId));

    InteractionType type;
    try {
      type = InteractionType.valueOf(req.type().toUpperCase());
    } catch (Exception e) {
      throw new IllegalArgumentException("Invalid type. Must be call|email|chat");
    }

    InteractionEntity it = new InteractionEntity();
    it.setUser(user);
    it.setType(type);
    it.setNote(req.note());

    it = interactionRepository.save(it);
    return toResponse(it);
  }

  public List<InteractionResponse> listByUser(UUID userId) {
    // Return 404 if user doesn't exist
    if (!userRepository.existsById(userId)) {
      throw new NotFoundException("User not found: " + userId);
    }

    return interactionRepository.findByUser_IdOrderByCreatedAtDesc(userId)
        .stream().map(this::toResponse).toList();
  }

  private InteractionResponse toResponse(InteractionEntity it) {
    return new InteractionResponse(
        it.getId(),
        it.getUser().getId(),
        it.getType().name().toLowerCase(),
        it.getNote(),
        it.getCreatedAt()
    );
  }
}
