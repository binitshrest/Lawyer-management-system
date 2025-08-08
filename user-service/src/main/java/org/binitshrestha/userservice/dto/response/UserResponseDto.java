package org.binitshrestha.userservice.dto.response;

import lombok.Builder;
import lombok.Data;
import org.binitshrestha.userservice.dto.RoleDto;


@Builder
public record UserResponseDto(String id, String firstName, String lastName, String email, RoleDto role) {
}
