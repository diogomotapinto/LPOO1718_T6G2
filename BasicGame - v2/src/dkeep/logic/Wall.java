package dkeep.logic;

import java.io.Serializable;

class Wall extends GameEntity implements Serializable {

	private static final long serialVersionUID = 8380840094629327405L;
	private static final char WALL_CHAR = 'X';

	/**
	 * Class Constructor
	 * 
	 * @param x position in the x-axis
	 * @param y position in the y-axis
	 */
	public Wall(int x, int y) {
		super(x, y);
	}

	/**
	 * 
	 * @return 'X'
	 */
	public static char getChar() {
		return WALL_CHAR;
	}

	@Override
	public void move(Level l, Position p) {
		// walls dont move
	}

	@Override
	public void move(Level l) {
		// walls dont move
	}

	@Override
	public boolean attack(GameEntity g) {
		// walls dont atttack
		return false;
	}

}
