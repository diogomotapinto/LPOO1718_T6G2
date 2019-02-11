package dkeep.logic;

import java.io.Serializable;

import dkeep.logic.GameEntity.State;

public class LevelOne extends Level implements Serializable {

	private static final long serialVersionUID = -2705608731076698375L;
	private Lever lever;
	private Guard guard;

	/**
	 * Class constructor
	 * 
	 * @param personality of the guard it can be 'Rookie', 'Drunken' or 'Suspicious'
	 */
	public LevelOne(FoeInfo info) {
		super(new MapDungeon(), "1");

		generateFoes(info);
		super.header = guard.toString();
		this.lever = new Lever(map.getStartingPositionLever().getX(), map.getStartingPositionLever().getY());
//		parseMap();
		fillMap();
	}

	/**
	 * Initializes the Map with the hero/guard/lever in it
	 */
	@Override
	protected final void fillMap() {
		placeOnBoardWalls(map.getWallsMap().values());

		Position pos = lever.getPosition();
		placeOnBoard(pos.getX(), pos.getY(), lever.getChar());

		placeOnBoardDoors(map.getDoorMap().values());
		placeOnBoardDoors(map.getOutDoorMap().values());

		pos = hero.getPosition();
		placeOnBoard(pos.getX(), pos.getY(), hero.getChar());

		pos = guard.getPosition();
		placeOnBoard(pos.getX(), pos.getY(), Guard.getChar());
	}

	/**
	 * Generates the Hero "enimies"
	 * 
	 * @param personality of the guard to be generated
	 */
	@Override
	protected final void generateFoes(FoeInfo personality) {
		MapDungeon dm = (MapDungeon) map;
		guard = new Guard(dm.getRoute(), personality);

	}

	/**
	 * Checks if the game is lost by checking if the guard is in a adjacent position
	 * to the hero
	 */
	@Override
	protected final boolean checkLost() {
		return hero.getCurrentState() == State.DEAD;
	}

	protected final boolean checkWon() {
		// winning positions could be set but since only out doors provide a win, this
		// alternative was chosen
		return (map.isOnOpenWinningDoor(hero.getPosition()) && lever.isActivated());
	}

	@Override
	public boolean hasNextLevel() {
		return true;
	}

	/**
	 * Game goes to the next level
	 * 
	 * @param info needed the next level
	 */
	@Override
	public final Level nextLevel(FoeInfo info) {
		return new LevelTwo(info);
	}

	/**
	 * Game goes to the next level
	 * 
	 * @param map needed to be run in the next level
	 */
	@Override
	public Level nextLevel(char[][] map, byte[] heroPosition, byte[] keyPosition, byte[][] ogrePositions,
			byte[][] winningDoors, byte[][] walls) {
		return new LevelTwo(map, heroPosition, keyPosition, ogrePositions, winningDoors, walls);
	}

	public boolean checkMove(int x, int y) {
		if (x < 0 || x >= this.playMap.length || y < 0 || y >= this.playMap.length) {
			return false;
		}

		// MOVER HEROI
		// pode - empty space, lever, door open;
		// nao pode - wall, door closed, hero, guard)

		Position pos = new Position(x, y);
		if (existsWall(pos) || existsDoor(pos, false) || existsHero(pos) || existsGuard(pos)) {
			return false;
		}
		return true;
	}

	private boolean existsGuard(Position pos) {
		return this.guard.getPosition().equals(pos) && this.guard.getPosition().hashCode() == pos.hashCode();
	}

	/**
	 * Checks if the hero has reached the lever
	 */
	private void checkLeverIsReached() {
		Position heroPosition = hero.getPosition();
		Position leverPosition = lever.getPosition();

		if (leverPosition.equals(heroPosition) && (heroPosition.hashCode() == leverPosition.hashCode())) {
			openMapDoors(!lever.isActivated(), true);
			lever.reverse();
		}
	}

	@Override
	protected boolean existsClub(Position p) {
		return false;
	}

	@Override
	protected boolean existsOgre(Position p) {
		return false;
	}

	@Override
	protected void update() {
		guard.move(this);
		guard.attack(hero);
		checkLeverIsReached();
	}

}
