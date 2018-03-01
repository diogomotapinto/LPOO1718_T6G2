package dkeep.logic;

import utilities.Utilities;

public class DungeonMap extends Map implements MapRules {

	private Guard guard;

	public DungeonMap() {
		super(new char[][] {
				{ WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR,
						WALL_CHAR },
				{ WALL_CHAR, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_DOOR_CLOSED, CHAR_BLANK_SPACE,
						WALL_CHAR, CHAR_BLANK_SPACE, Guard.getGuardChar(), WALL_CHAR },
				{ WALL_CHAR, WALL_CHAR, WALL_CHAR, CHAR_BLANK_SPACE, WALL_CHAR, WALL_CHAR, WALL_CHAR, CHAR_BLANK_SPACE,
						CHAR_BLANK_SPACE, WALL_CHAR },
				{ WALL_CHAR, CHAR_BLANK_SPACE, CHAR_DOOR_CLOSED, CHAR_BLANK_SPACE, CHAR_DOOR_CLOSED, WALL_CHAR,
						WALL_CHAR, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, WALL_CHAR },
				{ WALL_CHAR, WALL_CHAR, WALL_CHAR, CHAR_BLANK_SPACE, WALL_CHAR, WALL_CHAR, WALL_CHAR, CHAR_BLANK_SPACE,
						CHAR_BLANK_SPACE, WALL_CHAR },
				{ CHAR_DOOR_CLOSED, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE,
						CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, WALL_CHAR },
				{ CHAR_DOOR_CLOSED, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE,
						CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, WALL_CHAR },
				{ WALL_CHAR, WALL_CHAR, WALL_CHAR, CHAR_BLANK_SPACE, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR,
						CHAR_BLANK_SPACE, WALL_CHAR },
				{ WALL_CHAR, CHAR_BLANK_SPACE, CHAR_DOOR_CLOSED, CHAR_BLANK_SPACE, CHAR_DOOR_CLOSED, CHAR_BLANK_SPACE,
						WALL_CHAR, CHAR_LEVER, CHAR_BLANK_SPACE, WALL_CHAR },
				{ WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR,
						WALL_CHAR } },
				"\nX - Wall \nI - Door \nH - Hero \nG - Guard \nk - lever \nempty cell - free space", 1, 1);
		createGuard();
	}

	private void createGuard() {
		switch (Utilities.generateRandomNumber(0, 2)) {
		case 0:
			guard = new Rookie(1, 8);
			break;
		case 1:
			guard = new Drunken(1, 8);
			break;
		case 2:
			guard = new Suspicious(1, 8);
			break;
		}

	}

	public final void playLevel() {
		printHeader();
		initializeMap();
		do {
			super.printMap();
			super.printLegend();
			super.moveHero(this.view.getMove());
			super.checkLever();
			this.moveGuard();
		} while (!checkEndLevel());

		// printar estado final do mapa deste nivel
		super.printMap();
	}

	private final void moveGuard() {
		map[guard.getXPosition()][guard.getYPosition()] = CHAR_BLANK_SPACE;
		this.guard.moveToNextPosition();
		map[guard.getXPosition()][guard.getYPosition()] = Guard.getGuardChar();
	}

	@Override
	public void printHeader() {
		System.out.println("Nivel 1!!!");
	}

	@Override
	public void initializeMap() {
		map[hero.getXPosition()][hero.getYPosition()] = CHAR_HERO;
	}

	@Override
	public boolean checkEndLevel() {

		if (checkWon(hero.getXPosition(), hero.getYPosition())) {
			System.out.println("\nProximo Nivel!!!\n");
			return true;
		}

		if (checkLost(hero.getXPosition(), hero.getYPosition())) {
			printMap();
			System.out.print("\nPerdeu jogo");
			super.gameState = false;
			return true;
		}

		return false;
	}

	@Override
	public boolean checkWon(int x, int y) {
		// devia-se criar um objeto door e testava-se as coordenadas, de forma a no
		// futuro poder escalar o sistema
		return y == 0;
	}

	@Override
	public boolean checkLost(int x, int y) {
		// confirma se posiçoes adjacentes verticais e horizontais as passadas por
		// argumento (heroi) sao as do guarda
		return (((x - 1) == guard.getXPosition() || (x + 1) == guard.getXPosition()) && y == guard.getYPosition())
				|| (((y - 1) == guard.getYPosition() || (y + 1) == guard.getYPosition()) && x == guard.getXPosition());
	}

}
