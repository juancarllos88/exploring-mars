package br.com.mars.domain.services.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mars.domain.dto.MovementProbeDto;
import br.com.mars.domain.model.Field;
import br.com.mars.domain.model.Probe;
import br.com.mars.domain.services.FieldService;
import br.com.mars.domain.services.ProbeService;
import br.com.mars.domain.shared.Movement;
import br.com.mars.domain.strategy.MovementContext;
import br.com.mars.infrastructure.exceptions.InstructionInvalidException;
import br.com.mars.infrastructure.exceptions.SpaceAlreadyOccupiedException;
import br.com.mars.infrastructure.repositories.ProbeRepository;

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

	/**
	 * Moves a probe through a set of instructions.
	 * If it has a wrong move instruction, an exception is thrown.
	 *
	 * @param probeId identification of probe
	 * @param movementProbeDto movement instruction
	 * @throws InstructionInvalidException
	 * @return Probe
	 */
	@Override
	@Transactional
	public Probe move(UUID probeId, MovementProbeDto movementProbeDto) {
		Probe probe = findOne(probeId);
		String instructions = movementProbeDto.getMovement().toUpperCase();
		for (int i = 0; i < instructions.length(); i++) {
			Movement movement = Movement.getMovement(instructions.charAt(i)).orElseThrow(InstructionInvalidException::new);
			movementContext.defineContext(movement);
			movementContext.doMovement(probe);
		}

		super.save(probe);
		return probe;

	}

	/**
	 * Checks if there is space available in the field before saving the probe. 
	 * If there is a probe in place, an exception is thrown.
	 *
	 * @param probe probe to save
	 * @throws SpaceAlreadyOccupiedException
	 * @return Probe
	 */
	@Override
	@Transactional
	public Probe save(Probe probe) {
		Field field = fieldService.findOne(probe.getField().getId());
		if (!field.isAvailableSpace(probe.getPosition().getX(), probe.getPosition().getY())) {
			throw new SpaceAlreadyOccupiedException();
		}
		probe.setField(field);
		return super.save(probe);
	}

}
