package com.cromws.userapi.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cromws.userapi.models.User;
import com.cromws.userapi.repositories.UserRepository;
import com.cromws.userapi.security.UserSS;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> optional = repository.findByEmail(email);

		if (optional.isEmpty()) {
			throw new UsernameNotFoundException("Usuário não encontrado");
		}

		User user = optional.get();

		return new UserSS(user.getId(), user.getName(), user.getEmail(), user.getPassword());
	}

}
