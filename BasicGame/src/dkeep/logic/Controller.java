package dkeep.logic;

import java.util.Arrays;

import dkeep.cli.View;
import dkeep.gui.Window;

public final class Controller {

	private static final Controller INSTANCE = new Controller();

	private final Window window;
	private final View view;
	private final StateMachine stateMachine;
	private Map currentMap;

	private Controller() {
		stateMachine = new StateMachine();
		currentMap = new KeepMap();
		this.view = new View();
		this.window = new Window();
	}

	public static Controller getInstance() {
		return INSTANCE;
	}

	public void run() {

		do {
			if (stateMachine.getGameState() == StateMachine.State.INIT) {
				runStateInit();
			} else if (stateMachine.getGameState() == StateMachine.State.GAME_PLAYING) {
				runStateGamePlaying();
			}

		} while (stateMachine.getGameState() != StateMachine.State.GAME_LOST
				&& stateMachine.getGameState() != StateMachine.State.GAME_WON
				&& stateMachine.getGameState() != StateMachine.State.GAME_CLOSE);
	}

	private void runStateInit() {
		stateMachine.advanceState(StateMachine.Event.PLAY);
	}

	private void runStateGamePlaying() {
		view.printGameInfo(currentMap.getHeader(), currentMap.getMap(), currentMap.getLegend());
		do {
			currentMap.play(view.getMove());
			view.printGameInfo(currentMap.getHeader(), currentMap.getMap(), currentMap.getLegend());
			window.showMap(currentMap.toString());
		} while (!advanceLevel(currentMap.checkEndLevel()));
	}

	private boolean advanceLevel(byte endLevel) {
		switch (endLevel) {
		case -1:
			view.printString("\nPerdeu jogo");
			stateMachine.advanceState(StateMachine.Event.OVER);
			return true;
		case 0:
			return false;
		case 1:
			view.printString("\nProximo Nivel!!!");
			if (currentMap.nextLevel() == null) {
				stateMachine.advanceState(StateMachine.Event.WON);
				return true;
			} else {
				stateMachine.advanceState(StateMachine.Event.LEVEL_UP);
				currentMap = currentMap.nextLevel();
				view.printGameInfo(currentMap.getHeader(), currentMap.getMap(), currentMap.getLegend());
				return false;
			}
		default:
			return false;
		}
	}
	
	

}
