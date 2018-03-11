package dkeep.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dkeep.logic.DungeonMap;
import dkeep.logic.model.Position;
import dkeep.logic.model.TestGuard;
import utilities.Utilities;

class TestDungeonGameLogic {

	private DungeonMap map;

	@Test
	void testMoveHeroIntoToFreeCell() {
		map = new DungeonMap();
		assertEquals(new Position(1, 1), map.getHero().getPosition());
		map.play('d');
		assertEquals(new Position(1, 2), map.getHero().getPosition());
		assertEquals(0, map.checkEndLevel());
	}

	@Test
	void testMoveHeroIntoToWall() {
		map = new DungeonMap();
		assertEquals(new Position(1, 1), map.getHero().getPosition());
		map.play('w');
		assertEquals(new Position(1, 1), map.getHero().getPosition());
		assertEquals(0, map.checkEndLevel());
	}

	@Test
	void testMoveHeroIntoToGuard() {
		TestGuard t = new TestGuard();
		map = new DungeonMap(t);
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
		assertEquals(true, Utilities.checkAdjacentCollision(t.getPosition(), map.getHero().getPosition()));
		assertEquals(-1, map.checkEndLevel());
	}

	@Test
	void testMoveHeroIntoToClosedExitDoors() {
		TestGuard t = new TestGuard();
		map = new DungeonMap(t);
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
		TestGuard t = new TestGuard();
		map = new DungeonMap(t);
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
