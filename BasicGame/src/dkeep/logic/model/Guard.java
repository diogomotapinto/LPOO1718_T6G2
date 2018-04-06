package dkeep.logic.model;

import java.io.Serializable;

public abstract class Guard extends GameObject implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8469436697778674229L;
	/**
	 * 
	 */
	private static final char GUARD_CHAR = 'G';
	protected final int route[][];
	protected int index;

	/**
	 * Class constructor
	 * 
	 * @param route
	 *            of the guard
	 */
	protected Guard(int[][] route) {
		super(route[0][0], route[0][1]);
		this.route = route;
		index = 0;
	}

	/**
	 * Makes guard move to the next position
	 */
	public abstract void moveToNextPosition();

	/**
	 * Gives the guard character
	 * 
	 * @return 'G'
	 */
	public static final char getGuardChar() {
		return GUARD_CHAR;
	}

	public abstract String toString();
}
