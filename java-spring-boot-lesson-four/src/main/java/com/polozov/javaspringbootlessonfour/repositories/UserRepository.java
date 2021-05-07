package com.polozov.javaspringbootlessonfour.repositories;

import com.polozov.javaspringbootlessonfour.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findUserByLogin(String login);
}
