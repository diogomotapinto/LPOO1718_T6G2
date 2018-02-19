package game;

public class Character {
	protected int xPosition;
	protected int yPosition;

	public Character(int xPos, int yPos) {
		this.xPosition = xPos;
		this.yPosition = yPos;
	}

	public int getXPosition() {
		return this.xPosition;
	}

	public int getYPosition() {
		return this.yPosition;
	}

	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}
}
