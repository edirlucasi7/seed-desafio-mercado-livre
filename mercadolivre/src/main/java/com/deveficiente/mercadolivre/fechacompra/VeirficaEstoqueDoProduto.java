package com.deveficiente.mercadolivre.fechacompra;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.deveficiente.mercadolivre.novoproduto.Produto;

@Component
public class VeirficaEstoqueDoProduto implements Validator{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return NovaCompraRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(errors.hasErrors()) {
			return;
		}
		
		NovaCompraRequest request = (NovaCompraRequest)target;
		Produto produto = manager.find(Produto.class, request.getIdProduto());
		if(produto.getQuantidade() < request.getQuantidade()) {
			errors.rejectValue("quantidade", null, "O produto não tem estoque suficiente!");
		}
		
	}

}
