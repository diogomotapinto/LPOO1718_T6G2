package dkeep.logic;

public class Lever {
	protected static final char CHAR_LEVER = 'k';

	private boolean activated;
	// private String leverChar;
	private Position position;
	
	public Lever(int xPosition, int yPosition) {
		position = new Position(xPosition, yPosition);
		activated = false;
	
	}

	public static char getLeverChar() {
		return CHAR_LEVER;
	}
	

	public void activateLever() {
		activated = true;
	}

	public boolean isActivated() {
		return activated;
	}
	
	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
}
