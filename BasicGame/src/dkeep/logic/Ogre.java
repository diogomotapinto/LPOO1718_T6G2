package dkeep.logic.model;

import java.io.Serializable;

public final class Ogre extends GameObject implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4718734029463699930L;
	/**
	 * 
	 */
	private static final int STUN_COUNTER = 2;
	private int counter;
	private boolean stunned;
	private Club club;

	/**
	 * Class constructor
	 * 
	 * @param xPos
	 *            position on the x-axis
	 * @param yPos
	 *            position on the y-axis
	 */
	public Ogre(int xPos, int yPos) {
		super(xPos, yPos);
		club = new Club(xPos, yPos);
		counter = STUN_COUNTER;
		stunned = false;
	}

	/**
	 * Set if the ogre is stunned or not, if he is stunner put a counter equals to
	 * two
	 * 
	 * @param isStunned
	 *            true if the ogre is stunned false otherwise
	 */
	public final void setStunned(boolean isStunned) {
		if (stunned) {
			counter = STUN_COUNTER;
		} else {
			stunned = isStunned;
		}
	}

	/**
	 * Method used to know if the ogre is stunned or not
	 * 
	 * @return the state of the ogre
	 */
	public final boolean getStunned() {
		return stunned;
	}

	/**
	 * Used to decrement the stun counter
	 */
	public final void stunCounter() {
		counter--;
	}

	/**
	 * method used to know for how many plays the ogre is stunned
	 * 
	 * @return 2, 1 or 0
	 */
	public final int getStunCounter() {
		return counter;
	}

	/**
	 * Method used to know get the ogre character
	 * 
	 * @return 'O' if the ogre isn't stunned and '8' otherwise
	 */
	public final char getOgreChar() {
		if (stunned) {
			return '8';
		} else {
			return 'O';
		}
	}

	/**
	 * Method use to get the club that belongs to the ogre
	 * 
	 * @return club of the ogre
	 */
	public final Club getClub() {
		return club;
	}
}
