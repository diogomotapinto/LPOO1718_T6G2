package dkeep.logic;

import dkeep.logic.model.Drunken;
import dkeep.logic.model.Guard;
import dkeep.logic.model.Lever;
import dkeep.logic.model.Position;
import dkeep.logic.model.Rookie;
import dkeep.logic.model.Suspicious;
import utilities.Utilities;

public class DungeonMap extends Map {

	private static final int route[][] = new int[][] { { 1, 8 }, { 1, 7 }, { 2, 7 }, { 3, 7 }, { 4, 7 }, { 5, 7 },
			{ 5, 6 }, { 5, 5 }, { 5, 4 }, { 5, 3 }, { 5, 2 }, { 5, 1 }, { 6, 1 }, { 6, 2 }, { 6, 3 }, { 6, 4 },
			{ 6, 5 }, { 6, 6 }, { 6, 7 }, { 6, 8 }, { 5, 8 }, { 4, 8 }, { 3, 8 }, { 2, 8 } };

	private static final String header = "\nX - Wall \nI - Door \nH - Hero \nG - Guard \nk - lever \nempty cell - free space";
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
						WALL_CHAR, Lever.getLeverChar(), CHAR_BLANK_SPACE, WALL_CHAR },
				{ WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR,
						WALL_CHAR } },
				header, "Nivel 1!!!", 1, 1);
		generateFoes();
		super.header = guard.toString();
		initializeMap();
	}

	protected void moveGuard() {
		Position guardPosition = guard.getPosition();
		playMap[guardPosition.getXPosition()][guardPosition.getYPosition()] = CHAR_BLANK_SPACE;
		this.guard.moveToNextPosition();
		guardPosition = guard.getPosition();
		playMap[guardPosition.getXPosition()][guardPosition.getYPosition()] = Guard.getGuardChar();
	}

	@Override
	protected final void initializeMap() {
		Position heroPosition = hero.getPosition();
		playMap[heroPosition.getXPosition()][heroPosition.getYPosition()] = hero.getHeroChar();
	}

	@Override
	protected final void generateFoes() {
		switch (Utilities.generateRandomNumber(0, 2)) {
		case 0:
			guard = new Rookie(route);
			break;
		case 1:
			guard = new Drunken(route);
			break;
		case 2:
		default:
			guard = new Suspicious(route);
			break;
		}
	}

	@Override
	protected final boolean checkLost() {
		// confirma se posi�oes adjacentes verticais e horizontais as passadas por
		// argumento (heroi) sao as do guarda
		return Utilities.checkAdjacentCollision(hero.getPosition(), guard.getPosition());
	}

	@Override
	public final void play(char move) {
		super.moveHero(move);
		super.checkLever();
		this.moveGuard();
	}

	@Override
	public final Map nextLevel() {
		return new OgreMap();
	}

	@Override
	public final byte checkEndLevel() {
		Position heroPosition = hero.getPosition();

		if (checkWon(heroPosition.getYPosition())) {
			return 1;
		}

		if (checkLost()) {
			return -1;
		}

		return 0;
	}

}
