package com.deveficiente.mercadolivre.fechacompra;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.util.Assert;
import org.springframework.web.util.UriComponentsBuilder;

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
	@Enumerated(value = EnumType.STRING)
	private StatusCompra statusCompra = StatusCompra.INICIADA;
	@Enumerated(EnumType.STRING)
	@NotNull
	private GatewayPagamento gatewayPagamento;
	@OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
	private Set<Transacao> transacoes = new HashSet<>();
	
	@Deprecated
	public Compra() {
	}
	
	public Compra(@Positive @NotNull int quantidade, Produto produto, Usuario usuarioLogado, GatewayPagamento gatewayPagamento) {
		this.quantidade = quantidade;
		this.produto = produto;
		this.comprador = usuarioLogado;
		this.gatewayPagamento = gatewayPagamento;
	}

	@Override
	public String toString() {
		return "Compra [quantidade=" + quantidade + ", produto=" + produto
				+ ", statusCompra=" + statusCompra + "]";
	}

	public Long getId() {
		return id;
	}

	public Produto getProduto() {
		return produto;
	}
	
	public Set<Transacao> getTransacoes() {
		return transacoes;
	}
	
	public String urlRedirecionamento(UriComponentsBuilder uriComponentsBuilder) {
		return this.gatewayPagamento.criaUrlRetorno(this, uriComponentsBuilder);
	}

	public void adicionaTransacao(@Valid RetornoGatewayPagamento request) {
		Transacao novaTransacao = request.toTransacao(this);	
		Assert.isTrue(!this.transacoes.contains(novaTransacao), "Não pode ter transacoes iguais para uma mesma compra!"+novaTransacao);
		
		Set<Transacao> transacoesComSucesso = transacoesConcluidasComSucesso();
		Assert.isTrue(transacoesComSucesso.isEmpty(), "Não é permitido que uma compra tenha outra transacao apos um sucesso na transacao!");
		
		this.transacoes.add(request.toTransacao(this));
	}

	private Set<Transacao> transacoesConcluidasComSucesso() {
		Set<Transacao> transacoesComSucesso = this.transacoes.stream().filter(Transacao::concluidaComSucesso).collect(Collectors.toSet());
		return transacoesComSucesso;
	}

	public boolean processadaComSucesso() {
		return !transacoesConcluidasComSucesso().isEmpty();
	}

	public Usuario getComprador() {
		return comprador;
	}	

}
