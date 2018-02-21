package game;

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
	//so funciona se a lever estiver direita ou acima do ogre
	public final void moveToNextPosition(String positionUp, String positionRight) {
		int result = random.nextInt((upperBound - lowerBound) + 1) + lowerBound;

		switch (result) {
		case MOVE_UP:
			if (this.xPosition != 1 && positionUp != "k") {
				this.xPosition--;
			}
			break;

		case MOVE_DOWN:
			if (this.xPosition != 7) {
				this.xPosition++;
			}
			break;

		case MOVE_RIGHT:
			if (this.yPosition != 7 && positionRight != "k") {
				this.yPosition++;
			}
			break;

		case MOVE_LEFT:
			if (this.yPosition != 1) {
				this.yPosition--;
			}
			break;

		default:
			break;
		}
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
