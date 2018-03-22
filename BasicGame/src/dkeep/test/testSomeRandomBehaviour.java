package dkeep.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dkeep.logic.model.Position;
import utilities.Utilities;

class testSomeRandomBehaviour {

	@Test
	public void testRandomPositions() {
		Position pos = new Position(0,1);
		int counter = 0;
		for(int i = 0; i< 1000; i++) {
			if(pos.equals(Utilities.getAdjacentPosition(0, 0))) {
				counter++;
			}
		}
		
		assertTrue((counter > 200) && (counter < 300));
	}

}