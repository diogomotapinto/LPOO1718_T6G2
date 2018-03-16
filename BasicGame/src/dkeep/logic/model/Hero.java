package dkeep.logic.model;

public final class Hero extends Character {

	private static final char CHAR_HERO_lvl2 = 'A';
	private static final char CHAR_HERO = 'H';
	private static final char CHAR_HERO_KEY = 'K';
	private boolean lever;

	public Hero(int xPos, int yPos) {
		super(xPos, yPos);
		lever = false;
	}

	public final boolean getLeverState() {
		return lever;
	}

	public final void setLeverState(boolean lever) {
		this.lever = lever;
	}
	

	public static char getCharHero() {
		return CHAR_HERO;
	}

	public static char getCharHeroKey() {
		return CHAR_HERO_KEY;
	}
	
	public static char getCharHeroLvl2() {
		return CHAR_HERO_lvl2;
	}

//	public final char getHeroChar() {
//		if (!lever) {
//			return CHAR_HERO;
//		} else {
//			return CHAR_HERO_KEY;
//		}
//	}
	
	

}
