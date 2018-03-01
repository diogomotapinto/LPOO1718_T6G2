package dkeep.logic;

public final class Ogre extends Character {
	private static final char MASSIVE_CLUB = '*';
	private int counter;
	private boolean stunned;
	protected Club myClub;

	public Ogre(int xPos, int yPos) {
		super(xPos, yPos);
		counter = 2;
		stunned = false;
		myClub = new Club(xPos, yPos);
	}

	public final char getClub() {
		return MASSIVE_CLUB;
	}

	public final void setStunned(boolean isStunned) {
		if (stunned) {
			counter = 2;
		}
		stunned = isStunned;
	}

	public final boolean getStunned() {
		return stunned;
	}

	public final void stunCounter() {
		counter--;
	}

	public final int getStunCounter() {
		return counter;
	}

	public final Club getMyClub() {
		return myClub;
	}

	public  final void setMyClub(Club myClub) {
		this.myClub = myClub;
	}
}
