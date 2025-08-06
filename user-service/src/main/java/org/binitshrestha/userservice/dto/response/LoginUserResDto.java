package org.binitshrestha.userservice.dto.response;

import lombok.Builder;

@Builder
public record LoginUserResDto(String token, Long expiresIn) {
}
