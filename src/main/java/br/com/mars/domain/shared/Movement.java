package br.com.mars.domain.shared;

import java.util.Optional;

public enum Movement {
	FORWARD('M'), 
	LEFT('L'), 
	RIGHT('R');

	private final char code;

	Movement(char code) {
		this.code = code;
	}

	public static Optional<Movement> getMovement(char code) {
		for (Movement movement : Movement.values()) {
			if (movement.code == code) {
				return Optional.of(movement);
			}
		}
		return Optional.empty();
	}

}