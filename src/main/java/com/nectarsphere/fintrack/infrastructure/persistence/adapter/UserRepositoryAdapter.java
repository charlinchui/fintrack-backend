package com.nectarsphere.fintrack.infrastructure.persistence.adapter;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.nectarsphere.fintrack.domain.model.User;
import com.nectarsphere.fintrack.domain.repository.UserJpaRepository;
import com.nectarsphere.fintrack.domain.repository.UserRepository;
import com.nectarsphere.fintrack.infrastructure.persistence.entity.UserJpaEntity;

@Component
public class UserRepositoryAdapter implements UserRepository {
  private final UserJpaRepository jpaRepository;

  public UserRepositoryAdapter(UserJpaRepository jpaRepository) {
    this.jpaRepository = jpaRepository;
  }

  @Override
  public User save(User user) {
    UserJpaEntity entity = toEntity(user);
    UserJpaEntity savedEntity = jpaRepository.save(entity);
    return toDomain(savedEntity);
  }

  @Override
  public Optional<User> findById(UUID id) {
    return jpaRepository.findById(id)
      .map(this::toDomain);
  }

  @Override
  public Optional<User> findByAuth0Id(String auth0Id) {
    return jpaRepository.findByAuth0Id(auth0Id)
      .map(this::toDomain);
  }

  @Override
  public Optional<User> findByEmail(String email) {
    return jpaRepository.findByEmail(email)
      .map(this::toDomain);
  }

  @Override
  public boolean existsByAuth0Id(String auth0Id) {
    return jpaRepository.existsByAuth0Id(auth0Id);
  }

  @Override
  public void deleteById(UUID id) {
    jpaRepository.deleteById(id);
  }



  private UserJpaEntity toEntity(User user) {
    return new UserJpaEntity(
        user.getId(),
        user.getAuth0Id(),
        user.getEmail(),
        user.getName(),
        user.getCreatedAt(),
        user.getLastLoginAt()
    );
  }

  private User toDomain(UserJpaEntity entity) {
    return new User(
        entity.getId(),
        entity.getAuth0Id(),
        entity.getEmail(),
        entity.getName(),
        entity.getCreatedAt(),
        entity.getLastLoginAt()
    );
  }
}
