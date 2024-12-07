package com.his.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class PlanExceptionHandler {
	
	// PLANS Custom Exception
	@ExceptionHandler(value=PlanException.class)
	public ResponseEntity<PlanApiError> handleEdException(PlanException e) {
		PlanApiError apiError=new PlanApiError("PLANS-API-01", e.getMessage(), new Date());
		return new ResponseEntity<PlanApiError>(apiError,HttpStatus.BAD_REQUEST);
	};
	
	@ExceptionHandler(value=Exception.class)
	public ResponseEntity<PlanApiError> handleException(Exception e) {
		PlanApiError apiError=new PlanApiError("PLANS-API-01", e.getMessage(), new Date());
		return new ResponseEntity<PlanApiError>(apiError,HttpStatus.BAD_REQUEST);
	};

}
