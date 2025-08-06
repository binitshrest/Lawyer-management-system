package org.binitshrestha.userservice.dto;

import java.util.Map;

public record ApiResponse(String status, Map<String, String> data, String message) {
}
