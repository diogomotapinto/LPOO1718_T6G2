package dkeep.logic;

import java.io.Serializable;

abstract class Door extends GameEntity implements Serializable {

	private static final long serialVersionUID = -7096019683477771677L;
	private static final char DOOR_CLOSED = 'I';
	private static final char DOOR_OPEN = 'S';

	private boolean open;
	private boolean unlocked;

	/**
	 * Class constructor
	 * 
	 * @param x position of the door in the x-axis
	 * @param y position of the door in the y-axis
	 */
	Door(int x, int y, boolean isOutDoor) {
		super(x, y);
		this.open = false;
		this.unlocked = false;
	}

	/**
	 * 
	 * 
	 * /** method used to know if the door is open
	 * 
	 * @return true if the door is open and false otherwise
	 */
	public final boolean isOpen() {
		return open;
	}

	/**
	 * method to set the state of the door
	 * 
	 * @param open true if the door is open and false otherwise
	 */
	public final void setOpen(boolean open) {
		this.open = open;
	}

	/**
	 * method to get the door character
	 * 
	 * @return 'S' if the door is open and 'I' otherwise
	 */
	public char getChar() {
		return this.open ? DOOR_OPEN : DOOR_CLOSED;
	}

	public final boolean isUnlocked() {
		return unlocked;
	}

	public final void setUnlocked(boolean unlocked) {
		this.unlocked = unlocked;
	}

	public static final char getDoorOpen() {
		return DOOR_OPEN;
	}

}
