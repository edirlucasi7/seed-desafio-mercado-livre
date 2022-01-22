package com.deveficiente.mercadolivre.fechacompra;

public interface RetornoGatewayPagamento {
	
	Transacao toTransacao(Compra compra);

}
