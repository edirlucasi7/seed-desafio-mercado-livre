package com.deveficiente.mercadolivre.novacategoria;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class CategoriasController {
	
	@Autowired
	private EntityManager manager;

	@PostMapping("/categorias")
	@Transactional
	public String cria(@RequestBody @Valid NovaCategoriaRequest request) {
		Categoria possivalCategoria = request.toModel(manager);
		Optional.ofNullable(possivalCategoria).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		manager.persist(possivalCategoria);
		return possivalCategoria.toString();
	}
	
	
}
