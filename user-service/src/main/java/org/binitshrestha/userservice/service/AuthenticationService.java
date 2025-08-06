package org.binitshrestha.userservice.service;

import org.binitshrestha.userservice.dto.LoginUserDto;
import org.binitshrestha.userservice.dto.LoginUserResDto;
import org.binitshrestha.userservice.dto.RegisterUserReqDto;
import org.binitshrestha.userservice.dto.RegisterUserResDto;
import org.binitshrestha.userservice.model.User;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthenticationService {
    RegisterUserResDto signup(@RequestBody RegisterUserReqDto registerUserDto);
    User authenticate(@RequestBody LoginUserDto loginUserDto );
}
