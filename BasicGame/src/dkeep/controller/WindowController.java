package dkeep.logic;

import javax.swing.ImageIcon;
import dkeep.gui.CreateMapWindow;
import dkeep.gui.GameSettingsWindow;
import dkeep.gui.GameWindow;
import dkeep.gui.PopUpWindow;
import utilities.ImageLoader;
import utilities.Regex;

public final class WindowController {

  private final Controller controller;
  private final GameWindow gameWdw;
  private final GameSettingsWindow gameStgWdw;
  private final PopUpWindow popUpWdw;
  private final CreateMapWindow createMapWdw;
  private final ImageLoader imageLoader;
  // private int[][] visited;
  // private int[][] labirinth;

  WindowController(Controller controller) {
    this.controller = controller;
    this.imageLoader = new ImageLoader();
    this.gameStgWdw = new GameSettingsWindow();
    this.createMapWdw = new CreateMapWindow(imageLoader);
    this.gameWdw = new GameWindow(this);
    this.popUpWdw = new PopUpWindow();
    // visited = new int[15][15];
  }

  void updateGameWindow(char[][] map, String legend) {
    gameWdw.paintMap(createMap(map));
    gameWdw.setLegend(legend);
  }

  public void newGame() {
    String guardPersonality = gameStgWdw.getGuardPersonality();
    if (checkNewGame(guardPersonality))
      controller.newGame(guardPersonality);
  }

  public void makeMove(char move) {
    controller.makeMove(move);
  }

  public String getOgreNumber() {
    return gameStgWdw.getOgreNumber();
  }

  public void showWindowGameSettings() {
    gameStgWdw.setVisible(true);
  }

  public void showCreateGameWindow() {
    createMapWdw.setVisible(true);
  }

  public int getEditPanelSubSquareLength() {
    return createMapWdw.getEditPanelSubSquareLength();
  }

  char[][] generateMap() {
    ImageIcon[][] imgMap = createMapWdw.getEditMap();
    char[][] charMap = new char[imgMap.length][imgMap[0].length];
    int counter = 0;

    for (int i = 0; i < imgMap.length; i++) {
      for (int j = 0; j < imgMap[0].length; j++) {
        if (imgMap[i][j] == imageLoader.getHeroImg()) {
          charMap[i][j] = 'A';
        } else if (imgMap[i][j] == imageLoader.getOgreImg()) {
          charMap[i][j] = 'O';
        } else if (imgMap[i][j] == imageLoader.getDoorImg()) {
          charMap[i][j] = 'I';
        } else if (imgMap[i][j] == imageLoader.getKeyImg()) {
          charMap[i][j] = 'k';
        } else if (imgMap[i][j] == imageLoader.getWallImg()) {
          charMap[i][j] = 'X';
        } else if (imgMap[i][j] == imageLoader.getBlankSpaceImg()) {
          charMap[i][j] = ' ';
          counter++;
        }
      }
    }
    return (counter == (imgMap.length * imgMap[0].length)) ? new char[0][0] : charMap;
  }

