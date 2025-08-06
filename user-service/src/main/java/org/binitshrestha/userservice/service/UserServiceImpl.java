package org.binitshrestha.userservice.service;

import lombok.RequiredArgsConstructor;
import org.binitshrestha.userservice.dto.UserRequestDto;
import org.binitshrestha.userservice.dto.UserResponseDto;
import org.binitshrestha.userservice.dto.UserResponse;
import org.binitshrestha.userservice.exception.EmailAlreadyExistsException;
import org.binitshrestha.userservice.exception.UserNotFoundException;
import org.binitshrestha.userservice.mapper.UserMapper;
import org.binitshrestha.userservice.model.User;
import org.binitshrestha.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    public List<UserResponse> getAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(UserMapper::mapToUserResponse)
                .toList();
    }

    @Transactional
    @Override
    public UserResponseDto registerUser(UserRequestDto userCreateRequest){
        if(userRepository.existsByEmail(userCreateRequest.email())) {
            throw new RuntimeException("User has already been created");
        }
        User newUser = userRepository.save(UserMapper.toModel(userCreateRequest));

        return UserMapper.toResponse(newUser);
    }

    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto){
        User updateUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User Id not found," + id));

        if(userRepository.existsByEmailAndIdNot(updateUser.getEmail(), id)){
            throw new EmailAlreadyExistsException("A user with given email already exists" + userRequestDto.email());
        }

        updateUser.setFirstName(userRequestDto.firstName());
        updateUser.setLastName(userRequestDto.lastName());
        updateUser.setEmail(userRequestDto.email());
        updateUser.setRole(userRequestDto.role());

        userRepository.save(updateUser);
        return UserMapper.toResponse(updateUser);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByEmail(username);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
