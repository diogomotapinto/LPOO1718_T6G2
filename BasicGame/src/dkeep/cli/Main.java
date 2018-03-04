package dkeep.cli;

import dkeep.logic.Controller;
import dkeep.logic.OgreMap;

public final class Main {

	public static void main(String[] args) {

		Controller c = Controller.getInstance();
		c.run();
	}

}
