package com.deveficiente.mercadolivre.novapergunta;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.deveficiente.mercadolivre.novoproduto.Produto;
import com.deveficiente.mercadolivre.novousuario.Usuario;

public class NovaPerguntaRequest {

	
	private @NotBlank String titulo;
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public Pergunta toModel(@NotNull Produto produto, @NotNull Usuario usuario) {
		return new Pergunta(titulo, usuario, produto);
	}	
	
}
