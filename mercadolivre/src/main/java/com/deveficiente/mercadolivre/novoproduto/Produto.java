package com.deveficiente.mercadolivre.novoproduto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.deveficiente.mercadolivre.novacategoria.Categoria;
import com.deveficiente.mercadolivre.novousuario.Usuario;

@Entity
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotBlank String nome;
	@Positive
	private @NotNull BigDecimal valor;
	@Positive
	private @NotNull int quantidade;
	@ElementCollection
	private @NotNull @Size(min = 3) List<String> caracteristica;
	private @NotBlank @Size(max = 1000) String descricao;
	@ManyToOne
	private @NotNull Categoria categoria;
	private @NotNull LocalDateTime instanteCriacao;
	@OneToOne
	private @NotNull Usuario usuarioLogado;
	
	@Deprecated
	public Produto() { }
	
	public Produto(@NotBlank String nome, @Positive @NotNull BigDecimal valor, @Positive @NotNull int quantidade,
			@NotNull @Size(min = 3) List<String> caracteristica, @NotBlank @Size(max = 1000) String descricao,
			Categoria categoria) {
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.caracteristica = caracteristica;
		this.descricao = descricao;
		this.categoria = categoria;
		this.instanteCriacao = LocalDateTime.now().withNano(0);
	}

	@Override
	public String toString() {
		return "Produto [nome=" + nome + ", valor=" + valor + ", quantidade=" + quantidade + ", caracteristicas="
				+ caracteristica + ", descricao=" + descricao + ", categoria=" + categoria + ", instanteCriacao="
				+ instanteCriacao + ", usuarioLogado=" + usuarioLogado + "]";
	}

	public void setUsuarioLogado(Usuario usuario) {
		this.usuarioLogado = usuario;
		
	}
	
}
