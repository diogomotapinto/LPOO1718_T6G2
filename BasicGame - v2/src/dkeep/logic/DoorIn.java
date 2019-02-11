package dkeep.logic;

import java.io.Serializable;

public class DoorIn extends Door implements Serializable {

	private static final long serialVersionUID = -7808355926268934478L;

	DoorIn(int x, int y) {
		super(x, y, false);
	}

	@Override
	public void move(Level l, Position p) {
		// door in doesnt move
	}

	@Override
	public void move(Level l) {
		// door in doesnt move
	}

	@Override
	public boolean attack(GameEntity g) {
		// door in dont attack
		return false;
	}

}
