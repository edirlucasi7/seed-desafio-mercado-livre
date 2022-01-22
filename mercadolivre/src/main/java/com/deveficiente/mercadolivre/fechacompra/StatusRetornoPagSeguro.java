package com.deveficiente.mercadolivre.fechacompra;

public enum StatusRetornoPagSeguro {

	SUCESSO, ERRO;

	StatusTransacao normaliza() {
		if(this.equals(SUCESSO)) {
			return StatusTransacao.sucesso;
		}
		
		return StatusTransacao.erro;
	}
}
