package com.deveficiente.mercadolivre;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.deveficiente.mercadolivre.novacategoria.Categoria;

public class CategoriaTest {

	@Test
	@DisplayName("cria categoria com catagoria mae nao nulo")
	void teste1() throws Exception {
		
		Categoria categoriaMae = new Categoria("suporte");
		Categoria categoria = new Categoria("celulares");
		categoria.setCategoriaMae(categoriaMae);
		
		Assertions.assertNotNull(categoria);		
	}
	
	@Test
	@DisplayName("nao cria categoria com categoria mae igual a nulo")
	void teste2() throws Exception {
		
		Categoria categoriaMae = null;
		Categoria categoria = new Categoria("celulares");
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			categoria.setCategoriaMae(categoriaMae);
		});	
	}
	
}
