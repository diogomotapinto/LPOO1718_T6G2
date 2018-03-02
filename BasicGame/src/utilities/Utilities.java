package utilities;

import java.util.Random;

import dkeep.logic.Position;

public class Utilities {
	private static final byte MOVE_UP = 1;
	private static final byte MOVE_DOWN = 2;
	private static final byte MOVE_RIGHT = 3;
	private static final byte MOVE_LEFT = 4;

	private Utilities() {

	}

	public static final int[] getAdjacentPosition(int xPosition, int yPosition) {
		int[] position = { xPosition, yPosition };
		Random random = new Random();
		byte lowerBound = 1;
		byte upperBound = 4;
		int result = random.nextInt((upperBound - lowerBound) + 1) + lowerBound;

		switch (result) {
		case MOVE_UP:
			position[0]--;
			break;
		case MOVE_DOWN:
			position[0]++;
			break;
		case MOVE_RIGHT:
			position[1]++;
			break;
		case MOVE_LEFT:
			position[1]--;
			break;
		default:
			break;
		}
		return position;

	}

	public static final int generateRandomNumber(int lowerBound, int upperBound) {
		Random random = new Random();
		int lower_Bound = lowerBound;
		int upper_Bound = upperBound;

		return random.nextInt((upper_Bound - lower_Bound) + 1) + lower_Bound;
	}

	public static final boolean checkAdjacentCollision(Position posOne, Position posTwo) {
		// check if there is something down
		if (posOne.getXPosition() + 1 == posTwo.getXPosition() && posOne.getYPosition() == posTwo.getYPosition()) {
			return true;
		}
		// check if there is something up
		if (posOne.getXPosition() - 1 == posTwo.getXPosition() && posOne.getYPosition() == posTwo.getYPosition()) {
			return true;
		}
		// check if there is something on the left
		if (posOne.getXPosition() == posTwo.getXPosition() && posOne.getYPosition() - 1 == posTwo.getYPosition()) {
			return true;
		}
		// check if there is something on the right
		if (posOne.getXPosition() == posTwo.getXPosition() && posOne.getYPosition() + 1 == posTwo.getYPosition()) {
			return true;
		}

		return false;
	}
}
