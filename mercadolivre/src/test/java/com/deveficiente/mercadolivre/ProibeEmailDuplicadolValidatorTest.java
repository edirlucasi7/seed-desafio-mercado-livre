package com.deveficiente.mercadolivre;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import com.deveficiente.mercadolivre.novousuario.NovoUsuarioRequest;
import com.deveficiente.mercadolivre.novousuario.ProibeEmailDuplicadolValidator;
import com.deveficiente.mercadolivre.novousuario.Usuario;
import com.deveficiente.mercadolivre.novousuario.UsuarioRepository;

public class ProibeEmailDuplicadolValidatorTest {

	private UsuarioRepository repository = Mockito.mock(UsuarioRepository.class);
	private NovoUsuarioRequest request = new NovoUsuarioRequest("icety@gmail","123456");
	
	@Test
	@DisplayName("nao deveria permitir request de novo usuario com email duplicado")
	void teste1() throws Exception {
		
		Usuario usuario = request.toModel();		
		Mockito.when(repository.findByEmail(request.getEmail())).thenReturn(usuario);
		
		Errors errors = new BeanPropertyBindingResult(request, "target");
		ProibeEmailDuplicadolValidator validator = new ProibeEmailDuplicadolValidator(repository);
		
		validator.validate(request, errors);
		
		Assertions.assertTrue(errors.hasErrors());
		Assertions.assertEquals("Não pode existir dois usuários com o mesmo email!", errors.getFieldErrors().get(0).getDefaultMessage());
	}
	
	@Test
	@DisplayName("deveria permitir request de novo usuario com email nao duplicado")
	void teste2() throws Exception {
		
		Errors errors = new BeanPropertyBindingResult(request, "target");
		ProibeEmailDuplicadolValidator validator = new ProibeEmailDuplicadolValidator(repository);
		
		validator.validate(request, errors);
		
		Assertions.assertFalse(errors.hasErrors());
	}
	
	@Test
	@DisplayName("deveria parar caso já tenha erro")
	void teste4() throws Exception {
	
		Errors errors = new BeanPropertyBindingResult(request, "target");
		errors.reject("codigo");
		
		ProibeEmailDuplicadolValidator validator = new ProibeEmailDuplicadolValidator(repository);
		validator.validate(request, errors);
		
		Assertions.assertTrue(errors.hasErrors());
		Assertions.assertEquals("codigo", errors.getGlobalErrors().get(0).getCode());
	
	}
	
}
