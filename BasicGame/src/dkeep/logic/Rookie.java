package dkeep.logic;

import java.io.Serializable;

final class Rookie extends Guard implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7795474620529992686L;

	/**
	 * Class constructor
	 * 
	 * @param route
	 *            of the Rookie Guard
	 */
	public Rookie(int[][] route) {
		super(route);
	}

	/**
	 * Makes Rookie move to the next possition in the route
	 */
	public final void moveToNextPosition() {
		index++;
		if (index == route.length) {
			index = 0;
		}
		this.setPosition(new Position(route[index][0], route[index][1]));

	}

	@Override
	public String toString() {
		return "Guard [tipo= " + getClass().getSimpleName() + "]";
	}
}
