package com.example.foo.exception.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.foo.exception.ExceptionResponse;
import com.example.foo.exception.UnsuportedMathException;

@ControllerAdvice
@RestController
public class CustimizedResponseExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request){
		ExceptionResponse eResp = new ExceptionResponse(new Date() , ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<>(eResp, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UnsuportedMathException.class)
	public final ResponseEntity<ExceptionResponse> handleBadRequestExceptions(Exception ex, WebRequest request){
		ExceptionResponse eResp = new ExceptionResponse(new Date() , ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<>(eResp, HttpStatus.BAD_REQUEST);
	}

}
