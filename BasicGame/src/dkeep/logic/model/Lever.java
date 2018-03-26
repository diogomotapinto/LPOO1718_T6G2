package dkeep.logic.model;

public final class Lever extends GameObject {
	private static final char CHAR_LEVER = 'k';
	private boolean activated;

	public Lever(int xPos, int yPos) {
		super(xPos, yPos);
		activated = false;
	}

	public static char getLeverChar() {
		return CHAR_LEVER;
	}

	public void activateLever() {
		activated = true;
	}

	public boolean isActivated() {
		return activated;
	}

}
