package dkeep.logic;
import java.util.ArrayList;

public class LevelOne extends Game {

	private static final String GUARD_CHAR = "G";
	private Drunken guard;
	private ArrayList<Guard> guardList;
	private 	int nGuards;

	public LevelOne() {
		// passes map and legend as argument
		super(new String[][] {
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
						WALL_CHAR } },
				"\nX - Wall \nI - Door \nH - Hero \nG - Guard \nk - lever \nempty cell - free space", 1, 1);
		nGuards = Utilities.generateRandomNumber(0,3);
		guard = new Drunken(1, 8);
		
		
	}

	public final void playLevelOne() {
		System.out.println("Nivel 1!!!");
		boolean advanceLevel = false;
		do {
			printMap();
			printLegend();
			moveHero();
			checkLever();
			map[this.hero.getXPosition()][hero.getYPosition()] = HERO_CHAR;
			moveGuard();
			advanceLevel = checkAdvanceLevel();
		} while (!advanceLevel);

		nextLevel();
	}

	private final boolean checkAdvanceLevel() {
		// para terminar basta chegar a um dos cantos
		if (this.hero.getXPosition() == 0 || this.hero.getYPosition() == 0) {
			System.out.println("\nProximo Nivel!!!\n");
			return true;
		}

		try {
			if (map[this.hero.getXPosition() - 1][hero.getYPosition()] == GUARD_CHAR
					|| map[this.hero.getXPosition() + 1][hero.getYPosition()] == GUARD_CHAR
					|| map[this.hero.getXPosition()][hero.getYPosition() - 1] == GUARD_CHAR
					|| map[this.hero.getXPosition()][hero.getYPosition() + 1] == GUARD_CHAR) {

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

	private final void moveGuard() {
		map[guard.getXPosition()][guard.getYPosition()] = BLANK_SPACE;
		this.guard.moveToNextPosition();
		map[guard.getXPosition()][guard.getYPosition()] = GUARD_CHAR;
	}

	private final void nextLevel() {
		LevelTwo levelTwo = new LevelTwo();
		levelTwo.playLevelTwo();
	}
}
