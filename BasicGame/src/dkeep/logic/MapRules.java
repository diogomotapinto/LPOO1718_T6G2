package dkeep.logic;

public interface MapRules {

	public void play(char move);

	public void initializeMap();

	public byte checkEndLevel();

	public boolean checkLost(int x, int y);

	public void generateFoes();

}
