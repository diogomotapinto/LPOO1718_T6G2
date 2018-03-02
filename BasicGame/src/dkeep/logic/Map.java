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

	public abstract void playLevel();

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
		Position p = hero.getPosition();
		int xPosition = p.getXPosition();
		int yPosition = p.getYPosition();

		switch (move) {
		case CHAR_MOVE_UP:
			if (checkMoveHero(xPosition - 1, yPosition)) {
				moveHero(xPosition - 1, yPosition);
			}
			break;
		case CHAR_MOVE_DOWN:
			if (checkMoveHero(xPosition + 1, yPosition)) {
				moveHero(xPosition + 1, yPosition);
			}
			break;
		case CHAR_MOVE_RIGHT:
			if (checkMoveHero(xPosition, yPosition + 1)) {
				moveHero(xPosition, yPosition + 1);
			}
			break;
		case CHAR_MOVE_LEFT:
			if (checkMoveHero(xPosition, yPosition - 1)) {
				moveHero(xPosition, yPosition - 1);
			}
			break;
		default:
			break;
		}

	}

	private void moveHero(int x, int y) {
		Position heroPosition = hero.getPosition();
		map[heroPosition.getXPosition()][heroPosition.getYPosition()] = CHAR_BLANK_SPACE;
		heroPosition.setXPosition(x);
		heroPosition.setYPosition(y);
		hero.setPosition(heroPosition);
		map[x][y] = hero.getHeroChar(this.lever.isActivated());
	}

	// criar objeto lever e checkar atraves das coordenadas do objeto lever em vez
	// de usar coordenadas do map
	private boolean checkMoveHero(int x, int y) {
		return (map[x][y] == Lever.getLeverChar() || map[x][y] == CHAR_BLANK_SPACE ||map[x][y] == hero.getClub().getClubChar() || map[x][y] == CHAR_DOOR_OPEN);
	}

	// criar objeto door (com boolean aberto/fechado e checkar atraves das
	// coordenadas do objeto lever) em vez de usar coordenadas do map
	protected final void checkLever() {
		Position heroPosition = hero.getPosition();
		Position leverPosition = lever.getPosition();
		if (leverPosition.equals(heroPosition) && (heroPosition.hashCode() == leverPosition.hashCode())) {
			lever.activateLever();
			// criar classe porta com icone porta fechada e porta aberta em vez de usar
			// coordenadas do map
			for (int i = 0; i < this.map.length; i++) {
				for (int j = 0; j < this.map[i].length; j++) {
					if (map[i][j] == CHAR_DOOR_CLOSED) {
						map[i][j] = CHAR_DOOR_OPEN;
						hero.setLeverState(true);
					}
				}
			}
		}
	}

}
