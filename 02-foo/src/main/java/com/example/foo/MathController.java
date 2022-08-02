package com.example.foo;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.foo.exception.UnsuportedMathException;

@RestController
public class MathController {

		
	@RequestMapping(value="/sum/{numberOne}/{numberTwo}" , method = RequestMethod.GET)
	public Double sum(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value ="numberTwo") String numberTwo) throws Exception {
		
		if(!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsuportedMathException("Preencha um valor numérico");
		}
		
		Double sum = convertToDouble(numberOne) + convertToDouble(numberTwo);
		
		return sum;
	}
	
	@RequestMapping(value="/sub/{numberOne}/{numberTwo}" , method = RequestMethod.GET)
	public Double sub(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value ="numberTwo") String numberTwo) throws Exception {
		
		if(!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsuportedMathException("Preencha um valor numérico");
		}
		
		Double sum = convertToDouble(numberOne) - convertToDouble(numberTwo);
		
		return sum;
	}
	
	@RequestMapping(value="/div/{numberOne}/{numberTwo}" , method = RequestMethod.GET)
	public Double div(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value ="numberTwo") String numberTwo) throws Exception {
		
		if(!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsuportedMathException("Preencha um valor numérico");
		}
		
		Double sum = convertToDouble(numberOne) / convertToDouble(numberTwo);
		
		return sum;
	}
	
	@RequestMapping(value="/mult/{numberOne}/{numberTwo}" , method = RequestMethod.GET)
	public Double mult(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value ="numberTwo") String numberTwo) throws Exception {
		
		if(!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsuportedMathException("Preencha um valor numérico");
		}
		
		Double sum = convertToDouble(numberOne) * convertToDouble(numberTwo);
		
		return sum;
	}
	
	@RequestMapping(value="/raiz/{numberOne}" , method = RequestMethod.GET)
	public Double raiz(@PathVariable(value = "numberOne") String numberOne) throws Exception {
		
		if(!isNumeric(numberOne)) {
			throw new UnsuportedMathException("Preencha um valor numérico");
		}
		
		Double sum = Math.sqrt(convertToDouble(numberOne));
		
		return sum;
	}
	
	@RequestMapping(value="/media/{numberOne}/{numberTwo}" , method = RequestMethod.GET)
	public Double media(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value ="numberTwo") String numberTwo) throws Exception {
		
		if(!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsuportedMathException("Preencha um valor numérico");
		}
		
		Double sum = (convertToDouble(numberOne) + convertToDouble(numberTwo)) / 2;
		
		return sum;
	}
	
	private boolean isNumeric(String strNumber) {
		// TODO Auto-generated method stub
		if(strNumber == null) return false;
		
		String number = strNumber.replace(",", ".");
		return number.matches("[+-]?[0-9]*\\.?[0-9]+");
		
	}
	private Double convertToDouble(String strNumber) {
		if(strNumber == null) return 0D;
		
		String number = strNumber.replace(",", ".");
		if(isNumeric(number)) return Double.parseDouble(number);
		
		return 0D;
	}
	
	
	
	
}
