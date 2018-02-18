package game;

public class LevelTwo extends Game {

	private static final String OGRE_CHAR = "O";
	private Ogre ogre;

	public LevelTwo() {
		// passes map and legend as argument
		super(new String[][] {
				{ WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR },
				{ DOOR_CHAR, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, OGRE_CHAR, BLANK_SPACE, BLANK_SPACE, LEVER_CHAR,
						WALL_CHAR },
				{ WALL_CHAR, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE,
						WALL_CHAR },
				{ WALL_CHAR, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE,
						WALL_CHAR },
				{ WALL_CHAR, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE,
						WALL_CHAR },
				{ WALL_CHAR, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE,
						WALL_CHAR },
				{ WALL_CHAR, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE,
						WALL_CHAR },

				{ WALL_CHAR, HERO_CHAR, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE,
						WALL_CHAR },
				{ WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR } },
				"\nX - Wall \nI - Exit Door \nH - Hero \nO - Crazy Ogre \nk - key \nempty cell - free space", 7, 1);
		ogre = new Ogre(1, 4);
	}

	public void playLevelTwo() {
		System.out.println("Nivel 2!!!");
		boolean advanceLevel = false;
		do {
			printMap();
			printLegend();
			moveHero();
			checkLever();
			map[this.hero.getXPosition()][hero.getYPosition()] = HERO_CHAR;
			moveOgre();
			advanceLevel = checkAdvanceLevel();
		} while (!advanceLevel);
	}

	private boolean checkAdvanceLevel() {
		// para terminar basta chegar a um dos cantos
		if (this.hero.getXPosition() == 0 || this.hero.getYPosition() == 0) {
			System.out.println("\nGanhou o jogo");
			return true;
		}

		try {
			if (map[this.hero.getXPosition() - 1][hero.getYPosition()] == OGRE_CHAR
					|| map[this.hero.getXPosition() + 1][hero.getYPosition()] == OGRE_CHAR
					|| map[this.hero.getXPosition()][hero.getYPosition() - 1] == OGRE_CHAR
					|| map[this.hero.getXPosition()][hero.getYPosition() + 1] == OGRE_CHAR) {
				System.out.print("\nPerdeu jogo");
				printMap();
				return true;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		return false;
	}

	private void moveOgre() {
		map[ogre.getXPosition()][ogre.getYPosition()] = BLANK_SPACE;
		this.ogre.moveToNextPosition(map[ogre.getXPosition() - 1][ogre.getYPosition()],
				map[ogre.getXPosition()][ogre.getYPosition() + 1]);
		map[ogre.getXPosition()][ogre.getYPosition()] = OGRE_CHAR;
	}

}
