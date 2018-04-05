package dkeep.controller;

import java.io.Serializable;

public final class StateMachine implements Serializable {

	private State gameState;

	enum State {
		INIT, GAME_PLAYING, GAME_LOST, GAME_WON, GAME_CLOSE;
	}

	enum Event {
		PLAY, OVER, LEVEL_UP, WON, END;
	}

	public StateMachine() {
		gameState = State.INIT;
	}

	void advanceState(Event evt) {

		switch (this.gameState) {
		case INIT:
			if (evt == Event.PLAY) {
				this.gameState = State.GAME_PLAYING;
			}
			break;

		case GAME_PLAYING:
			if (evt == Event.OVER) {
				this.gameState = State.GAME_LOST;
			}
			if (evt == Event.LEVEL_UP) {
				// carregar novo mapa
				// dar sinalizal�ao que subiu de nivel
			}
			if (evt == Event.WON) {
				this.gameState = State.GAME_WON;
				// sair do ciclo e mandar mensagem de sucesso
			}
			break;

		case GAME_LOST:
			if (evt == Event.PLAY) {
				this.gameState = State.GAME_PLAYING;
				// sair do ciclo e mandar mensagem de sucesso
			} else if (evt == Event.END) {
				this.gameState = State.GAME_CLOSE;
			}

			break;
		case GAME_WON:
			if (evt == Event.OVER) {
				this.gameState = State.GAME_CLOSE;
			} else if (evt == Event.PLAY) {
				this.gameState = State.GAME_PLAYING;
			}
			break;
		case GAME_CLOSE:
			break;
		}
	}

	public final State getGameState() {
		return gameState;
	}
}
