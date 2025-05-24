package com.nectarsphere.fintrack.domain.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
    
    public UserNotFoundException(String auth0Id, String message) {
        super(String.format("User with ID '%s' not found: %s", auth0Id, message));
    }
}

