package dkeep.logic;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import utilities.Utilities;

final class Ogre extends GameEntity implements Serializable {

//	private static final int[][] moves = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	private static final long serialVersionUID = -4718734029463699930L;
	private static final int STUN_COUNTER = 2;
	private static final int MINIMAL_SAFE_DISTANCE = 1;
	private static final char OGRE_CHAR = '0';
	private static final char OGRE_CHAR_STUNNED = '8';
	private static final char OGRE_CHAR_ON_KEY = '$';

	private Club club;
	private int stunCounter;
	private boolean stunned;
	private boolean onKey;

	/**
	 * Class constructor
	 * 
	 * @param x position on the x-axis
	 * @param y position on the y-axis
	 */
	public Ogre(Level l) {
		super();
		position = generateOgrePosition(l);
		club = new Club(position, l);
		stunCounter = 0;
		stunned = false;
		onKey = false;
	}

	public Ogre(Level l, Position position) {
		super();
		this.position = position;
		club = new Club(position, l);
		stunCounter = 0;
		stunned = false;
		onKey = false;
	}

	/**
	 * Set if the ogre is stunned or not, if he is stunner put a counter equals to
	 * two
	 * 
	 * @param isStunned true if the ogre is stunned false otherwise
	 */
	public final void setStunned(boolean isStunned) {
		if (isStunned) {
			stunned = isStunned;
			stunCounter = STUN_COUNTER;
		} else if (stunCounter == 0) {
			stunned = isStunned;
		}
	}

	/**
	 * Method used to know if the ogre is stunned or not
	 * 
	 * @return the state of the ogre
	 */
	public final boolean isStunned() {
		return stunned;
	}

	/**
	 * Used to decrement the stun counter
	 */
	private final void updateStunCounter() {
		this.stunCounter--;
	}

	/**
	 * method used to know for how many plays the ogre is stunned
	 * 
	 * @return 2, 1 or 0
	 */
	public final int getStunCounter() {
		return stunCounter;
	}

	/**
	 * Method used to know get the ogre character
	 * 
	 * @return 'O' if the ogre isn't stunned and '8' otherwise
	 */
	public final char getChar() {
		if (stunned) {
			return OGRE_CHAR_STUNNED;
		} else if (onKey) {
			return OGRE_CHAR_ON_KEY;
		} else {
			return OGRE_CHAR;
		}
	}

	public final boolean isOnKey() {
		return onKey;
	}

	public final void setOnKey(boolean onKey) {
		this.onKey = onKey;
	}

	/**
	 * Method use to get the club that belongs to the ogre
	 * 
	 * @return club of the ogre
	 */
	public final Club getClub() {
		return club;
	}

	private Position generateOgrePosition(Level l) {
		int x, y;
		Position pos;

		do {
			x = Utilities.generateRandomNumber(0, l.getPlayMap().length - 1);
			y = Utilities.generateRandomNumber(0, l.getPlayMap().length - 1);
			pos = new Position(x, y);
		} while (!(checkMove(pos, l) && secureStart(pos, l)));

		return new Position(x, y);
	}

	/**
	 * Determine if the position of the ogre is far enought for a secure start of
	 * the hero We set this distance as more than 3 positions
	 * 
	 * @param position
	 * @return true or false
	 */
	private boolean secureStart(Position temp, Level l) {
		return l.getHero().getPosition().distance(temp) > MINIMAL_SAFE_DISTANCE;
	}

	@Override
	public void move(Level l, Position p) {
		// ogre has no input movement
	}

	@Override
	public void move(Level l) {
		moveOgre(l);
		club.move(l, position);
	}

	private void moveOgre(Level l) {
		if (isStunned() && getStunCounter() > 0) {
			updateStunCounter();
			return;
		}

		position.changeTo(generateNewPosition(l));
	}

	private Position generateNewPosition(Level l) {
		Position[] arr = position.getAdjacentPositions();
		Collections.shuffle(Arrays.asList(arr));
		for (Position p : arr) {
			if (checkMove(p, l)) {
				return p;
			}
		}
		return position;
	}

	private boolean checkMove(Position pos, Level l) {
		if (pos.getX() < 0 || pos.getX() >= l.getPlayMap().length || pos.getY() < 0
				|| pos.getY() >= l.getPlayMap().length) {
			return false;
		}

		// COLOCAR
		// pode - key, ogre, empty cell, (sem heroi em casa adjacente)
		// nao pode - wall, hero, club, exit door, ter heroi em casa adjacente)

		// MOVER
		// pode - key, club, exit door open, empty space
		// nao pode - ogre, wall, exit door closed)

		if (l.existsWall(pos) || l.existsHero(pos) || l.existsClub(pos) || l.existsDoor(pos, false)
				|| l.existsDoor(pos, true)) {
			return false;
		}

		if (isAdjacent(l.getHero()) || isAt(l.getHero())) {
			return false;
		}

		return true;
	}

	@Override
	public boolean attack(GameEntity g) {
		// attacked area could be calculated and check if GameEntity g.position is equal
		// to any of them
		boolean flag = (this.isAdjacent(g) || this.isAt(g)) && !stunned;
		if (flag) {
			g.currentState = State.DEAD;
		}
		return flag;
	}
}
