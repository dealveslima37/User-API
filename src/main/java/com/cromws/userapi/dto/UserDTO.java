package com.cromws.userapi.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.cromws.userapi.models.User;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	@NotEmpty(message = "Por favor, preencha o campo nome")
	@Size(min = 3, max = 30, message = "O campo nome deve conter entre 3 e 30 caracteres")
	private String name;

	@NotEmpty(message = "Por favor, preencha o campo email")
	@Email(message = "Por favor, Digite um email válido")
	private String email;

	@NotEmpty(message = "Por favor, preencha o campo senha")
	@Size(min = 8, max = 16, message = "O campo senha deve conter entre 8 e 16 caracteres")
	private String password;

	public UserDTO(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
	}

}
