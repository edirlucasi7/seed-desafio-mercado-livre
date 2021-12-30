package com.deveficiente.mercadolivre.fechacompra;

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

import com.deveficiente.mercadolivre.novousuario.Usuario;
import com.deveficiente.mercadolivre.novousuario.UsuarioRepository;

@RestController
public class FechaComprasController {

	@PersistenceContext
	private EntityManager manager;
	@Autowired
	private UsuarioRepository repository;
	@Autowired
	private GatewayPagamento formaPagamento;
	@Autowired
	private VeirficaEstoqueDoProduto veirficaEstoqueDoProduto;
	@Autowired
	private EmailSenderService emailSenderService;
	
	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(veirficaEstoqueDoProduto);
	}
	
	@PostMapping("/compras")
	@Transactional
	public String cria(@RequestBody @Valid NovaCompraRequest request) {
		Usuario fakeUsuarioLogado = repository.findByEmail("icety@gmail");
		
		Compra compra = request.toModel(manager, fakeUsuarioLogado);
		manager.persist(compra);
		
		String emailDonoProduto = compra.getProduto().getDono().getEmail();
		emailSenderService.fechaCompra(emailDonoProduto, "Um nova compra foi cadastrada", "teste");
		
		String url = formaPagamento.getaway(compra.getId());
		return "redirect:/"+url;
	}
	
}
