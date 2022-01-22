package com.deveficiente.mercadolivre.fechacompra;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.deveficiente.mercadolivre.novoproduto.Produto;
import com.deveficiente.mercadolivre.novousuario.Usuario;
import com.deveficiente.mercadolivre.novousuario.UsuarioRepository;

@RestController
public class FechaComprasController {

	@PersistenceContext
	private EntityManager manager;
	@Autowired
	private UsuarioRepository repository;
	@Autowired
	private VeirficaEstoqueDoProduto veirficaEstoqueDoProduto;
	
	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(veirficaEstoqueDoProduto);
	}
	
	@PostMapping("/compras")
	@Transactional
	public String cria(@RequestBody @Valid NovaCompraRequest request, UriComponentsBuilder uriComponentsBuilder) throws BindException {
		Produto produtoASerComprado = manager.find(Produto.class, request.getIdProduto());
		
		int quantidade = request.getQuantidade();
		boolean abateu = produtoASerComprado.abateQuantidadeEstoque(quantidade);
		if(abateu) {
			Usuario fakeUsuarioLogado = repository.findByEmail("icety@gmail");
			
			GatewayPagamento gateway = request.getGateway();
			
			Compra novaCompra = new Compra(quantidade, produtoASerComprado, fakeUsuarioLogado, gateway);
			manager.persist(novaCompra);
			
			return novaCompra.urlRedirecionamento(uriComponentsBuilder);
		}
		
		BindException problemaComEstoque = new BindException(request, "novaCompraRequest");
		problemaComEstoque.reject(null, "Não foi possível realizar a compra por conta do estoque insuficiente");
		
		throw problemaComEstoque;
	
	}
	
}
