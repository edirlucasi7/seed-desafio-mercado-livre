package com.deveficiente.mercadolivre.fechacompra;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Transacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Enumerated(EnumType.STRING)
	private StatusTransacao status;
	private @NotBlank String idTransacaoGateway;
	@NotNull
	private LocalDateTime instante;
	@NotNull
	@ManyToOne
	private Compra compra;
	
	public Transacao() {
	}

	public Transacao(StatusTransacao status, @NotBlank String idTransacaoGateway, Compra compra) {
		this.status = status;
		this.idTransacaoGateway = idTransacaoGateway;
		this.instante = LocalDateTime.now();
		this.compra = compra;
	}
	
	public boolean concluidaComSucesso() {
		return this.status.equals(StatusTransacao.sucesso);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idTransacaoGateway == null) ? 0 : idTransacaoGateway.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transacao other = (Transacao) obj;
		if (idTransacaoGateway == null) {
			if (other.idTransacaoGateway != null)
				return false;
		} else if (!idTransacaoGateway.equals(other.idTransacaoGateway))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Transacao [id=" + id + ", status=" + status + ", idTransacaoGateway=" + idTransacaoGateway
				+ ", instante=" + instante + ", compra=" + compra + "]";
	}
}
