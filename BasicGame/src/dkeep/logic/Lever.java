package dkeep.logic;

public class Lever {
	protected static final char CHAR_LEVER = 'k';

	private boolean activated;
	// private String leverChar;
	private int xPosition;
	private int yPosition;

	public Lever(int xPosition, int yPosition) {
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		activated = false;
		// leverChar = "k";
	}

	// public String getLever() {
	// return leverChar;
	// }

	// public void setLever(String lever) {
	// leverChar = lever;
	// }

	public void activateLever() {
		activated = true;
	}

	public boolean isActivated() {
		return activated;
	}

	public int getXPosition() {
		return xPosition;
	}

	public int getYPosition() {
		return yPosition;
	}

}
