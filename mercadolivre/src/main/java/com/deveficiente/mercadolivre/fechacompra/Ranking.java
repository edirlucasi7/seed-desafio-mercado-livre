package com.deveficiente.mercadolivre.fechacompra;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Ranking implements EventoCompraSucesso {

	@Override
	public void executa(Compra compra) {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> request = Map.of("idCompra", compra.getId(), "idComprador", compra.getComprador().getId());
		
		restTemplate.postForEntity("http://localhost:8080/ranking", request, String.class);
		
	}
	
}
