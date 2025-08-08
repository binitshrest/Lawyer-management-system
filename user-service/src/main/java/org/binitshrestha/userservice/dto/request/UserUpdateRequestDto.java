package org.binitshrestha.userservice.dto.request;

import lombok.Builder;
import org.binitshrestha.userservice.dto.RoleDto;

@Builder
public record UserUpdateRequestDto(String firstName, String lastName, String email, String password, RoleDto role) {
}