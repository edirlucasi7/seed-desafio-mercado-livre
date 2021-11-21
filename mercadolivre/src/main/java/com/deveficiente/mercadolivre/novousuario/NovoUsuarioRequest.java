package com.deveficiente.mercadolivre.novousuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NovoUsuarioRequest {
	
	private @NotBlank @Email String email;
	private @NotBlank @Size(min = 6) String senha;
	
	public NovoUsuarioRequest(@NotBlank @Email String email, @NotBlank @Size(min = 6) String senha) {
		super();
		this.email = email;
		this.senha = senha;
	}

	public Usuario toModel() {
		return new Usuario(email,new SenhaLimpa(senha));
	}

	public String getEmail() {
		return email;
	}
	
}
