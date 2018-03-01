package dkeep.logic;

import java.util.ArrayList;

import utilities.Utilities;

public class OgreMap extends Map {

	private static final char OGRE_CHAR = 'O';
	private static final char CLUB_CHAR = '*';
	private static final char HCLUB_CHAR = 'º';
	private Ogre ogre;
	private ArrayList<Ogre> ogreList;
	private int ogresNumber;

	public OgreMap() {
		// passes map and legend as argument
		super(new char[][] {
				{ WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR },
				{ CHAR_DOOR_CLOSED, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE,
						CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_LEVER, WALL_CHAR },
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

				{ WALL_CHAR, CHAR_HERO, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE,
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
			map[this.hero.getXPosition()][this.hero.getYPosition()] = hero.getHeroChar();
			for (int i = 0; i < ogresNumber; i++) {
				moveOgre(ogreList.get(i));
			}
			checkIfStunned();
		} while (!checkAdvanceLevel());
	}

	private final boolean checkAdvanceLevel() {
		// para terminar basta chegar a um dos cantos
		if (this.hero.getXPosition() == 0 || this.hero.getYPosition() == 0) {
			System.out.println("\nGanhou o jogo");
			return true;
		}

		try {
			if (map[this.hero.getXPosition() - 1][hero.getYPosition()] == OGRE_CHAR
					|| map[this.hero.getXPosition() + 1][hero.getYPosition()] == OGRE_CHAR
					|| map[this.hero.getXPosition()][hero.getYPosition() - 1] == OGRE_CHAR
					|| map[this.hero.getXPosition()][hero.getYPosition() + 1] == OGRE_CHAR
					|| map[this.hero.getXPosition() - 1][hero.getYPosition()] == CLUB_CHAR
					|| map[this.hero.getXPosition() + 1][hero.getYPosition()] == CLUB_CHAR
					|| map[this.hero.getXPosition()][hero.getYPosition() - 1] == CLUB_CHAR
					|| map[this.hero.getXPosition()][hero.getYPosition() + 1] == CLUB_CHAR) {

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

	public final void initializeMap() {
		for (int i = 0; i < ogresNumber; i++) {
			moveOgre(ogreList.get(i));
		}
		moveClub(hero);
		map[hero.getXPosition()][hero.getYPosition()] = CHAR_HERO;
	}

	private final void moveOgre(Ogre myOgre) {

		if (myOgre.getStunned()) {
			if (myOgre.getStunCounter() > 0) {
				myOgre.stunCounter();
				map[myOgre.getXPosition()][myOgre.getYPosition()] = '8';
				return;
			} else {
				myOgre.setStunned(false);
			}
		}

		map[myOgre.getXPosition()][myOgre.getYPosition()] = CHAR_BLANK_SPACE;

		int position[];
		do {
			position = Utilities.getAdjacentPosition(myOgre.getXPosition(), myOgre.getYPosition());
		} while (map[position[0]][position[1]] != CHAR_BLANK_SPACE);

		myOgre.setXPosition(position[0]);
		myOgre.setYPosition(position[1]);
		map[myOgre.getXPosition()][myOgre.getYPosition()] = OGRE_CHAR;
		moveClub(myOgre);
	}

	private final void moveClub(Ogre character) {
//		map[character.getMyClub().getXPosition()][character.getMyClub().getYPosition()] = CHAR_BLANK_SPACE;
//
//		int position[];
//		do {
//			position = Utilities.getAdjacentPosition(character.getXPosition(), character.getYPosition());
//		} while (map[position[0]][position[1]] == WALL_CHAR || map[position[0]][position[1]] == OGRE_CHAR
//				|| map[position[0]][position[1]] == CHAR_HERO || map[position[0]][position[1]] == CHAR_DOOR_CLOSED
//				|| map[position[0]][position[1]] == 'S' || map[position[0]][position[1]].equals("HCLUB_CHAR"));
//
//		Club tempClub = character.getMyClub();
//		tempClub.setxPosition(position[0]);
//		tempClub.setyPosition(position[1]);
//		character.setMyClub(tempClub);
//
//		if (character.getMyClub().getXPosition() == 1 && character.getMyClub().getYPosition() == 7) {
//			if (!this.lever.isActivated()) {
//				map[1][7] = '$';
//			}
//		} else {
//			map[character.getMyClub().getXPosition()][character.getMyClub().getYPosition()] = CLUB_CHAR;
//
//			if (!this.lever.isActivated()) {
//				map[1][7] = CHAR_LEVER;
//			}
//
//		}

	}

	private final void moveClub(Hero character) {
//		map[character.getMyClub().getXPosition()][character.getMyClub().getYPosition()] = CHAR_BLANK_SPACE;
//
//		int position[];
//		do {
//			position = Utilities.getAdjacentPosition(character.getXPosition(), character.getYPosition());
//		} while (map[position[0]][position[1]] == (WALL_CHAR) || map[position[0]][position[1]] == (OGRE_CHAR)
//				|| map[position[0]][position[1]] == (CHAR_HERO) || map[position[0]][position[1]] == (CHAR_DOOR_CLOSED)
//				|| map[position[0]][position[1]] == ('S') || map[position[0]][position[1]] == ("HCLUB_CHAR"));
//
//		Club tempClub = character.getMyClub();
//		tempClub.setxPosition(position[0]);
//		tempClub.setyPosition(position[1]);
//		character.setMyClub(tempClub);
//
//		if (character.getMyClub().getXPosition() == 1 && character.getMyClub().getYPosition() == 7) {
//			if (!this.lever.isActivated()) {
//				map[1][7] = '$';
//			}
//		} else {
//
//			map[character.getMyClub().getXPosition()][character.getMyClub().getYPosition()] = HCLUB_CHAR;
//
//			if (!this.lever.isActivated()) {
//				map[1][7] = CHAR_LEVER;
//			}
//
//		}

	}

	public void checkIfStunned() {

		// for (int i = 0; i < ogreList.size(); i++) {
		// if (map[ogreList.get(i).getXPosition() - 1][ogreList.get(i).getYPosition()]
		// == HCLUB_CHAR
		// || map[ogreList.get(i).getXPosition() + 1][ogreList.get(i).getYPosition()] ==
		// HCLUB_CHAR
		// || map[ogreList.get(i).getXPosition()][ogreList.get(i).getYPosition() - 1] ==
		// HCLUB_CHAR
		// || map[ogreList.get(i).getXPosition()][ogreList.get(i).getYPosition() + 1] ==
		// HCLUB_CHAR) {
		// ogreList.get(i).setStunned(true);
		// }
		// }
	}

}
