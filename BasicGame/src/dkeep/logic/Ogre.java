package dkeep.logic;

import java.util.Random;

public class Ogre extends Character {
	private static final String MASSIVE_CLUB = "*";
	private Club myClub; 

	private final Random random;
	private int clubXPos;
	private int clubYPos;

	public Ogre(int xPos, int yPos) {
		super(xPos, yPos);
		random = new Random();
		myClub = new Club(xPos,yPos);
		
	}

	// passar position up and right
	// so funciona se a lever estiver direita ou acima do ogre
	


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
