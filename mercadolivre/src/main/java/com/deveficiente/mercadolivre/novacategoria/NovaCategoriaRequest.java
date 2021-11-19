package com.deveficiente.mercadolivre.novacategoria;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import com.deveficiente.mercadolivre.compartilhado.ExistsId;
import com.deveficiente.mercadolivre.compartilhado.UniqueValue;

public class NovaCategoriaRequest {

	@UniqueValue(domainClass = Categoria.class,fieldName = "nome")
	private @NotBlank String nome;
	@Positive
	@ExistsId(domainClass = Categoria.class, fieldName = "id")
	private Long idCategoriaMae;

	public void setNome(@NotBlank String nome) {
		this.nome = nome;
	}
	
	public void setIdCategoriaMae(Long idCategoriaMae) {
		this.idCategoriaMae = idCategoriaMae;
	}

	public Categoria toModel(EntityManager manager) {

		Categoria categoria = new Categoria(nome);
		
		if(idCategoriaMae!=null) {
			categoria.setCategoriaMae(manager.find(Categoria.class, idCategoriaMae));
		}
	
		return categoria;
	}

}
