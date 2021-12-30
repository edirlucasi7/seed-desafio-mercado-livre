package com.deveficiente.mercadolivre.fechacompra;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class PayPal implements GatewayPagamento{

	@Override
	public String getaway(Long idGeradoDaCompra) {
		return "paypal.com/{"+idGeradoDaCompra+"}?redirectUrl={urlRetornoAppPosPagamento}";
	}

}
