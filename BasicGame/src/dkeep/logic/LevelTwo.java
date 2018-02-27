package dkeep.logic;
import java.util.ArrayList;
//import java.util.Random;

public class LevelTwo extends Game {

	private static final String OGRE_CHAR = "O";
	private static final String CLUB_CHAR = "*";
	private static final String HCLUB_CHAR = "º";
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
		initializeMap();
		do {
			printMap();
			printLegend();
			moveHero();
			moveClub(hero);
			checkLever();
			map[this.hero.getXPosition()][this.hero.getYPosition()] = hero.getHeroChar();
			for(int i = 0; i < ogresNumber; i++){
				moveOgre(ogreList.get(i));		
			}
			checkIfStunned();
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
			if (map[this.hero.getXPosition() - 1][hero.getYPosition()].equals(OGRE_CHAR)
					|| map[this.hero.getXPosition() + 1][hero.getYPosition()].equals(OGRE_CHAR)
					|| map[this.hero.getXPosition()][hero.getYPosition() - 1].equals(OGRE_CHAR)
					|| map[this.hero.getXPosition()][hero.getYPosition() + 1].equals(OGRE_CHAR) 
					|| map[this.hero.getXPosition() - 1][hero.getYPosition()].equals(CLUB_CHAR)
					|| map[this.hero.getXPosition() + 1][hero.getYPosition()].equals(CLUB_CHAR)
					|| map[this.hero.getXPosition()][hero.getYPosition() - 1].equals(CLUB_CHAR)
					|| map[this.hero.getXPosition()][hero.getYPosition() + 1].equals(CLUB_CHAR)) {

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
	
	
	public final void initializeMap()
	{
		for(int i = 0; i < ogresNumber; i++){
			moveOgre(ogreList.get(i));
		}
			moveClub(hero);
			map[hero.getXPosition()][hero.getYPosition()] = HERO_CHAR;	
	}

	private final void moveOgre(Ogre myOgre) {
		
		if(myOgre.getStunned()) {
			if(myOgre.getStunCounter() >0) {
				myOgre.stunCounter();
			map[myOgre.getXPosition()][myOgre.getYPosition()] = "8";
				return;
			}
			else {
				myOgre.setStunned(false);
			}	
		}

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
		} while (map[position[0]][position[1]].equals(WALL_CHAR)  ||
				map[position[0]][position[1]].equals(OGRE_CHAR) ||
				map[position[0]][position[1]].equals(HERO_CHAR) ||
				map[position[0]][position[1]].equals(DOOR_CHAR) ||
				map[position[0]][position[1]].equals("S") ||
				map[position[0]][position[1]].equals("HCLUB_CHAR"));

		Club tempClub = character.getMyClub();
		tempClub.setxPosition(position[0]);
		tempClub.setyPosition(position[1]);
		character.setMyClub(tempClub);

		if(character.getMyClub().getXPosition() == 1 && character.getMyClub().getYPosition() == 7) {
			if(!this.lever.isActivated()) {
				map[1][7] = "$";
			}
		}else
		{
			if(character instanceof Ogre ) {
			map[character.getMyClub().getXPosition()][character.getMyClub().getYPosition()] = CLUB_CHAR;
			}else
				map[character.getMyClub().getXPosition()][character.getMyClub().getYPosition()] = HCLUB_CHAR;
			
			if(!this.lever.isActivated()) {
				map[1][7] = LEVER_CHAR;
			}
			
		}

	}
	
	
	public void checkIfStunned()
	{
		
		for(int i = 0; i < ogreList.size(); i++)
		{
			if( map[ogreList.get(i).getXPosition() - 1][ogreList.get(i).getYPosition()].equals(HCLUB_CHAR) 
					|| map[ogreList.get(i).getXPosition() + 1][ogreList.get(i).getYPosition()].equals(HCLUB_CHAR)
					|| map[ogreList.get(i).getXPosition()][ogreList.get(i).getYPosition() - 1].equals(HCLUB_CHAR) 
					|| map[ogreList.get(i).getXPosition()][ogreList.get(i).getYPosition() + 1].equals(HCLUB_CHAR)) {
				ogreList.get(i).setStunned(true);
			}
		}
	}

}
