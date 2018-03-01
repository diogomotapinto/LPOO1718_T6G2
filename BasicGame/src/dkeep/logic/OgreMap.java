package dkeep.logic;

import java.util.ArrayList;

import utilities.Utilities;

public class OgreMap extends Map {

	private static final char OGRE_CHAR = 'O';
	private static final char CLUB_CHAR = '*';

	private Ogre ogre;
	private ArrayList<Ogre> ogreList;
	private int ogresNumber;

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

				{ WALL_CHAR, Hero.getHeroChar(), CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE,
						CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, WALL_CHAR },
				{ WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR } },
				"\nX - Wall \nI - Exit Door \nH - Hero \nO - Crazy Ogre \nk - key \nempty cell - free space", 7, 1);

		ogreList = new ArrayList<Ogre>();
		ogresNumber = Utilities.generateRandomNumber(1, 3);
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

	public final void playLevelTwo() {
		System.out.println("Nivel 2!!!");
		initializeMap();
		do {
			printMap();
			printLegend();
			super.moveHero(this.view.getMove());
			moveClub(hero);
			checkLever();
			map[this.hero.getPosition().getXPosition()][this.hero.getPosition().getYPosition()] = Hero.getHeroChar();
			for (int i = 0; i < ogresNumber; i++) {
				moveOgre(ogreList.get(i));
			}
			checkIfStunned();
		} while (!checkAdvanceLevel());
	}

	public final void initializeMap() {
		Position heroPosition = hero.getPosition();
		 for (int i = 0; i < ogresNumber; i++) {
		 moveOgre(ogreList.get(i));
		 }
		 moveClub(hero);
		 map[heroPosition.getXPosition()][heroPosition.getYPosition()] = Hero.getHeroChar();
	}

	private final boolean checkAdvanceLevel() {
		 // para terminar basta chegar a um dos cantos
		Position heroPosition = hero.getPosition();
		
		 if (this.hero.getLeverState() && (heroPosition.getXPosition() == 1 &&
		 heroPosition.getYPosition() == 0)) {
		 System.out.println("\nGanhou o jogo");
		 return true;
		 }
		
		 try {
		 if (checkOgreCollision() || checkClubCollision()) {
		
		 printMap();
		 System.out.print("\nPerdeu jogo");
		 super.gameState = false;
		 return true;
		 }
		 } catch (ArrayIndexOutOfBoundsException e) {
		 e.printStackTrace();
		 }
		return false;
	}

	private final boolean checkOgreCollision() {
		for (int i = 0; i < ogreList.size(); i++) {
			if (Utilities.checkAdjacentCollision(this.hero.getPosition(),
					this.ogreList.get(i).getPosition())) {
				return true;
			}
		}
		return false;
	}

	private final boolean checkClubCollision() {
		for (int i = 0; i < ogreList.size(); i++) {
			if (Utilities.checkAdjacentCollision(this.hero.getPosition(),
					this.ogreList.get(i).getMyClub().getPosition())) {
				return true;
			}
		}	
		return false;
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
		
		 int position[];
		 do {
		 position = Utilities.getAdjacentPosition(ogrePosition.getXPosition(),
				 ogrePosition.getYPosition());
		 } while (map[position[0]][position[1]] != CHAR_BLANK_SPACE);
		
	
		 ogre.setPosition(ogrePosition);
		 map[ogrePosition.getXPosition()][ogrePosition.getYPosition()] = OGRE_CHAR;
		 moveClub(ogre);
	}

	private final void moveClub(Ogre ogre) {
		Position clubPosition = ogre.getMyClub().getPosition();

		map[clubPosition.getXPosition()][clubPosition.getYPosition()]
				= CHAR_BLANK_SPACE;

		int position[];
		do {
			position = Utilities.getAdjacentPosition(ogre.getPosition().getXPosition(),ogre.getPosition().getYPosition());
		} while (map[position[0]][position[1]] == WALL_CHAR ||
				map[position[0]][position[1]] == OGRE_CHAR
				|| map[position[0]][position[1]] == Hero.getHeroChar()
				|| map[position[0]][position[1]] == CHAR_DOOR_CLOSED ||
				map[position[0]][position[1]] == 'S'
				//|| map[position[0]][position[1]] == Hero.getHeroClubChar()
				);

		ogre.getMyClub().setPosition(clubPosition);

		if (clubPosition.getXPosition() == 1 &&
				clubPosition.getYPosition() == 7) {
			if (!this.lever.isActivated()) {
				map[1][7] = '$';
			}
		} else {
			map[clubPosition.getXPosition()][clubPosition.getYPosition()]
					= CLUB_CHAR;
			if (!this.lever.isActivated()) {
				map[1][7] = Lever.getLeverChar();
			}

		}

	}

	private final void moveClub(Hero hero) {
		Position clubPosition = hero.getMyClub().getPosition();
		map[clubPosition.getXPosition()][clubPosition.getYPosition()]
				= CHAR_BLANK_SPACE;

		int position[];
		do {
			position = Utilities.getAdjacentPosition(hero.getPosition().getXPosition(),hero.getPosition().getYPosition());
		} while (map[position[0]][position[1]] == (WALL_CHAR) ||
				map[position[0]][position[1]] == (OGRE_CHAR)	|| 
				map[position[0]][position[1]] == (Hero.getHeroChar())|| 
				map[position[0]][position[1]] == (CHAR_DOOR_CLOSED) ||
				map[position[0]][position[1]] == ('S')	||
				map[position[0]][position[1]] == Hero.getHeroChar());

		hero.getMyClub().setPosition(clubPosition);

		if (clubPosition.getXPosition() == 1 &&
				clubPosition.getYPosition() == 7) {
			if (!this.lever.isActivated()) {
				map[1][7] = '$';
			}
		} else {

			map[clubPosition.getXPosition()][clubPosition.getYPosition()] = Club.getClubChar();

			if (!this.lever.isActivated()) {
				map[1][7] = Lever.getLeverChar();
			}

		}

	}

	public void checkIfStunned() {

		for (int i = 0; i < ogreList.size(); i++) {
			if (Utilities.checkAdjacentCollision(this.ogreList.get(i).getPosition(),
					this.hero.getMyClub().getPosition())) {
				ogreList.get(i).setStunned(true);
			}
		}
	}

}
