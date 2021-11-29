package com.deveficiente.mercadolivre.novoproduto;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.deveficiente.mercadolivre.novousuario.Usuario;
import com.deveficiente.mercadolivre.novousuario.UsuarioRepository;

@RestController
public class ProdutosController {

	@PersistenceContext
	private EntityManager manager;
	@Autowired
	private UsuarioRepository repository;
	@Autowired
	private Uploader uploadFake;
	@Autowired
	private ProibeCaracteristicaComNomeIgualValidator proibeCaracteristicaComNomeIgualValidator;
	
	@InitBinder(value = "novoProdutoRequest")
	public void init(WebDataBinder binder) {
		binder.addValidators(proibeCaracteristicaComNomeIgualValidator);
	}
	
	@PostMapping("/produtos")
	@Transactional
	public String cria(@RequestBody @Valid NovoProdutoRequest request) {
		Usuario fakeUsuarioLogado = repository.findByEmail("icety@gmail");
		
		Produto produto = request.toModel(manager,fakeUsuarioLogado);
		manager.persist(produto);
		
		return produto.toString();

	}
	
	@PostMapping("/produtos/{id}/imagens")
	@Transactional
	public String adicionaImagens(@Valid NovaImagensRequest request, @PathVariable("id") Long id) {
		/*
		 * 1) enviar imagens para o local onde elas vão ficar
		 * 2) pegar os links de todas as imagens
		 * 3) associar esses links com o produto em questão
		 * 4) preciso carregar o produto
		 * 5) depois que associar eu preciso atualizar a nova versão do produto 
		 */
		Usuario possivelDono = repository.findByEmail("icety@gmail");
		Produto produto = manager.find(Produto.class, id);
		
		if(!produto.pertenceAoUsuario(possivelDono)) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
	
		Set<String> links = uploadFake.envia(request.getImagens());
		produto.associaImagens(links);
		manager.merge(produto);
		return produto.toString();
	}
	
}
