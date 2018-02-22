package dkeep.cli;

import java.util.Scanner;

public class Interface {

	public Interface() {
	}

	private static final Scanner keyboard = new Scanner(System.in);

	public final String getMove() {
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
		return move;
	}

}
