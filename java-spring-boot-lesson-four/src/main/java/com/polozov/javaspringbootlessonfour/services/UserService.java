package com.polozov.javaspringbootlessonfour.services;

import com.polozov.javaspringbootlessonfour.entities.User;
import com.polozov.javaspringbootlessonfour.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

	private UserRepository repository;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Autowired
	public void setRepository(UserRepository repository) {
		this.repository = repository;
	}

	@Transactional
	public User createOrUpdate(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return  repository.save(user);
	}

	public Optional<User> findById(Long id) {
		return repository.findById(id);
	}

	public List<User> findAll () {
		return repository.findAll();
	}

	@Transactional
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	public boolean existById(Long id) {
		return repository.existsById(id);
	}
}
