package com.example.crm.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(
    name = "interactions",
    indexes = {
        @Index(name = "idx_interactions_user", columnList = "user_id"),
        @Index(name = "idx_interactions_created", columnList = "created_at")
    }
)
public class InteractionEntity {

  @Id
  @GeneratedValue
  @UuidGenerator
  private UUID id;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private UserEntity user;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 10)
  private InteractionType type;

  @Column(nullable = false, length = 2000)
  private String note;

  @Column(name = "created_at", nullable = false, updatable = false)
  private Instant createdAt;

  @PrePersist
  void prePersist() {
    if (createdAt == null) createdAt = Instant.now();
  }

  // Getters / setters
  public UUID getId() { return id; }
  public void setId(UUID id) { this.id = id; }

  public UserEntity getUser() { return user; }
  public void setUser(UserEntity user) { this.user = user; }

  public InteractionType getType() { return type; }
  public void setType(InteractionType type) { this.type = type; }

  public String getNote() { return note; }
  public void setNote(String note) { this.note = note; }

  public Instant getCreatedAt() { return createdAt; }
  public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
