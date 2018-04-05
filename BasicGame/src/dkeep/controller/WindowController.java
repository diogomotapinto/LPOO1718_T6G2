package dkeep.controller;

import javax.swing.ImageIcon;

import dkeep.controller.Controller.GameAmbient;
import dkeep.gui.CreateMapWindow;
import dkeep.gui.GameSettingsWindow;
import dkeep.gui.GameWindow;
import dkeep.gui.PopUpWindow;
import dkeep.logic.Map;
import dkeep.logic.model.Guard;
import utilities.ImageLoader;
import utilities.Regex;

public final class WindowController {

	private final Controller controller;
	private final GameWindow gameWdw;
	private final GameSettingsWindow gameStgWdw;
	private final PopUpWindow popUpWdw;
	private final CreateMapWindow createMapWdw;
	private final ImageLoader imageLoader;
	private boolean[][] visited;
	private int[][] labirinth;

	/**
	 * Class constructor
	 * 
	 * @param controller
	 */
	WindowController(Controller controller) {
		this.controller = controller;
		this.imageLoader = new ImageLoader();
		this.gameStgWdw = new GameSettingsWindow();
		this.createMapWdw = new CreateMapWindow(imageLoader);
		this.gameWdw = new GameWindow(this);
		this.popUpWdw = new PopUpWindow();
		visited = new boolean[0][0];
	}

	void updateGameWindow(char[][] map, String legend) {
		gameWdw.paintMap(createMap(map));
		gameWdw.setLegend(legend);
	}

	/**
	 * Loads a new game
	 */
	public void newGame() {
		String guardPersonality = gameStgWdw.getGuardPersonality();
		GameAmbient gameAmbient = gameStgWdw.getGameAmbient();
		if (checkNewGame(guardPersonality, gameAmbient))
			controller.newGame(guardPersonality, gameAmbient);
	}

	/**
	 * 
	 * @param move
	 */
	public void makeMove(char move) {
		controller.makeMove(move);
	}

	/**
	 * Used to get the number of ogres chosen by the user
	 * 
	 * @return
	 */
	public String getOgreNumber() {
		return gameStgWdw.getOgreNumber();
	}

	/**
	 * Makes the window game settings visible
	 */
	public void showWindowGameSettings() {
		gameStgWdw.setVisible(true);
	}

	/**
	 * Creates the game window
	 */
	public void showCreateGameWindow() {
		createMapWdw.setVisible(true);
	}

	/**
	 * 
	 * @return
	 */
	public int getEditPanelSubSquareLength() {
		return createMapWdw.getEditPanelSubSquareLength();
	}

	/**
	 * Generates the map created by the user
	 * 
	 * @return the map if there is more than one image in the map created and an
	 *         empty otherwise
	 */
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

	/**
	 * Creates the map with images from the multidimensional array
	 * 
	 * @param map
	 *            to be created with images
	 * @return multidimensional array of images that represents the map
	 */
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

	/**
	 * Validates the map and gives a warning if some requirement is not fulfilled
	 * 
	 * @param guardPersonality
	 *            string that represents the guard personality
	 * @param gameAmbient
	 * @return true if all requirement are fulfilled and false otherwise
	 */
	private boolean checkNewGame(String guardPersonality, GameAmbient gameAmbient) {

		String ogreNumber = gameStgWdw.getOgreNumber();
		boolean mapCreated = createMapWdw.isMapCreated();

		if (gameAmbient.equals("")) {
			popUpWdw.printErrorMessageDialog("Game Ambient is not chosen");
			return false;
		}

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

		char[][] editMap = generateMap();

		if (mapCreated && !validatePath(editMap, 'A', 'I')) {
			popUpWdw.printErrorMessageDialog("No path to the door");
			return false;
		}

		if (mapCreated && !validatePath(editMap, 'A', 'k')) {
			popUpWdw.printErrorMessageDialog("No path to key");
			return false;
		}

		if (mapCreated && !checkCharacterBlocked(editMap, 'O')) {
			popUpWdw.printWarningMessageDialog("Ogre is blocked by walls");
			return false;
		}

		if (mapCreated && !checkCharacterBlocked(editMap, 'A')) {
			popUpWdw.printWarningMessageDialog("Hero is blocked by walls");
			return false;
		}

		if (!mapCreated) {
			Regex regex = new Regex();
			if (!regex.checkOgreNumber(ogreNumber)) {
				popUpWdw.printWarningMessageDialog("Invalid number of ogres");
				return false;
			}
		} else {
			if (!checkMap(editMap)) {
				popUpWdw.printWarningMessageDialog("Invalid edit map");
				return false;
			}
		}
		if (gameAmbient == GameAmbient.CONSOLE) {
			this.gameWdw.closeWindow();
		}
		return true;
	}

