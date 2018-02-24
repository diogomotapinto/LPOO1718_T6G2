package dkeep.logic;

import java.util.Random;

public class Utilities {
	private static final byte lowerBound = 1;
	private static final byte upperBound = 4;
	private static final byte MOVE_UP = 1;
	private static final byte MOVE_DOWN = 2;
	private static final byte MOVE_RIGHT = 3;
	private static final byte MOVE_LEFT = 4;

	private Utilities() {

	}

	public static final int[] getAdjacentPosition(int xPosition, int yPosition) {
		int[] position = { xPosition, yPosition };
		Random random = new Random();
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

	public static final boolean generateRandom() {
		Random random = new Random();
		int lower_Bound = 0;
		int upper_Bound = 1;

		return random.nextInt((upper_Bound - lower_Bound) + 1) + lower_Bound == 1 ? true : false;
	}
	
	public static final int generateRandomNumber(int lowerBound, int upperBound) {
		Random random = new Random();
		int lower_Bound = lowerBound;
		int upper_Bound = upperBound;

		return random.nextInt((upper_Bound - lower_Bound) + 1) + lower_Bound;
	}

}
