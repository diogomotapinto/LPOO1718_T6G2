
public class Hero extends Character {
	private boolean gotKey;
	
	public Hero(int xPos, int yPos){
		super(xPos,yPos); 
		
		this.gotKey = false;
		
	}
	
	public void setKey(boolean myBool)
	{
		this.gotKey = myBool;
	}
	
}