	/**
	 * 
	 * @param charMap
	 * @return
	 */
	private boolean checkMap(char[][] charMap) {
		if (!checkCharacter('A', 1, 1, charMap)) {
			return false;
		}
		if (!checkCharacter('O', 1, 5, charMap)) {
			return false;
		}
		if (!checkCharacter('I', 1, 1, charMap)) {
			return false;
		}
		if (!checkCharacter('k', 1, 1, charMap)) {
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

	/**
	 * Check if a character is in the right amount on a map
	 * 
	 * @param character
	 *            to be checked
	 * @param numMin
	 *            amount of times he is allowed
	 * @param numMax
	 *            amount of times he is allowed
	 * @param map
	 *            to be checked
	 * @return true if the character is in the right amount of times and false
	 *         otherwise
	 */
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

	/**
	 * Check if the borders are only walls 'X' or doors 'I'
	 * 
	 * @param map
	 *            multidimensional array to be checked
	 * @return false if there is anything different than a door or a wall in the
	 *         edges and true otherwise
	 */
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

	/**
	 * Checks if character is surrounded by another element
	 * 
	 * @param multidimensional
	 *            array to be checked
	 * @param character
	 *            to be validated
	 * 
	 * @return true if it isn't and false otherwise
	 */
	private boolean checkCharacterBlocked(char[][] map, char character) {

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {

				if (map[i][j] == character) {
					return checkSurroundings(map, i, j, 'X');
				}

			}
		}
		return true;
	}

	/**
	 * Checks if some element is surrounded by another
	 * 
	 * @param map
	 * @param x
	 *            position of the element in the x-axis
	 * @param y
	 *            position of the element in the y-axis
	 * @param surround
	 *            char to be checked around other char
	 * @return false if it is blocked and false otherwise
	 */
	private boolean checkSurroundings(char[][] map, int x, int y, char surround) {

		if (map[x - 1][y] == surround && map[x + 1][y] == surround && map[x][y - 1] == surround
				&& map[x + 1][y + 1] == surround) {
			return false;
		}

		return true;
	}

	/**
	 * Recursive function that finds the path between the points given and the goal
	 * 
	 * @param x
	 *            position in the x-axis
	 * @param y
	 *            position in the y-axis
	 * @return if the goal is reached and false otherwise
	 */
	private boolean findGoalRec(int x, int y) {
		// Check if this position is worth visiting (limits checking could
		// be omitted because the labyrinth is surrounded by walls)
		if (x < 0 || y < 0 || x >= this.labirinth.length || y >= this.labirinth.length
				|| (this.labirinth[x][y] == 0 || this.visited[x][y] == true) || this.labirinth[x][y] == 0)
			return false;

		// Mark as visited
		visited[x][y] = true;
		// Check if the exit was reached
		if (labirinth[x][y] == 2) {
			return true;
		}
		// Try all the adjacent cells
		return findGoalRec(x - 1, y) || findGoalRec(x + 1, y) || findGoalRec(x, y - 1) || findGoalRec(x, y + 1);
		// check if maze[x][y] is feasible to move
	}

	/**
	 * Validates if the path between the char of origin and the one in the destiny
	 * is reached
	 * 
	 * @return true if the path is valid and false otherwise
	 */
	private boolean validatePath(char[][] map, char origin, char destiny) {
		labirinth = new int[map.length][map.length];
		int x = 0, y = 0;

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == origin) {
					x = i;
					y = j;
				}

				if (map[i][j] == ' ' || map[i][j] == origin) {
					labirinth[i][j] = 1;
				} else if (map[i][j] == destiny) {
					labirinth[i][j] = 2;
				}

			}
		}

		visited = new boolean[map.length][map.length];

		if (findGoalRec(x, y)) {
			return true;
		}
		return false;
	}
}
