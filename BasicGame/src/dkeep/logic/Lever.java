package dkeep.logic;

import java.io.Serializable;

final class Lever extends GameObject implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7975238719147852428L;
	/**
	 * 
	 */
	private static final char CHAR_LEVER = 'k';
	private boolean activated;

	/**
	 * Class constructor
	 * @param xPos
	 * @param yPos
	 */
	Lever(int xPos, int yPos) {
		super(xPos, yPos);
		activated = false;
	}
	/**
	 * Returns the level char representation
	 * @return 'k'
	 */
	static char getLeverChar() {
		return CHAR_LEVER;
	}

	/**
	 * Sets the state of the lever as true 
	 */
	void activateLever() {
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
