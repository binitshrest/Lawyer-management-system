package org.binitshrestha.userservice.service;

import org.binitshrestha.userservice.dto.request.LoginUserDto;
import org.binitshrestha.userservice.dto.request.RegisterUserReqDto;
import org.binitshrestha.userservice.dto.response.RegisterUserResDto;
import org.binitshrestha.userservice.model.User;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthenticationService {
    RegisterUserResDto signup(@RequestBody RegisterUserReqDto registerUserDto);
    User authenticate(@RequestBody LoginUserDto loginUserDto );
}
