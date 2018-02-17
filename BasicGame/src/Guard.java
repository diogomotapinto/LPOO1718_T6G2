
public class Guard extends Character {
	private int route[][];
	private int index;

	public Guard(int xPos, int yPos) {
		super(xPos, yPos);
		route = new int[][] { { 1, 8 }, { 1, 7 }, { 2, 7 }, { 3, 7 }, { 4, 7 }, { 5, 7 }, { 5, 6 }, { 5, 5 }, { 5, 4 },
				{ 5, 3 }, { 5, 2 }, { 5, 1 }, { 6, 1 }, { 6, 2 }, { 6, 3 }, { 6, 4 }, { 6, 5 }, { 6, 6 }, { 6, 7 },
				{ 6, 8 }, { 5, 8 }, { 4, 8 }, { 3, 8 }, { 2, 8 } };
		index = 0;
	}

	public int[] getPosition() {
		return route[index];
	}

	public int[] getNextPosition() {
		index++;
		if (index == route.length) {
			index = 0;
		}
		return route[index];
	}
}
