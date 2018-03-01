package dkeep.logic;

import utilities.Utilities;

public final class Drunken extends Guard {

	private boolean sleep;

	public Drunken(int xPos, int yPos, int[][] route) {
		super(xPos, yPos, route);
		sleep = false;
	}

	public final void moveToNextPosition() {
		if (!sleep) {
			index++;
			if (index == route.length) {
				index = 0;
			}

			setXPosition(route[index][0]);
			setYPosition(route[index][1]);
		}
		sleep = (Utilities.generateRandomNumber(0, 1) == 0) ? false : true;
	}

	@Override
	public String toString() {
		return "Guard [tipo= " + getClass().getSimpleName() + "]";
	}

}
