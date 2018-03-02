package dkeep.logic;

public final class Ogre extends ClubHolder {
	private static final int STUN_COUNTER = 2;
	private int counter;
	private boolean stunned;

	public Ogre(int xPos, int yPos) {
		super(xPos, yPos);
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

}
