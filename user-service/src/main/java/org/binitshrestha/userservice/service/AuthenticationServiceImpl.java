package org.binitshrestha.userservice.service;

import lombok.RequiredArgsConstructor;
import org.binitshrestha.userservice.dto.*;
import org.binitshrestha.userservice.dto.request.LoginUserDto;
import org.binitshrestha.userservice.dto.request.RegisterUserReqDto;
import org.binitshrestha.userservice.dto.response.RegisterUserResDto;
import org.binitshrestha.userservice.exception.EmailAlreadyExistsException;
import org.binitshrestha.userservice.mapper.UserMapper;
import org.binitshrestha.userservice.model.Role;
import org.binitshrestha.userservice.model.RoleType;
import org.binitshrestha.userservice.model.User;
import org.binitshrestha.userservice.repository.RoleRepository;
import org.binitshrestha.userservice.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    @Transactional
    @Override
    public RegisterUserResDto signup(RegisterUserReqDto registerUserDto) {
        Optional<Role> optionalRole = roleRepository.findByName(RoleType.valueOf(registerUserDto.role()));

        if (optionalRole.isEmpty()) {
            return null;
        }
        if (userRepository.existsByEmail(registerUserDto.email())) {
            throw new EmailAlreadyExistsException("A user with given email already exists" + registerUserDto.email());
        }
        User user = User.builder()
                .firstName(registerUserDto.firstName())
                .lastName(registerUserDto.lastName())
                .email(registerUserDto.email())
                .password(
                        passwordEncoder.encode(registerUserDto.password()))
                .role(optionalRole.get())
                .build();

        User newAuthUser = userRepository.save(user);
        return UserMapper.toResponseDto(newAuthUser);
    }

    @Override
    public User authenticate(LoginUserDto loginUserDto) {
        try {
            System.out.println("Attempting to authenticate user: " + loginUserDto.email());
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginUserDto.email(),
                            loginUserDto.password()));
            System.out.println("Authentication successful for: " + loginUserDto.email());
            return userRepository.findByEmail(loginUserDto.email()).orElseThrow();
        } catch (Exception e) {
            System.out.println("Authentication failed for: " + loginUserDto.email() + " - " + e.getMessage());
            throw e;
        }
    }
}
