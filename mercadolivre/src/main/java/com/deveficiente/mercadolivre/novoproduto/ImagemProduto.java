package com.deveficiente.mercadolivre.novoproduto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class ImagemProduto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private @NotNull Produto produto;
	private @NotBlank String link; 
	
	public ImagemProduto() { }
	
	public ImagemProduto(@NotNull Produto produto, @NotBlank String link) {
		this.produto = produto;
		this.link = link;
	}

	@Override
	public String toString() {
		return "ImagemProduto [id=" + id + ", link=" + link + "]";
	}

	public String getLink() {
		return link;
	}	

}
