package dkeep.logic.model;

public class Wall extends GameObject{
	private final char wallChar; 
	
	public Wall(int xPos, int yPos) {
		super(xPos, yPos);
		this.wallChar = 'X';
	}
	
	public char getWallChar() {
		return this.wallChar;
	}

}
