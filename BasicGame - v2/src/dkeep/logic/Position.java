package dkeep.logic;

import java.io.Serializable;

public final class Position implements Serializable {

	private static final long serialVersionUID = -6112035613033930460L;
	private int x;
	private int y;

	/**
	 * Class constructor
	 * 
	 * @param x position in the x-axis
	 * @param y position in the y-axis
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Position(Position p) {
		this.x = p.getX();
		this.y = p.getY();
	}

	/**
	 * Get position in the x-axis
	 * 
	 * @return int equal or bigger than zero
	 */
	public final int getX() {
		return this.x;
	}

	/**
	 * Set position in the x-axis
	 * 
	 * @param xPosition position in the x-axis
	 */
	public final void changeTo(int xPosition, int yPosition) {
		this.x = xPosition;
		this.y = yPosition;
	}

	public final void changeTo(Position pos) {
		this.x = pos.getX();
		this.y = pos.getY();
	}

	/**
	 * Get position in the y-axis
	 * 
	 * @return int equal or bigger than zero
	 */
	public final int getY() {
		return this.y;
	}

	@Override
	public final int hashCode() {
		int result = 17;
		result = 31 * result + x;
		result = 31 * result + y;
		return result;
	}

	/**
	 * check if the object is equals as the one passed as a @param
	 * 
	 * @param obj
	 * @return true if the obj is equals false otherwise
	 */
	@Override
	public final boolean equals(Object obj) {

		if (obj == null) {
			return false;
		}

		if (obj == this) {
			return true;
		}

		if (!(obj instanceof Position)) {
			return false;
		}

		Position otherObj = (Position) obj;
		return this.x == otherObj.getX() && this.y == otherObj.getY();
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("x=");
		s.append(x);
		s.append(" y=");
		s.append(y);
		return s.toString();
	}

	public double distance(Position temp) {
		int x1 = temp.getX();
		int y1 = temp.getY();
		/**
		 * Distance formula = sqrt( (x1-x)^2 + (y1-y)^2 )
		 */
		return Math.sqrt(Math.pow((x1 - x), 2) + Math.pow((y1 - y), 2));
	}

	/**
	 * Generates an adjacent position.
	 * 
	 * @param xPosition position in the x-axis
	 * @param yPosition position in the y-axis
	 * @return position generated randomly
	 */
	public final Position[] getAdjacentPositions() {
		// up, down, left, right
		return new Position[] { new Position(x - 1, y), new Position(x + 1, y), new Position(x, y - 1),
				new Position(x, y + 1) };
	}

}
