package dkeep.gui;

import javax.swing.ImageIcon;

import dkeep.controller.Controller;
import dkeep.controller.Controller.GameAmbient;
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
	private char[][] savedEditMap;

	
	public WindowController(Controller controller) {
		this.controller = controller;
		this.imageLoader = new ImageLoader();
		this.gameStgWdw = new GameSettingsWindow();
		this.createMapWdw = new CreateMapWindow(imageLoader);
		this.gameWdw = new GameWindow(this);
		this.popUpWdw = new PopUpWindow();
		visited = new boolean[0][0];
		savedEditMap = new char[0][0];
	}

	public WindowController(Controller controller, String ogreNumber, char[][] savedEditMap) {
		this.controller = controller;
		this.imageLoader = new ImageLoader();
		this.gameStgWdw = new GameSettingsWindow();
		this.createMapWdw = new CreateMapWindow(imageLoader);
		this.popUpWdw = new PopUpWindow();
		this.visited = new boolean[0][0];
		this.savedEditMap = savedEditMap;
		gameStgWdw.setOgreNumber(ogreNumber);
		this.gameWdw = new GameWindow(this);
	}

	public void updateGameWindow(char[][] map, String legend) {
		gameWdw.paintMap(createMap(map));
		gameWdw.setLegend(legend);
	}

	
	void newGame() {
		String guardPersonality = gameStgWdw.getGuardPersonality();
		GameAmbient gameAmbient = gameStgWdw.getGameAmbient();
		if (checkNewGame(guardPersonality, gameAmbient))
			controller.newGame(guardPersonality, gameAmbient);
	}

	
	void makeMove(char move) {
		controller.makeMove(move);
	}

	
	public String getOgreNumber() {
		return gameStgWdw.getOgreNumber();
	}

	
	void showWindowGameSettings() {
		gameStgWdw.setVisible(true);
	}

	
	void showCreateGameWindow() {
		createMapWdw.setVisible(true);
	}

	
	public int getEditPanelSubSquareLength() {
		return createMapWdw.getEditPanelSubSquareLength();
	}

	
	public ImageIcon[][] getEditMap() {
		return this.createMapWdw.getEditMap();
	}

	
	public char[][] generateMap() {
		if (this.savedEditMap.length != 0)
			return savedEditMap;

		ImageIcon[][] imgMap = createMapWdw.getEditMap();
		char[][] charMap = new char[imgMap.length][imgMap[0].length];
		int counter = 0;

		for (int i = 0; i < imgMap.length; i++)
			for (int j = 0; j < imgMap[0].length; j++) {
				if (imgMap[i][j] == imageLoader.getHeroImg())
					charMap[i][j] = 'A';
				else if (imgMap[i][j] == imageLoader.getOgreImg())
					charMap[i][j] = 'O';
				else if (imgMap[i][j] == imageLoader.getDoorImg())
					charMap[i][j] = 'I';
				else if (imgMap[i][j] == imageLoader.getKeyImg())
					charMap[i][j] = 'k';
				else if (imgMap[i][j] == imageLoader.getWallImg())
					charMap[i][j] = 'X';
				else if (imgMap[i][j] == imageLoader.getGuardImg()) 
						charMap[i][j] = 'G';
				else if (imgMap[i][j] == imageLoader.getOgreStunned()) 
					charMap[i][j] = '8';
				else if (imgMap[i][j] == imageLoader.getBlankSpaceImg()) {
					charMap[i][j] = ' ';
					counter++;
				}
			}

		if (counter == 0)
			charMap[0][0] = '-';

		return (counter == (imgMap.length * imgMap[0].length)) ? new char[0][0] : charMap;
	}

	
	private ImageIcon[][] createMap(char[][] map) {
		ImageIcon[][] imgMap = new ImageIcon[map.length][map[0].length];

		for (int i = 0; i < map.length; i++)
			for (int j = 0; j < map[0].length; j++) {

				if (map[i][j] == 'H')
					imgMap[i][j] = imageLoader.getHeroImg();
				else if (map[i][j] == 'A')
					imgMap[i][j] = imageLoader.getHeroArmedImg();
				else if (map[i][j] == 'K')
					imgMap[i][j] = imageLoader.getHeroArmedImg();
				else if (map[i][j] == 'O')
					imgMap[i][j] = imageLoader.getOgreImg();
				else if (map[i][j] == 'I')
					imgMap[i][j] = imageLoader.getDoorImg();
				else if (map[i][j] == 'k')
					imgMap[i][j] = imageLoader.getKeyImg();
				else if (map[i][j] == 'X')
					imgMap[i][j] = imageLoader.getWallImg();
				else if (map[i][j] == ' ')
					imgMap[i][j] = imageLoader.getBlankSpaceImg();
				else if (map[i][j] == 'G')
					imgMap[i][j] = imageLoader.getGuardImg();
				else if (map[i][j] == 'S')
					imgMap[i][j] = imageLoader.getDoorOpenImg();
				else if (map[i][j] == '*')
					imgMap[i][j] = imageLoader.getClubImg();
				else if (map[i][j] == 'G')
					imgMap[i][j] = imageLoader.getGuardImg();
				else if (map[i][j] == '8')
					imgMap[i][j] = imageLoader.getOgreStunned();
			}

		return imgMap;
	}

	
	private boolean checkNewGame(String guardPersonality, GameAmbient gameAmbient) {

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
		} else if (!checkMap(editMap)) {
			popUpWdw.printWarningMessageDialog("Invalid edit map");
			return false;
		}

		if (gameAmbient == GameAmbient.CONSOLE)
			this.gameWdw.closeWindow();

		return true;
	}

	
	private boolean checkMap(char[][] charMap) {
		if (!checkCharacter('A', 1, 1, charMap))
			return false;

		if (!checkCharacter('O', 1, 5, charMap))
			return false;

		if (!checkCharacter('I', 1, 1, charMap))
			return false;

		if (!checkCharacter('k', 1, 1, charMap))
			return false;

		if (!checkCharacter('X', 1, charMap.length * charMap.length, charMap))
			return false;

		if (!checkCharacter(' ', 1, charMap.length * charMap.length, charMap))
			return false;

		return checkBorders(charMap);
	}

	
	private boolean checkCharacter(char character, int numMin, int numMax, char[][] map) {
		int counter = 0;

		for (int i = 0; i < map.length; i++)
			for (int j = 0; j < map[i].length; j++)
				if (map[i][j] == character)
					counter++;

		return (counter >= numMin && counter <= numMax);
	}

	
	private boolean checkBorders(char[][] map) {

		if (!(map[0][0] == 'X' && map[0][map[0].length - 1] == 'X' && map[map.length - 1][0] == 'X'
				&& map[map.length - 1][map.length - 1] == 'X'))
			return false;

		for (int j = 1; j < map[0].length - 1; j++)
			if (!(map[0][j] == 'X' || map[0][j] == 'I'))
				return false;

		for (int i = 1; i < map.length - 1; i++) {
			if (!(map[i][0] == 'X' || map[i][0] == 'I'))
				return false;

			if (!(map[i][map.length - 1] == 'X' || map[i][map.length - 1] == 'I'))
				return false;
		}

		for (int j = 1; j < map[0].length - 1; j++)
			if (!(map[map.length - 1][j] == 'X' || map[map.length - 1][j] == 'I'))
				return false;

		return true;
	}

	
	private boolean checkCharacterBlocked(char[][] map, char character) {

		for (int i = 0; i < map.length; i++)
			for (int j = 0; j < map[i].length; j++)
				if (map[i][j] == character)
					return checkSurroundings(map, i, j, 'X');

		return true;
	}

	
	private boolean checkSurroundings(char[][] map, int x, int y, char surround) {
		return !(map[x - 1][y] == surround && map[x + 1][y] == surround && map[x][y - 1] == surround
				&& map[x + 1][y + 1] == surround);

	}

	
	private boolean findGoal(int x, int y) {
		// Check if this position is worth visiting (limits checking could
		// be omitted because the labyrinth is surrounded by walls)
		if (x < 0 || y < 0 || x >= this.labirinth.length || y >= this.labirinth.length
				|| (this.labirinth[x][y] == 0 || this.visited[x][y]))
			return false;

		// Mark as visited
		visited[x][y] = true;
		// Check if the exit was reached
		if (labirinth[x][y] == 2) {
			return true;
		}
		// Try all the adjacent cells
		return findGoal(x - 1, y) || findGoal(x + 1, y) || findGoal(x, y - 1) || findGoal(x, y + 1);
		// check if maze[x][y] is feasible to move
	}

	
	private boolean validatePath(char[][] map, char origin, char destiny) {
		labirinth = new int[map.length][map.length];
		int x = 0, y = 0;

		for (int i = 0; i < map.length; i++)
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == origin) {
					x = i;
					y = j;
				}

				if (map[i][j] == ' ' || map[i][j] == origin)
					labirinth[i][j] = 1;
				else if (map[i][j] == destiny)
					labirinth[i][j] = 2;

			}

		visited = new boolean[map.length][map.length];

		return findGoal(x, y);
	}

	void serialize() {
		this.controller.serialize();
	}
}
