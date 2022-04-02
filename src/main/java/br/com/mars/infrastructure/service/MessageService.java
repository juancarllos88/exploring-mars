package br.com.mars.infrastructure.service;

import org.springframework.validation.FieldError;

public interface MessageService {

	String getMessage(String key);

	String getMessage(FieldError fieldError);

}
