package br.com.mars.domain.strategy;

import org.springframework.stereotype.Component;

import br.com.mars.domain.model.Probe;
import br.com.mars.domain.shared.Movement;

@Component
public class MovementForward extends MovementStrategy {

	public MovementForward() {
		super(Movement.FORWARD);
	}

	@Override
	public void doMovement(Probe probe) {
		probe.setDirection(probe.getDirection().moveForward());
		probe.setPosition(probe.getPosition().changePosition(probe));
	}

}
