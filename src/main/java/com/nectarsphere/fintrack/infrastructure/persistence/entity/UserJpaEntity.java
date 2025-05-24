package com.nectarsphere.fintrack.infrastructure.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserJpaEntity {
  
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "auth0_id", nullable = false, unique = true)
  private String auth0Id;

  @Column(name = "email", nullable = false, unique = true)
  private String email;
  
  @Column(name = "name", nullable = false)
  private String name;
  
  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;

  @Column(name = "last_login", nullable = false)
  private LocalDateTime lastLoginAt;

  public UserJpaEntity(){}

  public UserJpaEntity(
      UUID id, 
      String auth0Id,
      String email,
      String name,
      LocalDateTime createdAt,
      LocalDateTime lastLoginAt
  ) {
        this.id = id;
        this.auth0Id = auth0Id;
        this.email = email;
        this.name = name;
        this.createdAt = createdAt;
        this.lastLoginAt = lastLoginAt;
  }
  
  public UUID getId() { return id; }
  public void setId(UUID id) { this.id = id; }

  public String getAuth0Id() { return auth0Id; }
  public void setAuth0Id(String auth0Id) { this.auth0Id = auth0Id; }

  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public LocalDateTime getCreatedAt() { return createdAt; }
  public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

  public LocalDateTime getLastLoginAt() { return lastLoginAt; }
  public void setLastLoginAt(LocalDateTime lastLoginAt) { this.lastLoginAt = lastLoginAt; }
}
