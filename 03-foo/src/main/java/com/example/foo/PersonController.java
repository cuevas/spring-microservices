package com.example.foo;


import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.foo.exception.UnsuportedMathException;
import com.example.foo.model.Person;
import com.example.foo.services.PersonService;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;
import yahoofinance.histquotes2.IntervalMapper;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonService services;
	
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Person create(@RequestBody Person person){
				
		return services.create(person);
	}
	
	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Person update(@RequestBody Person person){
				
		return services.update(person);
	}
	
	@RequestMapping(value="/{id}" , method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@PathVariable(value = "id") String id){
		
		services.delete(id);
	}
	
	@RequestMapping(value="/{id}" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Person findById(@PathVariable(value = "id") String id){
		
		
		return services.findById(id);
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Person> findAll(){
		return services.findAll();
	}
	
	@RequestMapping(value="stock/{id}" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<HistoricalQuote> stock(@PathVariable(value = "id") String id){
		
		Stock stock;
		String result = "";
		List<HistoricalQuote> precos = new ArrayList<HistoricalQuote>();
		try {
			stock = YahooFinance.get(id);
		

			BigDecimal price = stock.getQuote().getPrice();
			BigDecimal change = stock.getQuote().getChangeInPercent();
			BigDecimal peg = stock.getStats().getPeg();
			BigDecimal dividend = stock.getDividend().getAnnualYieldPercent();
	
			stock.print();
			Interval i = Interval.MONTHLY;
			//result = stock.getName() + " " + price.toString() + " "+stock.getHistory(Interval.MONTHLY).toString();
			precos = stock.getHistory(Interval.MONTHLY);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return precos;
	}
	
	
	
}
