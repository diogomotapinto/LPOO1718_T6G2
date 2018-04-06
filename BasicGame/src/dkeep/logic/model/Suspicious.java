package dkeep.logic.model;

import java.io.Serializable;

import utilities.Utilities;

public final class Suspicious extends Guard implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7417454072218940051L;
	private boolean direction;

	/**
	 * Class constructor
	 * 
	 * @param route
	 *            of the Suspicious Guard
	 */
	public Suspicious(int[][] route) {
		super(route);
		direction = true;
	}

	/**
	 * Makes Suspicious Guard move to the next position in the route
	 */
	public final void moveToNextPosition() {
		if (direction) {
			index++;
			if (index == route.length) {
				index = 0;
			}
		} else {
			index--;
			if (index == -1) {
				index = route.length - 1;
			}
		}
		this.setPosition(new Position(route[index][0], route[index][1]));
		direction = (Utilities.generateRandomNumber(0, 1) == 0) ? false : true;
	}

	@Override
	public String toString() {
		return "Guard [tipo= " + getClass().getSimpleName() + "]";
	}
}
