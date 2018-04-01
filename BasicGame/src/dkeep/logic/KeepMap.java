package dkeep.logic;

import java.util.ArrayList;
import java.util.HashMap;

import dkeep.logic.model.Lever;
import dkeep.logic.model.Wall;
import dkeep.logic.model.Club;
import dkeep.logic.model.Door;
import dkeep.logic.model.Hero;
import dkeep.logic.model.Ogre;
import dkeep.logic.model.Position;
import utilities.Utilities;

class KeepMap extends Map {

	/**
	 * Class constructor
	 * 
	 * @param ogresNumber
	 *            number of ogres in the game
	 */
	KeepMap(String ogresNumber) {
		super(new char[][] {
				{ WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR,
						WALL_CHAR },
				{ CHAR_DOOR_CLOSED, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE,
						CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, Lever.getLeverChar(), WALL_CHAR },
				{ WALL_CHAR, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE,
						CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, WALL_CHAR },
				{ WALL_CHAR, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE,
						CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, WALL_CHAR },
				{ WALL_CHAR, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE,
						CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, WALL_CHAR },
				{ WALL_CHAR, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE,
						CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, WALL_CHAR },
				{ WALL_CHAR, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE,
						CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, WALL_CHAR },
				{ WALL_CHAR, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE,
						CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, WALL_CHAR },
				{ WALL_CHAR, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE,
						CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, WALL_CHAR },
				{ WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR,
						WALL_CHAR } },
				"\nX - Wall \nI - Exit Door \nH - Hero \nO - Crazy Ogre \nk - key \nempty cell - free space",
				"Nivel 2!!!", new Position(8, 1), new Position(3, 7)

		);

		ogreList = new ArrayList<Ogre>();
		parseMap();
		generateFoes(ogresNumber);
		initializeMap();
	}

	/**
	 * Class constructor that receives the map as a @param instead of the number of
	 * ogres
	 * 
	 * @param map
	 *            of the game
	 */
	KeepMap(char[][] map) {
		super(map, "\nX - Wall \nI - Exit Door \nH - Hero \nO - Crazy Ogre \nk - key \nempty cell - free space",
				"Nivel 2!!!", Utilities.findPosition(map, 'A'), Utilities.findPosition(map, 'k'));

		this.wallMap = new HashMap<Position, Wall>();
		this.doorMap = new HashMap<Position, Door>();
		this.ogreList = new ArrayList<Ogre>();
		parseMap();
		initializeMap();

	}

	/**
	 * Parses the information of the map and initializes the objects in it it also
	 * sets the new position for the hero and for the lever
	 */
	@Override
	public void parseMap() {
		for (int i = 0; i < super.playMap.length; i++) {
			for (int j = 0; j < super.playMap[i].length; j++) {
				Position position = new Position(i, j);

				switch (super.playMap[i][j]) {
				case 'X':
					Wall wall = new Wall(i, j);
					this.wallMap.put(position, wall);
					break;
				case 'I':
					Door door = new Door(i, j);
					this.doorMap.put(position, door);
					break;
				case 'O':
					Ogre ogre = new Ogre(i, j);
					this.ogreList.add(ogre);
					break;
				case 'H':
					this.hero.setPosition(position);
					break;
				case 'K':
					this.lever.setPosition(position);
					break;
				default:
					break;
				}
			}
		}

	}

	/**
	 * Generates a position for the ogre
	 * 
	 * @param ogre
	 *            that is going to have a new position
	 * @return new ogre position
	 */
	protected Position moveOgre(Ogre ogre) {
		Position ogrePosition = ogre.getPosition();

		if (isStunned(ogre)) {
			return ogre.getPosition();
		}

		playMap[ogrePosition.getXPosition()][ogrePosition.getYPosition()] = ' ';

		if (canMove(ogre.getPosition())) {
			return ogre.getPosition();
		}

		Position newPosition;
		do {
			newPosition = Utilities.getAdjacentPosition(ogrePosition.getXPosition(), ogrePosition.getYPosition());
		} while (playMap[newPosition.getXPosition()][newPosition.getYPosition()] != ' '
				|| (ogre.getPosition().equals(this.lever.getPosition())));

		return newPosition;
	}

	/**
	 * Sets the new position for the ogre and in the map
	 * 
	 * @param ogrePosition
	 *            new ogre position
	 * @param ogre
	 *            ogre to have a new position
	 */
	private final void setOgre(Position ogrePosition, Ogre ogre) {

		ogre.setPosition(ogrePosition);

		if (ogrePosition.equals(this.lever.getPosition())) {
			if (!this.hero.getLeverState()) {
				playMap[ogrePosition.getXPosition()][ogrePosition.getYPosition()] = '$';
			}
		} else {
			playMap[ogrePosition.getXPosition()][ogrePosition.getYPosition()] = ogre.getOgreChar();
		}
	}

