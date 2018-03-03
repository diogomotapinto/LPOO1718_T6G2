package dkeep.logic.model;

public final class Position {
	private int xPosition;
	private int yPosition;

	public Position(int xPosition, int yPosition) {
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}

	public final int getXPosition() {
		return this.xPosition;
	}

	public final void setXPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	public final int getYPosition() {
		return this.yPosition;
	}

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

}
