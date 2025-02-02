package com.deveficiente.mercadolivre;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Assumptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import net.jqwik.api.ForAll;
import net.jqwik.api.Label;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.AlphaChars;
import net.jqwik.api.constraints.StringLength;
import net.jqwik.spring.JqwikSpringSupport;

@JqwikSpringSupport
@SpringBootTest
@AutoConfigureMockMvc
public class UsuariosControllerTest {

	@Autowired
	private CustomMockMvc mvc;
	private static Set<String> emailUnicos = new HashSet<>();
	
	@Property(tries = 1)
	@Label("fluxo de cadastro de um novo usuario")
	void teste(@ForAll @AlphaChars @StringLength(min = 1, max = 20) String email,
			@ForAll @AlphaChars @StringLength(min = 6, max = 20) String senha) throws Exception {
		
		Assumptions.assumeTrue(emailUnicos.add(email));
		
		mvc.post("/usuarios", Map.of("email",email+"@gmail",
				"senha",senha))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
		
		mvc.post("/usuarios", Map.of("email",email+"@gmail",
				"senha",senha))
		.andExpect(MockMvcResultMatchers.status().is4xxClientError());
		
	}
	
	
}
