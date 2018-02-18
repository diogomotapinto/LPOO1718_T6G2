package game;

import java.util.Random;

public class Ogre extends Character {
	private static final byte MOVE_UP = 1;
	private static final byte MOVE_DOWN = 2;
	private static final byte MOVE_RIGHT = 3;
	private static final byte MOVE_LEFT = 4;
	private static final byte lowerBound = 1;
	private static final byte upperBound = 4;
	private Random random;

	public Ogre(int xPos, int yPos) {
		super(xPos, yPos);
		random = new Random();
	}

	// passar position up and right
	public void moveToNextPosition(String positionUp, String positionRight) {
		int result = random.nextInt((upperBound - lowerBound) + 1) + lowerBound;

		switch (result) {
		case MOVE_UP:
			if (this.xPosition != 1 && positionUp != "k") {
				this.xPosition--;
			}
			break;

		case MOVE_DOWN:
			if (this.xPosition != 7) {
				this.xPosition++;
			}
			break;

		case MOVE_RIGHT:
			if (this.yPosition != 7 && positionRight != "k") {
				this.yPosition++;
			}
			break;

		case MOVE_LEFT:
			if (this.yPosition != 1) {
				this.yPosition--;
			}
			break;
		default:
			break;
		}
	}

}
