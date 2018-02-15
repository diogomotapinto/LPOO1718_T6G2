
public class Game {
	static final String wall = "X";
	static final String door = "I";

	private String[][] map;
	private String[][] legend;
	private Interface myInterface;
	private Character hero;

	class Legend {
		public String wall;
		public String door;
		public String guard;
		public String lever;
		public String freeSpace;
	}

	public Game() {
		this.map = new String[][] { { "X", "X", "X", "X", "X", "X", "X", "X", "X", "X" },
				{ "X", "H", " ", " ", "I", " ", "X", " ", "G", "X" },
				{ "X", "X", "X", " ", "X", "X", "X", " ", " ", "X" },
				{ "X", " ", "I", " ", "I", "X", "X", " ", " ", "X" },
				{ "X", "X", "X", " ", "X", "X", "X", " ", " ", "X" },
				{ "I", " ", " ", " ", " ", " ", " ", " ", " ", "X" },
				{ "I", " ", " ", " ", " ", " ", " ", " ", " ", "X" },
				{ "X", "X", "X", " ", "X", "X", "X", "X", " ", "X" },
				{ "X", " ", "I", " ", "I", " ", "X", "K", " ", "X" },
				{ "X", "X", "X", "X", "X", "X", "X", "X", "X", "X" } };

		this.legend = new String[][] { { "X - Wall" }, { "I - Door" }, { "H - Hero" }, { "G - Guard" }, { "k - lever" },
				{ "empty cell - free space" } };
		this.myInterface = new Interface();
		int[] pos = this.findCharacter();
		hero = new Hero(pos[0], pos[1]);
	}

	public void playGame() {
		do {
			printMap();
			printLegend();
			moveCharacter();
		} while (true);
	}

	public String[][] getMap() {
		return this.map;
	}

	public String[][] getLegend() {

		return this.legend;
	}

	public void printMap() {
		for (int i = 0; i < this.map.length; i++) {
			for (int j = 0; j < this.map[i].length; j++) {
				System.out.print(" " + this.map[i][j]);
			}
			System.out.println();
		}
	}

	// public void printArray(String[][] myArray)
	// {
	// for(int i = 0; i < myArray.length; i++)
	// {
	// for(int j = 0; j < myArray[i].length; j++)
	// {
	// System.out.print(myArray[i][j]);
	// }
	// System.out.println();
	// }
	// }

	public void printLegend() {

		for (int i = 0; i < this.legend.length; i++) {

			for (int j = 0; j < this.legend[i].length; j++) {

				System.out.print(" " + this.legend[i][j]);
			}

			System.out.println();
		}
	}

	public void moveCharacter() {
		String move = this.myInterface.getMove();
		
		try {
		switch (move) {
		case "u":
			if (!(map[this.hero.getXPosition() - 1][hero.getYPosition()].equals(wall)
					|| (map[this.hero.getXPosition() - 1][hero.getYPosition()].equals(door)))) {
					
				map[this.hero.getXPosition() ][hero.getYPosition()] = " ";
				hero.setxPosition(hero.getXPosition() - 1);
				checkLever();
				map[this.hero.getXPosition() ][hero.getYPosition()] = "H";
				
			}
			break;
		case "d":
			if (!(map[this.hero.getXPosition() + 1][hero.getYPosition()].equals(wall)
					|| (map[this.hero.getXPosition() + 1][hero.getYPosition()].equals(door)))) {
				
				map[this.hero.getXPosition() ][hero.getYPosition()] = " ";
				hero.setxPosition(hero.getXPosition() + 1);
				checkLever();
				map[this.hero.getXPosition() ][hero.getYPosition()] = "H";
				
			}
			break;
		case "l":
			if (!(map[this.hero.getXPosition()][hero.getYPosition() - 1].equals(wall)
					|| (map[this.hero.getXPosition()][hero.getYPosition() - 1].equals(door)))) {
				
				map[this.hero.getXPosition() ][hero.getYPosition()] = " ";
				hero.setyPosition(hero.getYPosition() - 1);
				checkLever();
				map[this.hero.getXPosition() ][hero.getYPosition()] = "H";
			}
			break;
		case "r":

			if (!(map[this.hero.getXPosition()][hero.getYPosition() + 1].equals(wall)
					|| (map[this.hero.getXPosition()][hero.getYPosition() + 1].equals(door)))) {
				
				map[this.hero.getXPosition() ][hero.getYPosition()] = " ";
				hero.setyPosition(hero.getYPosition() + 1);
				checkLever();
				map[this.hero.getXPosition() ][hero.getYPosition()] = "H";
			}
			break;
		}
		}catch (IndexOutOfBoundsException e) {
		    System.err.println("IndexOutOfBoundsException "+e.getMessage());
		}
		
			

	}
	
	
	public int[] findCharacter() {
		int[] pos = { -1, -1 };
		for (int i = 0; i < this.map.length; i++) {
			for (int j = 0; j < this.map[0].length; j++) {
				if (map[i][j].equals("H")) {
					pos[0] = i;
					pos[1] = j;
					return pos;
				}
			}
		}
		return pos;
	}
	
	public void checkLever() {
		
		if(map[this.hero.getXPosition()][hero.getYPosition()].equals("K")) {
			for (int i = 0; i < this.map.length; i++) {
				for (int j = 0; j < this.map[i].length; j++) {
					if (map[i][j].equals("I")) {
						map[i][j] = "S";
					}
				}
			}
		}
				
	}
}
