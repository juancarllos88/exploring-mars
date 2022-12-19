package br.com.mars.application.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.mars.domain.dto.FieldDto;
import br.com.mars.domain.dto.FieldResponseDto;
import br.com.mars.domain.model.Field;
import br.com.mars.domain.services.FieldService;
import br.com.mars.infrastructure.services.ConverterService;
import br.com.mars.infrastructure.services.MessageService;
import br.com.mars.infrastructure.services.ResponseService;

//@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@WebMvcTest(FieldController.class)
public class FieldController2Test {
	
	/*
	 * Typical usage of Spring Boot @MockBean
	 * 
	 * As we write a test class annotated with @WebMvcTest (web test slice).
	 */

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private FieldService fieldService;
	
	@MockBean
	private ConverterService converterService;

	@MockBean
	private ResponseService responseService;
	
	@MockBean
	private MessageService messageService;

	@Autowired
	private ObjectMapper objectMapper;
	
	@Mock
	private Field field;
	
	@Mock
	private FieldResponseDto fieldResponseDto;
	

	@Test
	public void createField() throws Exception {
		
		FieldDto fieldDto = FieldDto.builder().axisX(6).axisY(6).build();
		when(converterService.convert(fieldDto, Field.class)).thenReturn(field);
		when(fieldService.save(field)).thenReturn(field);
		when(converterService.convert(field, FieldResponseDto.class)).thenReturn(fieldResponseDto);
		when(responseService.create(fieldResponseDto)).thenReturn(ResponseEntity.status(HttpStatus.CREATED).body(new FieldResponseDto(UUID.randomUUID(),1,1)));
		mockMvc.perform(post("/fields")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(fieldDto)))
		        .andExpect(status().isCreated());
	}

}
