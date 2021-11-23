package com.deveficiente.mercadolivre.novoproduto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.util.Assert;

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
	@OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
	private @NotNull @Size(min = 3) Set<CaracteristicaProduto> caracteristicas = new HashSet<>();
	private @NotBlank @Size(max = 1000) String descricao;
	@ManyToOne
	private @NotNull Categoria categoria;
	private @NotNull LocalDateTime instanteCriacao;
	@OneToOne
	private @NotNull Usuario usuarioLogado;
	
	@Deprecated
	public Produto() { }
	
	public Produto(@NotBlank String nome, @Positive @NotNull BigDecimal valor, @Positive @NotNull int quantidade,
			@Valid @NotNull @Size(min = 3) Collection<NovaCaracteristicaRequest> caracteristicas, @NotBlank @Size(max = 1000) String descricao,
			Categoria categoria) {
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.categoria = categoria;
		this.instanteCriacao = LocalDateTime.now().withNano(0);
		this.caracteristicas.addAll(caracteristicas.stream().map(caracteristica -> caracteristica.toModel(this)).collect(Collectors.toSet()));
	
		Assert.isTrue(this.caracteristicas.size()>=3,"é preciso ter no minimo tres caracteristicas para um produto!");
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", valor=" + valor + ", quantidade=" + quantidade
				+ ", caracteristicas=" + caracteristicas + ", descricao=" + descricao + ", categoria=" + categoria
				+ ", instanteCriacao=" + instanteCriacao + ", usuarioLogado=" + usuarioLogado + "]";
	}

	public void setUsuarioLogado(Usuario usuario) {
		this.usuarioLogado = usuario;	
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Produto other = (Produto) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
}
