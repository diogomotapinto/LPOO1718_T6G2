package dkeep.logic.model;

public class Club {
	private Position position;
	private static final char CLUB_CHAR = '*';

	/**
	 * Class constructor
	 * @param xPos coordinate of the club in the x axis 
	 * @param yPos coordinate fo the club in the y axis
	 */
	public Club(int xPos, int yPos) {
		position = new Position(xPos, yPos);
	}

	/**
	 * method for getting the club character
	 * @return the club char
	 */
	public static char getClubChar() {
		return CLUB_CHAR;
	}

	/**
	 * method to get the position of the club
	 * @return the position of the club
	 */
	public final Position getPosition() {
		return this.position;
	}
	/**
	 * 
	 * @param position 
	 */
	public void setPosition(Position position) {
		this.position = position;
	}

}
