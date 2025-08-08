package org.binitshrestha.userservice.dto;

import lombok.Builder;

@Builder
public record CreateAdminResponseDto(String firstName, String lastName, String email, RoleDto roleDto) {
}
