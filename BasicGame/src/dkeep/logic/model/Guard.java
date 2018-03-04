package dkeep.logic.model;

public abstract class Guard extends Character {

	private static final char GUARD_CHAR = 'G';
	protected final int route[][];
	protected int index;

	protected Guard(int[][] route) {
		super(route[0][0], route[0][1]);
		this.route = route;
		index = 0;
	}

	public abstract void moveToNextPosition();

	public static final char getGuardChar() {
		return GUARD_CHAR;
	}

	public abstract String toString();
}