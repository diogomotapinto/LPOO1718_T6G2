package dkeep.logic;

public final class Hero extends Character {

	protected static final char CHAR_HERO = 'H';
	protected static final char CHAR_HERO_KEY = 'A';
	
	private boolean lever;

	// ver se se altera este caracter, e se sim discutir em grupo
	private char heroChar;
	private Club club;

	public Hero(int xPos, int yPos) {
		super(xPos, yPos);
		club = new Club(xPos, yPos);
		heroChar = 'H';
		lever = false;
	}

	
	public final char getHeroClubChar() {
		return club.getClubChar();
	}
	
	public final boolean getLeverState() {
		return lever;
	}
	
	public  final void setLeverState(boolean leverState) {
		this.lever = leverState;
	}
	
	public final static char getHeroChar() {
		return CHAR_HERO;
	}

	public final void setHeroChar(char ch) {
		heroChar = ch;
	}

	public final Club getMyClub() {
		return club;
	}

	public final void setMyClub(Club myClub) {
		this.club = myClub;
	}

}
