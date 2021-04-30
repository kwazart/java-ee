package com.polozov.javaspringbootlessonfour.rest;

import com.polozov.javaspringbootlessonfour.entities.User;
import com.polozov.javaspringbootlessonfour.services.UserService;
import com.polozov.javaspringbootlessonfour.services.exceptions.NotFoundException;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User API", description = "API to manipulate user resources")
@RestController
@RequestMapping("/api/v1/user")
public class UserRestController {

	private UserService service;

	@Autowired
	public void setService(UserService service) {
		this.service = service;
	}

	@GetMapping(path = "/{id}/id", produces = "application/json")
	public User findById(@PathVariable("id") Long id) {
		return service.findById(id).orElseThrow(NotFoundException::new);
	}

	@GetMapping(path = "/list", produces = "application/json")
	public List<User> findAll() {
		return service.findAll();
	}

	@PostMapping(consumes = "application/json", produces = "application/json")
	public User createUser(@RequestBody User user) {
		service.createOrUpdate(user);
		return user;
	}

	@PutMapping(consumes = "application/json", produces = "application/json")
	public User updateUser(@RequestBody User user) {
		service.createOrUpdate(user);
		return user;
	}

	@DeleteMapping("/{id}/id")
	public void deleteById(@PathVariable("id") Long id) {
		service.deleteById(id);
	}

	@ExceptionHandler
	public ResponseEntity<String> notFoundExceptionHandler(NotFoundException e) {
		return new ResponseEntity<>("Entity not found", HttpStatus.NOT_FOUND);
	}
}
