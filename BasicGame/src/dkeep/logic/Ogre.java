package dkeep.logic;

import java.util.Random;

public class Ogre extends Character {
	private static final String MASSIVE_CLUB = "*";
	private Club myClub;

	public Ogre(int xPos, int yPos) {
		super(xPos, yPos);
		myClub = new Club(xPos, yPos);

	}

	public final String getClub() {
		return MASSIVE_CLUB;
	}

	public Club getMyClub() {
		return myClub;
	}

	public void setMyClub(Club myClub) {
		this.myClub = myClub;
	}
	
	public void setClubPosition()
	{
		
	}

}
