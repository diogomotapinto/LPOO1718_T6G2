package dkeep.logic;

import java.io.Serializable;

final class Lever extends GameEntity implements Serializable {

	private static final long serialVersionUID = -7975238719147852428L;
	private static final char CHAR_LEVER = 'k';
	private boolean activated;

	/**
	 * Class constructor
	 * 
	 * @param x
	 * @param y
	 */
	Lever(int x, int y) {
		super(x, y);
		activated = false;
	}

	/**
	 * Returns the level char representation
	 * 
	 * @return 'k'
	 */
	char getChar() {
		return CHAR_LEVER;
	}

	/**
	 * Sets the state of the lever as true
	 */
	void reverse() {
		activated = !activated;
	}

	/**
	 * Returns the state of the lever
	 * 
	 * @return true if it is activated and false otherwise
	 * 
	 */
	public boolean isActivated() {
		return activated;
	}

	@Override
	public void move(Level l, Position p) {
		// levers dont move
	}

	@Override
	public void move(Level l) {
		// levers dont move
	}

	@Override
	public boolean attack(GameEntity g) {
		// levers dont atttack
		return false;
	}

}
