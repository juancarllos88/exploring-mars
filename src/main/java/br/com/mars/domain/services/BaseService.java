package br.com.mars.domain.services;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseService<T> {

	T save(T entity);

	T findOne(UUID id);

	void delete(UUID id);

	T update(UUID id, Object dto);

	Page<T> findAll(Pageable pageable);

}
