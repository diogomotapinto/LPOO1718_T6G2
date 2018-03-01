package dkeep.logic;

public class Position {
	private int xPosition;
	private int yPosition;

	public Position(int xPosition, int yPosition){
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}
	
	
//	public void changePositio(int xPosition, int yPosition)
//	{
//		this.xPosition = xPosition;
//		this.yPosition = yPosition;
//	}
	
	public int getXPosition(){
		return this.xPosition;
	}
	
	public void setXPosition(int xPosition){
		this.xPosition = xPosition;
	}
	
	public int getYPosition(){
		return this.yPosition;
	}
	
	public void setYPosition(int yPosition){
		this.yPosition = yPosition;
	}


	@Override
	public boolean equals(Object obj) {
		
		if(obj == null){
			return false;
		}
	
		if(obj == this) {
			return true;
		}
		
		if(!(obj instanceof Position)) {
			return false;
		}
		
		Position otherObj = (Position)obj;
		
		return this.xPosition == otherObj.getXPosition() && this.yPosition == otherObj.getYPosition();
	}
	
	
	

}
