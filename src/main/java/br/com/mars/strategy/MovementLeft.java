package br.com.mars.strategy;

import org.springframework.stereotype.Component;

import br.com.mars.domain.Probe;
import br.com.mars.enums.Movement;

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
