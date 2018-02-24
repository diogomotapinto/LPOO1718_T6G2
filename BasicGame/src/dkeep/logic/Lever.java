package dkeep.logic;

public class Lever {
	private boolean  activated;
	private String leverChar;
	
	
	public Lever() {
		activated = false;
		leverChar = "k";
	}
	
	
	public String getLever() {
		return leverChar;
	}
	
	public void setLever(String lever)
	{
		leverChar = lever;
	}
	
	
	public void activateLever()
	{
		activated = true;
	}
	
	public boolean isActivated() {
		return activated;
	}

}
