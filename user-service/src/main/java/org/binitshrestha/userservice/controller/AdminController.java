package org.binitshrestha.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.binitshrestha.userservice.dto.CreateAdminRequestDto;
import org.binitshrestha.userservice.dto.CreateAdminResponseDto;
import org.binitshrestha.userservice.dto.response.UserResponse;
import org.binitshrestha.userservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;

    @GetMapping // api for admin2
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> allUser = userService.getAllUsers();
        return ResponseEntity.ok(allUser);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CreateAdminResponseDto> createAdministrator(
            @RequestBody CreateAdminRequestDto adminRequestDto) {
        CreateAdminResponseDto createAdmin = userService.createAdministrator(adminRequestDto);
        return ResponseEntity.ok(createAdmin);
    }

}
