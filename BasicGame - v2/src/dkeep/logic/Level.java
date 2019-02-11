package dkeep.logic;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import utilities.Utilities;

public abstract class Level implements AdvanceLevel, Serializable {
	// interface AdvanceLevel - each level must declare which level is the next one
	// after itself. This strategy was chosen, instead of loading every level and
	// iterating over them, since this methodology makes not only the application
	// lighter/faster but also easier for maintainability. other alternative would
	// be to have the out doors provide each one a new level if in the same level
	// the player could move to more than one different level

	private static final long serialVersionUID = 8616146323120675712L;

	protected char[][] playMap;
	protected String header;
	protected Map map;

	protected Hero hero;

	/**
	 * Class constructor
	 * 
	 * @param baseMap
	 * @param legend
	 * @param header
	 * @param heroPosition
	 * @param leverPosition
	 * @param winningDoors
	 * @param doors
	 */
	protected Level(Map map, String header) {
		this.map = map;
		this.playMap = Utilities.deepCopy(map.getMap());
		this.header = "Nivel " + header + "!!!";

//		this.wallMap = new HashMap<Position, Wall>();

		hero = new Hero(map.getStartingPositionHero().getX(), map.getStartingPositionHero().getY(),
				map.isAttackingHero());
	}

	/* Abstract Methods to be implemented in subclasses */
	protected abstract void fillMap();

	protected abstract void generateFoes(FoeInfo info);

	protected abstract boolean checkLost();

	protected abstract boolean checkWon();

	protected abstract void update();

	protected abstract boolean checkMove(int x, int y);

	protected abstract boolean existsClub(Position p);

	protected abstract boolean existsOgre(Position p);

	/* End of Abstract Methods */

//	protected void parseMap() {
//		for (int i = 0; i < this.baseMap.length; i++) {
//			for (int j = 0; j < this.baseMap[i].length; j++) {
//				Position position = new Position(i, j);
//
//				if (baseMap[i][j] == 'X') {
//					Wall wall = new Wall(i, j);
//					this.wallMap.put(position, wall);
//				} else if (baseMap[i][j] == 'I') {
//					this.doorMap.put(position, new Door(i, j));
//				}
//			}
//		}
//
//	}

	public void play(Direction move) {
		playMap = cloneMap();
		hero.move(this, this.getPosition(hero, move));
		update();
		fillMap();
	}

	/**
	 * checks if the game is won
	 * 
	 * @param y position of the hero in the y-axis
	 * @return true if it is won and false otherwise
	 */

	private char[][] cloneMap() {
		return Utilities.deepCopy(map.getMap());
	}

	/**
	 * Makes the hero move
	 * 
	 * @param move     direction of the movement
	 * @param heroChar character of the hero
	 */
	private final Position getPosition(GameEntity obj, Direction move) {
		int x = obj.getPosition().getX();
		int y = obj.getPosition().getY();

		switch (move) {
		case UP:
		case DOWN:
		case RIGHT:
		case LEFT:
		case INVALID:
			x += move.getVector()[0];
			y += move.getVector()[1];
			break;
		default:
			break;
		}
		return new Position(x, y);
	}

	// ----------------------------------------------
	protected void placeOnBoardDoors(Collection<Door> arr) {
		for (Door elem : arr) {
			Position pos = elem.getPosition();
			placeOnBoard(pos.getX(), pos.getY(), elem.getChar());
		}
	}

	protected void placeOnBoardWalls(Collection<Wall> arr) {
		for (Wall elem : arr) {
			Position pos = elem.getPosition();
			placeOnBoard(pos.getX(), pos.getY(), Wall.getChar());
		}
	}
	// ----------------------------------------------

	protected void placeOnBoard(int x, int y, char symbol) {
		playMap[x][y] = symbol;
	}

	public final char[][] getPlayMap() {
		return playMap;
	}

	public final String getHeader() {
		return header;
	}

	public final Hero getHero() {
		return hero;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		StringBuilder stringbuilder = new StringBuilder();

		for (int i = 0; i < this.playMap.length; i++) {
			for (int j = 0; j < this.playMap[i].length; j++) {
				stringbuilder.append(this.playMap[i][j]);
				stringbuilder.append(" ");
			}
			stringbuilder.append("\n");
		}

		return stringbuilder.toString();
	}

	/**
	 * Checks if the game is over
	 * 
	 * @return 1 if the game is won, -1 if the game is lost and 0 if the game isn't
	 *         over or lost
	 */
	public byte getGameState() {
		if (checkWon()) {
			return 1;
		}

		if (checkLost()) {
			return -1;
		}

		return 0;
	}

	private void updateDoor(HashMap<Position, Door> hash, boolean open, boolean instantUnlocked) {
		for (Door door : hash.values()) {
			if (instantUnlocked || ((hero.isAdjacent(door) || hero.isAt(door)) && hero.isWithKey())) {
				if (instantUnlocked || door.isUnlocked()) {
					door.setOpen(open);
				} else {
					door.setUnlocked(true);
				}
			}
		}
	}

	protected void openMapDoors(boolean open, boolean instantUnlocked) {
		updateDoor(this.map.getDoorMap(), open, instantUnlocked);
		updateDoor(this.map.getOutDoorMap(), open, instantUnlocked);
	}

	public final Map getMap() {
		return map;
	}

	protected boolean existsWall(Position pos) {
		return this.map.getWallsMap().containsKey(pos);
	}

	private boolean existsDoorIn(Position pos, boolean open) {
		boolean flag = map.getDoorMap().containsKey(pos);
		if (!flag) {
			return false;
		}

		Door d = map.getDoorMap().get(pos);
		if (d.isOpen() == open) {
			return true;
		}
		return false;
	}

	private boolean existsDoorOut(Position pos, boolean open) {
		boolean flag = map.getOutDoorMap().containsKey(pos);
		if (!flag) {
			return false;
		}

		Door d = map.getOutDoorMap().get(pos);
		if (d.isOpen() == open) {
			return true;
		}
		return false;
	}

	protected boolean existsDoor(Position pos, boolean open) {
		return existsDoorIn(pos, open) || existsDoorOut(pos, open);
	}

	protected boolean existsHero(Position pos) {
		return hero.getPosition().equals(pos) && (hero.getPosition().hashCode() == pos.hashCode());
	}
}
