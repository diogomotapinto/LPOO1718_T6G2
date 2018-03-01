package dkeep.logic;

import dkeep.cli.View;

public abstract class Map {

	// mudar variaveis para classes respetivas
	private static final char CHAR_MOVE_UP = 'w';
	private static final char CHAR_MOVE_DOWN = 's';
	private static final char CHAR_MOVE_RIGHT = 'd';
	private static final char CHAR_MOVE_LEFT = 'a';

	protected static final char WALL_CHAR = 'X';
	protected static final char CHAR_DOOR_CLOSED = 'I';
	protected static final char CHAR_DOOR_OPEN = 'S';

	protected static final char CHAR_HERO = 'H';

	protected static final char CHAR_BLANK_SPACE = ' ';

	private final String legend;
	protected final View view;

	protected Lever lever;
	protected final Hero hero;
	protected final char[][] map;
	protected boolean gameState; // fazer tipo Enum

	protected Map(char[][] map, String legend, int heroXPosition, int heroYPosition) {
		this.map = map;
		this.legend = legend;
		gameState = true;
		hero = new Hero(heroXPosition, heroYPosition);
		this.lever = new Lever(8, 8);
		this.view = new View();
	}

	// deve.se tirar este metodo, substituir por um get q passa por argumento para o
	// controller e o the view printa
	protected final void printMap() {
		view.printMatrix(map);
	}

	// deve.se tirar este metodo, substituir por um get q passa por argumento para o
	// controller e o the view printa
	protected final void printLegend() {
		view.printString(legend);
	}

	protected final void moveHero(char move) {
		switch (move) {
		case CHAR_MOVE_UP:
			if (checkMoveHero(this.hero.getXPosition() - 1, hero.getYPosition())) {
				moveHero(this.hero.getXPosition() - 1, hero.getYPosition());
			}
			break;
		case CHAR_MOVE_DOWN:
			if (checkMoveHero(this.hero.getXPosition() + 1, hero.getYPosition())) {
				moveHero(this.hero.getXPosition() + 1, hero.getYPosition());
			}
			break;
		case CHAR_MOVE_RIGHT:
			if (checkMoveHero(this.hero.getXPosition(), hero.getYPosition() + 1)) {
				moveHero(this.hero.getXPosition(), hero.getYPosition() + 1);
			}
			break;
		case CHAR_MOVE_LEFT:
			if (checkMoveHero(this.hero.getXPosition(), hero.getYPosition() - 1)) {
				moveHero(this.hero.getXPosition(), hero.getYPosition() - 1);
			}
			break;
		default:
			break;
		}

	}

	private void moveHero(int x, int y) {
		map[this.hero.getXPosition()][hero.getYPosition()] = CHAR_BLANK_SPACE;
		hero.setXPosition(x);
		hero.setYPosition(y);
		map[x][y] = CHAR_HERO;
	}

	// criar objeto lever e checkar atraves das coordenadas do objeto lever em vez
	// de usar coordenadas do map
	private boolean checkMoveHero(int x, int y) {
		return (map[x][y] == CHAR_LEVER || map[x][y] == CHAR_BLANK_SPACE || map[x][y] == CHAR_DOOR_OPEN);
	}

	// criar objeto door (com boolean aberto/fechado e checkar atraves das
	// coordenadas do objeto lever) em vez de usar coordenadas do map
	protected final void checkLever() {

		if (this.hero.getXPosition() == lever.getXPosition() && this.hero.getYPosition() == lever.getYPosition()) {
			lever.activateLever();

			// para que utilizar o caracter A
			hero.setHeroChar('A');

			// criar classe porta com icone porta fechada e porta aberta em vez de usar
			// coordenadas do map
			for (int i = 0; i < this.map.length; i++) {
				for (int j = 0; j < this.map[i].length; j++) {
					if (map[i][j] == CHAR_DOOR_CLOSED) {
						map[i][j] = CHAR_DOOR_OPEN;
					}
				}
			}
		}
	}

}
