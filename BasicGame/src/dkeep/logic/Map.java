package dkeep.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Map implements PlayMap, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8616146323120675712L;
	/**
	 * 
	 */
	// mudar variaveis para classes respetivas
	private static final char CHAR_MOVE_UP = 'w';
	private static final char CHAR_MOVE_DOWN = 's';
	private static final char CHAR_MOVE_RIGHT = 'd';
	private static final char CHAR_MOVE_LEFT = 'a';

	protected static final char WALL_CHAR = 'X';
	protected static final char CHAR_DOOR_CLOSED = 'I';
	private static final char CHAR_DOOR_OPEN = 'S';

	protected static final char CHAR_BLANK_SPACE = ' ';

	private final String legend;
	protected String header;
	protected HashMap<Position, Wall> wallMap;
	protected HashMap<Position, Door> doorMap;
	protected ArrayList<Ogre> ogreList;

	protected Door door;
	protected final Lever lever;
	protected final Hero hero;
	protected final char[][] playMap;

	/**
	 * Class constructor
	 * 
	 * @param playMap
	 * @param legend
	 * @param header
	 * @param heroPosition
	 * @param leverPosition
	 */
	protected Map(char[][] playMap, String legend, String header, Position heroPosition, Position leverPosition) {
		this.playMap = playMap;
		this.legend = legend;
		this.header = header;
		this.door = new Door(0, 0);
		this.wallMap = new HashMap<Position, Wall>();
		this.doorMap = new HashMap<Position, Door>();
		this.ogreList = new ArrayList<Ogre>();
		hero = new Hero(heroPosition.getXPosition(), heroPosition.getYPosition());
		this.lever = new Lever(leverPosition.getXPosition(), leverPosition.getYPosition());
	}

	/* Abstract Methods to be implemented in subclasses */
	protected abstract void initializeMap();

	protected abstract void generateFoes(String info);

	protected abstract boolean checkLost();

	protected abstract void resetBackground();
	/* End of Abstract Methods */

	protected void parseMap() {
		for (int i = 0; i < this.playMap.length; i++) {
			for (int j = 0; j < this.playMap[i].length; j++) {
				Position position = new Position(i, j);

				if (playMap[i][j] == 'X') {
					Wall wall = new Wall(i, j);
					this.wallMap.put(position, wall);
				} else if (playMap[i][j] == 'I') {
					this.doorMap.put(position, new Door(i, j));
				}
			}
		}

	}

	/**
	 * Makes the hero move
	 * 
	 * @param move
	 *            direction of the movement
	 * @param heroChar
	 *            character of the hero
	 */
	protected final void moveHero(char move, char heroChar) {
		Position p = hero.getPosition();
		int xPosition = p.getXPosition();
		int yPosition = p.getYPosition();

		switch (move) {
		case CHAR_MOVE_UP:
			if (checkMoveHero(xPosition - 1, yPosition)) {
				moveHero(xPosition - 1, yPosition, heroChar);
			}
			break;
		case CHAR_MOVE_DOWN:
			if (checkMoveHero(xPosition + 1, yPosition)) {
				moveHero(xPosition + 1, yPosition, heroChar);
			}
			break;
		case CHAR_MOVE_RIGHT:
			if (checkMoveHero(xPosition, yPosition + 1)) {
				moveHero(xPosition, yPosition + 1, heroChar);
			}
			break;
		case CHAR_MOVE_LEFT:
			if (checkMoveHero(xPosition, yPosition - 1)) {
				moveHero(xPosition, yPosition - 1, heroChar);
			}
			break;
		default:
			break;
		}
	}

	// criar objeto lever e checkar atraves das coordenadas do objeto lever em vez
	// de usar coordenadas do map
	/**
	 * Check if the move is allowed
	 * 
	 * @param x
	 * @param y
	 * @return true if it is, false otherwise
	 */
	private boolean checkMoveHero(int x, int y) {
		return (playMap[x][y] == Lever.getLeverChar() || playMap[x][y] == CHAR_BLANK_SPACE
				|| playMap[x][y] == Club.getClubChar() || playMap[x][y] == CHAR_DOOR_OPEN);
	}

	private void moveHero(int x, int y, char heroChar) {
		Position heroPosition = hero.getPosition();
		playMap[heroPosition.getXPosition()][heroPosition.getYPosition()] = CHAR_BLANK_SPACE;
		heroPosition.setXPosition(x);
		heroPosition.setYPosition(y);
		hero.setPosition(heroPosition);
		playMap[x][y] = heroChar;
	}

	/**
	 * checks if the game is won
	 * 
	 * @param y
	 *            position of the hero in the y-axis
	 * @return true if it is won and false otherwise
	 */
	private final boolean checkWon() {
		// devia-se criar um objeto door e testava-se as coordenadas, de forma a no
		// futuro poder escalar o sistema
		return (this.hero.getLeverState() && checkOnDoors(hero.getPosition())
				&& (hero.getPosition().getYPosition() == 0) || hero.getPosition().getXPosition() == 0);// ogre
																										// map
	}

	/**
	 * Checks if the position passed as @param is the same as the position of one of
	 * the doors
	 * 
	 * @param pos
	 * @return true if the position is the same and false otherwise
	 */
	protected final boolean checkOnDoors(Position pos) {

		for (Position key : doorMap.keySet()) {
			if (key.equals(pos)) {
				return true;
			}
		}
		return false;
	}

	public final String getLegend() {
		return legend;
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
	public byte checkEndLevel() {
		// para terminar basta chegar a um dos cantos

		if (checkWon()) {
			return 1;
		}

		// tem de se fazer refactor do codigo
		if (checkLost()) {
			return -1;
		}

		return 0;
	}

	
	public Lever getLever() {
		return this.lever;
	}
}
