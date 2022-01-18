package com.deveficiente.mercadolivre.detalheproduto;

import java.math.BigDecimal;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.SortedSet;
import java.util.stream.IntStream;

import com.deveficiente.mercadolivre.novoproduto.Produto;

public class DetalheProdutoResponse {

	
	private String descricao;
	private String nome;
	private BigDecimal preco;
	private Set<DetalheProdutoCaracteristica> caracteristicas;
	private Set<String> imagens;
	private SortedSet<String> perguntas;
	private Set<Map<String,String>> opinioes;
	private double mediaNotas;
	
	
	public DetalheProdutoResponse(Produto produto) {
		this.descricao = produto.getDescricao();
		this.nome = produto.getNome();
		this.preco = produto.getValor();
		this.caracteristicas = produto.mapCaracteristicas(DetalheProdutoCaracteristica::new);
		this.imagens = produto.mapImagens(imagem -> imagem.getLink());
		this.perguntas = produto.mapPerguntas(pergunta -> pergunta.getTitulo());
		
		Opinioes opinioes = produto.getOpinioes();
		
		this.opinioes = opinioes.mapOpinioes(opiniao -> {
			return Map.of("titutlo", opiniao.getTitulo(), "descricao", opiniao.getDescricao());
		});

		this.mediaNotas = opinioes.media();
	}
	
	public double getMediaNotas() {
		return mediaNotas;
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


	public SortedSet<String> getPerguntas() {
		return perguntas;
	}


	public Set<Map<String, String>> getOpinioes() {
		return opinioes;
	}


	public void setOpinioes(Set<Map<String, String>> opinioes) {
		this.opinioes = opinioes;
	}

}
