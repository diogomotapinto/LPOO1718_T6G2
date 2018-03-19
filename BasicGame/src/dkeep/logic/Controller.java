package dkeep.logic;

import dkeep.gui.GameWindow;

public final class Controller {

	private static final Controller INSTANCE = new Controller();

	private final StateMachine stateMachine;
	private final GameWindow gameWdw;
	private Map currentMap;
	private String ogresNumber;

	private Controller() {
		stateMachine = new StateMachine();
		gameWdw = new GameWindow(this);
	}

	public static Controller getInstance() {
		return INSTANCE;
	}

	public void newGame(String ogresNumber, String personality) {
		this.ogresNumber = ogresNumber;
		stateMachine.advanceState(StateMachine.Event.PLAY);
		currentMap = new DungeonMap(personality);
		gameWdw.setMap(currentMap.toString());
		gameWdw.setLegend("You can play now");
	}

	public void makeMove(char move) {
		if (stateMachine.getGameState() == StateMachine.State.GAME_PLAYING) {
			currentMap.play(move);
			gameWdw.setMap(currentMap.toString());
			gameWdw.setLegend("You can move");
			advanceLevel(currentMap.checkEndLevel());
		}
	}

	private boolean advanceLevel(byte endLevel) {
		switch (endLevel) {
		case -1:
			stateMachine.advanceState(StateMachine.Event.OVER);
			gameWdw.setLegend("Game Over");
			return true;
		case 0:
			return false;
		case 1:
			if (currentMap.nextLevel(this.ogresNumber) == null) {
				stateMachine.advanceState(StateMachine.Event.WON);
				gameWdw.setLegend("Game Won");
				return true;
			} else {
				stateMachine.advanceState(StateMachine.Event.LEVEL_UP);
				currentMap = currentMap.nextLevel(this.ogresNumber);
				gameWdw.setMap(currentMap.toString());
				gameWdw.setLegend("Next Level");
				return false;
			}
		default:
			return false;
		}
	}

}
