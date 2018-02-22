package dkeep.logic;

//import java.util.Random;

public class LevelTwo extends Game {

	private static final String OGRE_CHAR = "O";
	private static final String CLUB_CHAR = "*";
	private final Ogre ogre;
	// private Random random;
	// private int jogada;

	public LevelTwo() {
		// passes map and legend as argument
		super(new String[][] {
				{ WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR },
				{ DOOR_CHAR, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, LEVER_CHAR,
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
		// jogada = 0;
	}

	public final void playLevelTwo() {
		System.out.println("Nivel 2!!!");
		boolean advanceLevel = false;
		moveClub();
		map[ogre.getXPosition()][ogre.getYPosition()] = OGRE_CHAR;

		do {
			printMap();
			printLegend();
			moveHero();
			checkLever();
			map[this.hero.getXPosition()][this.hero.getYPosition()] = HERO_CHAR;
			moveOgre();
			advanceLevel = checkAdvanceLevel();
		} while (!advanceLevel);
	}

	private final boolean checkAdvanceLevel() {
		// para terminar basta chegar a um dos cantos
		if (this.hero.getXPosition() == 0 || this.hero.getYPosition() == 0) {
			System.out.println("\nGanhou o jogo");
			return true;
		}

		try {
			if (map[this.hero.getXPosition() - 1][hero.getYPosition()] == OGRE_CHAR
					|| map[this.hero.getXPosition() + 1][hero.getYPosition()] == OGRE_CHAR
					|| map[this.hero.getXPosition()][hero.getYPosition() - 1] == OGRE_CHAR
					|| map[this.hero.getXPosition()][hero.getYPosition() + 1] == OGRE_CHAR
					|| map[this.hero.getXPosition() - 1][hero.getYPosition()] == CLUB_CHAR
					|| map[this.hero.getXPosition() + 1][hero.getYPosition()] == CLUB_CHAR
					|| map[this.hero.getXPosition()][hero.getYPosition() - 1] == CLUB_CHAR
					|| map[this.hero.getXPosition()][hero.getYPosition() + 1] == CLUB_CHAR) {

				printMap();
				System.out.print("\nPerdeu jogo");
				super.gameState = false;
				return true;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		return false;
	}

	private final void moveOgre() {

		map[ogre.getXPosition()][ogre.getYPosition()] = BLANK_SPACE;

		int position[];
		do {
			position = Utilities.getAdjacentPosition(ogre.getXPosition(), ogre.getYPosition());
		} while (map[position[0]][position[1]] != BLANK_SPACE);

		ogre.setxPosition(position[0]);
		ogre.setyPosition(position[1]);
		map[ogre.getXPosition()][ogre.getYPosition()] = OGRE_CHAR;
		moveClub();
	}

	private final void moveClub() {
		map[ogre.getMyClub().getXPosition()][ogre.getMyClub().getYPosition()] = BLANK_SPACE;

		int position[];
		do {
			position = Utilities.getAdjacentPosition(ogre.getXPosition(), ogre.getYPosition());
		} while (map[position[0]][position[1]] != BLANK_SPACE);

		Club tempClub = ogre.getMyClub();
		tempClub.setxPosition(position[0]);
		tempClub.setyPosition(position[1]);
		ogre.setMyClub(tempClub);

		map[ogre.getMyClub().getXPosition()][ogre.getMyClub().getYPosition()] = CLUB_CHAR;

	}

}
