package dkeep.logic.model;

public abstract class GameObject {
	private Position position;

	public GameObject(int xPos, int yPos) {
		position = new Position(xPos, yPos);
	}

	public final Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

}
