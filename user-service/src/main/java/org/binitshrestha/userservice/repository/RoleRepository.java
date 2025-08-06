package org.binitshrestha.userservice.repository;

import org.binitshrestha.userservice.model.Role;
import org.binitshrestha.userservice.model.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleType name);
}
