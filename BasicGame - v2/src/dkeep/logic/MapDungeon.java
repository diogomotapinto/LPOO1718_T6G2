package dkeep.logic;

import java.io.Serializable;
import java.util.HashMap;

public class MapDungeon extends Map implements Serializable {

	private static final long serialVersionUID = 4317907170193005562L;
	private static char map[][] = {
			{ CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE,
					CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE },
			{ CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE,
					CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE },
			{ CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE,
					CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE },
			{ CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE,
					CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE },
			{ CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE,
					CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE },
			{ CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE,
					CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE },
			{ CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE,
					CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE },
			{ CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE,
					CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE },
			{ CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE,
					CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE },
			{ CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE,
					CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE } };
	private static byte winningDoors[][] = { { 5, 0 }, { 6, 0 } };
	private static byte doors[][] = { { 1, 4 }, { 3, 2 }, { 3, 4 }, { 8, 2 }, { 8, 4 } };
	private static byte walls[][] = { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 0, 3 }, { 0, 4 }, { 0, 5 }, { 0, 6 }, { 0, 7 },
			{ 0, 8 }, { 0, 9 }, { 1, 0 }, { 1, 6 }, { 1, 9 }, { 2, 0 }, { 2, 1 }, { 2, 2 }, { 2, 4 }, { 2, 5 },
			{ 2, 6 }, { 2, 9 }, { 3, 0 }, { 3, 5 }, { 3, 6 }, { 3, 9 }, { 4, 0 }, { 4, 1 }, { 4, 2 }, { 4, 4 },
			{ 4, 5 }, { 4, 6 }, { 4, 9 }, { 5, 9 }, { 6, 9 }, { 7, 0 }, { 7, 1 }, { 7, 2 }, { 7, 4 }, { 7, 5 },
			{ 7, 6 }, { 7, 7 }, { 7, 9 }, { 8, 0 }, { 8, 6 }, { 8, 9 }, { 9, 0 }, { 9, 1 }, { 9, 2 }, { 9, 3 },
			{ 9, 4 }, { 9, 5 }, { 9, 6 }, { 9, 7 }, { 9, 8 }, { 9, 9 } };
	private static byte heroPosition[] = { 1, 1 };
	private static byte guardRoute[][] = new byte[][] { { 1, 8 }, { 1, 7 }, { 2, 7 }, { 3, 7 }, { 4, 7 }, { 5, 7 },
			{ 5, 6 }, { 5, 5 }, { 5, 4 }, { 5, 3 }, { 5, 2 }, { 5, 1 }, { 6, 1 }, { 6, 2 }, { 6, 3 }, { 6, 4 },
			{ 6, 5 }, { 6, 6 }, { 6, 7 }, { 6, 8 }, { 5, 8 }, { 4, 8 }, { 3, 8 }, { 2, 8 } };

//	private static byte simpleGuardYmovement[] = new byte[] { 8, 7, 7, 7, 7, 7, 6, 5, 4, 3, 2, 1, 1, 2, 3, 4, 5, 6, 7,
//			8, 8, 8, 8, 8 };
//
//	private static byte simpleGuardXmovement[] = new byte[] { 1, 1, 2, 3, 4, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6,
//			6, 5, 4, 3, 2 };

	private static byte leverPosition[] = { 8, 7 };
	private static boolean instantaneousDoorOpen = true;

	private HashMap<Position, Door> doorMap;
	private Position startingPositionLever;

	public MapDungeon() {
		super(map, winningDoors, walls,
				"\nX - Wall \nI - Door \nH - Hero \nG - Guard \nk - lever \nempty cell - free space", heroPosition,
				false, instantaneousDoorOpen);

		this.doorMap = new HashMap<Position, Door>();
		populateInDoors(doors, this.doorMap);

		this.startingPositionLever = new Position(leverPosition[0], leverPosition[1]);
	}

	@Override
	public final HashMap<Position, Door> getDoorMap() {
		return doorMap;
	}

	@Override
	public Position getStartingPositionLever() {
		return startingPositionLever;
	}

	@Override
	public Position getStartingPositionKey() {
		return new Position(-1, -1);
	}

	public byte[][] getRoute() {
		return guardRoute;
	}
}
