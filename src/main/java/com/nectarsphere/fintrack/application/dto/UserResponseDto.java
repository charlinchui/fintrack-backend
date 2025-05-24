package com.nectarsphere.fintrack.application.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.nectarsphere.fintrack.domain.model.User;


public class UserResponseDto {
  private final UUID id;
  private final String email, name;
  private final LocalDateTime createdAt, lastLoginAt;

  public UserResponseDto(
      UUID id,
      String email,
      String name,
      LocalDateTime createdAt,
      LocalDateTime lastLoginAt
  ) {
    this.id = id;
    this.email = email;
    this.name = name;
    this.createdAt = createdAt;
    this.lastLoginAt = lastLoginAt;
  }

  public static UserResponseDto fromDomain(User user) {
    return new UserResponseDto(
        user.getId(),
        user.getEmail(),
        user.getName(),
        user.getCreatedAt(),
        user.getLastLoginAt()
    );
  }

  public UUID getId() { return id; }
  public String getEmail() { return email; }
  public String name() { return name; }
  public LocalDateTime getCreatedAt() { return createdAt; }
  public LocalDateTime getLastLoginAt() { return lastLoginAt; }
}
