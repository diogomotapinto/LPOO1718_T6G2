package dkeep.logic;

import dkeep.gui.GameWindow;

public final class Controller {

	private static final Controller INSTANCE = new Controller();

	private final StateMachine stateMachine;
	private final GameWindow gameWdw;
	private Map currentMap;

	private Controller() {
		stateMachine = new StateMachine();
		currentMap = new KeepMap();
		gameWdw = new GameWindow(this);
	}

	public static Controller getInstance() {
		return INSTANCE;
	}

	public void newGame() {
		stateMachine.advanceState(StateMachine.Event.PLAY);
		gameWdw.setMap(currentMap.toString());
	}

	public void makeMove(char move) {
		if (stateMachine.getGameState() == StateMachine.State.GAME_PLAYING) {
			currentMap.play(move);
			gameWdw.setMap(currentMap.toString());
			advanceLevel(currentMap.checkEndLevel());
		}
	}

	private boolean advanceLevel(byte endLevel) {
		switch (endLevel) {
		case -1:
			stateMachine.advanceState(StateMachine.Event.OVER);
			return true;
		case 0:
			return false;
		case 1:
			if (currentMap.nextLevel() == null) {
				stateMachine.advanceState(StateMachine.Event.WON);
				return true;
			} else {
				stateMachine.advanceState(StateMachine.Event.LEVEL_UP);
				currentMap = currentMap.nextLevel();
				gameWdw.setMap(currentMap.toString());
				return false;
			}
		default:
			return false;
		}
	}

}
