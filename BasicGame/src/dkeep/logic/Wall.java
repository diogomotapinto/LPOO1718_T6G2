package dkeep.logic;

import java.io.Serializable;

class Wall extends GameObject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8380840094629327405L;
	private final char wallChar;

	/**
	 * Class Constructor
	 * 
	 * @param xPos
	 *            position in the x-axis
	 * @param yPos
	 *            position in the y-axis
	 */
	public Wall(int xPos, int yPos) {
		super(xPos, yPos);
		this.wallChar = 'X';
	}

	/**
	 * 
	 * @return 'X'
	 */
	public char getWallChar() {
		return this.wallChar;
	}

}
