package com.deveficiente.mercadolivre.fechacompra;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.deveficiente.mercadolivre.novoproduto.Produto;
import com.deveficiente.mercadolivre.novousuario.Usuario;

@Entity
public class Compra {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Positive
	@NotNull
	private int quantidade;
	@NotNull
	@OneToOne
	private Produto produto;
	@NotNull
	@ManyToOne
	private Usuario comprador;
	@NotNull
	private String formaPagamento;
	@Enumerated(value = EnumType.STRING)
	private StatusCompra statusCompra = StatusCompra.INICIADA;
	
	public Compra(@Positive @NotNull int quantidade, Produto produto, @NotNull String formaPagamento, Usuario usuarioLogado) {
		this.quantidade = quantidade;
		this.produto = produto;
		this.formaPagamento = formaPagamento;
		this.comprador = usuarioLogado;
	}

	@Override
	public String toString() {
		return "Compra [quantidade=" + quantidade + ", produto=" + produto + ", formaPagamento=" + formaPagamento
				+ ", statusCompra=" + statusCompra + "]";
	}

	public Long getId() {
		return id;
	}

	public Produto getProduto() {
		return produto;
	}
	
	
}
