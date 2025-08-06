package org.binitshrestha.userservice.dto;

import lombok.Builder;
import org.binitshrestha.userservice.model.Role;
import org.binitshrestha.userservice.model.RoleType;

@Builder
public record UserRequestDto(String firstName, String lastName, String email, String password, Role role) {
}
