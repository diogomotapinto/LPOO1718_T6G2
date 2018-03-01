package dkeep.logic;

public class Rookie extends Guard {

	public Rookie(int xPos, int yPos) {
		super(xPos, yPos);
	}

	public void moveToNextPosition() {
		index++;
		if (index == route.length) {
			index = 0;
		}
		setXPosition(route[index][0]);
		setXPosition(route[index][1]);
		
	}
}
