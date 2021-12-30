package com.deveficiente.mercadolivre.fechacompra;

public enum StatusCompra {

	INICIADA("Iniciada"),
	ANDAMENTO("Andamento"),
	CONCLUIDA("Concluida");
	
	private String descricao;

	private StatusCompra(String descricao) {
		this.descricao = descricao;
	}
	
}
