package br.com.mars.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mars.domain.Field;
import br.com.mars.domain.Probe;
import br.com.mars.dto.MovementProbeDto;
import br.com.mars.enums.Movement;
import br.com.mars.infrastructure.exceptions.SpaceAlreadyOccupiedException;
import br.com.mars.repositories.ProbeRepository;
import br.com.mars.service.FieldService;
import br.com.mars.service.ProbeService;
import br.com.mars.strategy.MovementContext;

@Service
public class ProbeServiceImpl extends BaseServiceImpl<Probe> implements ProbeService {

	@Autowired
	private ProbeRepository probeRepository;

	@Autowired
	private FieldService fieldService;

	@Autowired
	private MovementContext movementContext;

	@Override
	protected ProbeRepository getRepository() {
		return probeRepository;
	}

	@Override
	public Probe move(UUID probeId, MovementProbeDto movementProbeDto) {
		Probe probe = findOne(probeId);
		String instructions = movementProbeDto.getMovement().toUpperCase();
		for (int i = 0; i < instructions.length(); i++) {
			Movement movement = Movement.getMovement(instructions.charAt(i)).orElseThrow(IllegalArgumentException::new);
			movementContext.defineContext(movement);
			movementContext.doMovement(probe);
		}

		super.save(probe);
		return probe;

	}

	@Override
	public Probe save(Probe probe) {
		Field field = fieldService.findOne(probe.getField().getId());
		if (!field.isAvailableSpace(probe.getPosition().getX(), probe.getPosition().getY())) {
			throw new SpaceAlreadyOccupiedException();
		}
		probe.setField(field);
		return super.save(probe);
	}

}
