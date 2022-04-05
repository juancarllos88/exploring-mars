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

	/**
	 * As the value 0 is considered, it is by adding + 1 to the field limit
	 *
	 * @param field field to save
	 * @return Field
	 */
	@Override
	public Field save(Field field) {
		field.setAxisX(field.getAxisX() + 1);
		field.setAxisY(field.getAxisY()+ 1);
		return super.save(field);
	}

}
