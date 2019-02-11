package dkeep.logic;

import java.io.Serializable;
import java.util.HashMap;

public abstract class Map implements InteractableObjects, Serializable {

	private static final long serialVersionUID = 5475213155588436917L;

	private char map[][];
	private String legend;
	private Position startingPositionHero;
	private boolean attackingHero;
	private boolean instantaneousDoorUnlocked;

	private HashMap<Position, Door> outDoorMap;
	private HashMap<Position, Wall> wallsMap;
	protected static final char CHAR_BLANK_SPACE = ' ';

	public Map(char map[][], byte winning_doors[][], byte[][] walls, String legend, byte[] startingPositionHero,
			boolean heroArmed, boolean instantaneousDoorOpen) {

		this.outDoorMap = new HashMap<Position, Door>();
		populateOutDoors(winning_doors, outDoorMap);

		this.wallsMap = new HashMap<Position, Wall>();
		populateWalls(walls, wallsMap);

		this.map = map;
		this.legend = legend;
		this.startingPositionHero = new Position(startingPositionHero[0], startingPositionHero[1]);
		this.attackingHero = heroArmed;
		this.instantaneousDoorUnlocked = instantaneousDoorOpen;
	}

	public abstract HashMap<Position, Door> getDoorMap();

	public boolean isOnOpenWinningDoor(Position pos) {
		for (Door d : this.outDoorMap.values()) {
			if (d.getPosition().equals(pos)) {
				return true;
			}
		}
		return false;
	}

	private void populateOutDoors(byte[][] arr, HashMap<Position, Door> dest) {
		for (byte[] pos : arr) {
			dest.put(new Position(pos[0], pos[1]), new DoorOut(pos[0], pos[1]));
		}
	}

	protected void populateInDoors(byte[][] arr, HashMap<Position, Door> dest) {
		for (byte[] pos : arr) {
			dest.put(new Position(pos[0], pos[1]), new DoorIn(pos[0], pos[1]));
		}
	}

	private void populateWalls(byte[][] arr, HashMap<Position, Wall> dest) {
		for (byte[] pos : arr) {
			dest.put(new Position(pos[0], pos[1]), new Wall(pos[0], pos[1]));
		}
	}

//	public final byte[][] getWinningDoors() {
//		return winningDoors;
//	}

	public final char[][] getMap() {
		return map;
	}

	public final String getLegend() {
		return legend;
	}

//	public final int[] getStartingPositionHero() {
//		return startingPositionHero;
//	}
//
//	public final int[] getStartingPositionLever() {
//		return startingPositionLever;
//	}

	public final Position getStartingPositionHero() {
		return startingPositionHero;
	}

	public final HashMap<Position, Door> getOutDoorMap() {
		return outDoorMap;
	}

	public final boolean isAttackingHero() {
		return attackingHero;
	}

	public final HashMap<Position, Wall> getWallsMap() {
		return wallsMap;
	}

	public boolean isInstantaneousDoorUnlocked() {
		return instantaneousDoorUnlocked;
	}

}
