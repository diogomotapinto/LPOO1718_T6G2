package dkeep.logic;

public class Club {
	private int xPosition;
	private int yPosition;

	public Club() {
	}

	public Club(int xPos, int yPos) {
		this.xPosition = xPos;
		this.yPosition = yPos;
	}

	public final int getXPosition() {
		return this.xPosition;
	}

	public final int getYPosition() {
		return this.yPosition;
	}

	public final void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	public final void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}

}
