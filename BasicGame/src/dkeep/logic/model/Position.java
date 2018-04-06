package dkeep.logic.model;

import java.io.Serializable;

public final class Position implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6112035613033930460L;
	/**
	 * 
	 */
	private int xPosition;
	private int yPosition;

	/**
	 * Class constructor
	 * 
	 * @param xPosition
	 *            position in the x-axis
	 * @param yPosition
	 *            position in the y-axis
	 */
	public Position(int xPosition, int yPosition) {
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}

	/**
	 * Get position in the x-axis
	 * 
	 * @return int equal or bigger than zero
	 */
	public final int getXPosition() {
		return this.xPosition;
	}

	/**
	 * Set position in the x-axis
	 * 
	 * @param xPosition
	 *            position in the x-axis
	 */
	public final void setXPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	/**
	 * Get position in the y-axis
	 * 
	 * @return int equal or bigger than zero
	 */
	public final int getYPosition() {
		return this.yPosition;
	}

	/**
	 * Set position in the y-axis
	 * 
	 * @param yPosition
	 *            position in the y-axis
	 */
	public final void setYPosition(int yPosition) {
		this.yPosition = yPosition;
	}

	@Override
	public final int hashCode() {
		int result = 17;
		result = 31 * result + xPosition;
		result = 31 * result + yPosition;
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
		return this.xPosition == otherObj.getXPosition() && this.yPosition == otherObj.getYPosition();
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("xPosition= ");
		s.append(xPosition);
		s.append(" yPosition= ");
		s.append(yPosition);
		return s.toString();
	}
}
