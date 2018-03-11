package dkeep.logic.model;

public class TestGuard extends Guard {

	public TestGuard() {
		super(new int[][] { { 1, 8 } });
	}

	@Override
	public void moveToNextPosition() {
		// has no body because it is not meant to move
	}

	@Override
	public String toString() {
		return "Guard [tipo= " + getClass().getSimpleName() + "]";
	}

}
