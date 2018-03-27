package dkeep.logic;

public final class Controller {

  private static final Controller INSTANCE = new Controller();
  private final StateMachine stateMachine;
  private final WindowController wdwController;
  private Map currentMap;

  private Controller() {
    stateMachine = new StateMachine();
    wdwController = new WindowController(this);
  }

  public static Controller getInstance() {
    return INSTANCE;
  }

  void newGame(String personality) {
    stateMachine.advanceState(StateMachine.Event.PLAY);
    currentMap = new DungeonMap(personality);
    wdwController.updateGameWindow(currentMap.getPlayMap(), "You can play now");
  }

  void makeMove(char move) {
    if (stateMachine.getGameState() == StateMachine.State.GAME_PLAYING) {
      currentMap.play(move);
      wdwController.updateGameWindow(currentMap.getPlayMap(), "You can move");
      advanceLevel(currentMap.checkEndLevel());
    }
  }

  private boolean advanceLevel(byte endLevel) {
    switch (endLevel) {
    case -1:
      stateMachine.advanceState(StateMachine.Event.OVER);
      wdwController.updateGameWindow(currentMap.getPlayMap(), "Game Over");
      return true;
    case 0:
      return false;
    case 1:

      if (currentMap.nextLevel("0") == null) {
        stateMachine.advanceState(StateMachine.Event.WON);
        wdwController.updateGameWindow(currentMap.getPlayMap(), "Game Won");
        return true;
      } else {

        char[][] editMap = wdwController.generateMap();
        stateMachine.advanceState(StateMachine.Event.LEVEL_UP);
        if (editMap.length == 0) {
          currentMap = currentMap.nextLevel(wdwController.getOgreNumber());
        } else {
          currentMap = currentMap.nextLevel(editMap);
        }

        wdwController.updateGameWindow(currentMap.getPlayMap(), "Next Level");
        return false;
      }
    default:
      return false;
    }
  }

}
