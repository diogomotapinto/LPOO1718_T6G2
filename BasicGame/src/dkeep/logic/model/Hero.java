package dkeep.logic.model;

import java.io.Serializable;

public final class Hero extends GameObject implements Serializable  {

	private static final char CHAR_HERO_lvl2 = 'A';
	private static final char CHAR_HERO = 'H';
	private static final char CHAR_HERO_KEY = 'K';
	private boolean lever;

	/**
	 * Class constructor
	 * 
	 * @param xPos
	 *            position of the hero in the x-axis
	 * @param yPos
	 *            position of the hero in the y-axis
	 */
	public Hero(int xPos, int yPos) {
		super(xPos, yPos);
		lever = false;
	}

	/**
	 * Used to know if the hero has the key
	 * 
	 * @return true if the hero has the key and false otherwise
	 */
	public final boolean getLeverState() {
		return lever;
	}

	/**
	 * used to set the lever state
	 * 
	 * @param lever
	 *            true if the hero has caught the key and false otherwise
	 * 
	 */
	public final void setLeverState(boolean lever) {
		this.lever = lever;
	}

	/**
	 * 
	 * @return 'H'
	 */
	public static char getCharHero() {
		return CHAR_HERO;
	}

	/**
	 * 
	 * @return 'k'
	 */
	public static char getCharHeroKey() {
		return CHAR_HERO_KEY;
	}

	/**
	 * 
	 * @return 'A'
	 */
	public static char getCharHeroLvl2() {
		return CHAR_HERO_lvl2;
	}

}
