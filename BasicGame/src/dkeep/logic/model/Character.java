package dkeep.logic.model;

public abstract class Character {
	private Position position;

	public Character(int xPos, int yPos) {
		position = new Position(xPos, yPos);
	}

	public final Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

}
