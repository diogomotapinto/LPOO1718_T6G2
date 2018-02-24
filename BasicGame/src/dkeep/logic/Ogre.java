package dkeep.logic;

import java.util.Random;

public class Ogre extends Character {
	private static final String MASSIVE_CLUB = "*";

	public Ogre(int xPos, int yPos) {
		super(xPos, yPos);
	}

	public final String getClub() {
		return MASSIVE_CLUB;
	}
}
