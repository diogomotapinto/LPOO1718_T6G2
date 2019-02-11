package dkeep.cli;

import java.util.Scanner;

public final class View {

	private final Scanner keyboard;
	private static final byte NUMBER_OF_CONSOLE_LINES = 20;

	/**
	 * Class constructor
	 */
	public View() {
		keyboard = new Scanner(System.in);
	}

	/**
	 * Returns the move
	 * 
	 * @return 'w' for the hero to move up, 's' for the hero to move down, 'a' for
	 *         the hero to move left and 'd' for the hero move right
	 */
	public final char getMove() {
		promptMsg("Introduza o movimento: ");
		try {
			return keyboard.nextLine().charAt(0);
		} catch (Exception e) {
			return ' ';
		}
	}

	// implementar um toString na classe Dungeon
	public void printGameInfo(String header, char[][] map, String legend) {
//		clearScreen();
		promptMsg("");
		promptMsg(header);
		printMatrix(map);
		promptMsg(legend);
	}

	/**
	 * Prints the map
	 * 
	 * @param map to be printed
	 */
	private void printMatrix(char[][] map) {
		promptMsg("");
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(" " + map[i][j]);
			}
			System.out.println("");
		}
	}

	private void clearScreen() {
		for (int i = 0; i < NUMBER_OF_CONSOLE_LINES; i++) {
			System.out.println();
		}
	}

	/**
	 * Prints the string passed as @param
	 * 
	 * @param s string to be printed
	 */
	public void promptMsg(String s) {
		System.out.println(s);
	}

	public void drawFinalMessage(boolean won) {
		if (won) {
			System.out.print("\n\nVICTORY!!!\n\n");
			promptMsg("\nYou won the game!! :D");
		} else {
			System.out.print("\n\nGAME OVER...\n\n");
			promptMsg("\nYou lost the game ...");
		}
	}

	public void close() {
		keyboard.close();
	}

//	public static void boardToFile(char[][] board, String nameFile) throws IOException {
//		File file = new File(DungeonKeepUI.BOARDS_DIR + nameFile + ".txt");
//		file.createNewFile();
//		FileWriter writer = new FileWriter(file);
//		writer.write(toString(board));
//		writer.close();
//	}

}
