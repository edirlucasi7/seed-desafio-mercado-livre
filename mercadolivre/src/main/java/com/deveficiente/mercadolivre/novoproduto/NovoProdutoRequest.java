package com.deveficiente.mercadolivre.novoproduto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.util.Assert;

import com.deveficiente.mercadolivre.novacategoria.Categoria;
import com.deveficiente.mercadolivre.novousuario.Usuario;

public class NovoProdutoRequest {
	
	private @NotBlank String nome;
	@Positive
	private @NotNull BigDecimal valor;
	@Positive
	private @NotNull int quantidade;
	private @Valid @NotNull @Size(min = 3) List<NovaCaracteristicaRequest> caracteristicas = new ArrayList<>();
	private @NotBlank @Size(max = 1000) String descricao;
	private @NotNull Long idCategoria;
	
	public NovoProdutoRequest(@NotBlank String nome, @Positive @NotNull BigDecimal valor,
			@Positive @NotNull @Min(1) int quantidade, @Valid @NotNull @Size(min = 3) List<NovaCaracteristicaRequest> caracteristicas,
			@NotBlank @Size(max = 1000) String descricao, @NotNull Long idCategoria) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.idCategoria = idCategoria;
		this.caracteristicas.addAll(caracteristicas);
	}

	public Produto toModel(EntityManager manager, Usuario usuario) {		
		Categoria categoria = manager.find(Categoria.class, idCategoria);
		Assert.notNull(categoria, "Um produto não pode ser criado sem categoria");
		
		Produto produto = new Produto(nome,valor,quantidade,caracteristicas,descricao,categoria);
	
		produto.setUsuarioLogado(usuario);
		
		return produto; 			
	}

	public String getNome() {
		return nome;
	}

	public List<NovaCaracteristicaRequest> getCaracteristicas() {
		return caracteristicas;
	}

	@Override
	public String toString() {
		return "NovoProdutoRequest [nome=" + nome + ", valor=" + valor + ", quantidade=" + quantidade
				+ ", caracteristicas=" + caracteristicas + ", descricao=" + descricao + ", idCategoria=" + idCategoria
				+ "]";
	}

	public boolean temCaracteristicasComNomesIguais() {
		HashSet<String> nomesIguais = new HashSet<>();
		for (NovaCaracteristicaRequest caracteristica : caracteristicas) {
			if(!nomesIguais.add(caracteristica.getNome())) {
				return true;
			}
		}
		return false;
	}
	
}
