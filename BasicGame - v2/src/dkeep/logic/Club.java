package dkeep.logic;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;

class Club extends GameEntity implements Serializable {
	private static final long serialVersionUID = 4450097633388885031L;
	private static final int MINIMAL_SAFE_DISTANCE = 1;
	private static final char CLUB_CHAR = '*';
	private static final char CLUB_CHAR_ON_KEY = '$';

	private boolean onKey;

	Club(Position p, Level l) {
		super();
		position = generateClubPosition(l, p, false);
		onKey = false;
	}

	/**
	 * method for getting the club character
	 * 
	 * @return the club char
	 */
	public char getChar() {
		return onKey ? CLUB_CHAR_ON_KEY : CLUB_CHAR;
	}

	public final boolean isOnKey() {
		return onKey;
	}

	public final void setOnKey(boolean onKey) {
		this.onKey = onKey;
	}

	@Override
	public void move(Level l, Position p) {
		// club has no input movement
		this.position = generateClubPosition(l, p, true);

	}

	@Override
	public void move(Level l) {
		// club has no simple movement logic based on its own atributes
	}

//	/**
//	 * Generates a new position for the ogre club
//	 * 
//	 * @param ogre to be generated a new position of his club
//	 * @return newClubPosition
//	 */
	private Position generateClubPosition(Level l, Position p, boolean notFirstPosition) {
		Position[] arr = p.getAdjacentPositions();
		Collections.shuffle(Arrays.asList(arr));
		for (Position pTemp : arr) {
			if (isClubPlacingPossible(pTemp, l) && (secureStart(pTemp, l) || notFirstPosition)) {
				return pTemp;
			}
		}
		return position;
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

////	/**
////	 * Sets a new position for the ogre club
////	 * 
////	 * @param newClubPosition new club position
////	 * @param ogre            to be setted a new position of his club
////	 */
//	private final void setClubPosition(Position newClubPosition, Ogre ogre) {
//		ogre.getClub().setPosition(newClubPosition);
//	}

	private boolean isClubPlacingPossible(Position pos, Level l) {
		if (pos.getX() < 0 || pos.getX() >= l.getPlayMap().length || pos.getY() < 0
				|| pos.getY() >= l.getPlayMap().length) {
			return false;
		}

		// COLOCAR (pode - key, empty cell ; nao pode - wall, ogre, club, exit door)

		if (l.existsWall(pos) || l.existsOgre(pos) || l.existsClub(pos) || l.existsDoor(pos, false)
				|| l.existsDoor(pos, true)) {
			return false;
		}

		return true;
	}

}
