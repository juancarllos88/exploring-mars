package br.com.mars.infrastructure.services;

import org.springframework.validation.FieldError;

public interface MessageService {

	String getMessage(String key);

	String getMessage(FieldError fieldError);

}
