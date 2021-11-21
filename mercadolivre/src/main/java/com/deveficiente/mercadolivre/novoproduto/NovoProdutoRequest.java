package com.deveficiente.mercadolivre.novoproduto;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
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
	private @NotNull @Size(min = 3) List<String> caracteristica;
	private @NotBlank @Size(max = 1000) String descricao;
	private @NotNull Long idCategoria;
	
	public NovoProdutoRequest(@NotBlank String nome, @Positive @NotNull BigDecimal valor,
			@Positive @NotNull @Min(1) int quantidade, @NotNull @Size(min = 3) List<String> caracteristica,
			@NotBlank @Size(max = 1000) String descricao, @NotNull Long idCategoria) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.caracteristica = caracteristica;
		this.descricao = descricao;
		this.idCategoria = idCategoria;
	}

	public Produto toModel(EntityManager manager, Usuario usuario) {
		Categoria categoria = manager.find(Categoria.class, idCategoria);
		Assert.notNull(categoria, "Um produto não pode ser criado sem categoria");
		Produto produto = new Produto(nome,valor,quantidade,caracteristica,descricao,categoria);
	
		produto.setUsuarioLogado(usuario);
		
		return produto; 			
	}
	
}
