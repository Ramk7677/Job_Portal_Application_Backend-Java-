package com.job.portal.Repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import com.job.portal.Entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
