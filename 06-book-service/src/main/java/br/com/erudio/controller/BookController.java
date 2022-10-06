package br.com.erudio.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import br.com.erudio.model.Book;
import br.com.erudio.proxy.CambioProxy;
import br.com.erudio.repository.BookRepository;
import br.com.erudio.response.Cambio;

@RestController
@RequestMapping("book-service")
public class BookController {
	
	@Autowired
	private Environment env; 
	
	@Autowired
	private BookRepository repository;
	@Autowired
	private CambioProxy proxy;
	
	@GetMapping(value = "/{id}/{currency}")
	public Book findBook(
			@PathVariable("id") Long id,
			@PathVariable("currency") String currency
			) {
		
		var port = env.getProperty("local.server.port");
		
		var book = repository.findById(id).get();
		if(book == null) {
			throw new RuntimeException("Book unsupported");
		}
		
		//Cambio cambio = this.getCambio(book.getPrice(), "USD", currency);
		Cambio cambio = proxy.getCambio(book.getPrice(), "USD", currency);
		book.setPrice(cambio.getConversionValue());
		book.setEnvironment(port + " = BOOK PORT  |  " + cambio.getEnvironment()+ " = CAMBIO PORT");
		
		return book;
	}
	
	
	//não recomendado
	public Cambio getCambio(Double amount, String from, String to) {
		
		HashMap<String, String> params = new HashMap<>();
		params.put("amount", amount.toString());
		params.put("from", from);
		params.put("to", to);
		
		var response = new RestTemplate()
				.getForEntity("http://localhost:8001/cambio-service/{amount}/{from}/{to}",
				Cambio.class,
				params);
				
		return response.getBody();
		
	}

}
