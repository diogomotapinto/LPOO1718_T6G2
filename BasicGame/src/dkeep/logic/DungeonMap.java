package dkeep.logic;

import utilities.Utilities;

public final class DungeonMap extends Map implements MapRules {

	private Guard guard;
	private static final int route[][] = new int[][] { { 1, 8 }, { 1, 7 }, { 2, 7 }, { 3, 7 }, { 4, 7 }, { 5, 7 },
			{ 5, 6 }, { 5, 5 }, { 5, 4 }, { 5, 3 }, { 5, 2 }, { 5, 1 }, { 6, 1 }, { 6, 2 }, { 6, 3 }, { 6, 4 },
			{ 6, 5 }, { 6, 6 }, { 6, 7 }, { 6, 8 }, { 5, 8 }, { 4, 8 }, { 3, 8 }, { 2, 8 } };

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
						WALL_CHAR, Lever.getLeverChar(), CHAR_BLANK_SPACE, WALL_CHAR },
				{ WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR,
						WALL_CHAR } },
				"\nX - Wall \nI - Door \nH - Hero \nG - Guard \nk - lever \nempty cell - free space", 1, 1);
		createGuard();
	}

	private final void createGuard() {
		switch (Utilities.generateRandomNumber(0, 2)) {
		case 0:
			guard = new Rookie(1, 8, route);
			break;
		case 1:
			guard = new Drunken(1, 8, route);
			break;
		case 2:
			guard = new Suspicious(1, 8, route);
			break;
		}

	}

	@Override
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
		// super.printMap();
	}

	private final void moveGuard() {
		Position guardPosition = guard.getPosition();
		map[guardPosition.getXPosition()][guardPosition.getYPosition()] = CHAR_BLANK_SPACE;
		this.guard.moveToNextPosition();
		guardPosition = guard.getPosition();
		map[guardPosition.getXPosition()][guardPosition.getYPosition()] = Guard.getGuardChar();
	}

	@Override
	public final void printHeader() {
		view.printString("Nivel 1!!!");
		System.out.println(guard);
		// view.printCharacter(guard);
	}

	@Override
	public final void initializeMap() {
		Position heroPosition = hero.getPosition();
		map[heroPosition.getXPosition()][heroPosition.getYPosition()] = hero.getHeroChar();
	}

	@Override
	public final boolean checkEndLevel() {
		Position heroPosition = hero.getPosition();
		int xPosition = heroPosition.getXPosition();
		int yPosition = heroPosition.getYPosition();
		if (checkWon(xPosition, yPosition)) {
			System.out.println("\nProximo Nivel!!!\n");
			return true;
		}

		if (checkLost(xPosition, yPosition)) {
			printMap();
			System.out.print("\nPerdeu jogo");
			super.gameState = false;
			return true;
		}

		return false;
	}

	@Override
	public final boolean checkWon(int x, int y) {
		// devia-se criar um objeto door e testava-se as coordenadas, de forma a no
		// futuro poder escalar o sistema
		return y == 0;
	}

	@Override
	public final boolean checkLost(int x, int y) {
		// confirma se posi�oes adjacentes verticais e horizontais as passadas por
		// argumento (heroi) sao as do guarda
		return Utilities.checkAdjacentCollision(hero.getPosition(), guard.getPosition());

	}

}
