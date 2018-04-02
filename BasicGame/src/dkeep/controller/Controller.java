package dkeep.controller;

import dkeep.cli.View;
import dkeep.logic.DungeonMap;
import dkeep.logic.Map;
import dkeep.logic.NextLevelNotAvailable;
import dkeep.logic.model.Guard;

public final class Controller {

	public enum GameAmbient {
		GUI, CONSOLE
	};

	private static final Controller INSTANCE = new Controller();
	private final StateMachine stateMachine;
	private final WindowController wdwController;
	private final View view;
	private Map currentMap;
	private GameAmbient gameAmbient;

	/**
	 * Class constructor
	 */
	private Controller() {
		stateMachine = new StateMachine();
		wdwController = new WindowController(this);
		view = new View();
		gameAmbient = GameAmbient.GUI;
	}

	/**
	 * 
	 * @return
	 */
	public static Controller getInstance() {
		return INSTANCE;
	}

	/**
	 * Method called to start a new Game
	 * 
	 * @param personality
	 *            string with the information for the DungeonMap
	 */
	void newGame(String personality, GameAmbient gameAmbient) {
		this.gameAmbient = gameAmbient;
		stateMachine.advanceState(StateMachine.Event.PLAY);
		currentMap = new DungeonMap(personality);
		printGame();
	}

	private void printGame() {

		if (gameAmbient == GameAmbient.GUI) {
			wdwController.updateGameWindow(currentMap.getPlayMap(), "You can play now");
		} else if (gameAmbient == GameAmbient.CONSOLE) {

			view.printGameInfo(currentMap.getHeader(), currentMap.getPlayMap(), currentMap.getLegend());
			do {
				currentMap.play(view.getMove());
				view.printGameInfo(currentMap.getHeader(), currentMap.getPlayMap(), currentMap.getLegend());
			} while (!advanceLevel(currentMap.checkEndLevel()));

		}
	}

	/**
	 * Method called to make the hero move, the other characters in the game will
	 * also move
	 * 
	 * @param move
	 *            char that "indicates" the direction of the move
	 */
	void makeMove(char move) {
		if (stateMachine.getGameState() == StateMachine.State.GAME_PLAYING) {
			currentMap.play(move);
			printGame();
			advanceLevel(currentMap.checkEndLevel());
		}
	}

	/**
	 * Methood set the state of the game
	 * 
	 * @param endLevel
	 * @return true if the game is won and false otherwise
	 */
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

				if (editMap.length == 0)
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

}
