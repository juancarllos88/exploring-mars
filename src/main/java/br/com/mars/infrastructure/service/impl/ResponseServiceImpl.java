package br.com.mars.infrastructure.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.mars.infrastructure.service.ResponseService;

@Service
public class ResponseServiceImpl implements ResponseService{
	
	public <T> ResponseEntity<T> create(T data) {
		return ResponseEntity.status(HttpStatus.CREATED).body(data);
	}
	
	public <T> ResponseEntity<T> ok(T data) {
		return ResponseEntity.status(HttpStatus.OK).body(data);
	}
	
	public <T> ResponseEntity<T> notFound(T mensagem) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagem);
	}

}
