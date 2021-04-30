package com.polozov.javaspringbootlessonfour.services;

import com.polozov.javaspringbootlessonfour.entities.User;
import com.polozov.javaspringbootlessonfour.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

	private UserRepository repository;

	@Autowired
	public void setRepository(UserRepository repository) {
		this.repository = repository;
	}

	@Transactional
	public User createOrUpdate(User user) {
		return  repository.save(user);
	}

	@Transactional
	public Optional<User> findById(Long id) {
		return repository.findById(id);
	}

	@Transactional
	public List<User> findAll () {
		return repository.findAll();
	}

	@Transactional
	public void deleteById(Long id) {
		repository.deleteById(id);
	}


}
