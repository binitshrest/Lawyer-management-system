//package org.binitshrestha.userservice.bootstrap;
//
//import lombok.RequiredArgsConstructor;
//import org.binitshrestha.userservice.dto.CreateAdminRequestDto;
//import org.binitshrestha.userservice.dto.RoleDto;
//import org.binitshrestha.userservice.mapper.UserMapper;
//import org.binitshrestha.userservice.model.Role;
//import org.binitshrestha.userservice.model.RoleType;
//import org.binitshrestha.userservice.model.User;
//import org.binitshrestha.userservice.repository.RoleRepository;
//import org.binitshrestha.userservice.repository.UserRepository;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.util.Optional;
//
//@Component
//@RequiredArgsConstructor
//@Order(2)
//public class AdminSeeder implements ApplicationListener<ContextRefreshedEvent> {
//    private final RoleRepository roleRepository;
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent event) {
//        this.createSuperAdministrator();
//    }
//    private void createSuperAdministrator(){
//        CreateAdminRequestDto adminRequestDto = CreateAdminRequestDto.builder()
//                .firstName("Binit")
//                .lastName("Shrestha")
//                .email("super.admin@gmail.com")
//                .password("password@123")
//                .role(
//                        RoleDto.builder()
//                        .name("ADMIN")
//                        .build()
//                )
//                .build();
//        Optional<Role> optionalRole = roleRepository.findByName(RoleType.ADMIN);
//        Optional<User> optionalUser = userRepository.findByEmail(adminRequestDto.email());
//        if(optionalRole.isEmpty() && optionalUser.isEmpty()){
//            return;
//        }
//
//        String hashedPassword = passwordEncoder.encode(adminRequestDto.password());
//        User user = UserMapper.toModel(adminRequestDto)
//                .toBuilder()
//                .role(optionalRole.orElse(null))
//                .password(hashedPassword)
//                .build();
//
//        userRepository.save(user);
//    }
//}
