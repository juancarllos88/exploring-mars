package br.com.mars.strategy;

import org.springframework.stereotype.Component;

import br.com.mars.domain.Probe;
import br.com.mars.enums.Movement;

@Component
public class MovementRight extends MovementStrategy {

	public MovementRight() {
		super(Movement.RIGHT);
	}

	@Override
	public void doMovement(Probe probe) {
		probe.setDirection(probe.getDirection().turnRight());
	}

}
