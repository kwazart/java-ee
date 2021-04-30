package com.polozov.javaspringbootlessonfour.repositories;

import com.polozov.javaspringbootlessonfour.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
