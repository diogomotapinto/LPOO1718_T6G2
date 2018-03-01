package dkeep.logic;

public abstract class Guard extends Character {

	// ver se se altera este caracter
	private static final char GUARD_CHAR = 'G';

	// mudar o route de sitio, provavelmente para a class dungeonMap, o rota nao é
	// propriedade do guarda mas sim do mapa
	protected static final int route[][] = new int[][] { { 1, 8 }, { 1, 7 }, { 2, 7 }, { 3, 7 }, { 4, 7 }, { 5, 7 },
			{ 5, 6 }, { 5, 5 }, { 5, 4 }, { 5, 3 }, { 5, 2 }, { 5, 1 }, { 6, 1 }, { 6, 2 }, { 6, 3 }, { 6, 4 },
			{ 6, 5 }, { 6, 6 }, { 6, 7 }, { 6, 8 }, { 5, 8 }, { 4, 8 }, { 3, 8 }, { 2, 8 } };

	protected int index;

	protected Guard(int xPos, int yPos) {
		super(route[0][0], route[0][1]);
		index = 0;
	}

	protected abstract void moveToNextPosition();

	public static char getGuardChar() {
		return GUARD_CHAR;
	}

}
