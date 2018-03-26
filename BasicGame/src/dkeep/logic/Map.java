package dkeep.logic;

import dkeep.logic.model.Club;
import dkeep.logic.model.Door;
import dkeep.logic.model.Hero;
import dkeep.logic.model.Lever;
import dkeep.logic.model.Ogre;
import dkeep.logic.model.Position;
import dkeep.logic.model.Wall;

public abstract class Map implements PlayMap {

	// mudar variaveis para classes respetivas
	private static final char CHAR_MOVE_UP = 'w';
	private static final char CHAR_MOVE_DOWN = 's';
	private static final char CHAR_MOVE_RIGHT = 'd';
	private static final char CHAR_MOVE_LEFT = 'a';

	protected static final char WALL_CHAR = 'X';
	protected static final char CHAR_DOOR_CLOSED = 'I';
	protected static final char CHAR_DOOR_OPEN = 'S';

	protected static final char CHAR_BLANK_SPACE = ' ';

	private final String legend;
	protected String header;

	protected final Lever lever;
	protected final Hero hero;
	protected final char[][] playMap;
	
	/**
	 * Class constructor
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
		hero = new Hero(heroPosition.getXPosition(), heroPosition.getXPosition());
		this.lever = new Lever(leverPosition.getXPosition(), leverPosition.getYPosition());
	}

	/* Abstract Methods to be implemented in subclasses */
	protected abstract void initializeMap();

	protected abstract void generateFoes(String info);

	protected abstract boolean checkLost();
	/* End of Abstract Methods */

	/**
	 * Makes the hero move
	 * @param move direction of the movement 
	 * @param heroChar character of the hero
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
	 *Check if the move is allowed 
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

	// criar objeto door (com boolean aberto/fechado e checkar atraves das
	// coordenadas do objeto lever) em vez de usar coordenadas do map
	
	
	/**
	 * Checks if the hero has reached the lever
	 */
	protected final void checkLever() {
		Position heroPosition = hero.getPosition();
		Position leverPosition = lever.getPosition();
		System.out.println();
		System.out.print("Lever ");
		System.out.println(leverPosition);

		System.out.print("Heroi ");
		System.out.println(heroPosition);

		if (leverPosition.equals(heroPosition) && (heroPosition.hashCode() == leverPosition.hashCode())) {
			lever.activateLever();
			// criar classe porta com icone porta fechada e porta aberta em vez de usar
			// coordenadas do map
			for (int i = 0; i < this.playMap.length; i++) {
				for (int j = 0; j < this.playMap[i].length; j++) {
					if (playMap[i][j] == CHAR_DOOR_CLOSED) {
						playMap[i][j] = CHAR_DOOR_OPEN;
						hero.setLeverState(true);
					}
				}
			}
		}
	}

	/**
	 * checks if the game is won 
	 * @param y position of the hero in the y-axis
	 * @return true if it is won and false otherwise
	 */
	protected final boolean checkWon(int y) {
		// devia-se criar um objeto door e testava-se as coordenadas, de forma a no
		// futuro poder escalar o sistema
		return this.hero.getLeverState() && y == 0;// ogre map
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

}
