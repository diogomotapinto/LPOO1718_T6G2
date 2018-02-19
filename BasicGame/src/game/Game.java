package game;

public class Game {
	private static final String MOVE_UP_CHAR = "w";
	private static final String MOVE_DOWN_CHAR = "s";
	private static final String MOVE_RIGHT_CHAR = "d";
	private static final String MOVE_LEFT_CHAR = "a";

	protected static final String WALL_CHAR = "X";
	protected static final String DOOR_CHAR = "I";
	protected static final String HERO_CHAR = "H";
	protected static final String LEVER_CHAR = "k";
	protected static final String BLANK_SPACE = " ";
	protected boolean gameState;

	protected String[][] map;
	private String legend;
	private Interface myInterface;
	protected Hero hero;
	

	public Game(String[][] map, String legend, int heroXPosition, int heroYPosition) {
		this.map = map;
		this.legend = legend;
		this.myInterface = new Interface();
		gameState = true;
		hero = new Hero(heroXPosition, heroYPosition);
	}

	protected void printMap() {
		System.out.println();
		for (int i = 0; i < this.map.length; i++) {
			for (int j = 0; j < this.map[i].length; j++) {
				System.out.print(BLANK_SPACE + this.map[i][j]);
			}
			System.out.println();
		}
	}

	protected void printLegend() {
		System.out.println(legend);
	}

	protected void moveHero() {
		String move = this.myInterface.getMove();

		try {
			switch (move) {
			case MOVE_UP_CHAR:
				if (!(map[this.hero.getXPosition() - 1][hero.getYPosition()].equals(WALL_CHAR)
						|| (map[this.hero.getXPosition() - 1][hero.getYPosition()].equals(DOOR_CHAR)))) {
					map[this.hero.getXPosition()][hero.getYPosition()] = BLANK_SPACE;
					hero.setxPosition(hero.getXPosition() - 1);
				}
				break;
			case MOVE_DOWN_CHAR:
				if (!(map[this.hero.getXPosition() + 1][hero.getYPosition()].equals(WALL_CHAR)
						|| (map[this.hero.getXPosition() + 1][hero.getYPosition()].equals(DOOR_CHAR)))) {
					map[this.hero.getXPosition()][hero.getYPosition()] = BLANK_SPACE;
					hero.setxPosition(hero.getXPosition() + 1);
				}
				break;
			case MOVE_RIGHT_CHAR:
				if (!(map[this.hero.getXPosition()][hero.getYPosition() + 1].equals(WALL_CHAR)
						|| (map[this.hero.getXPosition()][hero.getYPosition() + 1].equals(DOOR_CHAR)))) {
					map[this.hero.getXPosition()][hero.getYPosition()] = BLANK_SPACE;
					hero.setyPosition(hero.getYPosition() + 1);
				}
				break;
			case MOVE_LEFT_CHAR:
				if (!(map[this.hero.getXPosition()][hero.getYPosition() - 1].equals(WALL_CHAR)
						|| (map[this.hero.getXPosition()][hero.getYPosition() - 1].equals(DOOR_CHAR)))) {
					map[this.hero.getXPosition()][hero.getYPosition()] = BLANK_SPACE;
					hero.setyPosition(hero.getYPosition() - 1);
				}
				break;
			default:
				break;

			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("IndexOutOfBoundsException " + e.getMessage());
		}
	}

	protected void checkLever() {
		if (map[this.hero.getXPosition()][hero.getYPosition()].equals(LEVER_CHAR)) {
			for (int i = 0; i < this.map.length; i++) {
				for (int j = 0; j < this.map[i].length; j++) {
					if (map[i][j].equals(DOOR_CHAR)) {
						map[i][j] = "S";
					}
				}
			}
		}
	}
	

}
