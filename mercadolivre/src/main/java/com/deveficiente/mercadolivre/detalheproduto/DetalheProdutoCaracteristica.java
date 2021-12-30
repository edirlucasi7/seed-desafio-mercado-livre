package com.deveficiente.mercadolivre.detalheproduto;

import com.deveficiente.mercadolivre.novoproduto.CaracteristicaProduto;

public class DetalheProdutoCaracteristica {

	
	private String nome;
	private String descricao;

	public DetalheProdutoCaracteristica(CaracteristicaProduto caracteristicaProduto) {
		this.nome = caracteristicaProduto.getNome();
		this.descricao = caracteristicaProduto.getDescricao();
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
