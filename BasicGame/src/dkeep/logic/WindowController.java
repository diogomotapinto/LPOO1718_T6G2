package dkeep.logic;

import javax.swing.JOptionPane;

import dkeep.gui.GameSettingsWindow;
import dkeep.gui.GameWindow;
import dkeep.gui.PopUpWindow;
import utilities.Regex;

public final class WindowController {
	private final Controller controller;
	private final GameWindow gameWdw;
	private final GameSettingsWindow gameStgWdw;
	private final PopUpWindow popUpWdw;
	private final Regex regex;
	private String ogreNumber;
	private String guardPersonality;

	public WindowController(Controller controller) {
		this.controller = controller;
		this.gameWdw = new GameWindow(this);
		this.gameStgWdw = new GameSettingsWindow(this);
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
		// String numberOfOgres = ogreTextField.getText();
		// String personality = comboBox.getSelectedItem().toString();
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

	public void showWindowGameSettings() {
		gameStgWdw.setVisible(true);
	}

	public String getOgreNumber() {
		return ogreNumber;
	}
}
