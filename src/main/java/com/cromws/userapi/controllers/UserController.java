package com.cromws.userapi.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cromws.userapi.dto.UserDTO;
import com.cromws.userapi.services.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

	private UserService service;

	@PostMapping
	public ResponseEntity<UserDTO> save(@Valid @RequestBody UserDTO dto) {
		UserDTO user = service.save(dto);

		return new ResponseEntity<UserDTO>(user, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<UserDTO> findById() {
		UserDTO user = service.findById();

		return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
	}

}
