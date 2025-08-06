package org.binitshrestha.userservice.dto;

import lombok.Builder;

@Builder
public record UserResponseDto(String id, String firstName, String lastName, String email, String role) {
}
