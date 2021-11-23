package com.deveficiente.mercadolivre.novoproduto;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProibeCaracteristicaComNomeIgualValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return NovoProdutoRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(errors.hasErrors()) {
			return;
		}
		
		NovoProdutoRequest request = (NovoProdutoRequest) target;
		if(request.temCaracteristicasComNomesIguais()) {
			errors.rejectValue("caracteristicas", null, "Os nomes das caracteristicas não podem ser iguais!");
		}
		
	}

}
