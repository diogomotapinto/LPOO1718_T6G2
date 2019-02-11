package dkeep.logic;

import java.io.Serializable;

final class BehaviourRookie implements ImplementedBehaviour, Serializable {

	private static final long serialVersionUID = -7795474620529992686L;

	/**
	 * Class constructor
	 * 
	 * @param route of the Rookie Guard
	 */
	public BehaviourRookie() {
	}

	/**
	 * Makes Rookie move to the next possition in the route
	 */
	@Override
	public final int getNextMove(int index, int length) {
		index++;
		if (index == length) {
			index = 0;
		}

		return index;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}
}
