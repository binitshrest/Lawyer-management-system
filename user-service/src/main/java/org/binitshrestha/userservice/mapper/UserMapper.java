package org.binitshrestha.userservice.mapper;

import org.binitshrestha.userservice.dto.RoleDto;
import org.binitshrestha.userservice.dto.request.UserRequestDto;
import org.binitshrestha.userservice.dto.response.RegisterUserResDto;
import org.binitshrestha.userservice.dto.response.UserResponse;
import org.binitshrestha.userservice.dto.response.UserResponseDto;
import org.binitshrestha.userservice.model.User;

public class UserMapper {
    private UserMapper(){}
    public static UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole().getName())
                .build();
    }

    public static User toModel(UserRequestDto userCreateRequest){
        return User.builder()
                .firstName(userCreateRequest.firstName())
                .lastName(userCreateRequest.lastName())
                .email(userCreateRequest.email())
                .password(userCreateRequest.password())
                .role(userCreateRequest.role())
                .build();
    }

    public static UserResponseDto toResponse(User newUser){
        return UserResponseDto.builder()
                .id(String.valueOf(newUser.getId()))
                .firstName(newUser.getFirstName())
                .lastName(newUser.getLastName())
                .email(newUser.getEmail())
                .role(String.valueOf(newUser.getRole()))
                .build();
    }
    public static RegisterUserResDto toResponseDto(User newAuthUser){
        return RegisterUserResDto.builder()
                .email(newAuthUser.getEmail())
                .firstName(newAuthUser.getFirstName())
                .lastName(newAuthUser.getLastName())
                .role(RoleDto.builder()
                        .name(newAuthUser.getRole().getName().toString())
                        .description(newAuthUser.getRole().getDescription())
                        .build()
                ).build();
    }


}
