package org.binitshrestha.userservice.service;

import org.binitshrestha.userservice.dto.UserRequestDto;
import org.binitshrestha.userservice.dto.UserResponseDto;
import org.binitshrestha.userservice.dto.UserResponse;
import org.binitshrestha.userservice.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserResponse> getAllUsers();
    UserResponseDto registerUser(UserRequestDto userCreateRequest);
    UserResponseDto updateUser(Long id, UserRequestDto userRequestDto);

    Optional<User> findByUsername(String username);
    void deleteUser(Long id);
}
