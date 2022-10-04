package com.example.demo.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Cambio;
import com.example.demo.repository.CambioRepository;

@RestController
@RequestMapping("cambio-service")
public class CambioController {

	@Autowired
	private Environment env; 
	
	@Autowired
	private CambioRepository repository; 
	
	@GetMapping(value="/{amount}/{from}/{to}")
	public Cambio getCambio(
			@PathVariable("amount") BigDecimal amount,
			@PathVariable("from") String from,
			@PathVariable("to") String to
			) {
		
		var port = env.getProperty("local.server.port");
		
		var cambio = repository.findByFromAndTo(from, to);
		if(cambio == null) {
			throw new RuntimeException("Corrency unsupported");
		}
		
		BigDecimal conversionFactory =  cambio.getConversionFactor();
		BigDecimal convertedValue = conversionFactory.multiply(amount);
		
		cambio.setConversionValue(convertedValue.setScale(2,RoundingMode.CEILING));
		
		
		cambio.setEnvironment(port);
		
		
		
		return cambio;
	}
}
