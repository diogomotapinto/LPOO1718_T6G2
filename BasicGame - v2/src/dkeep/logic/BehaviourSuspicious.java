package dkeep.logic;

import java.io.Serializable;

import utilities.Utilities;

final class BehaviourSuspicious implements ImplementedBehaviour, Serializable {

	private static final long serialVersionUID = -7417454072218940051L;
	private final static double CONST_PROB_INVERT = 0.3;

	private boolean direction;

	/**
	 * Class constructor
	 * 
	 * @param route of the Suspicious Guard
	 */
	public BehaviourSuspicious() {
		direction = true;
	}

	/**
	 * Makes Suspicious Guard move to the next position in the route
	 */
	@Override
	public final int getNextMove(int index, int length) {
		updateDirection();

		if (direction) {
			index++;
			if (index == length) {
				index = 0;
			}

		} else {
			index--;
			if (index == -1) {
				index = length - 1;
			}
		}

		return index;
	}

	private void updateDirection() {
		if (Utilities.generateRandomNumber() < CONST_PROB_INVERT) {
			direction = !direction;
		}
	}

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}

}
