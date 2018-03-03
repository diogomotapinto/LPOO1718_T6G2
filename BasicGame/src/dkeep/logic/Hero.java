package dkeep.logic;

public final class Hero extends ClubHolder {

	protected static final char CHAR_HERO = 'H';
	protected static final char CHAR_HERO_KEY = 'A';

	private boolean lever;

	public Hero(int xPos, int yPos) {
		super(xPos, yPos);
		lever = false;
	}

	public final boolean getLeverState() {
		return lever;
	}

	public final void setLeverState(boolean leverState) {
		this.lever = leverState;
	}

	public final char getHeroChar(boolean leverState) {
	
		if(!leverState) {
			return CHAR_HERO;
		}else
		{
			return CHAR_HERO_KEY;
		}
		
	}

	public final void setHeroChar(char ch) {
	}

}
