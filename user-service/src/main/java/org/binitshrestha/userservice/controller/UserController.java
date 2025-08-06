package org.binitshrestha.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.binitshrestha.userservice.dto.UserRequestDto;
import org.binitshrestha.userservice.dto.UserResponseDto;
import org.binitshrestha.userservice.dto.UserResponse;
import org.binitshrestha.userservice.model.User;
import org.binitshrestha.userservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Optional<User>> authenticatedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> currentUser = userService.findByUsername(username);
        return ResponseEntity.ok(currentUser);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        List<UserResponse> allUser = userService.getAllUsers();
        return ResponseEntity.ok(allUser);
    }
    @PostMapping
    public ResponseEntity<UserResponseDto> registerUser(@RequestBody UserRequestDto userRequest){
        UserResponseDto response = userService.registerUser(userRequest);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable("id") Long id, @RequestBody UserRequestDto userRequest){
        UserResponseDto response = userService.updateUser(id, userRequest);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
