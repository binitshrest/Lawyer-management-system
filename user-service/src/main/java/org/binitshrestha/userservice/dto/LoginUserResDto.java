package org.binitshrestha.userservice.dto;

import lombok.Builder;

@Builder
public record LoginUserResDto(String token, Long expiresIn) {
}
