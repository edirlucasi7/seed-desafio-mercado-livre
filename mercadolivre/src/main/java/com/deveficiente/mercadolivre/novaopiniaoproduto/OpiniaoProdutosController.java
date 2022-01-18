package com.deveficiente.mercadolivre.novaopiniaoproduto;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.deveficiente.mercadolivre.novoproduto.Produto;
import com.deveficiente.mercadolivre.novoproduto.ProdutoRepository;
import com.deveficiente.mercadolivre.novousuario.Usuario;
import com.deveficiente.mercadolivre.novousuario.UsuarioRepository;

@RestController
public class OpiniaoProdutosController {
	
	@PersistenceContext
	private EntityManager manager;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostMapping("/opinioes/{id}/produtos")
	@Transactional
	public ResponseEntity<Void> cria(@Valid @RequestBody NovaOpiniaoProdutoRequest request, @PathVariable("id") Long id) {
		Usuario fakeUsuarioLogado = usuarioRepository.findByEmail("icety@gmail");
		
		Optional<Produto> produto = produtoRepository.findById(id);
		if(produto.isPresent()) {
			Opiniao novaOpniao = request.toModel(produto.get(),fakeUsuarioLogado);
			manager.persist(novaOpniao);
			return ResponseEntity.created(null).build();
		}
		return ResponseEntity.notFound().build();
	}
	
}
