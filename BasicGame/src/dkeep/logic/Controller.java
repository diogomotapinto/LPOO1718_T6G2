package dkeep.logic;

import dkeep.cli.View;

public final class Controller {

	private static final Controller INSTANCE = new Controller();

	private final View view;
	private StateMachine stateMachine;
	private Map currentMap;

	private Controller() {
		stateMachine = new StateMachine();
		currentMap = new DungeonMap();
		this.view = new View();
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
			} else if (stateMachine.getGameState() == StateMachine.State.GAME_LOST) {
				// runStateGameLost();
			} else if (stateMachine.getGameState() == StateMachine.State.GAME_WON) {
				// runStateGameWon();
			} else if (stateMachine.getGameState() == StateMachine.State.GAME_CLOSE) {
				// runStateGameClose();
			}

			// retirar condiçoes a medida que as funcionalidades dentro dos ifs respetivos
			// forem implementadas
		} while (stateMachine.getGameState() != StateMachine.State.GAME_LOST
				&& stateMachine.getGameState() != StateMachine.State.GAME_WON
				&& stateMachine.getGameState() != StateMachine.State.GAME_CLOSE);
	}

	private void runStateInit() {
		stateMachine.advanceState(StateMachine.Event.PLAY);
	}

	private void runStateGamePlaying() {
		view.printGameInfo(currentMap.getMap(), currentMap.getLegend());
		do {
			currentMap.play(view.getMove());
			view.printGameInfo(currentMap.getMap(), currentMap.getLegend());
		} while (!advanceLevel(currentMap.checkEndLevel()));
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
				return false;
			}
		default:
			return false;
		}
	}

}
