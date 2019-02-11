package dkeep.logic;

import java.io.Serializable;

public final class Hero extends GameEntity implements Serializable {

	private static final long serialVersionUID = 8227693520584850801L;
	private static final char CHAR_HERO_ARMED = 'A';
	private static final char CHAR_HERO = 'H';
	private static final char CHAR_HERO_KEY = 'K';
	private boolean withKey;
	private boolean armed;

	/**
	 * Class constructor
	 * 
	 * @param x position of the hero in the x-axis
	 * @param y position of the hero in the y-axis
	 */
	Hero(int x, int y, boolean armed) {
		super(x, y);
		withKey = false;
		this.armed = armed;
	}

	/**
	 * Used to know if the hero has the key
	 * 
	 * @return true if the hero has the key and false otherwise
	 */
	public final boolean isWithKey() {
		return withKey;
	}

	/**
	 * used to set the lever state
	 * 
	 * @param lever true if the hero has caught the key and false otherwise
	 * 
	 */
	public final void setIsWithKey(boolean withKey) {
		this.withKey = withKey;
	}

	/**
	 * 
	 * @return 'H'
	 */
	char getChar() {
		if (withKey) {
			return CHAR_HERO_KEY;
		} else if (armed) {
			return CHAR_HERO_ARMED;
		} else {
			return CHAR_HERO;
		}
	}

	public final void setWithKey(boolean withKey) {
		this.withKey = withKey;
	}

	@Override
	public void move(Level l, Position p) {
		if (l.checkMove(p.getX(), p.getY())) {
			position.changeTo(p.getX(), p.getY());
		}
	}

	@Override
	public void move(Level l) {
		// hero has no inner logic movement
	}

	@Override
	public boolean attack(GameEntity g) {
		// attacked area could be calculated and check if GameEntity g.position is equal
		// to any of them
		boolean flag = (this.isAdjacent(g) || this.isAt(g)) && armed;
		if (flag) {
			g.currentState = State.DEAD;
		}
		return flag;
	}

}
