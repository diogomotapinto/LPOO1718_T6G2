package dkeep.controller;

import java.util.HashMap;
import dkeep.cli.View;
import dkeep.gui.WindowController;
import dkeep.logic.LevelOne;
import dkeep.logic.Direction;
import dkeep.logic.FoeInfo;
import dkeep.logic.Level;
import dkeep.logic.NextLevelNotAvailable;
import dkeep.logic.OgreNumber;
import utilities.Serialization;
import utilities.Utilities;

public final class Controller {
	private static final char CHAR_MOVE_UP = 'w';
	private static final char CHAR_MOVE_DOWN = 's';
	private static final char CHAR_MOVE_RIGHT = 'd';
	private static final char CHAR_MOVE_LEFT = 'a';

	public enum GameAmbient {
		GUI, CONSOLE
	}

	private final StateMachine stateMachine;
	private WindowController wdwController;
	private final View view;
	private Level currentLevel;
	private GameAmbient gameAmbient = GameAmbient.GUI;
	private Serialization ser;
	private HashMap<Character, Direction> moves;

	// ---------------------
	public Controller() {
		currentLevel = null;
		stateMachine = new StateMachine();
		view = new View();
		ser = new Serialization();
		wdwController = new WindowController(this);
		moves = new HashMap<Character, Direction>();
		loadValidMoves();
	}

//	public Controller(Level currentMap, StateMachine stateMachine, OgreNumber ogreNmb, char[][] editMap) {
//		this.currentLevel = currentMap;
//		this.stateMachine = stateMachine;
//		view = new View();
//		ser = new Serialization();
//		wdwController = new WindowController(this, ogreNmb, editMap);
//		moves = new HashMap<Character, Direction>();
//		loadValidMoves();
//		runGame();
//	}

	public Controller(Level currentMap, StateMachine stateMachine, OgreNumber ogreNmb, char[][] editMap) {
		this.currentLevel = currentMap;
		this.stateMachine = stateMachine;
		view = new View();
		ser = new Serialization();
		wdwController = new WindowController(this, ogreNmb, editMap);
		moves = new HashMap<Character, Direction>();
		loadValidMoves();
//		runGame();
	}

	private void loadValidMoves() {
		moves.put(CHAR_MOVE_UP, Direction.UP);
		moves.put(CHAR_MOVE_DOWN, Direction.DOWN);
		moves.put(CHAR_MOVE_LEFT, Direction.LEFT);
		moves.put(CHAR_MOVE_RIGHT, Direction.RIGHT);
	}

	// ---------------------
	public void newGame(FoeInfo personality, GameAmbient gameAmbient) {
		this.gameAmbient = gameAmbient;
		stateMachine.advanceState(StateMachine.Event.PLAY);
		currentLevel = new LevelOne(personality);
		runGame();
	}

	private void runGame() {
		if (gameAmbient == GameAmbient.GUI)
			wdwController.updateGameWindow(currentLevel.getPlayMap(), "You can play now");
		else if (gameAmbient == GameAmbient.CONSOLE) {
			runGameConsole();
		}
	}

	private void runGameConsole() {
		displayGame();
		do {
			makeMove(askMove());
		} while (isGameRunning());

		view.drawFinalMessage(isGameWon());
		view.close();
	}

	public void makeMove(Direction move) {
		currentLevel.play(move);
		displayGame();
		updateGameState(currentLevel.getGameState());
	}

	private final Direction askMove() {
		char move;

		boolean flag = false;
		do {
			move = view.getMove();
			if (moves.containsKey(move)) {
				flag = true;
			} else {
				view.promptMsg("Introduziu opcao invalida");
			}
		} while (!flag);

		return moves.get(move);
	}

	private boolean isGameRunning() {
		return stateMachine.getGameState() == StateMachine.State.GAME_RUNNING;
	}

	private boolean isGameWon() {
		return stateMachine.getGameState() == StateMachine.State.GAME_WON;
	}

	public final Direction getMove(char move) {

		if (moves.containsKey(move)) {
			return moves.get(move);
		} else {
			return Direction.INVALID;
		}
	}

	private void displayGame() {
		if (gameAmbient == GameAmbient.GUI)
			wdwController.updateGameWindow(currentLevel.getPlayMap(), "Next Level");
		else if (gameAmbient == GameAmbient.CONSOLE) {
			view.printGameInfo(currentLevel.getHeader(), currentLevel.getPlayMap(), currentLevel.getMap().getLegend());
		}
	}

	private void updateGameState(byte endLevel) {
		switch (endLevel) {
		case -1:
			stateMachine.advanceState(StateMachine.Event.OVER);
			break;
		case 1:
			try {

				stateMachine.advanceState(StateMachine.Event.LEVEL_UP);

				char[][] editMap = wdwController.generateMap();

				if (editMap.length == 0 || editMap[0][0] == '-')
					currentLevel = currentLevel.nextLevel(wdwController.getOgreNumber());
				else {
					byte[] heroPosition = Utilities.findPosition(editMap, 'A');
					byte[] leverPosition = Utilities.findPosition(editMap, 'k');
					byte[][] ogrePositions = Utilities.findPositions(editMap, '0');
					byte[][] winningDoorPositions = Utilities.findPositions(editMap, 'I');
					byte[][] wallPositions = Utilities.findPositions(editMap, 'X');
					editMap = clearMap(editMap);
					currentLevel = currentLevel.nextLevel(editMap, heroPosition, leverPosition, ogrePositions,
							winningDoorPositions, wallPositions);
				}
				displayGame();

			} catch (NextLevelNotAvailable e) {
				stateMachine.advanceState(StateMachine.Event.WON);
			}
			break;
		default:
			break;
		}
	}

	private char[][] clearMap(char[][] map) {
		for (byte i = 0; i < map.length; i++) {
			for (byte j = 0; j < map[i].length; j++) {
				if (!(map[i][j] == ' ' || map[i][j] == 'X')) {
					map[i][j] = ' ';
				}
			}
		}
		return map;
	}

	public final StateMachine getStateMachine() {
		return stateMachine;
	}

	public final Level getCurrentMap() {
		return currentLevel;
	}

	public final char[][] getEditMap() {
		return this.wdwController.generateMap();
	}

	public void serialize() {
		ser.serialize(this);
	}

	public OgreNumber getOgreNumber() {
		return ((OgreNumber) wdwController.getOgreNumber());
	}

}
