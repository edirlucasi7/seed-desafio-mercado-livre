package com.deveficiente.mercadolivre.novacategoria;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import org.springframework.util.Assert;

@Entity
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotBlank String nome;
	@ManyToOne
	private Categoria categoriaMae;
	
	@Deprecated
	public Categoria() { }
	
	public Categoria(@NotBlank String nome) {
		this.nome = nome;
	}

	public void setCategoriaMae(Categoria categoriaMae) {
		Assert.isTrue(categoriaMae!=null, "não pode chegar aqui sem uma categoria mãe");
		this.categoriaMae = categoriaMae;
	}

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nome=" + nome + ", categoriaMae=" + categoriaMae + "]";
	}

}
