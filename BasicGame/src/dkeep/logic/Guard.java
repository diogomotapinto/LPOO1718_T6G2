package dkeep.logic;

public abstract class Guard extends Character {

	private static final char GUARD_CHAR = 'G';
	protected final int route[][];
	protected int index;

	protected Guard(int xPos, int yPos, int[][] route) {
		super(route[0][0], route[0][1]);
		this.route = route;
		index = 0;
	}

	protected abstract void moveToNextPosition();

	public final static char getGuardChar() {
		return GUARD_CHAR;
	}

	

}
