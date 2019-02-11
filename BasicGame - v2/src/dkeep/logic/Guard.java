package dkeep.logic;

import java.io.Serializable;

public class Guard extends GameEntity implements Behaviour, Serializable {

	private static final long serialVersionUID = -8469436697778674229L;
	private static final char GUARD_CHAR = 'G';
	private final byte route[][];
	private int index;
	private boolean sleeping;

	public enum Personality implements FoeInfo {
		ROOKIE, DRUNKEN, SUSPICIOUS;
	}

	private ImplementedBehaviour behaviour;

	/**
	 * Class constructor
	 * 
	 * @param bs of the guard
	 */
	protected Guard(byte[][] route, FoeInfo personality) {
		super(route[0][0], route[0][1]);
		this.route = route;
		applyBahaviour(personality);
		index = 0;
		sleeping = false;
	}

	private void applyBahaviour(FoeInfo personality) {
		switch ((Personality) personality) {
		case ROOKIE:
			behaviour = new BehaviourRookie();
			break;
		case DRUNKEN:
			behaviour = new BehaviourDrunken();
			break;
		case SUSPICIOUS:
			behaviour = new BehaviourSuspicious();
			break;
		default:
			System.err.println("Error in Guard Constructor!!");
		}
	}

	/**
	 * Makes guard move to the next position
	 * 
	 * @return
	 */
	@Override
	public int getNextMove() {
		return behaviour.getNextMove(index, route.length);
	}

	/**
	 * Gives the guard character
	 * 
	 * @return 'G'
	 */
	public static final char getChar() {
		return GUARD_CHAR;
	}

	@Override
	public String toString() {
		return "Guard [behaviour=" + behaviour + "]";
	}

	@Override
	public void move(Level l, Position p) {
		// guard has no input movement
	}

	@Override
	public void move(Level l) {
		index = getNextMove();
		MapDungeon dm = (MapDungeon) l.getMap();
		sleeping = checkSleeping(dm.getRoute());
		position.changeTo(dm.getRoute()[index][0], dm.getRoute()[index][1]);
	}

	private boolean checkSleeping(byte[][] route) {
		Position p = new Position(route[index][0], route[index][1]);
		return position.equals(p) && position.hashCode() == p.hashCode();
	}

	public final boolean isSleeping() {
		return sleeping;
	}

	@Override
	public boolean attack(GameEntity g) {
		// attacked area could be calculated and check if GameEntity g.position is equal
		// to any of them
		boolean flag = (this.isAdjacent(g) || this.isAt(g)) && !sleeping;
		if (flag) {
			g.currentState = State.DEAD;
		}
		return flag;
	}

}
