package game;

public class Guard extends Character {

	private static final int route[][] = new int[][] { { 1, 8 }, { 1, 7 }, { 2, 7 }, { 3, 7 }, { 4, 7 }, { 5, 7 },
			{ 5, 6 }, { 5, 5 }, { 5, 4 }, { 5, 3 }, { 5, 2 }, { 5, 1 }, { 6, 1 }, { 6, 2 }, { 6, 3 }, { 6, 4 },
			{ 6, 5 }, { 6, 6 }, { 6, 7 }, { 6, 8 }, { 5, 8 }, { 4, 8 }, { 3, 8 }, { 2, 8 } };
	private int index;

	public Guard(int xPos, int yPos) {
		super(route[0][0], route[0][1]);
		index = 0;
	}

	public final void moveToNextPosition() {
		index++;
		if (index == route.length) {
			index = 0;
		}
		this.xPosition = route[index][0];
		this.yPosition = route[index][1];
	}
}
