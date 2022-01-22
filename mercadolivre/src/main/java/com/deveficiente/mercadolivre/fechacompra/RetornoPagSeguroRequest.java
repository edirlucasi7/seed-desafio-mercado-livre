package com.deveficiente.mercadolivre.fechacompra;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RetornoPagSeguroRequest implements RetornoGatewayPagamento {

	@NotBlank
	private String idTransacao;
	@NotNull
	private StatusRetornoPagSeguro status;

	public RetornoPagSeguroRequest(@NotBlank String idTransacao, StatusRetornoPagSeguro status) {
		this.idTransacao = idTransacao;
		this.status = status;
	}

	public Transacao toTransacao(Compra compra) {
		return new Transacao(status.normaliza(), idTransacao, compra);
	}

	@Override
	public String toString() {
		return "RetornoPagSeguroRequest [idTransacao=" + idTransacao + ", status=" + status + "]";
	}

}
