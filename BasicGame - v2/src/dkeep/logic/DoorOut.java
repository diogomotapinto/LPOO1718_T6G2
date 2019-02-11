package dkeep.logic;

import java.io.Serializable;

public class DoorOut extends Door implements Serializable {

	private static final long serialVersionUID = 8337277283036567214L;

	DoorOut(int x, int y) {
		super(x, y, true);
	}

	@Override
	public void move(Level l, Position p) {
		// door out doesnt move
	}

	@Override
	public void move(Level l) {
		// door out doesnt move
	}

	@Override
	public boolean attack(GameEntity g) {
		// door out dont attack
		return false;
	}

}
