package com.deveficiente.mercadolivre.novaopiniaoproduto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.deveficiente.mercadolivre.novoproduto.Produto;
import com.deveficiente.mercadolivre.novousuario.Usuario;

@Entity
public class NovaOpiniaoProduto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @Min(1) @Max(5) int nota;
	private @NotBlank String titulo;
	private @NotBlank @Size(max = 500) String descricao;
	@ManyToOne
	private @NotNull Usuario usuarioLogado;
	@ManyToOne
	private @NotNull Produto produto;

	public NovaOpiniaoProduto(@Min(1) @Max(5) int nota, @NotBlank String titulo,
			@NotBlank @Size(max = 500) String descricao, @NotNull Usuario ususarioLogado, @NotNull Produto produto) {
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.usuarioLogado = ususarioLogado;
		this.produto = produto;
	}

	@Override
	public String toString() {
		return "NovaOpiniaoProduto [nota=" + nota + ", titulo=" + titulo + ", descricao=" + descricao
				+ ", usuarioLogado=" + usuarioLogado + ", produto=" + produto + "]";
	}

}
