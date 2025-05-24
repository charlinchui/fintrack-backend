package com.nectarsphere.fintrack.application.usecase;

import org.springframework.stereotype.Service;

import com.nectarsphere.fintrack.domain.model.User;
import com.nectarsphere.fintrack.domain.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CreateOrUpdateUserUseCase {
  private final UserRepository userRepository;

  public CreateOrUpdateUserUseCase(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User execute(String auth0Id, String email, String name) {
    return userRepository.findByAuth0Id(auth0Id)
      .map(existingUser -> {
        User updatedUser = existingUser.updateLastLogin();
        return userRepository.save(updatedUser);
      })
      .orElseGet(()-> {
        User newUser = new User(auth0Id, email, name);
       return userRepository.save(newUser);
    });
  }
}
