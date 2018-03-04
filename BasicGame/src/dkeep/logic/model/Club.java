package dkeep.logic.model;

public class Club {
	private Position position;
	private static final char CLUB_CHAR = '*';

	public Club(int xPos, int yPos) {
		position = new Position(xPos, yPos);
	}

	public static char getClubChar() {
		return CLUB_CHAR;
	}

	public final Position getPosition() {
		return this.position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

}
