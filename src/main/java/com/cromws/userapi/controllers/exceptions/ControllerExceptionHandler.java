package com.cromws.userapi.controllers.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cromws.userapi.services.exceptions.EntityNotFoundException;
import com.cromws.userapi.services.exceptions.ExistingEmailException;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(EntityNotFoundException e, HttpServletRequest request) {

		var error = new StandardError(Instant.now(), HttpStatus.NOT_FOUND.value(), "Id inexistente", e.getMessage(),
				request.getRequestURI());

		return new ResponseEntity<StandardError>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ExistingEmailException.class)
	public ResponseEntity<StandardError> emailValidation(ExistingEmailException e, HttpServletRequest request) {

		var error = new StandardError(Instant.now(), HttpStatus.BAD_REQUEST.value(), "Usuário não cadastrado",
				e.getMessage(), request.getRequestURI());

		return new ResponseEntity<StandardError>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {

		var error = new StandardError(Instant.now(), HttpStatus.I_AM_A_TEAPOT.value(), "Erro de validação",
				"Usuário não cadastrado", request.getRequestURI());
		for (FieldError x : e.getBindingResult().getFieldErrors()) {
			error.addError(x.getDefaultMessage());
		}

		return new ResponseEntity<StandardError>(error, HttpStatus.I_AM_A_TEAPOT);
	}

}
