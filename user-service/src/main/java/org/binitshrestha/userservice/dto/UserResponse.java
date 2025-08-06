package org.binitshrestha.userservice.dto;

import lombok.Builder;
import org.binitshrestha.userservice.model.RoleType;

@Builder
public record UserResponse(Long id, String firstName, String lastName, RoleType role) {
}
