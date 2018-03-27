package dkeep.logic;

interface PlayMap {

  public void play(char move);

  public Map nextLevel(String info);

  public Map nextLevel(char[][] map);

}
