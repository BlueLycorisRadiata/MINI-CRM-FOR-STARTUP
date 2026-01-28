package com.example.crm.service;

import com.example.crm.dto.CreateUserRequest;
import com.example.crm.dto.UpdateUserRequest;
import com.example.crm.dto.UserResponse;
import com.example.crm.entity.UserEntity;
import com.example.crm.exception.NotFoundException;
import com.example.crm.repository.InteractionRepository;
import com.example.crm.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final InteractionRepository interactionRepository;

  public UserService(UserRepository userRepository, InteractionRepository interactionRepository) {
    this.userRepository = userRepository;
    this.interactionRepository = interactionRepository;
  }

  public UserResponse create(CreateUserRequest req) {
    if (userRepository.existsByEmailIgnoreCase(req.email())) {
      throw new IllegalArgumentException("Email already exists");
    }

    UserEntity u = new UserEntity();
    u.setName(req.name());
    u.setEmail(req.email());
    u.setTags(req.tags() == null ? new HashSet<>() : new HashSet<>(req.tags()));

    try {
      u = userRepository.save(u);
    } catch (DataIntegrityViolationException e) {
      // In case unique constraint triggers
      throw new IllegalArgumentException("Email already exists");
    }

    return toResponse(u);
  }

  public List<UserResponse> list(String search, String tag) {
    return userRepository.search(search, tag).stream().map(this::toResponse).toList();
  }


  public List<String> listAllTags() {
    return userRepository.findDistinctTags();
  }

  public UserResponse get(UUID id) {
    return toResponse(findEntity(id));
  }

  public UserResponse update(UUID id, UpdateUserRequest req) {
    UserEntity u = findEntity(id);

    if (req.name() != null) u.setName(req.name());
    if (req.email() != null) u.setEmail(req.email());
    if (req.tags() != null) u.setTags(new HashSet<>(req.tags()));

    try {
      u = userRepository.save(u);
    } catch (DataIntegrityViolationException e) {
      throw new IllegalArgumentException("Email already exists");
    }
    return toResponse(u);
  }

  @Transactional
  public void delete(UUID id) {
    // ensure user exists -> 404
    findEntity(id);

    // delete interactions first to avoid FK constraint issues
    interactionRepository.deleteByUser_Id(id);

    userRepository.deleteById(id);
  }

  private UserEntity findEntity(UUID id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("User not found: " + id));
  }

  private UserResponse toResponse(UserEntity u) {
    Set<String> tags = u.getTags() == null ? Set.of() : Set.copyOf(u.getTags());
    return new UserResponse(u.getId(), u.getName(), u.getEmail(), tags, u.getCreatedAt());
  }
}
