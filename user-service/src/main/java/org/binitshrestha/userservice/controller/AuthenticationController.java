package org.binitshrestha.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.binitshrestha.userservice.dto.LoginUserDto;
import org.binitshrestha.userservice.dto.LoginUserResDto;
import org.binitshrestha.userservice.dto.RegisterUserReqDto;
import org.binitshrestha.userservice.dto.RegisterUserResDto;
import org.binitshrestha.userservice.model.User;
import org.binitshrestha.userservice.service.AuthenticationService;
import org.binitshrestha.userservice.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/auth")
@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<RegisterUserResDto> register(@RequestBody RegisterUserReqDto registerUserReqDto){
        RegisterUserResDto registeredUser = authenticationService.signup(registerUserReqDto);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginUserResDto> authenticate(@RequestBody LoginUserDto loginUserDto){
        User authenticatedUser = authenticationService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginUserResDto loginResponse  = LoginUserResDto
                .builder()
                .token(jwtToken)
                .expiresIn(jwtService.getExpirationTime())
                .build();

        return ResponseEntity.ok(loginResponse);
    }

}
