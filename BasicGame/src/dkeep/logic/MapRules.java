package dkeep.logic;

public interface MapRules {

	// public void printHeader();

	public void play(char move);

	public void initializeMap();

	public boolean checkEndLevel();

	public boolean checkLost(int x, int y);

	public void generateFoes();

}
