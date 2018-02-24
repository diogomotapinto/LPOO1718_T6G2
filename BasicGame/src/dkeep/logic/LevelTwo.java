package dkeep.logic;
import java.util.ArrayList;
//import java.util.Random;

public class LevelTwo extends Game {

	private static final String OGRE_CHAR = "O";
	private static final String CLUB_CHAR = "*";
	private Ogre ogre;
	private ArrayList<Ogre> ogreList;
	private int ogresNumber;
	
	// private Random random;
	// private int jogada;

	public LevelTwo() {
		// passes map and legend as argument
		super(new String[][] {
				{ WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR },
				{ DOOR_CHAR, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, LEVER_CHAR,
						WALL_CHAR },
				{ WALL_CHAR, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE,
						WALL_CHAR },
				{ WALL_CHAR, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE,
						WALL_CHAR },
				{ WALL_CHAR, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE,
						WALL_CHAR },
				{ WALL_CHAR, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE,
						WALL_CHAR },
				{ WALL_CHAR, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE,
						WALL_CHAR },

				{ WALL_CHAR, HERO_CHAR, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE,
						WALL_CHAR },
				{ WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR } },
				"\nX - Wall \nI - Exit Door \nH - Hero \nO - Crazy Ogre \nk - key \nempty cell - free space", 7, 1);
		
		ogreList = new ArrayList<Ogre>();
		ogresNumber = Utilities.generateRandomNumber(1,3);
		int x;
		int y;
		for(int i = 0; i < ogresNumber; i++) {
			do {
				x = Utilities.generateRandomNumber(1,7);
				y = Utilities.generateRandomNumber(1,7);
			ogre = new Ogre(x,y);
			}while(map[x][y] != BLANK_SPACE);
			ogreList.add(ogre);
		}
		
		
		// jogada = 0;
	}

	public final void playLevelTwo() {
		System.out.println("Nivel 2!!!");
		boolean advanceLevel = false;
		for(int i = 0; i < ogresNumber; i++){
			moveClub(ogreList.get(i));		
		}
		for(int i = 0; i < ogresNumber; i++){
			map[ogreList.get(i).getXPosition()][ogreList.get(i).getYPosition()] = OGRE_CHAR;	
		}
		do {
			printMap();
			printLegend();
			moveHero();
			checkLever();
			map[this.hero.getXPosition()][this.hero.getYPosition()] = hero.getHeroChar();
			for(int i = 0; i < ogresNumber; i++){
				moveOgre(ogreList.get(i));		
			}
			advanceLevel = checkAdvanceLevel();
		} while (!advanceLevel);
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

	private final void moveOgre(Ogre myOgre) {

		map[myOgre.getXPosition()][myOgre.getYPosition()] = BLANK_SPACE;

		int position[];
		do {
			position = Utilities.getAdjacentPosition(myOgre.getXPosition(), myOgre.getYPosition());
		} while (map[position[0]][position[1]] != BLANK_SPACE );

		myOgre.setxPosition(position[0]);
		myOgre.setyPosition(position[1]);
		map[myOgre.getXPosition()][myOgre.getYPosition()] = OGRE_CHAR;
		moveClub(myOgre);
	}

	private final void moveClub(Character character) {
		map[character.getMyClub().getXPosition()][character.getMyClub().getYPosition()] = BLANK_SPACE;

		int position[];
		do {
			position = Utilities.getAdjacentPosition(character.getXPosition(), character.getYPosition());
		} while (map[position[0]][position[1]] == WALL_CHAR ||
				map[position[0]][position[1]] == OGRE_CHAR ||
				map[position[0]][position[1]] == HERO_CHAR ||
				map[position[0]][position[1]] == DOOR_CHAR ||
				map[position[0]][position[1]] == "S");

		Club tempClub = character.getMyClub();
		tempClub.setxPosition(position[0]);
		tempClub.setyPosition(position[1]);
		character.setMyClub(tempClub);

		if(character.getMyClub().getXPosition() == 1 && character.getMyClub().getYPosition() == 7) {
			map[1][7] = "$";
		}else
		{
			map[character.getMyClub().getXPosition()][character.getMyClub().getYPosition()] = CLUB_CHAR;
			
			if(!this.lever.isActivated()) {
				map[1][7] = LEVER_CHAR;
			}
			
		}

	}

}
