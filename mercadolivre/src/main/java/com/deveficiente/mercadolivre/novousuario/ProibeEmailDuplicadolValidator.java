package com.deveficiente.mercadolivre.novousuario;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProibeEmailDuplicadolValidator implements Validator {

	private UsuarioRepository repository;
	
	public ProibeEmailDuplicadolValidator(UsuarioRepository repository) {
		this.repository = repository;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return NovoUsuarioRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(errors.hasErrors()) {
			return;
		}
		
		NovoUsuarioRequest request = (NovoUsuarioRequest)target;
		
		Usuario possivelUsuario = repository.findByEmail(request.getEmail());
		if(possivelUsuario!=null) {
			errors.rejectValue("email",null,"Não pode existir dois usuários com o mesmo email!");
		}
		
	}

}
