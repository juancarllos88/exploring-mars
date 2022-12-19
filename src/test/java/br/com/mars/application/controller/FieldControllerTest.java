package br.com.mars.application.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.mars.domain.dto.FieldDto;

@SpringBootTest
@AutoConfigureMockMvc
public class FieldControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void createField() throws Exception {
		FieldDto fieldDto = FieldDto.builder().axisX(6).axisY(6).build();
		mockMvc.perform(post("/fields")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(fieldDto)))
		        .andExpect(status().isCreated());
	}

}
