package dkeep.logic;

import java.util.Random;

public class Ogre extends Character {
	private static final String MASSIVE_CLUB = "*";
	private int counter;
	private boolean stunned;

	public Ogre(int xPos, int yPos) {
		super(xPos, yPos);
		counter = 2;
		stunned = false;
	}

	public final String getClub() {
		return MASSIVE_CLUB;
	}
	
	public void setStunned(boolean isStunned)
	{
		if(stunned) {
			counter = 2;
		}
		stunned = isStunned;
	}
	
	public boolean getStunned()
	{
		return stunned;
	}
	
	public void stunCounter()
	{
		counter--;
	}
	
	public int getStunCounter()
	{
		return counter;
	}
}
