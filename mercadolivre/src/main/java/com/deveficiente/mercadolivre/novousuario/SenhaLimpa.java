package com.deveficiente.mercadolivre.novousuario;

import javax.validation.constraints.NotBlank;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

public class SenhaLimpa {

	private @NotBlank String senha;

	public SenhaLimpa(@NotBlank String senha) {
		super();
		Assert.hasLength(senha, "O texto não pode estar em branco");
		Assert.isTrue(senha.length() >= 6, "A senha precisa ter no minimo 6 caracteres");
		this.senha = senha;
	}

	public String hash() {
		return new BCryptPasswordEncoder().encode(senha);
	}
	
}
