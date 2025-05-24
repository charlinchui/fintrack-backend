package com.nectarsphere.fintrack.domain.repository;

import com.nectarsphere.fintrack.domain.model.User;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
  User save(User user);
  Optional<User> findById(UUID id);
  Optional<User> findByAuth0Id(String auth0Id);
  Optional<User> findByEmail(String email);
  boolean existsByAuth0Id(String auth0Id);
  void deleteById(UUID id);
}
