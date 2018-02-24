package dkeep.logic;

public class Hero extends Character {
	private String heroChar;
	private Club club;

	public Hero(int xPos, int yPos) {
		super(xPos, yPos);
		
		club = new Club(xPos, yPos);
		heroChar = "H";
		
	}
	
	public String getHeroChar()
	{		
		return heroChar;
	}


	public void setHeroChar(String ch) {
		heroChar = ch;
	}
	
	public Club getMyClub() {
		return club;
	}

	public void setMyClub(Club myClub) {
		this.club = club;
	}
	
	

}

