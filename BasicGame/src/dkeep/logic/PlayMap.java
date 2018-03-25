package dkeep.logic;

public interface PlayMap {

	public void play(char move);

	public byte checkEndLevel();

	public Map nextLevel(String info);

	public Map nextLevel(String info, char[][] map);

}
