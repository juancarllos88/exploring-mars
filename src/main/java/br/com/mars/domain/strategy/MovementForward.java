package br.com.mars.domain.strategy;

import org.springframework.stereotype.Component;

import br.com.mars.domain.model.Probe;
import br.com.mars.domain.shared.Movement;
import br.com.mars.domain.shared.Position;

@Component
public class MovementForward extends MovementStrategy {

	public MovementForward() {
		super(Movement.FORWARD);
	}

	@Override
	public void doMovement(Probe probe) {
		probe.setDirection(probe.getDirection().moveForward());
		Position changePosition = probe.getPosition().changePosition(probe.getDirection());

//		if (!probe.getField().isAvailableSpace(newPositionX, newPositionY)) {
//			Position newPosition = turnLeft(probe);
//			newPositionX = newPosition.getX();
//			newPositionY = newPosition.getY();
//		}
//		probe.setPosition(changePosition);
	}

}
