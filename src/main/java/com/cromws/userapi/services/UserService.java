package com.cromws.userapi.services;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cromws.userapi.dto.UserDTO;
import com.cromws.userapi.models.User;
import com.cromws.userapi.repositories.UserRepository;
import com.cromws.userapi.security.UserSS;
import com.cromws.userapi.services.exceptions.AuthorizationException;
import com.cromws.userapi.services.exceptions.EntityNotFoundException;
import com.cromws.userapi.services.exceptions.ExistingEmailException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserService {

	private UserRepository repository;
	private BCryptPasswordEncoder pe;

	public UserDTO save(UserDTO dto) {
		if (findByEmail(dto.getEmail()) != null) {
			throw new ExistingEmailException("Já existe um usuário cadastrado com esse email");
		}
		User user = new User(dto.getName(), dto.getEmail(), pe.encode(dto.getPassword()));
		user = repository.save(user);

		return new UserDTO(user);
	}

	public UserDTO findById() {
		UserSS userSystem = UserSystemService.authenticated();

		if (userSystem == null) {
			throw new AuthorizationException("Acesso negado");
		}

		Optional<User> user = repository.findById(userSystem.getId());
		if (user.isEmpty()) {
			throw new EntityNotFoundException("Não existe usuário cadastrado com esse id");
		}

		return new UserDTO(user.get());
	}

	public User findByEmail(String email) {
		Optional<User> user = repository.findByEmail(email);

		return user.orElse(null);
	}
}
