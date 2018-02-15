import java.util.Scanner;

public class Interface {

	public Interface() {

	}

	static final Scanner keyboard = new Scanner(System.in);

	public String getMove() {
		String move = "";
		boolean flag = false;
		while (!flag) {
			move = keyboard.nextLine();
			if (!(move == "u" || move == "d" || move == "l" || move == "r")) {
				flag = true;
			} else {
				System.out.println("Introduziu opçao invalida");
			}
		}
		return move;
	}

}
