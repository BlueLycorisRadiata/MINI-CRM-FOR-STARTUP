package com.example.crm.controller;

import com.example.crm.dto.CreateUserRequest;
import com.example.crm.dto.UpdateUserRequest;
import com.example.crm.dto.UserResponse;
import com.example.crm.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  // POST /users
  @PostMapping
  public ResponseEntity<UserResponse> create(@Valid @RequestBody CreateUserRequest req) {
    return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(req));
  }

  // GET /users?search=&tag=
  @GetMapping
  public List<UserResponse> list(
      @RequestParam(required = false) String search,
      @RequestParam(required = false) String tag
  ) {
    return userService.list(search, tag);
  }

  // GET /users/:id
  @GetMapping("/{id}")
  public UserResponse get(@PathVariable UUID id) {
    return userService.get(id);
  }

  // CRUD extras (requested)
  @PutMapping("/{id}")
  public UserResponse update(@PathVariable UUID id, @Valid @RequestBody UpdateUserRequest req) {
    return userService.update(id, req);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable UUID id) {
    userService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
