package dkeep.logic;

import java.io.Serializable;
import java.util.ArrayList;
import dkeep.logic.GameEntity.State;

public class LevelTwo extends Level implements Serializable {

	private static final long serialVersionUID = 4339149666601813329L;
	private ArrayList<Ogre> ogreList;
	private Key key;

	/**
	 * Class constructor
	 * 
	 * @param ogresNumber number of ogres in the game
	 */
	LevelTwo(FoeInfo ogresNumber) {
		super(new MapKeep(), "2");

//		parseMap();
		key = new Key(map.getStartingPositionKey().getX(), map.getStartingPositionKey().getY());
		ogreList = new ArrayList<Ogre>();
		generateFoes(ogresNumber);

		fillMap();
	}

	/**
	 * Class constructor that receives the map as a @param instead of the number of
	 * ogres
	 * 
	 * @param map of the game
	 */
	LevelTwo(char[][] byteMap, byte[] heroPosition, byte[] keyPosition, byte[][] ogrePositions, byte[][] winningDoors,
			byte[][] walls) {
		super(new MapKeep(byteMap, heroPosition, keyPosition, winningDoors, walls), "2");
		key = new Key(map.getStartingPositionKey().getX(), map.getStartingPositionKey().getY());
		ogreList = new ArrayList<Ogre>();
		generateOgres(ogrePositions);
//		parseMap();
		fillMap();
	}

	/**
	 * Parses the information of the map and initializes the objects in it it also
	 * sets the new position for the hero and for the lever
	 */
//	public void parseMap() {
//		for (int i = 0; i < map.getMap().length; i++) {
//			for (int j = 0; j < map.getMap()[i].length; j++) {
//				Position position = new Position(i, j);
//
//				switch (map.getMap()[i][j]) {
//				case 'I':
//					Door door = new DoorOut(i, j);
//					map.getOutDoorMap().put(position, door);
//					break;
//				case 'O':
//					Ogre ogre = new Ogre(i, j);
//					this.ogreList.add(ogre);
//					break;
//				case 'H':
//					this.hero.setPosition(i, j);
//					break;
////				case 'K':
////					map..setPosition(i, j);
////					break;
//				default:
//					break;
//				}
//			}
//		}
//
//	}

	/**
	 * Checks if the game is lost, in case there is collision between the hero and
	 * the club
	 * 
	 * @return true if it is and false otherwise
	 */
	@Override
	protected boolean checkLost() {
		return hero.getCurrentState() == State.DEAD;
	}

	protected final boolean checkWon() {
		return (map.isOnOpenWinningDoor(hero.getPosition()) && hero.isWithKey());
	}

	@Override
	public boolean hasNextLevel() {
		return false;
	}

	/**
	 * Throws exception because there is no level after this one
	 */
	@Override
	public Level nextLevel(FoeInfo info) throws NextLevelNotAvailable {
		throw new NextLevelNotAvailable();
	}

	/**
	 * Throws exception because there is no level after this one
	 */
	@Override
	public Level nextLevel(char[][] map, byte[] heroPosition, byte[] keyPosition, byte[][] ogrePositions,
			byte[][] winningDoors, byte[][] walls) throws NextLevelNotAvailable {
		throw new NextLevelNotAvailable();
	}

	private void openDoor() {
		openMapDoors(true, this.map.isInstantaneousDoorUnlocked());
	}

	private void checkClubOnKey(Club c) {
		boolean flag = (c.getPosition().equals(map.getStartingPositionKey())
				&& (c.getPosition().hashCode() == map.getStartingPositionKey().hashCode() && !key.isPicked()));
		c.setOnKey(flag);
	}

	private void checkOgreOnKey(Ogre o) {
		boolean flag = (o.getPosition().equals(map.getStartingPositionKey())
				&& (o.getPosition().hashCode() == map.getStartingPositionKey().hashCode() && !key.isPicked()));
		o.setOnKey(flag);
	}

	/**
	 * Initializes Map with the characters and sets their position on the map
	 * 
	 */
	@Override
	protected void fillMap() {
		placeOnBoardWalls(map.getWallsMap().values());

		Position pos = key.getPosition();
		placeOnBoard(pos.getX(), pos.getY(), key.getChar());

		placeOnBoardDoors(map.getDoorMap().values());
		placeOnBoardDoors(map.getOutDoorMap().values());

		pos = hero.getPosition();
		placeOnBoard(pos.getX(), pos.getY(), hero.getChar());

		for (Ogre ogre : ogreList) {
			pos = ogre.getPosition();
			placeOnBoard(pos.getX(), pos.getY(), ogre.getChar());
			pos = ogre.getClub().getPosition();
			placeOnBoard(pos.getX(), pos.getY(), ogre.getClub().getChar());
		}
	}

	/**
	 * Generates the enemies positions if the map hasn't been created by the user
	 * 
	 * @param numberOfOgres to be initialized and generated with random positions
	 */
	@Override
	protected final void generateFoes(FoeInfo info) {
		generateOgres(((OgreNumber) info).getRepresentation());
	}

	private void generateOgres(int ogresNumber) {
		Ogre ogre;

		for (int i = 0; i < ogresNumber; i++) {
			ogre = new Ogre(this);
			ogreList.add(ogre);
		}
	}

	private void generateOgres(byte[][] ogrePositions) {
		Ogre ogre;
		for (int i = 0; i < ogrePositions.length; i++) {
			ogre = new Ogre(this, new Position(ogrePositions[i][0], ogrePositions[i][1]));
			ogreList.add(ogre);
		}
	}

	protected boolean existsClub(Position pos) {
		for (Ogre o : this.ogreList) {
			if (o.getClub().getPosition().equals(pos) && (o.getClub().getPosition().hashCode() == pos.hashCode())) {
				return true;
			}
		}
		return false;
	}

	@Override
	protected boolean existsOgre(Position pos) {
		for (Ogre o : this.ogreList) {
			if (o.getPosition().equals(pos) && (o.getPosition().hashCode() == pos.hashCode())) {
				return true;
			}
		}
		return false;
	}

	private void stun(Ogre o) {
		o.setStunned((hero.isAdjacent(o) || hero.isAt(o)));
	}

	/**
	 * Checks if the hero has a key
	 */
	private void checkKeyIsPicked() {
		if (key.isPicked()) {
			return;
		}

		Position heroPosition = hero.getPosition();
		Position keyPosition = key.getPosition();

		if (keyPosition.equals(heroPosition) && (heroPosition.hashCode() == keyPosition.hashCode())) {
			key.setPicked(true);
			hero.setIsWithKey(true);
		}
	}

	@Override
	protected boolean checkMove(int x, int y) {
		if (x < 0 || x >= this.playMap.length || y < 0 || y >= this.playMap.length) {
			return false;
		}

		// MOVER HEROI
		// pode - empty space, key, door open
		// nao pode - wall, door closed, hero, ogre, club

		Position pos = new Position(x, y);
		if (existsWall(pos) || existsDoor(pos, false) || existsHero(pos) || existsOgre(pos) || existsClub(pos)) {
			return false;
		}

		return true;
	}

	@Override
	protected void update() {
		for (Ogre o : ogreList) {
			stun(o);
			o.move(this);
			o.attack(hero);
			o.getClub().attack(hero);
		}
		openDoor();
		checkKeyIsPicked();
		for (Ogre o : ogreList) {
			checkOgreOnKey(o);
			checkClubOnKey(o.getClub());
		}

	}

}
