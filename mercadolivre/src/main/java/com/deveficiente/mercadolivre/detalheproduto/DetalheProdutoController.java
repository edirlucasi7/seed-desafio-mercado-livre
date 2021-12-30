package com.deveficiente.mercadolivre.detalheproduto;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.deveficiente.mercadolivre.novoproduto.Produto;

@RestController
public class DetalheProdutoController {

	@Autowired
	private EntityManager manager;
	
	@GetMapping("/produtos/{id}")
	public DetalheProdutoResponse detalhe(@PathVariable("id") Long id) {
		Produto produto = manager.find(Produto.class, id);
		return new DetalheProdutoResponse(produto);
	}
	
}
