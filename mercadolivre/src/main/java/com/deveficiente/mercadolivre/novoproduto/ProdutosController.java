package com.deveficiente.mercadolivre.novoproduto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.deveficiente.mercadolivre.novousuario.Usuario;
import com.deveficiente.mercadolivre.novousuario.UsuarioRepository;

@RestController
public class ProdutosController {

	@PersistenceContext
	private EntityManager manager;
	@Autowired
	private UsuarioRepository repository;
	
	@PostMapping("/produtos")
	@Transactional
	public String cria(@RequestBody @Valid NovoProdutoRequest request) {
		Usuario fakeUsuarioLogado = repository.findByEmail("icety@gmail");
		
		Produto produto = request.toModel(manager,fakeUsuarioLogado);
		manager.persist(produto);
		
		return produto.toString();
		
	}
	
}
