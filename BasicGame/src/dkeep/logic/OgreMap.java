package dkeep.logic;

import java.util.ArrayList;

import utilities.Utilities;

public class OgreMap extends Map implements MapRules {

	private static final char OGRE_CHAR = 'O';
	private static final char CLUB_CHAR = '*';

	private Ogre ogre;
	private ArrayList<Ogre> ogreList;

	public OgreMap() {
		// passes map and legend as argument
		super(new char[][] {
			{ WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR },
			{ CHAR_DOOR_CLOSED, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE,
				CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, Lever.getLeverChar(), WALL_CHAR },
			{ WALL_CHAR, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE,
					CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, WALL_CHAR },
			{ WALL_CHAR, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE,
						CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, WALL_CHAR },
			{ WALL_CHAR, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE,
							CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, WALL_CHAR },
			{ WALL_CHAR, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE,
								CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, WALL_CHAR },
			{ WALL_CHAR, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE,
									CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, WALL_CHAR },

			{ WALL_CHAR, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE,
										CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, WALL_CHAR },
			{ WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR } },
				"\nX - Wall \nI - Exit Door \nH - Hero \nO - Crazy Ogre \nk - key \nempty cell - free space", 7, 1);

		ogreList = new ArrayList<Ogre>();
		int ogresNumber = Utilities.generateRandomNumber(1, 1);
		Position leverPos = new Position(1,7);
		lever.setPosition(leverPos);
		int x;
		int y;
		for (int i = 0; i < ogresNumber; i++) {
			do {
				x = Utilities.generateRandomNumber(1, 7);
				y = Utilities.generateRandomNumber(1, 7);
				ogre = new Ogre(x, y);
			} while (map[x][y] != CHAR_BLANK_SPACE);
			ogreList.add(ogre);
		}
	}

	@Override
	public void playLevel() {
		printHeader();
		initializeMap();
		do {
			super.printMap();
			super.printLegend();
			super.moveHero(this.view.getMove());
			moveClub(this.hero);
			checkLever();
			map[this.hero.getPosition().getXPosition()][this.hero.getPosition().getYPosition()] = this.hero.getHeroChar(this.lever.isActivated());
			checkIfStunned();
			for (int i = 0; i < ogreList.size(); i++) {
				moveOgre(ogreList.get(i));
				moveClub(ogreList.get(i));
			}
		} while (!checkEndLevel());
	}

	private final void moveOgre(Ogre ogre) {
		Position ogrePosition = ogre.getPosition();

		if (ogre.getStunned()) {
			if (ogre.getStunCounter() > 0) {
				ogre.stunCounter();
				map[ogrePosition.getXPosition()][ogrePosition.getYPosition()] = '8';
				return;
			} else {
				ogre.setStunned(false);
			}
		}

		map[ogrePosition.getXPosition()][ogrePosition.getYPosition()] = CHAR_BLANK_SPACE;

		Position newPosition;
		do {
			newPosition = Utilities.getAdjacentPosition(ogrePosition.getXPosition(), ogrePosition.getYPosition());
		} while (map[newPosition.getXPosition()][newPosition.getYPosition()] != CHAR_BLANK_SPACE);

		
		ogre.setPosition(newPosition);
		ogrePosition = ogre.getPosition();
		map[ogrePosition.getXPosition()][ogrePosition.getYPosition()] = OGRE_CHAR;
	}

	// qnd recebes o clubHolder, utilizando
	private final void moveClub(ClubHolder clubHolder) {
		Position clubPosition = clubHolder.getClub().getPosition();

		map[clubPosition.getXPosition()][clubPosition.getYPosition()] = CHAR_BLANK_SPACE;

		Position newClubPosition;
		do {
			newClubPosition = Utilities.getAdjacentPosition(clubHolder.getPosition().getXPosition(),
					clubHolder.getPosition().getYPosition());
		} while ( map[newClubPosition.getXPosition()][newClubPosition.getYPosition()] == WALL_CHAR 
				|| map[newClubPosition.getXPosition()][newClubPosition.getYPosition()] == CHAR_DOOR_CLOSED 
				|| map[newClubPosition.getXPosition()][newClubPosition.getYPosition()] == 'S'
				|| map[newClubPosition.getXPosition()][newClubPosition.getYPosition()] == Club.getClubChar()
				||checkValidPosition(newClubPosition));

		clubHolder.getClub().setPosition(newClubPosition);

		if (newClubPosition.getXPosition() == 1 && newClubPosition.getYPosition() == 7) {
			if (!this.lever.isActivated()) {
				map[1][7] = '$';
			}
		} else {
			map[newClubPosition.getXPosition()][newClubPosition.getYPosition()] = CLUB_CHAR;
			if (!this.lever.isActivated()) {
				map[1][7] = Lever.getLeverChar();
			}
		}
	}

	private boolean checkValidPosition(Position position) {

		for(int i = 0; i < this.ogreList.size(); i++){
			if(position.equals(this.ogreList.get(i).getPosition()))
			{
				return true;
			}
		}

		if(position.equals(this.hero.getPosition())) {
			return true;
		}

		return false;
	}

	private void checkIfStunned() {
		for (int i = 0; i < ogreList.size(); i++) {
			if (Utilities.checkAdjacentCollision(this.ogreList.get(i).getPosition(),
					this.hero.getClub().getPosition())) {
				ogreList.get(i).setStunned(true);
			}
		}
	}

	@Override
	public void printHeader() {
		view.printString("Nivel 2!!!");
		view.printString("\nNumero de ogres= " + this.ogreList.size());
	}

	@Override
	public boolean checkEndLevel() {
		// para terminar basta chegar a um dos cantos
		Position heroPosition = this.hero.getPosition();

		if (checkWon(heroPosition.getXPosition(), heroPosition.getYPosition())) {
			System.out.println("\nGanhou o jogo");
			return true;
		}

		// tem de se fazer refactor do codigo
		if (checkLost(0, 0)) {
			printMap();
			System.out.print("\nPerdeu jogo");
			super.gameState = false;
			return true;
		}

		return false;
	}

	@Override
	public boolean checkWon(int x, int y) {
		return this.hero.getLeverState() && (x == 1 && y == 0);
	}

	@Override
	public boolean checkLost(int x, int y) {
		return checkOgreCollision() || checkClubCollision();
	}

	// corrigido porque se perdia o jogo se passar numa posi��o adjacente ao ogre
	// mas com ele atordoado
	private final boolean checkOgreCollision() {
		for (int i = 0; i < ogreList.size(); i++) {
			if (!ogre.getStunned()
					&& Utilities.checkAdjacentCollision(this.hero.getPosition(), this.ogreList.get(i).getPosition())) {
				return true;
			}
		}
		return false;
	}

	// corrigido porque se perdia o jogo se passar numa posi��o adjacente ao ogre
	// mas com ele atordoado
	private final boolean checkClubCollision() {
		for (int i = 0; i < ogreList.size(); i++) {
			if (Utilities.checkAdjacentCollision(this.hero.getPosition(),
					this.ogreList.get(i).getClub().getPosition())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void initializeMap() {
		for (int i = 0; i < ogreList.size(); i++) {
			moveOgre(ogreList.get(i));
			moveClub(ogreList.get(i));
			
		}
		moveClub(hero);
		Position heroPosition = hero.getPosition();
		map[heroPosition.getXPosition()][heroPosition.getYPosition()] = this.hero.getHeroChar(this.lever.isActivated());

	}

}
