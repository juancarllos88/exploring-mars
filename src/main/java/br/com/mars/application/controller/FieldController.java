package br.com.mars.application.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mars.domain.dto.FieldDto;
import br.com.mars.domain.dto.FieldResponseDto;
import br.com.mars.domain.model.Field;
import br.com.mars.domain.services.FieldService;
import br.com.mars.infrastructure.services.ConverterService;
import br.com.mars.infrastructure.services.ResponseService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/fields")
@CrossOrigin(origins = "*", maxAge = 3600)
public class FieldController {

	@Autowired
	private FieldService fieldService;

	@Autowired
	private ConverterService converterService;

	@Autowired
	private ResponseService responseService;

	@ApiOperation(value = "Create a bounded space")
	@PostMapping
	public ResponseEntity<FieldResponseDto> save(@RequestBody @Valid FieldDto fieldDto) {
		Field field = converterService.convert(fieldDto, Field.class);
		FieldResponseDto fieldResponseDto = converterService.convert(fieldService.save(field), FieldResponseDto.class);
		return responseService.create(fieldResponseDto);
	}
	
	@ApiOperation(value = "List all limited spaces")
	@GetMapping
	public ResponseEntity<Page<FieldResponseDto>> findAll(
			@PageableDefault(page = 0, size = 100, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
		Page<Field> fields = fieldService.findAll(pageable);
		return responseService.ok(converterService.convert(fields, FieldResponseDto.class));
	}
	
	@ApiOperation(value = "Show only limited space")
	@GetMapping("/{fieldId}")
	public ResponseEntity<FieldResponseDto> findOne(@PathVariable UUID fieldId) {
		return responseService.ok(converterService.convert(fieldService.findOne(fieldId), FieldResponseDto.class));
	}

}
