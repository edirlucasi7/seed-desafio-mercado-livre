package com.deveficiente.mercadolivre;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.deveficiente.mercadolivre.novacategoria.Categoria;
import com.deveficiente.mercadolivre.novoproduto.NovaCaracteristicaRequest;
import com.deveficiente.mercadolivre.novoproduto.NovoProdutoRequest;
import com.deveficiente.mercadolivre.novousuario.SenhaLimpa;
import com.deveficiente.mercadolivre.novousuario.Usuario;
import com.deveficiente.mercadolivre.novousuario.UsuarioRepository;

public class ProdutoTest {
	
	private EntityManager manager = Mockito.mock(EntityManager.class);
	private UsuarioRepository repository = Mockito.mock(UsuarioRepository.class);

	private List<NovaCaracteristicaRequest> requestCaracteristicas = List.of(new NovaCaracteristicaRequest("galaxy1","celular1"),
			new NovaCaracteristicaRequest("galaxy2","celular2"));
	private NovoProdutoRequest request = new NovoProdutoRequest("celularar", BigDecimal.TEN, 1, requestCaracteristicas, "teste", 1l);
	
	private Usuario usuario = new Usuario("icety@gmail.com", new SenhaLimpa("123456"));
	
	private Categoria categoria = new Categoria("Comedia");
	
	{
		Mockito.when(repository.findByEmail("icety@gmail.com")).thenReturn(usuario);
		Mockito.when(manager.find(Categoria.class, 1l)).thenReturn(categoria);
	}
	
	@Test
	@DisplayName("nao cria produto com menos de tres caracteristicas")
	void teste1() throws Exception {
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {			
			request.toModel(manager, usuario);
		});
	}
	
	@Test
	@DisplayName("cria produto com pelo menos tres caracteristicas")
	void teste2() throws Exception {
		
		List<NovaCaracteristicaRequest> requestCaracteristicas = List.of(new NovaCaracteristicaRequest("galaxy1","celular1"),
				new NovaCaracteristicaRequest("galaxy2","celular2"), new NovaCaracteristicaRequest("galaxy3","celular3"));
		NovoProdutoRequest request = new NovoProdutoRequest("celularar", BigDecimal.TEN, 1, requestCaracteristicas, "teste", 1l);
		
		Assertions.assertNotNull(request.toModel(manager, usuario));		
		
	}
	
}
