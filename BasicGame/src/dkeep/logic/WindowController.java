package dkeep.logic;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

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
	private final Regex regex;
	private final ImageLoader imageLoader;
	private String ogreNumber;
	private String guardPersonality;

	public WindowController(Controller controller) {
		this.controller = controller;
		this.imageLoader = new ImageLoader();
		this.gameStgWdw = new GameSettingsWindow();
		this.createMapWdw = new CreateMapWindow(imageLoader);
		this.gameWdw = new GameWindow(this, imageLoader);
		this.popUpWdw = new PopUpWindow();
		this.regex = new Regex();
		ogreNumber = "";
		guardPersonality = "";
	}

	public void updateGameWindow(char[][] map, String legend) {
		setMap(map);
		gameWdw.setLegend(legend);
	}

	private void setMap(char[][] map) {
		ImageIcon[][] imgMap = new ImageIcon[map.length][map[0].length];

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {

				if (map[i][j] == 'H') {
					imgMap[i][j] = imageLoader.getHeroImg();
				} else if (map[i][j] == 'A') {
					imgMap[i][j] = imageLoader.getHeroArmedImg();
				} else if (map[i][j] == 'O') {
					imgMap[i][j] = imageLoader.getOgreImg();
				} else if (map[i][j] == 'I') {
					imgMap[i][j] = imageLoader.getKeyImg();
				} else if (map[i][j] == 'k') {
					imgMap[i][j] = imageLoader.getDoorImg();
				} else if (map[i][j] == 'X') {
					imgMap[i][j] = imageLoader.getWallImg();
				} else if (map[i][j] == ' ') {
					imgMap[i][j] = imageLoader.getBlankSpaceImg();
				} else if (map[i][j] == 'G') {
					imgMap[i][j] = imageLoader.getGuardImg();
				} else if (map[i][j] == 'S') {
					imgMap[i][j] = imageLoader.getDoorOpenImg();
				}

			}
		}
		if (map[6][0] == 'H') {
			int a = 0;
		}
		gameWdw.paintMap(imgMap);
	}

	public void newGame() {
		ogreNumber = gameStgWdw.getOgreNumber();
		guardPersonality = gameStgWdw.getGuardPersonality();
		if (ogreNumber.equals("") || guardPersonality.equals("")) {
			popUpWdw.printErrorMessageDialog("Missing information");
		} else if (!regex.checkOgreNumber(ogreNumber)) {
			popUpWdw.printWarningMessageDialog("Invalid number of Ogres");
		} else {
			controller.newGame(guardPersonality);
		}
	}

	public void makeMove(char move) {
		controller.makeMove(move);
	}

	public String getOgreNumber() {
		return ogreNumber;
	}

	public void showWindowGameSettings() {
		gameStgWdw.setVisible(true);
	}

	public void showCreateGameWindow() {
		createMapWdw.setVisible(true);
	}

	public char[][] getEditMap() {
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

	public int getEditPanelSubSquareLength() {
		return createMapWdw.getEditPanelSubSquareLength();
	}

}
