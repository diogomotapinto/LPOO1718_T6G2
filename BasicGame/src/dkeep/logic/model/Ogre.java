package dkeep.logic.model;

public final class Ogre extends GameObject {
	private static final int STUN_COUNTER = 2;
	private int counter;
	private boolean stunned;
	private Club club;

	public Ogre(int xPos, int yPos) {
		super(xPos, yPos);
		club = new Club(xPos, yPos);
		counter = STUN_COUNTER;
		stunned = false;
	}

	public final void setStunned(boolean isStunned) {
		if (stunned) {
			counter = STUN_COUNTER;
		} else {
			stunned = isStunned;
		}
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

	public final char getOgreChar() {
		if (stunned) {
			return '8';
		} else {
			return 'O';
		}
	}

	public final Club getClub() {
		return club;
	}
}
