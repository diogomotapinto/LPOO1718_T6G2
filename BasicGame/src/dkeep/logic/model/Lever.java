package dkeep.logic.model;

public final class Lever extends GameObject {
	private static final char CHAR_LEVER = 'k';
	private boolean activated;

	/**
	 * Class constructor
	 * @param xPos
	 * @param yPos
	 */
	public Lever(int xPos, int yPos) {
		super(xPos, yPos);
		activated = false;
	}
	/**
	 * Returns the level char representation
	 * @return 'k'
	 */
	public static char getLeverChar() {
		return CHAR_LEVER;
	}

	/**
	 * Sets the state of the lever as true 
	 */
	public void activateLever() {
		activated = true;
	}

	/**
	 * Returns the state of the lever
	 * @return true if it is activated and false otherwise
	 * 
	 */
	public boolean isActivated() {
		return activated;
	}

}
