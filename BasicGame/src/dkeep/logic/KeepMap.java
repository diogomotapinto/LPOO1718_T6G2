package dkeep.logic;

import java.util.HashSet;
import java.util.ArrayList;
import java.util.HashMap;

import dkeep.logic.model.Club;
import dkeep.logic.model.Lever;
import dkeep.logic.model.Wall;
import dkeep.logic.model.Door;
import dkeep.logic.model.Hero;
import dkeep.logic.model.Ogre;
import dkeep.logic.model.Position;
import utilities.Utilities;

public class KeepMap extends Map {
	private HashMap<Position, Wall> wallMap;
	private HashMap<Position, Door> doorMap;
	private ArrayList<Ogre> ogreList;
	private Ogre ogre;

	public KeepMap(String ogresNumber) {
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

		this.wallMap = new HashMap<Position, Wall>();
		this.doorMap = new HashMap<Position, Door>();
		this.ogreList = new ArrayList<Ogre>();
		parseMap();
		generateFoes(ogresNumber);
		initializeMap();
	}

	

	public KeepMap(char[][] map) { 
		super(map, "\nX - Wall \nI - Exit Door \nH - Hero \nO - Crazy Ogre \nk - key \nempty cell - free space",
				"Nivel 2!!!", Utilities.findPosition(map, 'A'), Utilities.findPosition(map, 'k'));

		this.wallMap = new HashMap<Position, Wall>();
		this.doorMap = new HashMap<Position, Door>();
		this.ogreList = new ArrayList<Ogre>();
		parseMap();
		initializeMap();

	}

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

