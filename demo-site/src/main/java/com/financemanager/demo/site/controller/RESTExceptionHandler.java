package com.financemanager.demo.site.controller;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.financemanager.demo.site.exception.APIException;
import com.financemanager.demo.site.exception.CategoryNotFoundException;

@ControllerAdvice
public class RESTExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = {IllegalStateException.class})
	protected ResponseEntity<Object> handleConflict(
			RuntimeException ex, WebRequest request) {
		
		HttpStatus httpStatus = HttpStatus.NOT_FOUND;
		APIException apiException = new APIException(
				ex.getMessage(),
				request.getContextPath(),
				httpStatus,
				ZonedDateTime.now(ZoneId.systemDefault())
				);		
		        return new ResponseEntity<>(apiException, httpStatus);
		    }
}
