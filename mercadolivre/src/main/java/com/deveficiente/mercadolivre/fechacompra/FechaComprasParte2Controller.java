package com.deveficiente.mercadolivre.fechacompra;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FechaComprasParte2Controller {

	@PersistenceContext
	private EntityManager manager;
	@Autowired
	private Set<EventoCompraSucesso> eventos;
	
	@PostMapping(value = "/retorno-pagseguro/{idCompra}")
	@Transactional
	public String processamentoPagSeguro(@PathVariable Long idCompra, @Valid @RequestBody RetornoPagSeguroRequest request) {
		return processa(idCompra, request);
	}
	
	@PostMapping(value = "/retorno-paypal/{idCompra}")
	@Transactional
	public String processamentoPaypal(@PathVariable Long idCompra, @Valid @RequestBody RetornoPaypalRequest request) {
		return processa(idCompra, request);
	}
	
	private String processa(Long idCompra, RetornoGatewayPagamento request) {
		Compra compra = manager.find(Compra.class, idCompra);
		compra.adicionaTransacao(request);
		manager.merge(compra);
		
		if(compra.processadaComSucesso()) {
			
			eventos.stream().forEach(e -> e.executa(compra));
			
		}
		
		return request.toString();
	}
	
}
