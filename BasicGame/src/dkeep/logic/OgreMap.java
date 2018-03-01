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
			map[this.hero.getXPosition()][this.hero.getYPosition()] = Hero.getHeroChar();
			for (int i = 0; i < ogresNumber; i++) {
				moveOgre(ogreList.get(i));
			}
			checkIfStunned();
		} while (!checkAdvanceLevel());
	}


	public final void initializeMap() {
		for (int i = 0; i < ogresNumber; i++) {
			moveOgre(ogreList.get(i));
		}
		moveClub(hero);
		map[hero.getXPosition()][hero.getYPosition()] = Hero.getHeroChar();
	}

	private final boolean checkAdvanceLevel() {
		// para terminar basta chegar a um dos cantos
		if (this.hero.getLeverState() && (this.hero.getXPosition() == 1 && this.hero.getYPosition() == 0)) {
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




	private final boolean checkCollision(Position posOne, Position posTwo)
	{
		if(posOne.getXPosition() + 1 == posTwo.getXPosition() &&
				posOne.getYPosition() == posTwo.getYPosition())
		{
			return true;
		}
		//check if there is an Ogre up
		if(posOne.getXPosition() - 1 == posTwo.getXPosition() &&
				posOne.getYPosition() == posTwo.getYPosition())
		{
			return true;
		}
		//check if there is an Ogre on the left
		if(posOne.getXPosition() == posTwo.getXPosition() &&
				posOne.getYPosition() -1 == posTwo.getYPosition())
		{
			return true;
		}
		//check if there is an Ogre on the right
		if(posOne.getXPosition() == posTwo.getXPosition() &&
				posOne.getYPosition() + 1 == posTwo.getYPosition())
		{
			return true;
		}

		return false;
	}

	private final boolean checkOgreCollision()
	{
		//		for(int i = 0; i < ogreList.size(); i++) {
		//			//checks if there is an Ogre below 
		//			if(this.hero.getXPosition() + 1 == this.ogreList.get(i).getXPosition() &&
		//					this.hero.getYPosition() == this.ogreList.get(i).getYPosition())
		//			{
		//				return true;
		//			}
		//			//check if there is an Ogre up
		//			if(this.hero.getXPosition() - 1 == this.ogreList.get(i).getXPosition() &&
		//					this.hero.getYPosition() == this.ogreList.get(i).getYPosition())
		//			{
		//				return true;
		//			}
		//			//check if there is an Ogre on the left
		//			if(this.hero.getXPosition() == this.ogreList.get(i).getXPosition() &&
		//					this.hero.getYPosition() -1 == this.ogreList.get(i).getYPosition())
		//			{
		//				return true;
		//			}
		//			//check if there is an Ogre on the right
		//			if(this.hero.getXPosition() == this.ogreList.get(i).getXPosition() &&
		//					this.hero.getYPosition() + 1 == this.ogreList.get(i).getYPosition())
		//			{
		//				return true;
		//			}
		//		}
		//
		//		return false;
		for(int i = 0; i < ogreList.size(); i++){
			if(checkCollision(this.hero.getPosition(), this.ogreList.get(i).getPosition())) {
				return true;
			}
		}
		return false;
	}

	private final boolean checkClubCollision()
	{
		for(int i = 0; i < ogreList.size(); i++) {
			//checks if there is an Ogre below 
			if(this.hero.getXPosition() + 1 == this.ogreList.get(i).getMyClub().getXPosition() &&
					this.hero.getYPosition() == this.ogreList.get(i).getMyClub().getYPosition())
			{
				return true;
			}
			//check if there is an Ogre up
			if(this.hero.getXPosition() - 1 == this.ogreList.get(i).getMyClub().getXPosition() &&
					this.hero.getYPosition() == this.ogreList.get(i).getMyClub().getYPosition())
			{
				return true;
			}
			//check if there is an Ogre on the left
			if(this.hero.getXPosition() == this.ogreList.get(i).getMyClub().getXPosition() &&
					this.hero.getYPosition() -1 == this.ogreList.get(i).getMyClub().getYPosition())
			{
				return true;
			}
			//check if there is an Ogre on the right
			if(this.hero.getXPosition() == this.ogreList.get(i).getMyClub().getXPosition() &&
					this.hero.getYPosition() + 1 == this.ogreList.get(i).getMyClub().getYPosition())
			{
				return true;
			}
		}

		return false;
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
		map[character.getMyClub().getXPosition()][character.getMyClub().getYPosition()] = CHAR_BLANK_SPACE;

		int position[];
		do {
			position = Utilities.getAdjacentPosition(character.getXPosition(), character.getYPosition());
		} while (map[position[0]][position[1]] == WALL_CHAR || map[position[0]][position[1]] == OGRE_CHAR
				|| map[position[0]][position[1]] == Hero.getHeroChar() || map[position[0]][position[1]] == CHAR_DOOR_CLOSED
				|| map[position[0]][position[1]] == 'S' || map[position[0]][position[1]] ==Hero.getHeroClubChar());


		character.getMyClub().setXPosition(position[0]);
		character.getMyClub().setYPosition(position[0]);


		if (character.getMyClub().getXPosition() == 1 && character.getMyClub().getYPosition() == 7) {
			if (!this.lever.isActivated()) {
				map[1][7] = '$';
			}
		} else {
			map[character.getMyClub().getXPosition()][character.getMyClub().getYPosition()] = CLUB_CHAR;
			if (!this.lever.isActivated()) {
				map[1][7] = Lever.getLeverChar();
			}

		}

	}

	private final void moveClub(Hero character) {
		map[character.getMyClub().getXPosition()][character.getMyClub().getYPosition()] = CHAR_BLANK_SPACE;

		int position[];
		do {
			position = Utilities.getAdjacentPosition(character.getXPosition(), character.getYPosition());
		} while (map[position[0]][position[1]] == (WALL_CHAR) || map[position[0]][position[1]] == (OGRE_CHAR)
				|| map[position[0]][position[1]] == (Hero.getHeroChar()) || map[position[0]][position[1]] == (CHAR_DOOR_CLOSED)
				|| map[position[0]][position[1]] == ('S') ||  map[position[0]][position[1]] == Hero.getHeroClubChar());

		character.getMyClub().setXPosition(position[0]);
		character.getMyClub().setYPosition(position[0]);

		if (character.getMyClub().getXPosition() == 1 && character.getMyClub().getYPosition() == 7) {
			if (!this.lever.isActivated()) {
				map[1][7] = '$';
			}
		} else {

			map[character.getMyClub().getXPosition()][character.getMyClub().getYPosition()] = Hero.getHeroClubChar();

			if (!this.lever.isActivated()) {
				map[1][7] = Lever.getLeverChar();
			}

		}

	}

	public void checkIfStunned() {

		for (int i = 0; i < ogreList.size(); i++) {
			if(checkCollision(this.ogreList.get(i).getPosition(),this.hero.getMyClub().getPosition()))
			{
				ogreList.get(i).setStunned(true);
			}
		}
	}



}
