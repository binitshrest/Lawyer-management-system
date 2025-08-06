package org.binitshrestha.userservice.exception;

import org.aspectj.bridge.IMessage;
import org.binitshrestha.userservice.dto.UserRequestDto;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message){
        super(message);
    }
}
