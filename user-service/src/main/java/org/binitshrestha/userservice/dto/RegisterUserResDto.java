package org.binitshrestha.userservice.dto;

import lombok.Builder;

@Builder
public record RegisterUserResDto(String email, String firstName, String lastName, String role) {
}
