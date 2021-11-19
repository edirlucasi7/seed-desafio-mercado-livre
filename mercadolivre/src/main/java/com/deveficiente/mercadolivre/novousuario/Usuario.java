package com.deveficiente.mercadolivre.novousuario;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotBlank @Email String email;
	private @NotBlank @Size(min = 6) String senha;
	private @NotNull LocalDateTime instanteCriacao;

	@Deprecated
	public Usuario() { }
	
	public Usuario(@NotBlank @Email String email, @NotBlank @Size(min = 6) @Valid @NotNull SenhaLimpa senhaLimpa, @NotNull LocalDateTime instanteCriacao) {
		Assert.isTrue(StringUtils.hasLength(email), "O login não pode estar em branco!");
		Assert.isTrue(instanteCriacao!=null, "O instante de criação não pode ser nulo!");
		Assert.isTrue(instanteCriacao.compareTo(LocalDateTime.now()) <= 0, "O instante de criação não pode ser no futuro!");
		Assert.notNull(senhaLimpa, "O objeto do tipo senha limpa nao pode ser nulo!");
		this.email = email;
		this.senha = senhaLimpa.hash();
		this.instanteCriacao = instanteCriacao;
	}

	@Override
	public String toString() {
		return "Usuario [Email=" + email + ", senha=" + senha + ", instanteCriacao=" + instanteCriacao + "]";
	}

}
