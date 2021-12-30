package com.deveficiente.mercadolivre.novaopiniaoproduto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.deveficiente.mercadolivre.novoproduto.Produto;
import com.deveficiente.mercadolivre.novousuario.Usuario;

public class NovaOpiniaoProdutoRequest {

	private @Min(1) @Max(5) int nota;
	private @NotBlank String titulo;
	private @NotBlank @Size(max = 500) String descricao;
	
	public NovaOpiniaoProdutoRequest(@Min(1) @Max(5) int nota, @NotBlank String titulo,
			@NotBlank @Size(max = 500) String descricao) {
		super();
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
	}
	
	public Opiniao toModel(Produto produto, Usuario fakeUsuarioLogado) {
		return new Opiniao(nota,titulo,descricao,fakeUsuarioLogado,produto);
	}
	
}
