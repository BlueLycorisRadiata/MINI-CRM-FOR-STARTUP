package com.example.crm.repository;

import com.example.crm.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

  boolean existsByEmailIgnoreCase(String email);

  @Query("""
      select distinct u from UserEntity u
      left join u.tags t
      where (:search is null or :search = '' or
             lower(u.name) like lower(concat('%', :search, '%')) or
             lower(u.email) like lower(concat('%', :search, '%')))
        and (:tag is null or :tag = '' or t = :tag)
      order by u.createdAt desc
    """)
  List<UserEntity> search(@Param("search") String search, @Param("tag") String tag);

  @Query("""
      select distinct t from UserEntity u
      join u.tags t
      order by t asc
    """)
  List<String> findDistinctTags();

}