	/**
	 * Validates if the ogre can move, if he is with objects around him he won't be
	 * able to move and it will return false
	 * 
	 * @param position
	 * @return true if he is able to move and false otherwise
	 */
	protected final boolean canMove(Position position) {
		int counter = 0;
		// check above
		Position posAbove = new Position(position.getXPosition()-1,position.getYPosition());
		Position posBelow = new Position(position.getXPosition()+1,position.getYPosition());
		Position posRight =  new Position(position.getXPosition(),position.getYPosition()+1);
		Position posLeft =  new Position(position.getXPosition(),position.getYPosition()-1);

		counter += checkPos(posAbove);
		counter += checkPos(posBelow);
		counter +=checkPos(posRight);
		counter += checkPos(posLeft);

		if(counter == 4) {
			return true;
		}else {
			return false;
		}
	}


	protected int checkPos(Position position) {
		
		if (playMap[position.getXPosition() ][position.getYPosition()] != ' '
				|| !(position.getXPosition()  == this.lever.getPosition().getXPosition()
						&& position.getYPosition() == this.lever.getPosition().getYPosition())) {

			for (int i = 0; i < this.ogreList.size(); i++) {
				if (!(position.getXPosition()  == this.ogreList.get(i).getPosition().getXPosition()
						&& position.getYPosition() == this.ogreList.get(i).getClub().getPosition().getYPosition())) {
					return 1;
				}
			}
		}
		return 0;
	}

	/**
	 * Check if the ogre is stunned and reduces the stun counter by one everytime it
	 * is called
	 * 
	 * @param ogre
	 * @return true if he is stunned and false otherwise
	 */
	private final boolean isStunned(Ogre ogre) {
		if (ogre.getStunned()) {
			if (ogre.getStunCounter() > 0) {
				ogre.stunCounter();
				return true;
			} else {
				ogre.setStunned(false);
				return false;
			}
		}
		return false;
	}

	/**
	 * Ganerates and sets a new position for the ogre club
	 * 
	 * @param ogre
	 */
	private final Position moveClub(Ogre ogre) {
		Position clubPosition = ogre.getClub().getPosition();

		playMap[clubPosition.getXPosition()][clubPosition.getYPosition()] = ' ';

		Position newClubPosition;

		do {
			newClubPosition = Utilities.getAdjacentPosition(ogre.getPosition().getXPosition(),
					ogre.getPosition().getYPosition());
		} while (playMap[newClubPosition.getXPosition()][newClubPosition.getYPosition()] == WALL_CHAR
				|| checkWalls(newClubPosition) || checkDoors(newClubPosition) || checkClubs(newClubPosition));

		return newClubPosition;

	}
	
	
	private final void setOgreClub(Position newClubPosition, Ogre ogre) {
		
		if (newClubPosition.equals(this.lever.getPosition())) {
			if (!this.hero.getLeverState()) {
				playMap[newClubPosition.getXPosition()][newClubPosition.getYPosition()] = '$';
			}
		} else {
			playMap[newClubPosition.getXPosition()][newClubPosition.getYPosition()] = Club.getClubChar();
		}

		ogre.getClub().setPosition(newClubPosition);
	}

