package dkeep.logic.model;

import utilities.Utilities;

public final class Drunken extends Guard {

	private boolean sleep;
	/**
	 * Class constructor
	 * @param route of the drunken guard
	 */
	public Drunken(int[][] route) {
		super(route);
		sleep = false;
	}

	/**
	 * Makes Drunken guard move to the next position
	 */
	public final void moveToNextPosition() {
		if (!sleep) {
			index++;
			if (index == route.length) {
				index = 0;
			}

			this.setPosition(new Position(route[index][0], route[index][1]));
		}
		sleep = (Utilities.generateRandomNumber(0, 1) == 0) ? false : true;
	}

	/**
	 * 
	 * 
	 */
	@Override
	public String toString() {
		return "Guard [tipo= " + getClass().getSimpleName() + "]";
	}

}
