package com.deveficiente.mercadolivre;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import com.deveficiente.mercadolivre.novoproduto.NovaCaracteristicaRequest;
import com.deveficiente.mercadolivre.novoproduto.NovoProdutoRequest;
import com.deveficiente.mercadolivre.novoproduto.ProibeCaracteristicaComNomeIgualValidator;


public class ProibeCaracteristicaComNomeIgualValidatorTest {

	private List<NovaCaracteristicaRequest> requestCaracteristicas = List.of(new NovaCaracteristicaRequest("galaxy1","celular1"),
			new NovaCaracteristicaRequest("galaxy2","celular2"), new NovaCaracteristicaRequest("galaxy3","celular3"));

	private NovoProdutoRequest request = new NovoProdutoRequest("celularar", BigDecimal.TEN, 1, requestCaracteristicas, "teste", 1l);
	
	@Test
	@DisplayName("deveria bloquear caso exista caracteristicas com nomes iguais")
	void teste1() throws Exception {
		
		List<NovaCaracteristicaRequest> requestCaracteristicas = List.of(new NovaCaracteristicaRequest("galaxy1","celular1"),
				new NovaCaracteristicaRequest("galaxy1","celular2"), new NovaCaracteristicaRequest("galaxy3","celular3"));
		
		NovoProdutoRequest request = new NovoProdutoRequest("celular", BigDecimal.TEN, 1, requestCaracteristicas, "teste", 1l);
		
		Errors errors = new BeanPropertyBindingResult(request, "target");
		
		ProibeCaracteristicaComNomeIgualValidator validator = new ProibeCaracteristicaComNomeIgualValidator();
		validator.validate(request, errors);
		
		Assertions.assertTrue(errors.hasErrors());
		Assertions.assertEquals("O nome das caracteristicas não podem ser iguais!", errors.getFieldErrors().get(0).getDefaultMessage());
		
	}
	
	@Test
	@DisplayName("deveria bloquear caso exista caracteristicas com nomes iguais")
	void teste2() throws Exception {
				
		Errors errors = new BeanPropertyBindingResult(request, "target");
		
		ProibeCaracteristicaComNomeIgualValidator validator = new ProibeCaracteristicaComNomeIgualValidator();
		validator.validate(request, errors);
		
		Assertions.assertFalse(errors.hasErrors());
		Assertions.assertEquals(false, request.temCaracteristicasComNomesIguais());
		
	}
	
	@Test
	@DisplayName("devevria parar caso já tenha erro")
	void teste3() throws Exception {
	
		Errors errors = new BeanPropertyBindingResult(request, "target");
		errors.reject("caracteristicas");
		
		ProibeCaracteristicaComNomeIgualValidator validator = new ProibeCaracteristicaComNomeIgualValidator();
		validator.validate(request, errors);
		
		Assertions.assertTrue(errors.hasErrors());
		Assertions.assertEquals("caracteristicas", errors.getGlobalErrors().get(0).getCode());
	
	}
	
}
