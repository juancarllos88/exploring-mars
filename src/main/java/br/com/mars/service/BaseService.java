package br.com.mars.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface BaseService<T> {

	T save(T entity);

	T findOne(UUID id);

	void delete(UUID id);

	T update(UUID id, Object dto);

	Page<T> findAll(Pageable pageable);

}
