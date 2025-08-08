package org.binitshrestha.userservice.dto;

import lombok.Builder;

@Builder
public record CreateAdminRequestDto(String firstName, String lastName, String email, String password) {
}
