package org.binitshrestha.userservice.dto.request;

import lombok.Builder;
import org.binitshrestha.userservice.model.Role;

@Builder
public record UserRequestDto(String firstName, String lastName, String email, String password, Role role) {
}
