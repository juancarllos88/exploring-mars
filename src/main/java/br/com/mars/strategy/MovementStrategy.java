package br.com.mars.strategy;

import br.com.mars.domain.Probe;
import br.com.mars.enums.Movement;

public abstract class MovementStrategy {

	protected Movement type;

	public MovementStrategy(Movement type) {
		this.type = type;
	}

	public Movement getType() {
		return type;
	}
	
	
	public abstract void doMovement(Probe probe);

}
