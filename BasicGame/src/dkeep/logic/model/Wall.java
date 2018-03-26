package dkeep.logic.model;

public class Wall extends GameObject{
	private final char wallChar; 
	
	/**
	 * Class Constructor
	 * @param xPos position in the x-axis
	 * @param yPos position in the y-axis
	 */
	public Wall(int xPos, int yPos) {
		super(xPos, yPos);
		this.wallChar = 'X';
	}
	/**
	 * 
	 * @return 'X'
	 */
	public char getWallChar() {
		return this.wallChar;
	}

}
