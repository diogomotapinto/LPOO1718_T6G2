package dkeep.logic;

import java.io.Serializable;

abstract class GameEntity implements Serializable {

	private static final long serialVersionUID = -3897431447612446501L;

	public enum State {
		ALIVE, DEAD
	};

	protected State currentState;
	protected Position position;

	/**
	 * Class constructor
	 * 
	 * @param x position of the game object in the x-axis
	 * @param y position of the game object in the y-axis
	 */
	GameEntity(int x, int y) {
		position = new Position(x, y);
		currentState = State.ALIVE;
	}

	GameEntity() {
		this.position = new Position(-1, -1);
	}

	public abstract void move(Level l, Position p);

	public abstract void move(Level l);

	/**
	 * Gives the position of the game object
	 * 
	 * @return game object position
	 */
	public final Position getPosition() {
		return position;
	}

	/**
	 * Check if there is a collision between two game characters
	 * 
	 * @param position position of the game character
	 * @param          g.getPosition() position of the other game character
	 * @return true if there is collision and false otherwise
	 */
	public boolean isAdjacent(GameEntity g) {

		// same column, either g.getPosition() above or below position
		if (position.getY() == g.getPosition().getY() && (g.getPosition().getX() == (position.getX() - 1)
				|| g.getPosition().getX() == (position.getX() + 1))) {
			return true;
		}

		// same row, either g.getPosition() to the left or to the right of position
		if (position.getX() == g.getPosition().getX() && (g.getPosition().getY() == (position.getY() - 1)
				|| g.getPosition().getY() == (position.getY() + 1))) {
			return true;
		}

		return false;
	}

	public boolean isAt(GameEntity g) {
		// same column, same row
		return position.equals(g.getPosition());
	}

	public final State getCurrentState() {
		return currentState;
	}

	public boolean attack(GameEntity g) {
		// attacked area could be calculated and check if GameEntity g.position is equal
		// to any of them
		boolean flag = this.isAdjacent(g) || this.isAt(g);
		if (flag) {
			g.currentState = State.DEAD;
		}
		return flag;
	}

}
