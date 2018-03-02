package dkeep.logic;

public abstract class Character {
	protected Position position;

	public Character(int xPos, int yPos) {
		position = new Position(xPos, yPos);

	}

	protected final Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	} 

}
