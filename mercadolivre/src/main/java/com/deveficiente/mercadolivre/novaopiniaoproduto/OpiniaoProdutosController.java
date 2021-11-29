package com.deveficiente.mercadolivre.novaopiniaoproduto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.deveficiente.mercadolivre.novoproduto.Produto;
import com.deveficiente.mercadolivre.novousuario.Usuario;
import com.deveficiente.mercadolivre.novousuario.UsuarioRepository;

@RestController
public class OpiniaoProdutosController {
	
	@PersistenceContext
	private EntityManager manager;
	@Autowired
	private UsuarioRepository repository;
	
	@PostMapping("/opinioes/{id}/produtos")
	@Transactional
	public String cria(@Valid @RequestBody NovaOpiniaoProdutoRequest request, @PathVariable("id") Long id) {
		Usuario fakeUsuarioLogado = repository.findByEmail("icety@gmail");
		Produto produto = manager.find(Produto.class, id);
		
		NovaOpiniaoProduto novaOpniao = request.toModel(produto,fakeUsuarioLogado);
		manager.persist(novaOpniao);
		return novaOpniao.toString();
	}
	
}
