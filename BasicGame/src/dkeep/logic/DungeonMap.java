package dkeep.logic;

import java.io.Serializable;

import dkeep.logic.model.Drunken;
import dkeep.logic.model.Guard;
import dkeep.logic.model.Hero;
import dkeep.logic.model.Lever;
import dkeep.logic.model.Position;
import dkeep.logic.model.Rookie;
import dkeep.logic.model.Suspicious;
import utilities.Utilities;

public class DungeonMap extends Map implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2705608731076698375L;

	/**
	 * 
	 */

	private static final int route[][] = { { 1, 8 }, { 1, 7 }, { 2, 7 }, { 3, 7 }, { 4, 7 }, { 5, 7 }, { 5, 6 },
			{ 5, 5 }, { 5, 4 }, { 5, 3 }, { 5, 2 }, { 5, 1 }, { 6, 1 }, { 6, 2 }, { 6, 3 }, { 6, 4 }, { 6, 5 },
			{ 6, 6 }, { 6, 7 }, { 6, 8 }, { 5, 8 }, { 4, 8 }, { 3, 8 }, { 2, 8 } };

	private static final String LEGEND = "\nX - Wall \nI - Door \nH - Hero \nG - Guard \nk - lever \nempty cell - free space";
	private Guard guard;

	/**
	 * Class constructor
	 * 
	 * @param personality
	 *            of the guard it can be 'Rookie', 'Drunken' or 'Suspicious'
	 */
	public DungeonMap(String personality) {
		super(new char[][] {
				{ WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR,
						WALL_CHAR },
				{ WALL_CHAR, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_DOOR_CLOSED, CHAR_BLANK_SPACE,
						WALL_CHAR, CHAR_BLANK_SPACE, Guard.getGuardChar(), WALL_CHAR },
				{ WALL_CHAR, WALL_CHAR, WALL_CHAR, CHAR_BLANK_SPACE, WALL_CHAR, WALL_CHAR, WALL_CHAR, CHAR_BLANK_SPACE,
						CHAR_BLANK_SPACE, WALL_CHAR },
				{ WALL_CHAR, CHAR_BLANK_SPACE, CHAR_DOOR_CLOSED, CHAR_BLANK_SPACE, CHAR_DOOR_CLOSED, WALL_CHAR,
						WALL_CHAR, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, WALL_CHAR },
				{ WALL_CHAR, WALL_CHAR, WALL_CHAR, CHAR_BLANK_SPACE, WALL_CHAR, WALL_CHAR, WALL_CHAR, CHAR_BLANK_SPACE,
						CHAR_BLANK_SPACE, WALL_CHAR },
				{ CHAR_DOOR_CLOSED, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE,
						CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, WALL_CHAR },
				{ CHAR_DOOR_CLOSED, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE,
						CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, WALL_CHAR },
				{ WALL_CHAR, WALL_CHAR, WALL_CHAR, CHAR_BLANK_SPACE, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR,
						CHAR_BLANK_SPACE, WALL_CHAR },
				{ WALL_CHAR, CHAR_BLANK_SPACE, CHAR_DOOR_CLOSED, CHAR_BLANK_SPACE, CHAR_DOOR_CLOSED, CHAR_BLANK_SPACE,
						WALL_CHAR, Lever.getLeverChar(), CHAR_BLANK_SPACE, WALL_CHAR },
				{ WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR,
						WALL_CHAR } },
				LEGEND, "Nivel 1!!!", new Position(1, 1), new Position(8, 7));
		generateFoes(personality);
		super.header = guard.toString();
		parseMap();
		initializeMap();
	}

	/**
	 * Makes the gurad move
	 */
	protected void moveGuard() {
		Position guardPosition = guard.getPosition();
		playMap[guardPosition.getXPosition()][guardPosition.getYPosition()] = CHAR_BLANK_SPACE;
		this.guard.moveToNextPosition();
		guardPosition = guard.getPosition();
		playMap[guardPosition.getXPosition()][guardPosition.getYPosition()] = Guard.getGuardChar();
	}

	/**
	 * Initializes the Map with the hero in it
	 */
	@Override
	protected final void initializeMap() {
		Position heroPosition = hero.getPosition();
		playMap[heroPosition.getXPosition()][heroPosition.getYPosition()] = Hero.getCharHero();
	}

	/**
	 * Generates the Hero "enimies"
	 * 
	 * @param personality
	 *            of the guard to be generated
	 */
	@Override
	protected final void generateFoes(String personality) {
		switch (personality) {
		case "Rookie":
			guard = new Rookie(route);
			break;
		case "Drunken":
			guard = new Drunken(route);
			break;
		case "Suspicious":
		default:
			guard = new Suspicious(route);
			break;
		}
	}

	/**
	 * Checks if the game is lost by checking if the guard is in a adjacent position
	 * to the hero
	 */
	@Override
	protected final boolean checkLost() {
		// confirma se posi�oes adjacentes verticais e horizontais as passadas por
		// argumento (heroi) sao as do guarda
		return Utilities.checkAdjacentCollision(hero.getPosition(), guard.getPosition());
	}

	/**
	 * Resets the background objects positions
	 */
	@Override
	protected final void resetBackground() {
		Position position = super.lever.getPosition();
		playMap[position.getXPosition()][position.getYPosition()] = Lever.getLeverChar();

		for (Position key : doorMap.keySet()) {
			playMap[key.getXPosition()][key.getYPosition()] = door.getChar();
		}
	}

	/**
	 * Makes the hero move
	 * 
	 * @param move
	 *            char that set the hero direction
	 */
	@Override
	public final void play(char move) {

		super.moveHero(move, Hero.getCharHero());
		checkLever();
		this.moveGuard();
		if (!hero.getPosition().equals(super.lever.getPosition()) && !checkOnDoors(hero.getPosition())) {
			resetBackground();
		}
	}

	/**
	 * Game goes to the next level
	 * 
	 * @param info
	 *            needed the next level
	 */
	@Override
	public final Map nextLevel(String info) {
		return new KeepMap(info);
	}

	/**
	 * Game goes to the next level
	 * 
	 * @param map
	 *            needed to be run in the next level
	 */
	@Override
	public Map nextLevel(char[][] map) {
		return new KeepMap(map);
	}

	// criar objeto door (com boolean aberto/fechado e checkar atraves das
	// coordenadas do objeto lever) em vez de usar coordenadas do map
	/**
	 * Checks if the hero has reached the lever
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
						playMap[i][j] = 'S';
						hero.setLeverState(true);
						door.setOpen(true);
					}
				}
			}
		}
	}
}
