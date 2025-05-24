package com.nectarsphere.fintrack.domain.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nectarsphere.fintrack.infrastructure.persistence.entity.UserJpaEntity;

@Repository
public interface UserJpaRepository extends JpaRepository<UserJpaEntity, UUID> {
  Optional<UserJpaEntity> findByAuth0Id(String auth0Id);
  Optional<UserJpaEntity> findByEmail(String email);
  boolean existsByAuth0Id(String auth0Id);
}
