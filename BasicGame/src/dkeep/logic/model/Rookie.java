package dkeep.logic.model;

public final class Rookie extends Guard {

	public Rookie(int[][] route) {
		super(route);
	}

	public final void moveToNextPosition() {
		index++;
		if (index == route.length) {
			index = 0;
		}
		this.setPosition(new Position(route[index][0], route[index][1]));

	}

	@Override
	public String toString() {
		return "Guard [tipo= " + getClass().getSimpleName() + "]";
	}
}
