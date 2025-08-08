package org.binitshrestha.userservice.dto.request;

import lombok.Builder;

@Builder
public record LoginUserDto(String email, String password) {
}
