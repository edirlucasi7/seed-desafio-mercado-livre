package com.deveficiente.mercadolivre.fechacompra;

import org.springframework.stereotype.Component;

@Component
public class Pagseguro implements GatewayPagamento{

	@Override
	public String getaway(Long idGeradoDaCompra) {
		return "pagseguro.com/{"+idGeradoDaCompra+"}?redirectUrl={urlRetornoAppPosPagamento}";
	}

}
