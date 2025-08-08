package org.binitshrestha.userservice.service;

import lombok.RequiredArgsConstructor;
import org.binitshrestha.userservice.dto.CreateAdminRequestDto;
import org.binitshrestha.userservice.dto.CreateAdminResponseDto;
import org.binitshrestha.userservice.dto.request.UserUpdateRequestDto;
import org.binitshrestha.userservice.dto.response.UserResponse;
import org.binitshrestha.userservice.dto.response.UserResponseDto;
import org.binitshrestha.userservice.exception.EmailAlreadyExistsException;
import org.binitshrestha.userservice.exception.UserNotFoundException;
import org.binitshrestha.userservice.mapper.UserMapper;
import org.binitshrestha.userservice.model.Role;
import org.binitshrestha.userservice.model.RoleType;
import org.binitshrestha.userservice.model.User;
import org.binitshrestha.userservice.repository.RoleRepository;
import org.binitshrestha.userservice.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::mapToUserResponse)
                .toList();
    }

    @Override
    public UserResponseDto updateUser(Long id, UserUpdateRequestDto userUpdateRequestDto) {
        User updateUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User Id not found," + id));

        if (userRepository.existsByEmailAndIdNot(updateUser.getEmail(), id)) {
            throw new EmailAlreadyExistsException(
                    "A user with given email already exists" + userUpdateRequestDto.email());
        }

        updateUser.setFirstName(userUpdateRequestDto.firstName());
        updateUser.setLastName(userUpdateRequestDto.lastName());
        updateUser.setEmail(userUpdateRequestDto.email());

        userRepository.save(updateUser);
        return UserMapper.toResponse(updateUser);
    }

    @Override
    public Optional<User> findByEmail(String userEmail) {
        return userRepository.findByEmail(userEmail);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public CreateAdminResponseDto createAdministrator(CreateAdminRequestDto userDto) {
        Optional<Role> optionalRole = roleRepository.findByName(RoleType.ADMIN);
        if (optionalRole.isEmpty()) {
            return null;
        }
        User mapped = UserMapper.toModel(userDto);
        User newAdminUser = userRepository.save(
                mapped.toBuilder()
                        .password(passwordEncoder.encode(userDto.password()))
                        .role(optionalRole.get())
                        .build());
        return UserMapper.toAdminResponseDto(newAdminUser);
    }

}
