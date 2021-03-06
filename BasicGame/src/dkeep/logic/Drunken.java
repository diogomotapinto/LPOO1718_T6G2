package dkeep.logic;

import java.io.Serializable;

import utilities.Utilities;

final class Drunken extends Guard  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1805847310392460226L;
	/**
	 * 
	 */
	private boolean sleep;

	/**
	 * Class constructor
	 * 
	 * @param route
	 *            of the drunken guard
	 */
	Drunken(int[][] route) {
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
