package dkeep.logic;

public final class Rookie extends Guard {

	public Rookie(int xPos, int yPos, int[][] route) {
		super(xPos, yPos, route);
	}

	public final void moveToNextPosition() {
		index++;
		if (index == route.length) {
			index = 0;
		}
		setXPosition(route[index][0]);
		setYPosition(route[index][1]);

	}

	@Override
	public String toString() {
		return "Guard [tipo= " + getClass().getSimpleName() + "]";
	}
}
