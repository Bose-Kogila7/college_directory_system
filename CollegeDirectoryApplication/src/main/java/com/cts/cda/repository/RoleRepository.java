package com.cts.cda.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.cda.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
