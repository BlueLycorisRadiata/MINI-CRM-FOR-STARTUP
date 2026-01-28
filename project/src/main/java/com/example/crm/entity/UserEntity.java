package com.example.crm.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(
    name = "users",
    indexes = {
        @Index(name = "idx_users_email", columnList = "email"),
        @Index(name = "idx_users_name", columnList = "name")
    }
)
public class UserEntity {

  @Id
  @GeneratedValue
  @UuidGenerator
  private UUID id;

  @Column(nullable = false, length = 200)
  private String name;

  @Column(nullable = false, unique = true, length = 320)
  private String email;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "user_tags", joinColumns = @JoinColumn(name = "user_id"))
  @Column(name = "tag", length = 50, nullable = false)
  private Set<String> tags = new HashSet<>();

  @Column(nullable = false, updatable = false)
  private Instant createdAt;

  @PrePersist
  void prePersist() {
    if (createdAt == null) createdAt = Instant.now();
  }

  // Getters / setters
  public UUID getId() { return id; }
  public void setId(UUID id) { this.id = id; }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }

  public Set<String> getTags() { return tags; }
  public void setTags(Set<String> tags) { this.tags = tags; }

  public Instant getCreatedAt() { return createdAt; }
  public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
