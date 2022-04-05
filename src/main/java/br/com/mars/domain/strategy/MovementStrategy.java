package br.com.mars.domain.strategy;

import br.com.mars.domain.model.Probe;
import br.com.mars.domain.shared.Movement;

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
