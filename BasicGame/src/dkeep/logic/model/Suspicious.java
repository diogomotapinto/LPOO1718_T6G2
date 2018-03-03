package dkeep.logic.model;

import utilities.Utilities;

public final class Suspicious extends Guard {

	private boolean direction;

	public Suspicious(int xPos, int yPos, int[][] route) {
		super(xPos, yPos, route);
		direction = true;
	}

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
