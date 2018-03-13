package dkeep.test;

import dkeep.logic.OgreMapTest;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dkeep.logic.Controller;
import dkeep.logic.Map;
import dkeep.logic.OgreMap;
import dkeep.logic.OgreMapTest;
import dkeep.logic.model.Position;

import org.junit.Test;

public class TestOgreGameLogic {
	private OgreMapTest map;

	@Test
	public void testMoveHeroIntoTo() {
		Position ogrePosition = new Position(7, 4);
		map = new OgreMapTest();
		map.play('d');
		map.play('d');
		assertEquals((byte) -1, map.checkEndLevel());
	}

	@Test
	public void testChangeToK() {
		map = new OgreMapTest();

		StringBuilder s = new StringBuilder();
		s.append("wwwwwwdddddd");

		while (s.length() > 0) {
			map.play(s.charAt(0));
			s.deleteCharAt(0);
		}
		assertEquals('K', map.getHero().getHeroChar());
	}

	@Test
	public void movesToCloseDoor() {
		map = new OgreMapTest();
		StringBuilder s = new StringBuilder();
		s.append("wwwwwwa");

		while (s.length() > 0) {
			map.play(s.charAt(0));
			s.deleteCharAt(0);
		}
		assertEquals('I', map.getMap()[1][0]);
	}

	@Test
	public void movesToOpenDoor() {
		map = new OgreMapTest();

		StringBuilder s = new StringBuilder();
		s.append("wwwwwwddddddaaaaaa");

		while (s.length() > 0) {
			map.play(s.charAt(0));
			s.deleteCharAt(0);
		}
		assertEquals('S', map.getMap()[1][0]);
	}

	@Test
	public void movesWin() {
		map = new OgreMapTest();

		StringBuilder s = new StringBuilder();
		s.append("wwwwwwddddddaaaaaaa");

		while (s.length() > 0) {
			map.play(s.charAt(0));
			s.deleteCharAt(0);
		}
		assertEquals(1, map.checkEndLevel());
	}

}
