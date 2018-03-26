package utilities;

import java.util.Random;

import dkeep.logic.model.Position;

public final class Utilities {
	private static final byte MOVE_UP = 1;
	private static final byte MOVE_DOWN = 2;
	private static final byte MOVE_RIGHT = 3;
	private static final byte MOVE_LEFT = 4;

	/**
	 * Class constructor
	 */
	private Utilities() {
	}

	/**
	 * Generates an adjacent position.
	 * 
	 * @param xPosition
	 *            position in the x-axis
	 * @param yPosition
	 *            position in the y-axis
	 * @return position generated randomly
	 */
	public static final Position getAdjacentPosition(int xPosition, int yPosition) {
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
		return new Position(position[0], position[1]);

	}

	public static final int generateRandomNumber(int lowerBound, int upperBound) {
		Random random = new Random();
		return random.nextInt((upperBound - lowerBound) + 1) + lowerBound;
	}

	/**
	 * Check if there is a collision between two game characters
	 * 
	 * @param posOne
	 *            position of the game character
	 * @param posTwo
	 *            position of the other game character
	 * @return true if there is collision and false otherwise
	 */
	public static final boolean checkAdjacentCollision(Position posOne, Position posTwo) {
		// check if it is in the same position
		if (posOne.equals(posTwo)) {
			return true;
		}
		// check if there is an Ogre down
		if (posOne.getXPosition() + 1 == posTwo.getXPosition() && posOne.getYPosition() == posTwo.getYPosition()) {
			return true;
		}
		// check if there is an Ogre up
		if (posOne.getXPosition() - 1 == posTwo.getXPosition() && posOne.getYPosition() == posTwo.getYPosition()) {
			return true;
		}
		// check if there is an Ogre on the left
		if (posOne.getXPosition() == posTwo.getXPosition() && posOne.getYPosition() - 1 == posTwo.getYPosition()) {
			return true;
		}
		// check if there is an Ogre on the right
		if (posOne.getXPosition() == posTwo.getXPosition() && posOne.getYPosition() + 1 == posTwo.getYPosition()) {
			return true;
		}

		return false;
	}

	/**
	 * Finds the position of some letter in the array
	 * 
	 * @param map
	 *            used in the game
	 * @param letter
	 *            to be searched for in the array
	 * @return the position of the letter, if the letter isn't found returns a
	 *         default position
	 */
	public static Position findPosition(char[][] map, char letter) {
		Position pos = new Position(0, 0);
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == letter) {
					pos.setXPosition(i);
					pos.setYPosition(j);
					return pos;
				}
			}
		}
		return pos;
	}

}
