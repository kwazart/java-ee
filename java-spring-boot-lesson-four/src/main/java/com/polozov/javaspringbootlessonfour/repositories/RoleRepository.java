package com.polozov.javaspringbootlessonfour.repositories;

import com.polozov.javaspringbootlessonfour.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
