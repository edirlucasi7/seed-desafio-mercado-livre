package com.deveficiente.mercadolivre.fechacompra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

	@Autowired
	private JavaMailSender emailSender;
	
	public void fechaCompra(String email, String corpo, String sujeito) {
		SimpleMailMessage message = corpo(email, corpo, sujeito);
		
		emailSender.send(message);
	}

	public void novaPergunta(String email, String corpo, String sujeito) {
		SimpleMailMessage message = corpo(email, corpo, sujeito);
		
		emailSender.send(message);
		
	}

	private SimpleMailMessage corpo(String email, String corpo, String sujeito) {
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setFrom("icetylucas@gmail.com");
		message.setTo(email);
		message.setText(corpo);
		message.setSubject(sujeito);
		
		return message;
	}
	
	
}
