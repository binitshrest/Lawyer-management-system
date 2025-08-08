package org.binitshrestha.userservice.service;

import org.binitshrestha.userservice.dto.response.UserResponse;
import org.binitshrestha.userservice.model.User;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    List<UserResponse> getAllUsers();
    Optional<User> findByEmail(String userEmail);
}
