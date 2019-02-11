package dkeep.logic;

import java.io.Serializable;
import java.util.HashMap;

public class MapKeep extends Map implements Serializable {

	private static final long serialVersionUID = -1459583532476972514L;
	private static char map[][] = {
			{ CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE,
					CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE },
			{ CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE,
					CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE },
			{ CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE,
					CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE },
			{ CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE,
					CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE },
			{ CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE,
					CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE },
			{ CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE,
					CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE },
			{ CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE,
					CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE },
			{ CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE,
					CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE },
			{ CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE,
					CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE } };
	private static byte winningDoors[][] = { { 1, 0 } };
	private static byte walls[][] = { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 0, 3 }, { 0, 4 }, { 0, 5 }, { 0, 6 }, { 0, 7 },
			{ 0, 8 }, { 1, 8 }, { 2, 0 }, { 2, 8 }, { 3, 0 }, { 3, 8 }, { 4, 0 }, { 4, 8 }, { 5, 0 }, { 5, 8 },
			{ 6, 0 }, { 6, 8 }, { 7, 0 }, { 7, 8 }, { 8, 0 }, { 8, 1 }, { 8, 2 }, { 8, 3 }, { 8, 4 }, { 8, 5 },
			{ 8, 6 }, { 8, 7 }, { 8, 8 } };
	private static byte heroPosition[] = { 7, 1 };
	private static byte keyPosition[] = { 1, 7 };
	private static boolean instantaneousDoorOpen = false;

	private Position startingPositionKey;

	public MapKeep() {
		super(map, winningDoors, walls,
				"\nX - Wall \nI - Exit Door \nH - Hero \nO - Crazy Ogre \nk - key \nempty cell - free space",
				new byte[] { heroPosition[0], heroPosition[1] }, true, instantaneousDoorOpen);

		this.startingPositionKey = new Position(keyPosition[0], keyPosition[1]);
	}

	public MapKeep(char[][] map, byte[] startingPositionHero, byte[] startingPositionKey, byte[][] winningDoors,
			byte[][] walls) {
		super(map, winningDoors, walls,
				"\nX - Wall \nI - Exit Door \nH - Hero \nO - Crazy Ogre \nk - key \nempty cell - free space",
				startingPositionHero, true, instantaneousDoorOpen);

		this.startingPositionKey = new Position(startingPositionKey[0], startingPositionKey[1]);

	}

	@Override
	public Position getStartingPositionLever() {
		return new Position(-1, -1);
	}

	@Override
	public HashMap<Position, Door> getDoorMap() {
		return new HashMap<Position, Door>();
	}

	@Override
	public Position getStartingPositionKey() {
		return startingPositionKey;
	}

}
