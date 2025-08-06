package org.binitshrestha.userservice.bootstrap;

import lombok.RequiredArgsConstructor;
import org.binitshrestha.userservice.model.Role;
import org.binitshrestha.userservice.model.RoleType;
import org.binitshrestha.userservice.repository.RoleRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RoleSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private final RoleRepository roleRepository;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.loadRoles();
    }
    private void loadRoles(){
        RoleType[] roleNames = new RoleType[]{ RoleType.USER, RoleType.ADMIN, RoleType.LAWYER};
        Map<RoleType, String> roleDescriptionMap = Map.of(
                RoleType.USER, "Default user role",
                RoleType.ADMIN, "Administrator role",
                RoleType.LAWYER, "Super Administrator role"
        );

        Arrays.stream(roleNames).forEach(roleName ->{
            Optional<Role> optionalRole = roleRepository.findByName(roleName);

            optionalRole.ifPresentOrElse(System.out::println, () -> {
                Role roleToCreate = new Role();
                roleToCreate.setName(RoleType.valueOf(roleName.name()));
                roleToCreate.setDescription(roleDescriptionMap.get(roleName));
                roleRepository.save(roleToCreate);
            });

        });
    }
}
