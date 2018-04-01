package dkeep.logic;

interface PlayMap {

	public void play(char move);

	public Map nextLevel(String info) throws NextLevelNotAvailable;

	public Map nextLevel(char[][] map) throws NextLevelNotAvailable;

	public abstract void checkLever();

}
