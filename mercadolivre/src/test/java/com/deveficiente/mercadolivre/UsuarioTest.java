package com.deveficiente.mercadolivre;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.deveficiente.mercadolivre.novousuario.SenhaLimpa;
import com.deveficiente.mercadolivre.novousuario.Usuario;

public class UsuarioTest {

	
	@Test
	@DisplayName("cria usuario com data")
	void teste1() throws Exception {
		
		SenhaLimpa senhaLimpa = new SenhaLimpa("123456");
		Usuario usuario = new Usuario("icety@gmail.com", senhaLimpa, LocalDateTime.now().withNano(0));
		Assertions.assertNotNull(usuario);
		
	}
	
	@Test
	@DisplayName("nao cria usuario sem data")
	void teste2() throws Exception {
		
		SenhaLimpa senhaLimpa = new SenhaLimpa("123456");
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Usuario("icety@gmail.com", senhaLimpa, null);
		});
		
	}
	
}
