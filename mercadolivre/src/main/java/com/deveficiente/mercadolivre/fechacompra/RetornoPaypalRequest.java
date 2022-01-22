package com.deveficiente.mercadolivre.fechacompra;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class RetornoPaypalRequest implements RetornoGatewayPagamento {

	@Min(0)
	@Max(1)
	private int status;
	@NotNull
	private String idTransacao;
	
	public RetornoPaypalRequest(@Min(0) @Max(1) int status, @NotNull String idTransacao) {
		this.status = status;
		this.idTransacao = idTransacao;
	}

	public Transacao toTransacao(Compra compra) {
		StatusTransacao status =  this.status == 0 ? StatusTransacao.erro : StatusTransacao.sucesso;
		return new Transacao(status, idTransacao, compra);
	}

	@Override
	public String toString() {
		return "RetornoPaypalRequest [status=" + status + ", idTransacao=" + idTransacao + "]";
	}
	
}
