package dkeep.logic;

public class ClubHolder extends Character {

	private Club club;

	public ClubHolder(int xPos, int yPos) {
		super(xPos, yPos);
		club = new Club(xPos, yPos);
	}

	public final Club getClub() {
		return club;
	}

	public final void setClub(Club club) {
		this.club = club;
	}

}
