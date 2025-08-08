package org.binitshrestha.userservice.dto.response;

import lombok.Builder;
import org.binitshrestha.userservice.dto.RoleDto;

@Builder
public record RegisterUserResDto(String email, String firstName, String lastName, RoleDto role) {
}
