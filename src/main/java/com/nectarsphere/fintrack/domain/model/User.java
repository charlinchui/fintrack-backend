package com.nectarsphere.fintrack.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class User {
  private final UUID id;
  private final String auth0Id, email, name;
  private final LocalDateTime createdAt, lastLoginAt;

  // First Login
  public User(String auth0Id, String email, String name) {
    if (auth0Id == null || auth0Id.trim().isEmpty()) {
      throw new IllegalArgumentException("Auth0 ID cannot be null or empty");
    }
    if (email == null || email.trim().isEmpty()) {
      throw new IllegalArgumentException("Auth0 ID cannot be null or empty");
    }
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("Auth0 ID cannot be null or empty");
    }

    this.id = UUID.randomUUID();
    this.auth0Id = auth0Id.trim();
    this.email = email.trim().toLowerCase();
    this.name = name.trim();
    this.createdAt = LocalDateTime.now();
    this.lastLoginAt = LocalDateTime.now();
  }

  public User(UUID id, String auth0Id, String email, String name, LocalDateTime createdAt, LocalDateTime lastLoginAt) {
    this.id = id;
    this.auth0Id = auth0Id;
    this.email = email; 
    this.name = name;
    this.createdAt = createdAt;
    this.lastLoginAt = lastLoginAt;
  }

  public User updateLastLogin() {
    return new User(
        this.id, 
        this.auth0Id,
        this.email,
        this.name,
        this.createdAt,
        LocalDateTime.now()
    );
  }

  public UUID getId() { return id; }
  public String getAuth0Id() { return auth0Id; }
  public String getEmail() { return email; }
  public String getName() { return name; }
  public LocalDateTime getCreatedAt() { return createdAt; }
  public LocalDateTime getLastLoginAt() { return lastLoginAt; }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    User user = (User) obj;
    return id.equals(user.id);
  } 

  @Override
  public int hashCode() {
    return id.hashCode();
  }

}

