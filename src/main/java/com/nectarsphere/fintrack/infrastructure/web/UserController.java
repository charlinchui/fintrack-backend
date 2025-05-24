package com.nectarsphere.fintrack.infrastructure.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nectarsphere.fintrack.application.dto.UserResponseDto;
import com.nectarsphere.fintrack.application.usecase.CreateOrUpdateUserUseCase;
import com.nectarsphere.fintrack.domain.model.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@RestController
@RequestMapping("/api/users")
public class UserController {

  private final CreateOrUpdateUserUseCase createOrUpdateUserUseCase;

  public UserController(CreateOrUpdateUserUseCase createOrUpdateUserUseCase) {
    this.createOrUpdateUserUseCase = createOrUpdateUserUseCase;
  }

  @PostMapping(path = "/sync", consumes = "application/json")
  public ResponseEntity<UserResponseDto> syncUser (
    @RequestHeader("Authorization") String authHeader,
    @RequestBody SyncUserRequest request
  ){
    try {
      // TODO: Extract auth0Id from JWT Token
    
      User user = createOrUpdateUserUseCase.execute(
          request.getAuth0Id(), 
          request.getEmail(),
          request.getName()
      );

      UserResponseDto response = UserResponseDto.fromDomain(user);
      return ResponseEntity.ok(response);
    } catch (Exception ex) {
      throw ex;
    }
  
    
  }

  public static class SyncUserRequest {
    @NotBlank(message = "Auth0 ID is required")
    private String auth0Id;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;
    
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    public SyncUserRequest() {}

    public String getAuth0Id() { return auth0Id; }
    public void setAuto0Id(String auth0Id) { this.auth0Id = auth0Id; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
  }

  @GetMapping("/health")
  public ResponseEntity<String> health() {
    return ResponseEntity.ok("UserController iss working!");
  }
}

