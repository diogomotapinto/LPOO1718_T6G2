
public class Game {
	static final String WALL_CHAR = "X";
	static final String DOOR_CHAR = "I";
	static final String HERO_CHAR = "H";
	static final String GUARD_CHAR = "G";
	static final String LEVER_CHAR = "K";
	static final String EMPTY_SPACE_CHAR = " ";

	private String[][] map;
	private String[][] legend;
	private Interface myInterface;
	private Character hero;

	class Legend {
		public String WALL_CHAR;
		public String DOOR_CHAR;
		public String guard;
		public String lever;
		public String freeSpace;
	}

	public Game() {
		this.map = new String[][] {
				{ WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR,
						WALL_CHAR },
				{ WALL_CHAR, HERO_CHAR, EMPTY_SPACE_CHAR, EMPTY_SPACE_CHAR, DOOR_CHAR, EMPTY_SPACE_CHAR, WALL_CHAR,
						EMPTY_SPACE_CHAR, GUARD_CHAR, WALL_CHAR },
				{ WALL_CHAR, WALL_CHAR, WALL_CHAR, EMPTY_SPACE_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, EMPTY_SPACE_CHAR,
						EMPTY_SPACE_CHAR, WALL_CHAR },
				{ WALL_CHAR, EMPTY_SPACE_CHAR, DOOR_CHAR, EMPTY_SPACE_CHAR, DOOR_CHAR, WALL_CHAR, WALL_CHAR,
						EMPTY_SPACE_CHAR, EMPTY_SPACE_CHAR, WALL_CHAR },
				{ WALL_CHAR, WALL_CHAR, WALL_CHAR, EMPTY_SPACE_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, EMPTY_SPACE_CHAR,
						EMPTY_SPACE_CHAR, WALL_CHAR },
				{ DOOR_CHAR, EMPTY_SPACE_CHAR, EMPTY_SPACE_CHAR, EMPTY_SPACE_CHAR, EMPTY_SPACE_CHAR, EMPTY_SPACE_CHAR,
						EMPTY_SPACE_CHAR, EMPTY_SPACE_CHAR, EMPTY_SPACE_CHAR, WALL_CHAR },
				{ DOOR_CHAR, EMPTY_SPACE_CHAR, EMPTY_SPACE_CHAR, EMPTY_SPACE_CHAR, EMPTY_SPACE_CHAR, EMPTY_SPACE_CHAR,
						EMPTY_SPACE_CHAR, EMPTY_SPACE_CHAR, EMPTY_SPACE_CHAR, WALL_CHAR },
				{ WALL_CHAR, WALL_CHAR, WALL_CHAR, EMPTY_SPACE_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR,
						EMPTY_SPACE_CHAR, WALL_CHAR },
				{ WALL_CHAR, EMPTY_SPACE_CHAR, DOOR_CHAR, EMPTY_SPACE_CHAR, DOOR_CHAR, EMPTY_SPACE_CHAR, WALL_CHAR,
						LEVER_CHAR, EMPTY_SPACE_CHAR, WALL_CHAR },
				{ WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR,
						WALL_CHAR } };

		this.legend = new String[][] { { "X - WALL_CHAR" }, { "I - DOOR_CHAR" }, { "H - Hero" }, { "G - Guard" },
				{ "k - lever" }, { "empty cell - free space" } };
		this.myInterface = new Interface();
		int[] pos = this.findCharacter();
		hero = new Hero(pos[0], pos[1]);
	}

	public void playGame() {
		do {
			printMap();
			printLegend();
			moveCharacter();
		} while (check_game_conditions());
	}

