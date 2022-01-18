package com.deveficiente.mercadolivre.detalheproduto;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.deveficiente.mercadolivre.novoproduto.Produto;
import com.deveficiente.mercadolivre.novoproduto.ProdutoRepository;

@RestController
public class DetalheProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping("/produtos/{id}")
	public DetalheProdutoResponse detalhe(@PathVariable("id") Long id) {
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, format("Produto com id %s not found", id)));
		return new DetalheProdutoResponse(produto);
	}
	
}
