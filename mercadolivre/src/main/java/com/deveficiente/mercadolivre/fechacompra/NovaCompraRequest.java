package com.deveficiente.mercadolivre.fechacompra;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.util.Assert;

import com.deveficiente.mercadolivre.novoproduto.Produto;
import com.deveficiente.mercadolivre.novousuario.Usuario;

public class NovaCompraRequest {

	@Positive
	@NotNull
	private int quantidade;
	@NotNull
	private Long idProduto;
	@NotNull
	private GatewayPagamento gateway;
	
	public NovaCompraRequest(@Positive @NotNull int quantidade, @NotNull Long idProduto, GatewayPagamento gateway) {
		super();
		this.quantidade = quantidade;
		this.idProduto = idProduto;
		this.gateway = gateway;
	}
	
	public Compra toModel(EntityManager manager, Usuario usuarioLogado) {
		Produto produto = manager.find(Produto.class, idProduto);
		Assert.notNull(produto, "O produto precisa existir aqui!");
		produto.abateQuantidadeEstoque(quantidade);
		
		return new Compra(quantidade, produto, usuarioLogado, gateway);
	}

	public Object getIdProduto() {
		return idProduto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public GatewayPagamento getGateway() {
		return gateway;
	}

}
