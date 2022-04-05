package br.com.mars.domain.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mars.domain.model.Field;
import br.com.mars.domain.services.FieldService;
import br.com.mars.infrastructure.repositories.FieldRepository;

@Service
public class FieldServiceImpl extends BaseServiceImpl<Field> implements FieldService {

	@Autowired
	private FieldRepository fieldRepository;

	@Override
	protected FieldRepository getRepository() {
		return fieldRepository;
	}

	@Override
	public Field save(Field entity) {
		entity.setAxisX(entity.getAxisX() + 1);
		entity.setAxisY(entity.getAxisY()+ 1);
		return super.save(entity);
	}

}
