package org.binitshrestha.userservice.dto.response;

import lombok.Builder;

@Builder
public record UserResponseDto(String id, String firstName, String lastName, String email, String role) {
}
