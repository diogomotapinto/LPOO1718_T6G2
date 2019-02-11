package dkeep.logic;

interface AdvanceLevel {

	public boolean hasNextLevel();

	public Level nextLevel(FoeInfo info) throws NextLevelNotAvailable;

	public Level nextLevel(char[][] map, byte[] heroPosition, byte[] keyPosition, byte[][] ogrePositions,
			byte[][] winningDoors, byte[][] walls) throws NextLevelNotAvailable;

}
