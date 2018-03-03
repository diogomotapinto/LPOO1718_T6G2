package dkeep.logic;

import dkeep.cli.View;

public class Controller {

	private final View view;
	private StateMachine stateMachine;
	private Map currentMap;

	public Controller() {
		stateMachine = new StateMachine();
		currentMap = new DungeonMap();
		this.view = new View();
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
		stateMachine.advanceState(StateMachine.Event.WON);
	}

	// private void runStateGameLost() {
	// }
	//
	// private void runStateGameWon() {
	// // TODO Auto-generated method stub
	// }
	//
	// private void runStateGameClose() {
	// // TODO Auto-generated method stub
	// }

	private boolean advanceLevel(boolean endLevel) {
		if (!endLevel) {
			return false;
		}

		if (currentMap.nextLevel() == null) {
			return true;
		} else {
			currentMap = currentMap.nextLevel();
			return false;
		}
	}

}
