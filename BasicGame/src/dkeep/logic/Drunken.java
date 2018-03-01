package dkeep.logic;

import utilities.Utilities;

public class Drunken extends Guard {

	private boolean sleep;

	public Drunken(int xPos, int yPos) {
		super(xPos, yPos);
		sleep = false;
	}

	public void moveToNextPosition() {
		if (!sleep) {
			index++;
			if (index == route.length) {
				index = 0;
			}

			this.xPosition = route[index][0];
			this.yPosition = route[index][1];
		}
		sleep = (Utilities.generateRandomNumber(0, 1) == 0) ? false : true;
	}

}
