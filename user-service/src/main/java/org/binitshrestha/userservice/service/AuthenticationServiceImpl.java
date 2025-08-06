package org.binitshrestha.userservice.service;

import lombok.RequiredArgsConstructor;
import org.binitshrestha.userservice.dto.LoginUserDto;
import org.binitshrestha.userservice.dto.LoginUserResDto;
import org.binitshrestha.userservice.dto.RegisterUserReqDto;
import org.binitshrestha.userservice.dto.RegisterUserResDto;
import org.binitshrestha.userservice.model.Role;
import org.binitshrestha.userservice.model.RoleType;
import org.binitshrestha.userservice.model.User;
import org.binitshrestha.userservice.repository.RoleRepository;
import org.binitshrestha.userservice.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    @Override
    public RegisterUserResDto signup(RegisterUserReqDto registerUserDto) {
        Optional<Role> optionalRole = roleRepository.findByName(RoleType.valueOf(registerUserDto.role()));

        if(optionalRole.isEmpty()){
            return null;
        }

        User user = User.builder()
                .firstName(registerUserDto.firstName())
                .lastName(registerUserDto.lastName())
                .email(registerUserDto.email())
                .password(
                        passwordEncoder.encode(registerUserDto.password())
                )
                .role(optionalRole.get())
                .build();

        User newAuthUser = userRepository.save(user);

        return RegisterUserResDto.builder()
                .email(newAuthUser.getEmail())
                .firstName(newAuthUser.getFirstName())
                .lastName(newAuthUser.getLastName())
                .role(String.valueOf(newAuthUser.getRole())).build();
    }

    @Override
    public User authenticate(LoginUserDto loginUserDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUserDto.email(),
                        loginUserDto.password()
                )
        );
        return userRepository.findByEmail(loginUserDto.email()).orElseThrow();
    }
}
