package dkeep.logic;

import java.util.Random;

public class Ogre extends Character {
	private static final byte MOVE_UP = 1;
	private static final byte MOVE_DOWN = 2;
	private static final byte MOVE_RIGHT = 3;
	private static final byte MOVE_LEFT = 4;
	private static final byte lowerBound = 1;
	private static final byte upperBound = 4;
	private static final String MASSIVE_CLUB = "*";

	private final Random random;
	private int clubXPos;
	private int clubYPos;

	public Ogre(int xPos, int yPos) {
		super(xPos, yPos);
		random = new Random();

		clubXPos = 1;
		clubYPos = 5;
	}

	// passar position up and right
	// so funciona se a lever estiver direita ou acima do ogre
	public final int[] getAdjacentPosition() {
		int[] position = { this.xPosition, this.yPosition };
		int result = random.nextInt((upperBound - lowerBound) + 1) + lowerBound;

		switch (result) {
		case MOVE_UP:
			position[0]--;
			break;
		case MOVE_DOWN:
			position[0]++;
			break;
		case MOVE_RIGHT:
			position[1]++;
			break;
		case MOVE_LEFT:
			position[1]--;
			break;
		default:
			break;
		}
		return position;

	}

	public final void moveOgresClub() {
		int xPos = random.nextInt((1 - -1) + 1) + -1;
		int yPos = random.nextInt((1 - -1) + 1) + -1;

		if (!(xPos == 0 || yPos == 0)) {
			this.clubXPos = xPos + this.xPosition;
			this.clubYPos = yPos + this.yPosition;
		}

	}

	public final int getClubXPos() {
		return this.clubXPos;
	}

	public final int getClubYPos() {
		return this.clubYPos;
	}

	public final String getClub() {
		return MASSIVE_CLUB;
	}

}