	protected Position moveOgre(Ogre ogre) {
		Position ogrePosition = ogre.getPosition();

		if (isStunned(ogre, ogrePosition)) {
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

	public final boolean canMove(Position position) {
		int counter = 0;
		// check above
		if (playMap[position.getXPosition() - 1][position.getYPosition()] != ' '
				|| !(position.getXPosition() - 1 == this.lever.getPosition().getXPosition()
						&& position.getYPosition() == this.lever.getPosition().getYPosition())) {

			for (int i = 0; i < this.ogreList.size(); i++) {
				if (!(position.getXPosition() - 1 == this.ogreList.get(i).getPosition().getXPosition()
						&& position.getYPosition() == this.ogreList.get(i).getClub().getPosition().getYPosition())) {
					counter++;
				}
			}
		}

		// check above
		if (playMap[position.getXPosition() + 1][position.getYPosition()] != ' '
				|| !(position.getXPosition() + 1 == this.lever.getPosition().getXPosition()
						&& position.getYPosition() == this.lever.getPosition().getYPosition())) {

			for (int i = 0; i < this.ogreList.size(); i++) {
				if (!(position.getXPosition() + 1 == this.ogreList.get(i).getPosition().getXPosition()
						&& position.getYPosition() == this.ogreList.get(i).getClub().getPosition().getYPosition())) {
					counter++;
				}
			}
		}

		// check right
		if (playMap[position.getXPosition()][position.getYPosition() + 1] != ' '
				|| !(position.getXPosition() == this.lever.getPosition().getXPosition()
						&& position.getYPosition() + 1 == this.lever.getPosition().getYPosition())) {

			for (int i = 0; i < this.ogreList.size(); i++) {
				if (!(position.getXPosition() == this.ogreList.get(i).getPosition().getXPosition()
						&& position.getYPosition() + 1 == this.ogreList.get(i).getClub().getPosition()
								.getYPosition())) {
					counter++;
				}
			}
		}

		// check left
		if (playMap[position.getXPosition()][position.getYPosition() - 1] != ' '
				|| !(position.getXPosition() == this.lever.getPosition().getXPosition()
						&& position.getYPosition() - 1 == this.lever.getPosition().getYPosition())) {

			for (int i = 0; i < this.ogreList.size(); i++) {
				if (!(position.getXPosition() == this.ogreList.get(i).getPosition().getXPosition()
						&& position.getYPosition() - 1 == this.ogreList.get(i).getClub().getPosition()
								.getYPosition())) {
					counter++;
				}
			}
		}

		if (counter == 4) {
			return true;
		}

		return false;
	}

	private final boolean isStunned(Ogre ogre, Position ogrePosition) {
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

	public final void moveClub(Ogre ogre) {
		Position clubPosition = ogre.getClub().getPosition();

		playMap[clubPosition.getXPosition()][clubPosition.getYPosition()] = ' ';

		Position newClubPosition;

		do {
			newClubPosition = Utilities.getAdjacentPosition(ogre.getPosition().getXPosition(),
					ogre.getPosition().getYPosition());
		} while (playMap[newClubPosition.getXPosition()][newClubPosition.getYPosition()] == WALL_CHAR
				|| checkWalls(newClubPosition) || checkDoors(newClubPosition) || checkClubs(newClubPosition));

		if (newClubPosition.equals(this.lever.getPosition())) {
			if (!this.hero.getLeverState()) {
				playMap[newClubPosition.getXPosition()][newClubPosition.getYPosition()] = '$';
			}
		} else {
			playMap[newClubPosition.getXPosition()][newClubPosition.getYPosition()] = ogre.getClub().getClubChar();
		}

		ogre.getClub().setPosition(newClubPosition);

	}

	public final boolean checkWalls(Position pos) {

		for (Position key : wallMap.keySet()) {
			if (key.equals(pos)) {
				return true;
			}
		}
		return false;
	}

	public final boolean checkDoors(Position pos) {

		for (Position key : doorMap.keySet()) {
			if (key.equals(pos)) {
				return true;
			}
		}
		return false;
	}

	public final boolean checkClubs(Position pos) {
		for (int i = 0; i < this.ogreList.size(); i++) {
			if (ogreList.get(i).getClub().getPosition().equals(pos)) {
				return true;
			}
		}
		return false;
	}

	private final boolean checkValidPosition(Position position) {

		for (int i = 0; i < this.ogreList.size(); i++) {
			if (position.equals(this.ogreList.get(i).getPosition())
					&& position.hashCode() == this.ogreList.get(i).getPosition().hashCode()) {
				return true;
			}
		}

		return position.equals(this.hero.getPosition()) && position.hashCode() == this.hero.getPosition().hashCode();
	}

	private final void checkIfStunned() {
		for (int i = 0; i < ogreList.size(); i++) {
			if (Utilities.checkAdjacentCollision(this.ogreList.get(i).getPosition(), this.hero.getPosition())) {
				ogreList.get(i).setStunned(true);
			}
		}
	}

	private final boolean checkOgreCollision() {
		for (int i = 0; i < ogreList.size(); i++) {
			if (Utilities.checkAdjacentCollision(this.hero.getPosition(), this.ogreList.get(i).getPosition())) {
				ogreList.get(i).setStunned(true);
				return true;
			}
		}
		return false;
	}

	// corrigido porque se perdia o jogo se passar numa posi��o adjacente ao
	// ogre
	// mas com ele atordoado
	private final boolean checkClubCollision() {
		for (int i = 0; i < ogreList.size(); i++) {
			if (Utilities.checkAdjacentCollision(this.hero.getPosition(),
					this.ogreList.get(i).getClub().getPosition())) {
				return true;
			}
		}
		return false;
	}

	@Override
	protected boolean checkLost() {
		return checkClubCollision();
	}

	@Override
	public byte checkEndLevel() {
		// para terminar basta chegar a um dos cantos
		Position heroPosition = this.hero.getPosition();

		if (checkWon(heroPosition.getYPosition())) {
			return 1;
		}

		// tem de se fazer refactor do codigo
		if (checkLost()) {
			return -1;
		}

		return 0;
	}

	public void checkLeverMap() {
		Position leverPos = lever.getPosition();
		if (playMap[leverPos.getXPosition()][leverPos.getYPosition()] == ' ' && !this.hero.getLeverState()) {
			playMap[leverPos.getXPosition()][leverPos.getYPosition()] = 'k';
		}
	}

	@Override
	public Map nextLevel(String info) {
		return null;
	}
	@Override
	public Map nextLevel(char[][] map) {
		return null;
	}
	@Override
	protected void initializeMap() {
		for (int i = 0; i < ogreList.size(); i++) {
			setOgre(moveOgre(ogreList.get(i)), ogreList.get(i));
			moveClub(ogreList.get(i));
		}
		Position heroPosition = hero.getPosition();
		playMap[heroPosition.getXPosition()][heroPosition.getYPosition()] = this.hero.getCharHeroLvl2();
	}

	@Override
	protected void generateFoes(String numberOfOgres) {
		ogreList = new ArrayList<Ogre>();
		int ogresNumber = Integer.parseInt(numberOfOgres);
		Position leverPos = new Position(1, 8);
		lever.setPosition(leverPos);
		int x;
		int y;
		for (int i = 0; i < ogresNumber; i++) {
			do {
				x = Utilities.generateRandomNumber(1, 5);
				y = Utilities.generateRandomNumber(1, 5);
				ogre = new Ogre(x, y);
			} while (playMap[x][y] != CHAR_BLANK_SPACE);
			moveClub(ogre);
			ogreList.add(ogre);
		}
	}

	@Override
	public void play(char move) {
		super.moveHero(move, super.lever.isActivated() ? hero.getCharHeroKey() : hero.getCharHeroLvl2());
		super.checkLever();
		for (int i = 0; i < ogreList.size(); i++) {

			setOgre(moveOgre(ogreList.get(i)), ogreList.get(i));
			checkOgreCollision();
			moveClub(ogreList.get(i));
		}
		checkLeverMap();
		checkIfStunned();
	}

}
