package dkeep.logic;

import java.io.Serializable;
import utilities.Utilities;

final class BehaviourDrunken implements ImplementedBehaviour, Serializable {

	private static final long serialVersionUID = -1805847310392460226L;
	private final static double CONST_PROB_SLEEP = 0.35;
	private final static double CONST_PROB_WAKE = 0.5;

	private boolean sleep;

	/**
	 * Class constructor
	 * 
	 * @param route of the drunken guard
	 */
	BehaviourDrunken() {
		sleep = false;
	}

	/**
	 * Makes Drunken guard move to the next position
	 */
	@Override
	public final int getNextMove(int index, int length) {
		updateSleeping();

		if (!sleep) {
			index++;
			if (index == length) {
				index = 0;
			}
		}
		return index;

	}

	private void updateSleeping() {
		/*
		 * counter each time the guard goes to sleep could be implemented but this way
		 * is much more random and compact solution
		 */
		if (sleep) {
			sleep = (Utilities.generateRandomNumber() < CONST_PROB_WAKE) ? false : true;
		} else {
			sleep = (Utilities.generateRandomNumber() < CONST_PROB_SLEEP) ? true : false;
		}
	}

	/**
	 * 
	 * 
	 */
	@Override
	public String toString() {
		return getClass().getSimpleName();
	}

}
