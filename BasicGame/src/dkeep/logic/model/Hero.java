package dkeep.logic.model;

public final class Hero extends ClubHolder {

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

	public final char getHeroChar(boolean leverState) {
		if (!leverState) {
			return CHAR_HERO;
		} else {
			return CHAR_HERO_KEY;
		}
	}

}
