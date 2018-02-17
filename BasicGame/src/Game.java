
public class Game {
	private static final String MOVE_UP_CHAR = "w";
	private static final String MOVE_DOWN_CHAR = "s";
	private static final String MOVE_RIGHT_CHAR = "d";
	private static final String MOVE_LEFT_CHAR = "a";

	private static final String WALL_CHAR = "X";
	private static final String DOOR_CHAR = "I";
	private static final String HERO_CHAR = "H";
	private static final String GUARD_CHAR = "G";
	private static final String LEVER_CHAR = "K";
	private static final String BLANK_SPACE = " ";

	private String[][] map;
	private String[][] legend;
	private Interface myInterface;
	private Hero hero;
	private Guard guard;

	public Game() {
		this.map = new String[][] {
				{ WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR,
						WALL_CHAR },
				{ WALL_CHAR, HERO_CHAR, BLANK_SPACE, BLANK_SPACE, DOOR_CHAR, BLANK_SPACE, WALL_CHAR, BLANK_SPACE,
						GUARD_CHAR, WALL_CHAR },
				{ WALL_CHAR, WALL_CHAR, WALL_CHAR, BLANK_SPACE, WALL_CHAR, WALL_CHAR, WALL_CHAR, BLANK_SPACE,
						BLANK_SPACE, WALL_CHAR },
				{ WALL_CHAR, BLANK_SPACE, DOOR_CHAR, BLANK_SPACE, DOOR_CHAR, WALL_CHAR, WALL_CHAR, BLANK_SPACE,
						BLANK_SPACE, WALL_CHAR },
				{ WALL_CHAR, WALL_CHAR, WALL_CHAR, BLANK_SPACE, WALL_CHAR, WALL_CHAR, WALL_CHAR, BLANK_SPACE,
						BLANK_SPACE, WALL_CHAR },
				{ DOOR_CHAR, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE,
						BLANK_SPACE, WALL_CHAR },
				{ DOOR_CHAR, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE,
						BLANK_SPACE, WALL_CHAR },
				{ WALL_CHAR, WALL_CHAR, WALL_CHAR, BLANK_SPACE, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, BLANK_SPACE,
						WALL_CHAR },
				{ WALL_CHAR, BLANK_SPACE, DOOR_CHAR, BLANK_SPACE, DOOR_CHAR, BLANK_SPACE, WALL_CHAR, LEVER_CHAR,
						BLANK_SPACE, WALL_CHAR },
				{ WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR,
						WALL_CHAR } };

		this.legend = new String[][] { { "X - WALL_CHAR" }, { "I - DOOR_CHAR" }, { "H - Hero" }, { "G - Guard" },
				{ "k - lever" }, { "empty cell - free space" } };
		this.myInterface = new Interface();

		hero = new Hero(1, 1);
		guard = new Guard(1, 8);
	}

	public void playGame() {
		do {
			printMap();
			printLegend();
			moveHero();
			moveGuard();
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

	private void printMap() {
		System.out.println("\n");
		for (int i = 0; i < this.map.length; i++) {
			for (int j = 0; j < this.map[i].length; j++) {
				System.out.print(BLANK_SPACE + this.map[i][j]);
			}
			System.out.println();
		}
	}

	private void printLegend() {
		for (int i = 0; i < this.legend.length; i++) {
			for (int j = 0; j < this.legend[i].length; j++) {
				System.out.print(BLANK_SPACE + this.legend[i][j]);
			}
			System.out.println();
		}
	}

	private void moveHero() {
		String move = this.myInterface.getMove();

		try {
			switch (move) {
			case MOVE_UP_CHAR:
				if (!(map[this.hero.getXPosition() - 1][hero.getYPosition()].equals(WALL_CHAR)
						|| (map[this.hero.getXPosition() - 1][hero.getYPosition()].equals(DOOR_CHAR)))) {
					map[this.hero.getXPosition()][hero.getYPosition()] = BLANK_SPACE;
					hero.setxPosition(hero.getXPosition() - 1);
					checkLever();
					map[this.hero.getXPosition()][hero.getYPosition()] = HERO_CHAR;
				}
				break;
			case MOVE_DOWN_CHAR:
				if (!(map[this.hero.getXPosition() + 1][hero.getYPosition()].equals(WALL_CHAR)
						|| (map[this.hero.getXPosition() + 1][hero.getYPosition()].equals(DOOR_CHAR)))) {
					map[this.hero.getXPosition()][hero.getYPosition()] = BLANK_SPACE;
					hero.setxPosition(hero.getXPosition() + 1);
					checkLever();
					map[this.hero.getXPosition()][hero.getYPosition()] = HERO_CHAR;
				}
				break;
			case MOVE_LEFT_CHAR:
				if (!(map[this.hero.getXPosition()][hero.getYPosition() - 1].equals(WALL_CHAR)
						|| (map[this.hero.getXPosition()][hero.getYPosition() - 1].equals(DOOR_CHAR)))) {
					map[this.hero.getXPosition()][hero.getYPosition()] = BLANK_SPACE;
					hero.setyPosition(hero.getYPosition() - 1);
					checkLever();
					map[this.hero.getXPosition()][hero.getYPosition()] = HERO_CHAR;
				}
				break;
			case MOVE_RIGHT_CHAR:
				if (!(map[this.hero.getXPosition()][hero.getYPosition() + 1].equals(WALL_CHAR)
						|| (map[this.hero.getXPosition()][hero.getYPosition() + 1].equals(DOOR_CHAR)))) {
					map[this.hero.getXPosition()][hero.getYPosition()] = BLANK_SPACE;
					hero.setyPosition(hero.getYPosition() + 1);
					checkLever();
					map[this.hero.getXPosition()][hero.getYPosition()] = HERO_CHAR;
				}
				break;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("IndexOutOfBoundsException " + e.getMessage());
		}
	}

	private void moveGuard() {
		int[] position = this.guard.getPosition();
		map[position[0]][position[1]] = BLANK_SPACE;

		int[] next_position = this.guard.getNextPosition();
		map[next_position[0]][next_position[1]] = GUARD_CHAR;
	}

	private void checkLever() {
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
