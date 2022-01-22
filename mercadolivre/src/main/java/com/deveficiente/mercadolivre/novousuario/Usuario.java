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
	
	public Usuario(@NotBlank @Email String email, @NotBlank @Size(min = 6) @Valid @NotNull SenhaLimpa senhaLimpa) {
		Assert.isTrue(StringUtils.hasLength(email), "O login não pode estar em branco!");
		Assert.notNull(senhaLimpa, "O objeto do tipo senha limpa nao pode ser nulo!");
		this.email = email;
		this.senha = senhaLimpa.hash();
		this.instanteCriacao = LocalDateTime.now().withNano(0);
	}

	@Override
	public String toString() {
		return "Usuario [Email=" + email + ", senha=" + senha + ", instanteCriacao=" + instanteCriacao + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	public String getEmail() {
		return email;
	}

	public Long getId() {
		return id;
	}

}
