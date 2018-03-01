package dkeep.logic;

public interface MapRules {

	public void printHeader();

	public void initializeMap();

	public boolean checkEndLevel();

	public boolean checkWon(int x, int y);

	public boolean checkLost(int x, int y);

}
