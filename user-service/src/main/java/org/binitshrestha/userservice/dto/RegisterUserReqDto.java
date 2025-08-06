package org.binitshrestha.userservice.dto;

import lombok.Builder;

@Builder
public record RegisterUserReqDto(String email, String password, String firstName, String lastName, String role) {
    public RegisterUserReqDto {
        role = role.toUpperCase();
    }
}
