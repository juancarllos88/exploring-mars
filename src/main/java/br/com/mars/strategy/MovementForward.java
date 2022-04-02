package br.com.mars.strategy;

import org.springframework.stereotype.Component;

import br.com.mars.domain.Probe;
import br.com.mars.enums.Movement;

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
