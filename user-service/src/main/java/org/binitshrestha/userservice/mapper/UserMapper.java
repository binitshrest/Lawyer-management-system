package org.binitshrestha.userservice.mapper;

import org.binitshrestha.userservice.dto.CreateAdminRequestDto;
import org.binitshrestha.userservice.dto.CreateAdminResponseDto;
import org.binitshrestha.userservice.dto.RoleDto;
import org.binitshrestha.userservice.dto.response.RegisterUserResDto;
import org.binitshrestha.userservice.dto.response.UserResponse;
import org.binitshrestha.userservice.dto.response.UserResponseDto;
import org.binitshrestha.userservice.model.Role;
import org.binitshrestha.userservice.model.RoleType;
import org.binitshrestha.userservice.model.User;

public class UserMapper {
    private UserMapper() {
    }

    public static UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole().getName())
                .build();
    }

    public static User toModel(CreateAdminRequestDto adminRequestDto) {
        return User.builder()
                .firstName(adminRequestDto.firstName())
                .lastName(adminRequestDto.lastName())
                .email(adminRequestDto.email())
                .build();
    }

    public static UserResponseDto toResponse(User newUser) {
        return UserResponseDto.builder()
                .id(String.valueOf(newUser.getId()))
                .firstName(newUser.getFirstName())
                .lastName(newUser.getLastName())
                .email(newUser.getEmail())
                .role(RoleDto.builder()
                        .name(newUser.getRole().getName().toString())
                        .description(newUser.getRole().getDescription())
                        .build())
                .build();
    }

    public static RegisterUserResDto toResponseDto(User newAuthUser) {
        return RegisterUserResDto.builder()
                .email(newAuthUser.getEmail())
                .firstName(newAuthUser.getFirstName())
                .lastName(newAuthUser.getLastName())
                .role(RoleDto.builder()
                        .name(newAuthUser.getRole().getName().toString())
                        .description(newAuthUser.getRole().getDescription())
                        .build())
                .build();
    }

    public static CreateAdminResponseDto toAdminResponseDto(User newAdminUser) {
        return CreateAdminResponseDto.builder()
                .firstName(newAdminUser.getFirstName())
                .lastName(newAdminUser.getLastName())
                .email(newAdminUser.getEmail())
                .roleDto(RoleDto.builder()
                        .name(newAdminUser.getRole().getName().toString())
                        .description(newAdminUser.getRole().getDescription())
                        .build())
                .build();
    }

    // public static Role mapToRole(RoleDto roleDto) {
    // return Role.builder()
    // .name(RoleType.valueOf(roleDto.name()))
    // .build();
    // }
}
