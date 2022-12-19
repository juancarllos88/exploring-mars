package br.com.mars.application.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import br.com.mars.domain.model.Field;
import br.com.mars.domain.services.FieldService;
import br.com.mars.domain.services.impl.FieldServiceImpl;
import br.com.mars.infrastructure.repositories.FieldRepository;

@ExtendWith(MockitoExtension.class)
public class FieldServiceTest {
	
	@InjectMocks
	private FieldService fieldService = new FieldServiceImpl();
	
	@Mock
	private FieldRepository fieldRepository;
	
	@MockBean
	private Pageable pageable;
	
	@Test
	public void getAllFields() {
		
	 List<Field> fields = Arrays.asList(Field.builder().id(UUID.randomUUID()).axisX(1).axisY(1).build());
	 Page<Field> page = new PageImpl<>(fields);
	 when(fieldRepository.findAll(pageable)).thenReturn(page);
	 
	 Page<Field> response = fieldService.findAll(pageable);
     
     assertEquals(1, response.getSize());
		
	}

}
