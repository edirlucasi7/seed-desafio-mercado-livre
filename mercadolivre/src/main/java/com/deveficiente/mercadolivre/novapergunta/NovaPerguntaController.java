package com.deveficiente.mercadolivre.novapergunta;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.deveficiente.mercadolivre.fechacompra.EmailSenderService;
import com.deveficiente.mercadolivre.novoproduto.Produto;
import com.deveficiente.mercadolivre.novousuario.Usuario;
import com.deveficiente.mercadolivre.novousuario.UsuarioRepository;

@RestController
public class NovaPerguntaController {
	
	@PersistenceContext
	private EntityManager manager;
	@Autowired
	private UsuarioRepository repository;
	@Autowired
	private EmailSenderService emailSenderService;
	
	@PostMapping("/perguntas/{id}/produtos")
	@Transactional
	public String cria(@PathVariable("id") Long id, @RequestBody @Valid NovaPerguntaRequest request) {
		Usuario fakeUsuarioLogado = repository.findByEmail("icety@gmail");
		Produto produto = manager.find(Produto.class, id);
		
		Pergunta pergunta = request.toModel(produto, fakeUsuarioLogado);
		manager.persist(pergunta);
		
//		String emailDonoProduto = produto.getDono().getEmail();
//		emailSenderService.novaPergunta(emailDonoProduto, pergunta.getTitulo(), "NOVA PERGUNTA");
		
		return pergunta.toString();
	}
	
}
