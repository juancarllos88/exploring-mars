package br.com.mars;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import br.com.mars.domain.model.Field;
import br.com.mars.domain.services.FieldService;
import br.com.mars.infrastructure.exceptions.EntityModelNotFoundException;
import br.com.mars.infrastructure.repositories.FieldRepository;
import br.com.mars.infrastructure.repositories.ProbeRepository;

@ActiveProfiles("test")
@SpringBootTest
public class FieldServiceTest {

	@Autowired
	private FieldService fieldService;

	@Autowired
	private FieldRepository fieldRepository;

	@Autowired
	private ProbeRepository probeRepository;

	@BeforeEach
	public void tearDown() {
		probeRepository.deleteAll();
		fieldRepository.deleteAll();
	}

	@Test
	public void findOneField() throws Exception {
		Field field = generateFieldsAndSave(1).get(0);
		Field fieldDataBase = fieldService.findOne(field.getId());
		Assertions.assertEquals(fieldDataBase.getId(), field.getId());
	}
	
	@Test
	public void fieldNotFound() throws Exception {
		try {
			fieldService.findOne(UUID.fromString("6309f0d3-5357-4201-b252-77889110fe50"));
		} catch (RuntimeException e) {
			Assertions.assertTrue(e.getClass().equals(EntityModelNotFoundException.class));
		}
	}

	@Test
	public void createField() throws Exception {
		Field field = generateField();
		Field fieldSaved = fieldService.save(field);
		Assertions.assertNotNull(fieldSaved.getId());

	}

	@Test
	public void findAllFieldsByPagination() throws Exception {
		generateFieldsAndSave(20);
		Page<Field> fields = fieldService.findAll(PageRequest.of(0, 20));
		Assertions.assertEquals(fields.getContent().size(), 20);
		Assertions.assertEquals(fields.getNumberOfElements(), 20);
		Assertions.assertEquals(fields.getTotalElements(), 20);
		Assertions.assertEquals(fields.getTotalPages(), 1);

	}

	private List<Field> generateFieldsAndSave(int total) {
		List<Field> fields = new ArrayList<>();
		for (int i = 0; i < total; i++) {
			fields.add(generateField());
		}
		return fieldRepository.saveAll(fields);
	}

	private Field generateField() {
		int bound = new Random().nextInt(100);
		Field field = new Field();
		field.setAxisX(bound);
		field.setAxisY(bound);
		return field;
	}

}
