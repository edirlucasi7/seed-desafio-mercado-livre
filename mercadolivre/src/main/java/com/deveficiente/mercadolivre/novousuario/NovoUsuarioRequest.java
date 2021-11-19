package com.deveficiente.mercadolivre.novousuario;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.deveficiente.mercadolivre.compartilhado.UniqueValue;

public class NovoUsuarioRequest {

	private static final LocalDateTime INSTANTE_CRIACAO = LocalDateTime.now().withNano(0);
	
	@UniqueValue(domainClass = Usuario.class, fieldName = "login")
	private @NotBlank @Email String login;
	private @NotBlank @Size(min = 6) String senha;
	
	public NovoUsuarioRequest(@NotBlank @Email String login, @NotBlank @Size(min = 6) String senha) {
		super();
		this.login = login;
		this.senha = senha;
	}

	public Usuario toModel() {
		return new Usuario(login,new SenhaLimpa(senha),INSTANTE_CRIACAO);
	}
	
}
