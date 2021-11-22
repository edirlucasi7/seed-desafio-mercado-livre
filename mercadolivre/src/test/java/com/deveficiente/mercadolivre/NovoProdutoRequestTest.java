package com.deveficiente.mercadolivre;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.deveficiente.mercadolivre.novacategoria.Categoria;
import com.deveficiente.mercadolivre.novoproduto.NovoProdutoRequest;
import com.deveficiente.mercadolivre.novousuario.SenhaLimpa;
import com.deveficiente.mercadolivre.novousuario.Usuario;


public class NovoProdutoRequestTest {

	private NovoProdutoRequest request = new NovoProdutoRequest("celularar", BigDecimal.TEN, 1, List.of("teste","teste","teste"), "teste", 1l);
	
	@Test
	@DisplayName("cria o produto com categoria")
	void teste1() throws Exception {
		
		EntityManager manager = Mockito.mock(EntityManager.class);
		
		Mockito.when(manager.find(Categoria.class, 1l)).thenReturn(new Categoria(""));
		
		Assertions.assertNotNull(request.toModel(manager, new Usuario("icety@gmail",new SenhaLimpa("123456"))));
		
	}
	
	@Test
	@DisplayName("nao cria o produto sem categoria")
	void teste2() throws Exception {
		
		EntityManager manager = Mockito.mock(EntityManager.class);
		
		Mockito.when(manager.find(Categoria.class, 1l)).thenReturn(null);
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			request.toModel(manager, new Usuario("icety@gmail",new SenhaLimpa("123456")));
		});
		
	}
	
}
