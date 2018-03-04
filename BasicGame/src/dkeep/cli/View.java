package dkeep.cli;

import java.util.Scanner;

public final class View {

	public View() {
	}

	private static final Scanner keyboard = new Scanner(System.in);

	public final char getMove() {
		String move = "";

		boolean flag = false;
		do {
			System.out.println("Introduza o movimento: ");
			move = keyboard.nextLine();
			if ((move.equals("w") || move.equals("s") || move.equals("a") || move.equals("d"))) {
				flag = true;
			} else {
				System.out.println("Introduziu op�ao invalida");
			}
		} while (!flag);
		return move.charAt(0);
	}

	// implementar um toString na classe Dungeon
	public void printGameInfo(char[][] map, String legend) {
		printMatrix(map);
		printString(legend);
	}

	private void printMatrix(char[][] map) {
		System.out.println();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(" " + map[i][j]);
			}
			System.out.println();
		}
	}

	private void printString(String s) {
		System.out.println(s);
	}

}
