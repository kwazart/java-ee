package com.polozov.javaspringbootlessonfour.security;

import com.polozov.javaspringbootlessonfour.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
public class UserAuthService implements UserDetailsService {

	private UserRepository repository;

	@Autowired
	public void setRepository(UserRepository repository) {
		this.repository = repository;
	}

	@Transactional
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		return repository.findUserByLogin(login)
				.map(user -> new User(
						user.getLogin(),
						user.getPassword(),
						user.getRoles()
								.stream()
								.map(role -> new SimpleGrantedAuthority(role.getName()))
								.collect(Collectors.toList())
				)).orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}

}
