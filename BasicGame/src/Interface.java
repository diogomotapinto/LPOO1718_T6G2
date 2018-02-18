package game;
import java.util.Scanner;

public class Interface {

	public Interface() {
	}

	private static final Scanner keyboard = new Scanner(System.in);

	public String getMove() {
		String move = "";

		boolean flag = false;
		while (!flag) {
			System.out.println("Introduza o movimento: ");
			move = keyboard.nextLine();
			if (!(move == "w" || move == "s" || move == "a" || move == "d")) {
				flag = true;
			} else {
				System.out.println("Introduziu op�ao invalida");
			}
		}
		return move;
	}

}