	/**
	 * Checks if the position passed as @param is the same as the position of the
	 * one of the walls
	 * 
	 * @param pos
	 * @return true if the position is the same and false otherwise
	 */
	private final boolean checkWalls(Position pos) {

		for (Position key : wallMap.keySet()) {
			if (key.equals(pos)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if the position passed as @param is the same as the position of the
	 * one of the doors
	 * 
	 * @param pos
	 * @return true if the position is the same and false otherwise
	 */
	private final boolean checkDoors(Position pos) {

		for (Position key : doorMap.keySet()) {
			if (key.equals(pos)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if the position passed as @param is the same as the position of the
	 * one of the clubs
	 * 
	 * @param pos
	 * @return true if the position is the same and false otherwise
	 */
	private final boolean checkClubs(Position pos) {
		for (int i = 0; i < this.ogreList.size(); i++) {
			if (ogreList.get(i).getClub().getPosition().equals(pos)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if the hero is on an adjacent position to the ogre, if it is sets the
	 * ogre as stunned
	 * 
	 */
	private final void checkIfStunned() {
		for (int i = 0; i < ogreList.size(); i++) {
			if (Utilities.checkAdjacentCollision(this.ogreList.get(i).getPosition(), this.hero.getPosition())) {
				ogreList.get(i).setStunned(true);
			}
		}
	}

	// ver com o joao
	private final boolean checkOgreCollision() {
		for (int i = 0; i < ogreList.size(); i++) {
			if (Utilities.checkAdjacentCollision(this.hero.getPosition(), this.ogreList.get(i).getPosition())) {
				ogreList.get(i).setStunned(true);
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if one of the clubs is in an adjacent position to the club
	 * 
	 * @return if it is and false otherwise
	 */
	private final boolean checkClubCollision() {
		for (int i = 0; i < ogreList.size(); i++) {
			if (Utilities.checkAdjacentCollision(this.hero.getPosition(),
					this.ogreList.get(i).getClub().getPosition())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if the game is lost, in case there is collision between the hero and
	 * the club
	 * 
	 * @return true if it is and false otherwise
	 */
	@Override
	protected boolean checkLost() {
		return checkClubCollision();
	}

	/**
	 * Puts the key on the map if the hero ha
	 */
	private void checkLeverMap() {
		Position leverPos = lever.getPosition();
		if (playMap[leverPos.getXPosition()][leverPos.getYPosition()] == ' ' && !this.hero.getLeverState()) {
			playMap[leverPos.getXPosition()][leverPos.getYPosition()] = 'k';
		}
	}

	@Override
	public Map nextLevel(String info) throws NextLevelNotAvailable {
		throw new NextLevelNotAvailable();
	}

	@Override
	public Map nextLevel(char[][] map) throws NextLevelNotAvailable {
		throw new NextLevelNotAvailable();
	}

	/**
	 * Initializes Map with the characters and sets their position on the map
	 * 
	 */
	@Override
	protected void initializeMap() {
		for (int i = 0; i < ogreList.size(); i++) {
			setOgre(moveOgre(ogreList.get(i)), ogreList.get(i));
			setOgreClub(moveClub(ogreList.get(i)),ogreList.get(i));
		}
		Position heroPosition = hero.getPosition();
		playMap[heroPosition.getXPosition()][heroPosition.getYPosition()] = Hero.getCharHeroLvl2();
	}

	/**
	 * Generates the enemies positions if the map hasn't been created by the user
	 * 
	 * @param numberOfOgres
	 *            to be initialized and generated with random positions
	 */
	@Override
	protected void generateFoes(String numberOfOgres) {

		Position leverPos = new Position(1, 8);
		lever.setPosition(leverPos);

		int x, y;
		Ogre ogre;
		int ogresNumber = Integer.parseInt(numberOfOgres);
		for (int i = 0; i < ogresNumber; i++) {
			do {
				x = Utilities.generateRandomNumber(1, 5);
				y = Utilities.generateRandomNumber(1, 5);
				ogre = new Ogre(x, y);
				
			} while (playMap[x][y] != CHAR_BLANK_SPACE);
			ogreList.add(ogre);
			setOgreClub(moveClub(ogreList.get(i)),ogreList.get(i));
		}
	}

	/**
	 * Resets the background objects positions
	 */
	@Override
	protected final void resetBackground() {
		for (Position key : doorMap.keySet()) {
			playMap[key.getXPosition()][key.getYPosition()] = door.getChar();
		}
	}

	@Override
	public void play(char move) {
		resetBackground();

		super.moveHero(move, super.lever.isActivated() ? Hero.getCharHeroKey() : Hero.getCharHeroLvl2());
		openDoors();
		checkLever();
		for (int i = 0; i < ogreList.size(); i++) {
			setOgre(moveOgre(ogreList.get(i)), ogreList.get(i));
			checkOgreCollision();
			setOgreClub(moveClub(ogreList.get(i)),ogreList.get(i));
		}
		checkLeverMap();
		checkIfStunned();
	}

	/**
	 * Checks if the hero has a key
	 */
	@Override
	public void checkLever() {
		Position heroPosition = hero.getPosition();
		Position leverPosition = lever.getPosition();

		if (leverPosition.equals(heroPosition) && (heroPosition.hashCode() == leverPosition.hashCode())) {
			lever.activateLever();
			// criar classe porta com icone porta fechada e porta aberta em vez de usar
			// coordenadas do map
			for (int i = 0; i < this.playMap.length; i++) {
				for (int j = 0; j < this.playMap[i].length; j++) {
					if (playMap[i][j] == 'I') {
						hero.setLeverState(true);
					}
				}
			}
		}
	}

	/**
	 * Opens the door if the hero has the key and is in an adjacent position to the door
	 */
	protected void openDoors() {

		for (Position key : doorMap.keySet()) {
			if (Utilities.checkAdjacentCollision(key, hero.getPosition()) && this.hero.getLeverState()) {
				playMap[key.getXPosition()][key.getYPosition()] = 'S';
				door.setOpen(true);
			}
		}

	}

}
