package dkeep.logic;

import java.io.Serializable;

public class Door extends GameObject  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7096019683477771677L;
	/**
	 * 
	 */
	private boolean open;
	private final char close;
	private final char openChar;

	/**
	 * Class constructor
	 * 
	 * @param xPos
	 *            position of the door in the x-axis
	 * @param yPos
	 *            position of the door in the y-axis
	 */
	public Door(int xPos, int yPos) {
		super(xPos, yPos);
		this.open = false;
		this.close = 'I';
		this.openChar = 'S';
	}

	/**
	 * method used to know if the door is open
	 * 
	 * @return true if the door is open and false otherwise
	 */
	public boolean isOpen() {
		return open;
	}

	/**
	 * method to set the state of the door
	 * 
	 * @param open
	 *            true if the door is open and false otherwise
	 */
	public void setOpen(boolean open) {
		this.open = open;
	}

	/**
	 * method to get the door character
	 * 
	 * @return 'S' if the door is open and 'I' otherwise
	 */
	public char getChar() {
		if (this.open) {
			return this.openChar;
		} else {
			return this.close;
		}
	}
}
