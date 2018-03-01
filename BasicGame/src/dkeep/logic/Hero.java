package dkeep.logic;

public class Hero extends Character {

	protected static final char CHAR_HERO = 'H';
	protected static final char CHAR_HERO_KEY = 'A';

	// ver se se altera este caracter, e se sim discutir em grupo
	private char heroChar;
	private Club club;

	public Hero(int xPos, int yPos) {
		super(xPos, yPos);
		club = new Club(xPos, yPos);
		heroChar = 'H';
	}

	public char getHeroChar() {
		return heroChar;
	}

	public void setHeroChar(char ch) {
		heroChar = ch;
	}

	public Club getMyClub() {
		return club;
	}

	public void setMyClub(Club myClub) {
		this.club = myClub;
	}

}
