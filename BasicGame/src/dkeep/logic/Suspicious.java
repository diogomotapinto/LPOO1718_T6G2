package dkeep.logic;

public class Suspicious extends Guard {

	private boolean direction;

	public Suspicious(int xPos, int yPos) {
		super(xPos, yPos);
		direction = true;
	}

	public void moveToNextPosition() {
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
		this.xPosition = route[index][0];
		this.yPosition = route[index][1];
		direction = Utilities.generateRandom();
	}

}
