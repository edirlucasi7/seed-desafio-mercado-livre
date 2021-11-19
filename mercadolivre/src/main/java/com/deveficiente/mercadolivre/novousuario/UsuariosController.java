package com.deveficiente.mercadolivre.novousuario;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuariosController {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private ProibeEmailDuplicadolValidator proibeEmailDuplicadolValidator;
	
	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(proibeEmailDuplicadolValidator);
	}
	
	@PostMapping("/usuarios")
	@Transactional
	public String cria(@RequestBody @Valid NovoUsuarioRequest request) {
		Usuario usuario = request.toModel();
		manager.persist(usuario);
		return usuario.toString();
	}
	
}
