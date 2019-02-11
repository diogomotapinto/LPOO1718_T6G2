package dkeep.controller;

import java.io.Serializable;

public final class StateMachine implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private State gameState;

	enum State {
		INIT, GAME_RUNNING, GAME_LOST, GAME_WON, GAME_CLOSE;
		
	}

	enum Event {
		PLAY, OVER, LEVEL_UP, WON, END;
	}

	public StateMachine() {
		gameState = State.INIT;
	}

	public void advanceState(Event evt) {

		switch (this.gameState) {
		case INIT:
			if (evt == Event.PLAY)
				this.gameState = State.GAME_RUNNING;

			break;

		case GAME_RUNNING:
			if (evt == Event.OVER)
				this.gameState = State.GAME_LOST;

			if (evt == Event.WON)
				this.gameState = State.GAME_WON;

			break;

		case GAME_LOST:
			if (evt == Event.PLAY)
				this.gameState = State.GAME_RUNNING;
			else if (evt == Event.END)
				this.gameState = State.GAME_CLOSE;

			break;
		case GAME_WON:
			if (evt == Event.OVER)
				this.gameState = State.GAME_CLOSE;
			else if (evt == Event.PLAY)
				this.gameState = State.GAME_RUNNING;

			break;
		case GAME_CLOSE:
			break;
		}
	}

	public final State getGameState() {
		return gameState;
	}
}
