package br.com.mars.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mars.domain.Field;
import br.com.mars.repositories.FieldRepository;
import br.com.mars.service.FieldService;

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
