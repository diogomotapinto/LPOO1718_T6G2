package dkeep.controller;

import dkeep.cli.View;
import dkeep.gui.WindowController;
import dkeep.logic.DungeonMap;
import dkeep.logic.Map;
import dkeep.logic.NextLevelNotAvailable;
import utilities.Serialization;

public final class Controller {

	public enum GameAmbient {
		GUI, CONSOLE
	}

	private final StateMachine stateMachine;
	private WindowController wdwController;
	private final View view;
	private Map currentMap;
	private GameAmbient gameAmbient;
	private Serialization ser;

	
	public Controller() {
		stateMachine = new StateMachine();
		wdwController = new WindowController(this);
		view = new View();
		gameAmbient = GameAmbient.GUI;
		ser = new Serialization();
	}

	public Controller(Map currentMap, StateMachine stateMachine, String ogreNmb, char[][] editMap) {
		this.currentMap = currentMap;
		this.stateMachine = stateMachine;
		view = new View();
		gameAmbient = GameAmbient.GUI;
		ser = new Serialization();
		wdwController = new WindowController(this, ogreNmb, editMap);
		printGame();
	}

	
	public void newGame(String personality, GameAmbient gameAmbient) {
		this.gameAmbient = gameAmbient;
		stateMachine.advanceState(StateMachine.Event.PLAY);
		currentMap = new DungeonMap(personality);
		printGame();
	}

	private void printGame() {

		if (gameAmbient == GameAmbient.GUI)
			wdwController.updateGameWindow(currentMap.getPlayMap(), "You can play now");
		else if (gameAmbient == GameAmbient.CONSOLE) {

			view.printGameInfo(currentMap.getHeader(), currentMap.getPlayMap(), currentMap.getLegend());
			do {
				currentMap.play(view.getMove());
				view.printGameInfo(currentMap.getHeader(), currentMap.getPlayMap(), currentMap.getLegend());
			} while (!advanceLevel(currentMap.checkEndLevel()));
		}
	}

	public void makeMove(char move) {
		if (stateMachine.getGameState() == StateMachine.State.GAME_PLAYING) {
			currentMap.play(move);
			printGame();
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
			try {
				currentMap.nextLevel("0");

				char[][] editMap = wdwController.generateMap();
				stateMachine.advanceState(StateMachine.Event.LEVEL_UP);

				if (editMap.length == 0 || editMap[0][0] == '-')
					currentMap = currentMap.nextLevel(wdwController.getOgreNumber());
				else
					currentMap = currentMap.nextLevel(editMap);

				wdwController.updateGameWindow(currentMap.getPlayMap(), "Next Level");
				return false;

			} catch (NextLevelNotAvailable e) {

				stateMachine.advanceState(StateMachine.Event.WON);
				wdwController.updateGameWindow(currentMap.getPlayMap(), "Game Won");
				return true;
			}
		default:
			return false;
		}
	}

	public final StateMachine getStateMachine() {
		return stateMachine;
	}

	public final Map getCurrentMap() {
		return currentMap;
	}

	public final char[][] getEditMap() {
		return this.wdwController.generateMap();
	}

	public final String getOgreNumber() {
		return this.wdwController.getOgreNumber();
	}

	public void serialize() {
		ser.serialize(this);
	}

}
