package dkeep.logic;

import dkeep.cli.View;

public final class Controller {

	private static final Controller INSTANCE = new Controller();

	private final View view;
	private final StateMachine stateMachine;
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
			}

			// retirar condi�oes de termina��o a medida que as funcionalidadaes do estado
			// respetivod forem implementadas e associadas a cadeia de ifs acima
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