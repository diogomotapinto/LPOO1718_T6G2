package dkeep.logic.model;

import java.io.Serializable;

abstract class GameObject implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3897431447612446501L;
	/**
	 * 
	 */
	private Position position;

	/**
	 * Class constructor
	 * 
	 * @param xPos
	 *            position of the game object in the x-axis
	 * @param yPos
	 *            position of the game object in the y-axis
	 */
	GameObject(int xPos, int yPos) {
		position = new Position(xPos, yPos);
	}

	/**
	 * Gives the position of the game object
	 * 
	 * @return game object position
	 */
	public final Position getPosition() {
		return position;
	}

	/**
	 * Sets the position of the game object
	 * 
	 * @param position,
	 *            new position of the game object
	 */
	public void setPosition(Position position) {
		this.position = position;
	}

}
