
package dkeep.logic;

import java.io.Serializable;

final class Key extends GameEntity implements Serializable {

	private static final long serialVersionUID = -7975238719147852428L;
	private static final char CHAR_KEY = 'k';
	private static final char CHAR_KEY_PICKED = ' ';
	private boolean picked;

	/**
	 * Class constructor
	 * 
	 * @param x
	 * @param y
	 */
	Key(int x, int y) {
		super(x, y);
		picked = false;
	}

	/**
	 * Returns the level char representation
	 * 
	 * @return 'k'
	 */
	char getChar() {
		return (picked) ? CHAR_KEY_PICKED : CHAR_KEY;
	}

	/**
	 * Sets the state of the lever as true
	 */
	boolean isPicked() {
		return picked;
	}

	public final void setPicked(boolean picked) {
		this.picked = picked;
	}

	@Override
	public void move(Level l, Position p) {
		// keys dont move		
	}
	
	@Override
	public void move(Level l) {
		// keys dont move		
	}

	@Override
	public boolean attack(GameEntity g) {
		// keys dont attack		
		return false;
	}

}
