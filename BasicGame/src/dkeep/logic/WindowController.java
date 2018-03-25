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
		this.gameWdw = new GameWindow(this);
		this.gameStgWdw = new GameSettingsWindow();
		this.imageLoader = new ImageLoader();
		this.createMapWdw = new CreateMapWindow(this, imageLoader);
		this.popUpWdw = new PopUpWindow();
		this.regex = new Regex();
		ogreNumber = "";
		guardPersonality = "";
	}

	public void updateGameWindow(String map, String legend) {
		gameWdw.setMap(map);
		gameWdw.setLegend(legend);
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

	// public void generateMap(ImageIcon[][] editMap) {
	// char[][] map = new char[editMap.length][editMap[0].length];
	//
	// for (int i = 0; i < editMap.length; i++) {
	// for (int j = 0; j < editMap[0].length; j++) {
	// if (editMap[i][j] == imageLoader.getBlankSpaceImg()) {
	//
	// } else if (editMap[i][j] == imageLoader.getHeroImg()) {
	//
	// } else if (editMap[i][j] == imageLoader.getOgreImg()) {
	//
	// } else if (editMap[i][j] == imageLoader.getBlankSpaceImg()) {
	//
	// }
	//
	// }
	// }
	// controller.setMap(map);
	// }
}
