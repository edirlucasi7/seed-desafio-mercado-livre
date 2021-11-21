package com.deveficiente.mercadolivre;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assumptions;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.deveficiente.mercadolivre.novousuario.SenhaLimpa;
import com.deveficiente.mercadolivre.novousuario.Usuario;
import com.deveficiente.mercadolivre.novousuario.UsuarioRepository;

import net.jqwik.api.ForAll;
import net.jqwik.api.Label;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.AlphaChars;
import net.jqwik.api.constraints.BigRange;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.StringLength;
import net.jqwik.spring.JqwikSpringSupport;

@JqwikSpringSupport
@SpringBootTest
@AutoConfigureMockMvc
public class ProdutosControllerTest {

	@Autowired
	private CustomMockMvc mvc;
	private static List<String> caracteristica = new ArrayList<>();
	private UsuarioRepository repository = Mockito.mock(UsuarioRepository.class);
	
	@Property(tries = 2)
	@Label("fluxo de cadastro de produto")
	@DirtiesContext(methodMode = MethodMode.BEFORE_METHOD)
	void teste(@ForAll @AlphaChars @StringLength(min = 1, max = 10) String nome,
			@ForAll @BigRange(min = "1", max = "10") BigDecimal valor,
			@ForAll @IntRange(min = 1, max = 10) int quantidade,
			@ForAll @AlphaChars @StringLength(min = 1, max = 1000) String descricao) throws Exception {
		
		Assumptions.assumeTrue(caracteristica.add("teste"));
		Assumptions.assumeTrue(caracteristica.add("teste"));
		Assumptions.assumeTrue(caracteristica.add("teste"));
		
		mvc.post("/categorias", Map.of("nome","Tecnologia"));
		mvc.post("/usuarios", Map.of("email","icety@gmail","senha","123456"));
				
		Mockito.when(repository.findByEmail("icety@gmail")).thenReturn(new Usuario("icety@gmail",new SenhaLimpa("123456")));
		
		mvc.post("/produtos", Map.of("nome",nome,
				"valor",valor,
				"quantidade",quantidade,
				"caracteristica",caracteristica,
				"descricao",descricao,
				"idCategoria","1"))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
		
	}
	
}
