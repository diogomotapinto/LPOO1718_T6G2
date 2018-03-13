package dkeep.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dkeep.logic.DungeonMap;
import dkeep.logic.DungeonMapTest;
import dkeep.logic.model.Position;

class TestDungeonGameLogic {

	private DungeonMap map;

	@Test
	void testMoveHeroIntoToFreeCell() {
		map = new DungeonMapTest();
		assertEquals(new Position(1, 1), map.getHero().getPosition());
		map.play('d');
		assertEquals(new Position(1, 2), map.getHero().getPosition());
		assertEquals(0, map.checkEndLevel());
	}

	@Test
	void testMoveHeroIntoToWall() {
		map = new DungeonMapTest();
		assertEquals(new Position(1, 1), map.getHero().getPosition());
		map.play('w');
		assertEquals(new Position(1, 1), map.getHero().getPosition());
		assertEquals(0, map.checkEndLevel());
	}

	@Test
	void testMoveHeroIntoToGuard() {
		map = new DungeonMapTest();
		map.play('d');
		map.play('d');

		map.play('s');
		map.play('s');
		map.play('s');
		map.play('s');

		map.play('d');
		map.play('d');
		map.play('d');
		map.play('d');

		map.play('w');
		map.play('w');
		map.play('w');
		map.play('w');

		assertEquals(new Position(1, 7), map.getHero().getPosition());
		assertEquals(-1, map.checkEndLevel());
	}

	@Test
	void testMoveHeroIntoToClosedExitDoors() {
		map = new DungeonMapTest();
		map.play('d');
		map.play('d');

		map.play('s');
		map.play('s');
		map.play('s');
		map.play('s');

		map.play('a');
		map.play('a');
		map.play('a');
		assertEquals(new Position(5, 1), map.getHero().getPosition());
		assertEquals(0, map.checkEndLevel());

	}

	// @Test
	// void testMoveHeroIntoToLeverAndDoorsOpen() {
	// TestGuard t = new TestGuard();
	// map = new DungeonMap(t);
	// map.moveHero('d');
	// map.moveHero('d');
	//
	// map.moveHero('s');
	// map.moveHero('s');
	// map.moveHero('s');
	// map.moveHero('s');
	//
	// map.moveHero('a');
	// map.moveHero('a');
	// map.moveHero('a');
	// }

	@Test
	void testMoveHeroIntoToOpenDoorsTotheKeep() {
		map = new DungeonMapTest();
		map.play('d');
		map.play('d');

		map.play('s');
		map.play('s');
		map.play('s');
		map.play('s');
		map.play('s');

		map.play('d');
		map.play('d');
		map.play('d');
		map.play('d');
		map.play('d');

		map.play('s');
		map.play('s');

		map.play('a');

		map.play('d');

		map.play('w');
		map.play('w');

		map.play('a');
		map.play('a');
		map.play('a');
		map.play('a');
		map.play('a');
		map.play('a');
		map.play('a');
		map.play('a');

		assertEquals(1, map.checkEndLevel());
	}
}
