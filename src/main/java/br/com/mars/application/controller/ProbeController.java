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

import br.com.mars.domain.dto.MovementProbeDto;
import br.com.mars.domain.dto.ProbeDto;
import br.com.mars.domain.dto.ProbeResponseDto;
import br.com.mars.domain.model.Probe;
import br.com.mars.domain.services.ProbeService;
import br.com.mars.infrastructure.services.ConverterService;
import br.com.mars.infrastructure.services.ResponseService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/probes")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProbeController {

	@Autowired
	private ProbeService probeService;

	@Autowired
	private ConverterService converterService;

	@Autowired
	private ResponseService responseService;

	@ApiOperation(value = "Moves the probe through a set of instructions")
	@PostMapping("/{probeId}/moves")
	public ResponseEntity<ProbeResponseDto> move(@PathVariable UUID probeId, @RequestBody @Valid MovementProbeDto probeDto) {
		ProbeResponseDto probeResponseDto = converterService.convert(probeService.move(probeId, probeDto), ProbeResponseDto.class);
		return responseService.create(probeResponseDto);
	}

	@ApiOperation(value = "Create a probe")
	@PostMapping
	public ResponseEntity<ProbeResponseDto> save(@RequestBody @Valid ProbeDto probeDto) {
		Probe probe = converterService.convert(probeDto, Probe.class);
		ProbeResponseDto probeResponseDto = converterService.convert(probeService.save(probe), ProbeResponseDto.class);
		return responseService.create(probeResponseDto);
	}
	
	@ApiOperation(value = "Displays only probe")
	@GetMapping("/{probeId}")
	public ResponseEntity<ProbeResponseDto> findOne(@PathVariable UUID probeId) {
		return responseService.ok(converterService.convert(probeService.findOne(probeId), ProbeResponseDto.class));
	}
	

	@ApiOperation(value = "List all probes")
	@GetMapping
	public ResponseEntity<Page<ProbeResponseDto>> findAll(
			@PageableDefault(page = 0, size = 100, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
		Page<Probe> probes = probeService.findAll(pageable);
		return responseService.ok(converterService.convert(probes, ProbeResponseDto.class));
	}

}
