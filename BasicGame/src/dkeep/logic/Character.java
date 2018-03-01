package dkeep.logic;

public abstract class Character {
	protected Position position;

	public Character(int xPos, int yPos) {
		position = new Position(xPos, yPos);

	}

//	public void setPosition(int xPosition,int yPosition)
//	{
//		this.position.setXPosition(xPosition);
//		this.position.setYPosition(yPosition);
//	}
	
	protected final int getXPosition() {
		return position.getXPosition();
	}

	protected final int getYPosition() {
		return position.getYPosition();
	}

	protected final void setXPosition(int xPosition) {
		position.setXPosition(xPosition);;
	}

	protected final void setYPosition(int yPosition) {
		position.setYPosition(yPosition);
	}
}
