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
	
	
	
}