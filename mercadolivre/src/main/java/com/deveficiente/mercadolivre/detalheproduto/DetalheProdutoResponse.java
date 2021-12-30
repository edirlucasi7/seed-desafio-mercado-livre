package com.deveficiente.mercadolivre.detalheproduto;

import java.math.BigDecimal;
import java.util.Set;
import java.util.SortedSet;

import com.deveficiente.mercadolivre.novoproduto.Produto;

public class DetalheProdutoResponse {

	
	private String descricao;
	private String nome;
	private BigDecimal preco;
	private Set<DetalheProdutoCaracteristica> caracteristicas;
	private Set<String> imagens;
	private SortedSet<String> perguntas;
	
	
	public DetalheProdutoResponse(Produto produto) {
		this.descricao = produto.getDescricao();
		this.nome = produto.getNome();
		this.preco = produto.getValor();
		this.caracteristicas = produto.mapCaracteristicas(DetalheProdutoCaracteristica::new);
		this.imagens = produto.mapImagens(imagem -> imagem.getLink());
		this.perguntas = produto.mapPerguntas(pergunta -> pergunta.getTitulo());
	}


	public String getDescricao() {
		return descricao;
	}


	public String getNome() {
		return nome;
	}


	public BigDecimal getPreco() {
		return preco;
	}


	public Set<DetalheProdutoCaracteristica> getCaracteristicas() {
		return caracteristicas;
	}


	public Set<String> getImagens() {
		return imagens;
	}


	public Set<String> getPerguntas() {
		return perguntas;
	}
	
}
