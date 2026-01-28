package com.example.crm.repository;

import com.example.crm.entity.InteractionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface InteractionRepository extends JpaRepository<InteractionEntity, UUID> {

  List<InteractionEntity> findByUser_IdOrderByCreatedAtDesc(UUID userId);

  void deleteByUser_Id(UUID userId);
}
