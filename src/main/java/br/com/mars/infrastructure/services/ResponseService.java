package br.com.mars.infrastructure.services;

import org.springframework.http.ResponseEntity;

public interface ResponseService {

	<T> ResponseEntity<T> create(T data);

	<T> ResponseEntity<T> ok(T data);

	<T> ResponseEntity<T> notFound(T mensagem);

}