  private ImageIcon[][] createMap(char[][] map) {
    ImageIcon[][] imgMap = new ImageIcon[map.length][map[0].length];

    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[0].length; j++) {

        if (map[i][j] == 'H') {
          imgMap[i][j] = imageLoader.getHeroImg();
        } else if (map[i][j] == 'A') {
          imgMap[i][j] = imageLoader.getHeroArmedImg();
        } else if (map[i][j] == 'K') {
          imgMap[i][j] = imageLoader.getHeroArmedImg();
        } else if (map[i][j] == 'O') {
          imgMap[i][j] = imageLoader.getOgreImg();
        } else if (map[i][j] == 'I') {
          imgMap[i][j] = imageLoader.getDoorImg();
        } else if (map[i][j] == 'k') {
          imgMap[i][j] = imageLoader.getKeyImg();
        } else if (map[i][j] == 'X') {
          imgMap[i][j] = imageLoader.getWallImg();
        } else if (map[i][j] == ' ') {
          imgMap[i][j] = imageLoader.getBlankSpaceImg();
        } else if (map[i][j] == 'G') {
          imgMap[i][j] = imageLoader.getGuardImg();
        } else if (map[i][j] == 'S') {
          imgMap[i][j] = imageLoader.getDoorOpenImg();
        } else if (map[i][j] == '*') {
          imgMap[i][j] = imageLoader.getClubImg();
        }
      }
    }
    return imgMap;
  }

  private boolean checkNewGame(String guardPersonality) {
    String ogreNumber = gameStgWdw.getOgreNumber();
    boolean mapCreated = createMapWdw.isMapCreated();

    if (guardPersonality.equals("")) {
      popUpWdw.printErrorMessageDialog("Dungeon Map needs a guard personality");
      return false;
    }

    if (ogreNumber.equals("") && !mapCreated) {
      popUpWdw.printWarningMessageDialog("Insert number of ogres or edit Keep Map");
      return false;
    }

    if (!ogreNumber.equals("") && mapCreated) {
      popUpWdw.printErrorMessageDialog("Keep Map cannot have default and edit maps at same time");
      return false;
    }

    if (!mapCreated) {
      Regex regex = new Regex();
      if (!regex.checkOgreNumber(ogreNumber)) {
        popUpWdw.printWarningMessageDialog("Invalid number of ogres");
        return false;
      }
    } else {
      char[][] editMap = generateMap();
      if (!checkMap(editMap)) {
        popUpWdw.printWarningMessageDialog("Invalid edit map");
        return false;
      }
    }
    return true;
  }

  private boolean checkMap(char[][] charMap) {
    if (!checkCharacter('A', 1, 1, charMap)) {
      return false;
    }
    if (!checkCharacter('O', 1, 5, charMap)) {
      return false;
    }
    if (!checkCharacter('I', 1, charMap.length * charMap.length, charMap)) {
      return false;
    }
    if (!checkCharacter('k', 1, charMap.length * charMap.length, charMap)) {
      return false;
    }
    if (!checkCharacter('X', 1, charMap.length * charMap.length, charMap)) {
      return false;
    }
    if (!checkCharacter(' ', 1, charMap.length * charMap.length, charMap)) {
      return false;
    }
    return checkBorders(charMap);
  }

  private boolean checkCharacter(char character, int numMin, int numMax, char[][] map) {
    int counter = 0;
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[i].length; j++) {
        if (map[i][j] == character) {
          counter++;
        }
      }
    }
    return (counter >= numMin && counter <= numMax);
  }

  private boolean checkBorders(char[][] map) {

    if (!(map[0][0] == 'X' && map[0][map[0].length - 1] == 'X' && map[map.length - 1][0] == 'X'
        && map[map.length - 1][map.length - 1] == 'X')) {
      return false;
    }

    for (int j = 1; j < map[0].length - 1; j++) {
      if (!(map[0][j] == 'X' || map[0][j] == 'I')) {
        return false;
      }
    }

    for (int i = 1; i < map.length - 1; i++) {
      if (!(map[i][0] == 'X' || map[i][0] == 'I')) {
        return false;
      }
      if (!(map[i][map.length - 1] == 'X' || map[i][map.length - 1] == 'I')) {
        return false;
      }
    }

    for (int j = 1; j < map[0].length - 1; j++) {
      if (!(map[map.length - 1][j] == 'X' || map[map.length - 1][j] == 'I')) {
        return false;
      }
    }

    return true;
  }

  // private void initializedVisited(char[][] map) {
  // {
  // for (int i = 0; i < map.length; i++)
  // for (int j = 0; j < map[i].length; j++)
  // visited[i][j] = 0;
  // }
  // }
  //
  // private boolean findGoalRec(int x, int y) {
  // // Check if this position is worth visiting (limits checking could
  // // be omitted because the labyrinth is surrounded by walls)
  // if (((x < 0 || y < 0) || (x >= this.labirinth.length - 1 || x >=
  // this.labirinth.length - 1))
  // || (this.labirinth[y][x] == 0 || this.visited[y][x] == 1))
  // return false;
  // // Mark as visited
  // visited[y][x] = 1;
  // // Check if the exit was reached
  // if (labirinth[y][x] == 2) {
  // System.out.println("Goald Found");
  // return true;
  // }
  // // Try all the adjacent cells
  // return findGoalRec(x - 1, y) || findGoalRec(x + 1, y) || findGoalRec(x, y -
  // 1) || findGoalRec(x, y + 1);
  // }
  //
  // private boolean checkWaytoKey(char[][] map) {
  // labirinth = new int[map.length][map.length];
  // int x = 0, y = 0;
  //
  // for (int i = 0; i < map.length; i++) {
  // for (int j = 0; j < map[i].length; j++) {
  //
  // if (map[i][j] == 'A') {
  // x = i;
  // y = j;
  // }
  // }
  // }
  //
  // for (int i = 0; i < map.length; i++) {
  // for (int j = 0; j < map[i].length; j++) {
  //
  // if (map[i][j] == ' ') {
  // labirinth[i][j] = 1;
  // } else if (map[i][j] == 'k') {
  // labirinth[i][j] = 2;
  // }
  // }
  // }
  //
  // initializedVisited(map);
  //
  // if (findGoalRec(x, y)) {
  // return true;
  // }
  //
  // return false;
  // }
}
