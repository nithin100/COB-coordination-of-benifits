package com.rsrit.cob.exceptionhandlers;

import java.io.FileNotFoundException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.rsrit.cob.rest.SurveyController;

@ControllerAdvice(basePackageClasses = SurveyController.class)
public class GlobalExceptionHandler {

	public GlobalExceptionHandler() {
		System.out.println("Added exception handler bean to context");
	}

	@ExceptionHandler(FileNotFoundException.class)
	public HttpEntity<String> handleException(final FileNotFoundException exeception) {
		return new ResponseEntity<>("File not found", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
