package dkeep.logic.model;

public class Club extends GameObject {
	private static final char CLUB_CHAR = '*';

	/**
	 * Class constructor
	 * 
	 * @param xPos
	 *            coordinate of the club in the x axis
	 * @param yPos
	 *            coordinate fo the club in the y axis
	 */
	public Club(int xPos, int yPos) {
		super(xPos, yPos);
	}

	/**
	 * method for getting the club character
	 * 
	 * @return the club char
	 */
	public static char getClubChar() {
		return CLUB_CHAR;
	}

}
