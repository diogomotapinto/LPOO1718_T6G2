package dkeep.cli;

import java.util.Scanner;

public final class View {

	private final Scanner keyboard;

	public View() {
		keyboard = new Scanner(System.in);
	}

	public final char getMove() {
		String move = "";

		boolean flag = false;
		do {
			printString("Introduza o movimento: ");
			move = keyboard.nextLine();
			if ((move.equals("w") || move.equals("s") || move.equals("a") || move.equals("d"))) {
				flag = true;
			} else {
				printString("Introduziu op�ao invalida");
			}
		} while (!flag);
		return move.charAt(0);
	}

	// implementar um toString na classe Dungeon
	public void printGameInfo(String header, char[][] map, String legend) {
		printString("");
		printString(header);
		printMatrix(map);
		printString(legend);
	}

	private void printMatrix(char[][] map) {
		printString("");
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				printString(" " + map[i][j]);
			}
			printString("");
		}
	}

	public void printString(String s) {
		System.out.println(s);
	}

}