	private boolean check_game_conditions() {
		try {
			if (map[this.hero.getXPosition() - 1][hero.getYPosition()] == GUARD_CHAR
					|| map[this.hero.getXPosition() + 1][hero.getYPosition()] == GUARD_CHAR
					|| map[this.hero.getXPosition()][hero.getYPosition() - 1] == GUARD_CHAR
					|| map[this.hero.getXPosition()][hero.getYPosition() + 1] == GUARD_CHAR) {
				System.out.print("\nPerdeu jogo");
				printMap();
				return false;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
		}

		if (this.hero.getXPosition() == 0 || this.hero.getYPosition() == 0) {
			System.out.println("\nGanhou o jogo");
			return false;
		}
		return true;
	}

	public String[][] getMap() {
		return this.map;
	}

	public String[][] getLegend() {
		return this.legend;
	}

	public void printMap() {
		System.out.println("\n");
		for (int i = 0; i < this.map.length; i++) {
			for (int j = 0; j < this.map[i].length; j++) {
				System.out.print(EMPTY_SPACE_CHAR + this.map[i][j]);
			}
			System.out.println();
		}
	}

	public void printLegend() {
		for (int i = 0; i < this.legend.length; i++) {
			for (int j = 0; j < this.legend[i].length; j++) {
				System.out.print(EMPTY_SPACE_CHAR + this.legend[i][j]);
			}
			System.out.println();
		}
	}

	public void moveCharacter() {
		String move = this.myInterface.getMove();

		try {
			switch (move) {
			case "u":
				if (!(map[this.hero.getXPosition() - 1][hero.getYPosition()].equals(WALL_CHAR)
						|| (map[this.hero.getXPosition() - 1][hero.getYPosition()].equals(DOOR_CHAR)))) {
					map[this.hero.getXPosition()][hero.getYPosition()] = EMPTY_SPACE_CHAR;
					hero.setxPosition(hero.getXPosition() - 1);
					checkLever();
					map[this.hero.getXPosition()][hero.getYPosition()] = HERO_CHAR;
				}
				break;
			case "d":
				if (!(map[this.hero.getXPosition() + 1][hero.getYPosition()].equals(WALL_CHAR)
						|| (map[this.hero.getXPosition() + 1][hero.getYPosition()].equals(DOOR_CHAR)))) {
					map[this.hero.getXPosition()][hero.getYPosition()] = EMPTY_SPACE_CHAR;
					hero.setxPosition(hero.getXPosition() + 1);
					checkLever();
					map[this.hero.getXPosition()][hero.getYPosition()] = HERO_CHAR;
				}
				break;
			case "l":
				if (!(map[this.hero.getXPosition()][hero.getYPosition() - 1].equals(WALL_CHAR)
						|| (map[this.hero.getXPosition()][hero.getYPosition() - 1].equals(DOOR_CHAR)))) {
					map[this.hero.getXPosition()][hero.getYPosition()] = EMPTY_SPACE_CHAR;
					hero.setyPosition(hero.getYPosition() - 1);
					checkLever();
					map[this.hero.getXPosition()][hero.getYPosition()] = HERO_CHAR;
				}
				break;
			case "r":
				if (!(map[this.hero.getXPosition()][hero.getYPosition() + 1].equals(WALL_CHAR)
						|| (map[this.hero.getXPosition()][hero.getYPosition() + 1].equals(DOOR_CHAR)))) {
					map[this.hero.getXPosition()][hero.getYPosition()] = EMPTY_SPACE_CHAR;
					hero.setyPosition(hero.getYPosition() + 1);
					checkLever();
					map[this.hero.getXPosition()][hero.getYPosition()] = HERO_CHAR;
				}
				break;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			// printMap();
			System.err.println("IndexOutOfBoundsException " + e.getMessage());
		}

	}

	public int[] findCharacter() {
		int[] pos = { -1, -1 };
		for (int i = 0; i < this.map.length; i++) {
			for (int j = 0; j < this.map[0].length; j++) {
				if (map[i][j].equals(HERO_CHAR)) {
					pos[0] = i;
					pos[1] = j;
					return pos;
				}
			}
		}
		return pos;
	}

	public void checkLever() {
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
