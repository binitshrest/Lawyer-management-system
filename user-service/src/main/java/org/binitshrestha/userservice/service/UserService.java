package org.binitshrestha.userservice.service;

import org.binitshrestha.userservice.dto.CreateAdminRequestDto;
import org.binitshrestha.userservice.dto.CreateAdminResponseDto;
import org.binitshrestha.userservice.dto.request.UserUpdateRequestDto;
import org.binitshrestha.userservice.dto.response.UserResponse;
import org.binitshrestha.userservice.dto.response.UserResponseDto;
import org.binitshrestha.userservice.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserResponse> getAllUsers();

    UserResponseDto updateUser(Long id, UserUpdateRequestDto userUpdateRequestDto);

    Optional<User> findByEmail(String userEmail);

    void deleteUser(Long id);

    public CreateAdminResponseDto createAdministrator(CreateAdminRequestDto userDto);
}
