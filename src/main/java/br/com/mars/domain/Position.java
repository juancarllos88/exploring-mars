package br.com.mars.domain;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Position {

	private Integer x;

	private Integer y;

	/**
	 * Moves the probe forward one space relative to its direction if space is
	 * available
	 *
	 * @param probe probe under analysis
	 * @return Position
	 */
	public Position changePosition(Probe probe) {

		int newPositionX = this.x;
		int newPositionY = this.y;

		switch (probe.getDirection()) {
		case NORTH:
			newPositionY += 1;
			break;
		case EAST:
			newPositionX += 1;
			break;
		case SOUTH:
			newPositionY -= 1;
			break;
		case WEST:
			newPositionX -= 1;
			break;
		default:
			throw new IllegalArgumentException();
		}

		if (!probe.getField().isAvailableSpace(newPositionX, newPositionY)) {
			Position newPosition = turnLeft(probe);
			newPositionX = newPosition.getX();
			newPositionY = newPosition.getY();
		}

		return new Position(newPositionX, newPositionY);
	}

	/**
	 * if there is no space available, turn left until you find a space
	 *
	 * @param probe probe under analysis
	 * @return Position
	 */
	private Position turnLeft(Probe probe) {
		probe.setDirection(probe.getDirection().turnLeft());
		return changePosition(probe);
	}

}