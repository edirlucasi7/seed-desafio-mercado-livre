package com.deveficiente.mercadolivre;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.deveficiente.mercadolivre.novacategoria.Categoria;
import com.deveficiente.mercadolivre.novacategoria.NovaCategoriaRequest;

public class NovaCategoriaRequestTest {

	private EntityManager manager = Mockito.mock(EntityManager.class);
	private NovaCategoriaRequest request = new NovaCategoriaRequest();
	
	@Test
	@DisplayName("cria categoria sem categoria mae")
	void teste1() throws Exception {
		
		request.setNome("celulares");
		Categoria categoria = request.toModel(manager);
		
		Assertions.assertNotNull(categoria);
		
		Mockito.verify(manager, Mockito.never()).find(Categoria.class, "1");
	}
	
	@Test
	@DisplayName("cria categoria com categoria mae")
	void teste2() throws Exception {
		
		Categoria categoriaMae = new Categoria("suporte");
		
		request.setNome("celulares");
		request.setIdCategoriaMae(1l);
		Mockito.when(manager.find(Categoria.class, 1l)).thenReturn(categoriaMae);
		Categoria categoria = request.toModel(manager);
		
		Assertions.assertNotNull(categoria);
		
		Mockito.verify(manager).find(Categoria.class, 1l);
		
	}
	
}
