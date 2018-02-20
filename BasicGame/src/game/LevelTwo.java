package game;

import java.util.Random;

public class LevelTwo extends Game {

	private static final String OGRE_CHAR = "O";
	private static final String CLUB_CHAR = "*";
	private Ogre ogre;
	private Random random;
	private int jogada;

	public LevelTwo() {
		// passes map and legend as argument
		super(new String[][] {
				{ WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR },
				{ DOOR_CHAR, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, LEVER_CHAR, WALL_CHAR },
				{ WALL_CHAR, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, WALL_CHAR },
				{ WALL_CHAR, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, WALL_CHAR },
				{ WALL_CHAR, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, WALL_CHAR },
				{ WALL_CHAR, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, WALL_CHAR },
				{ WALL_CHAR, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, WALL_CHAR },

				{ WALL_CHAR, HERO_CHAR, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE, BLANK_SPACE,WALL_CHAR },
				{ WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR } },
				"\nX - Wall \nI - Exit Door \nH - Hero \nO - Crazy Ogre \nk - key \nempty cell - free space", 7, 1);
		ogre = new Ogre(1, 4);
		jogada = 0;
	}

	public void playLevelTwo() {
		System.out.println("Nivel 2!!!");
		boolean advanceLevel = false;
		do {
			
			//printMap();
			
			printLegend();
			checkLever();
			map[this.hero.getXPosition()][this.hero.getYPosition()] = HERO_CHAR;
			moveOgre();
			moveHero();
			//moveClub();
			advanceLevel = checkAdvanceLevel();
		} while (!advanceLevel);
	}

	private boolean checkAdvanceLevel() {
		// para terminar basta chegar a um dos cantos
		if (this.hero.getXPosition() == 0 || this.hero.getYPosition() == 0) {
			System.out.println("\nGanhou o jogo");
			return true;
		}

		try {
			if (map[this.hero.getXPosition() - 1][hero.getYPosition()] == OGRE_CHAR
					|| map[this.hero.getXPosition() + 1][hero.getYPosition()] == OGRE_CHAR
					|| map[this.hero.getXPosition()][hero.getYPosition() - 1] == OGRE_CHAR
					|| map[this.hero.getXPosition()][hero.getYPosition() + 1] == OGRE_CHAR) {
				
				printMap();
				System.out.print("\nPerdeu jogo");
				return true;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		return false;
	}

//	private void moveOgre() {
//		
//	
//		map[ogre.getXPosition()][ogre.getYPosition()] = BLANK_SPACE;
//		this.ogre.moveToNextPosition(map[ogre.getXPosition() - 1][ogre.getYPosition()],
//				map[ogre.getXPosition()][ogre.getYPosition() + 1]);
//		map[ogre.getXPosition()][ogre.getYPosition()] = OGRE_CHAR;
//		
//	}
	
	private void moveOgre() {
		String[][]  mapClone = sendMap();
		
		
		mapClone[ogre.getXPosition()][ogre.getYPosition()] = BLANK_SPACE;
		
			this.ogre.moveToNextPosition(map[ogre.getXPosition() - 1][ogre.getYPosition()],
					map[ogre.getXPosition()][ogre.getYPosition() + 1]);
			if(map[ogre.getXPosition()][ogre.getYPosition()] == BLANK_SPACE) {
				
				mapClone[ogre.getXPosition()][ogre.getYPosition()] = OGRE_CHAR;
				
			}else {
				do {
				this.ogre.moveToNextPosition(map[ogre.getXPosition() - 1][ogre.getYPosition()],
						map[ogre.getXPosition()][ogre.getYPosition() + 1]);
				}while(map[ogre.getXPosition()][ogre.getYPosition()] != BLANK_SPACE);
				
				mapClone[ogre.getXPosition()][ogre.getYPosition()] = OGRE_CHAR;
			}
		
		levelTwoMap(mapClone);
		
	}
	
	protected void levelTwoMap(String[][]  mapClone) {
		System.out.println();
		for (int i = 0; i < mapClone.length; i++) {
			for (int j = 0; j < mapClone[i].length; j++) {
				System.out.print(BLANK_SPACE + mapClone[i][j]);
			}
			System.out.println();
		}
	}
	
	
	private void moveClub() {
		

		System.out.println(ogre.getClubXPos());
		System.out.println(ogre.getClubYPos());
		
		map[ogre.getClubXPos()][ogre.getClubYPos()] = BLANK_SPACE;
		ogre.moveOgresClub();
		if(ogre.getClubXPos() != 0 && ogre.getClubYPos() != 0 && ogre.getClubXPos() != 8 && ogre.getClubYPos() != 8) {
			map[ogre.getClubXPos()][ogre.getClubYPos()] = ogre.getClub();
		}else
		{
			map[ogre.getClubXPos()][ogre.getClubYPos()] = ogre.getClub();
		}
			
		
	}
	

}
