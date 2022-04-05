package br.com.mars.domain.strategy;

import org.springframework.stereotype.Component;

import br.com.mars.domain.model.Probe;
import br.com.mars.domain.shared.Movement;

@Component
public class MovementLeft extends MovementStrategy {

	public MovementLeft() {
		super(Movement.LEFT);
	}

	@Override
	public void doMovement(Probe probe) {
		probe.setDirection(probe.getDirection().turnLeft());
	}

}
