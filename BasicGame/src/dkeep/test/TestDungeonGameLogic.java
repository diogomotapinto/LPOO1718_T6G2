package dkeep.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dkeep.logic.DungeonMap;
import dkeep.logic.DungeonMapTest;
import dkeep.logic.model.Position;

class TestDungeonGameLogic {

  private DungeonMap map;
  private static String guardPersonality = "rookie";

  @Test
  void testMoveHeroIntoToFreeCell() {
    map = new DungeonMapTest(guardPersonality);
    assertEquals(new Position(1, 1), map.getHero().getPosition());
    map.play('d');
    assertEquals(new Position(1, 2), map.getHero().getPosition());
    assertEquals(0, map.checkEndLevel());
  }

  @Test
  void testMoveHeroIntoToWall() {
    map = new DungeonMapTest(guardPersonality);
    assertEquals(new Position(1, 1), map.getHero().getPosition());
    map.play('w');
    assertEquals(new Position(1, 1), map.getHero().getPosition());
    assertEquals(0, map.checkEndLevel());
  }

  @Test
  void testMoveHeroIntoToGuard() {
    map = new DungeonMapTest(guardPersonality);

    StringBuilder s = new StringBuilder();
    s.append("ddssssddddwwww");

    while (s.length() > 0) {
      map.play(s.charAt(0));
      s.deleteCharAt(0);
    }
    assertEquals(new Position(1, 7), map.getHero().getPosition());
    assertEquals(-1, map.checkEndLevel());
  }

  @Test
  void testMoveHeroIntoToClosedExitDoors() {
    map = new DungeonMapTest(guardPersonality);

    StringBuilder s = new StringBuilder();
    s.append("ddssssaaa");

    while (s.length() > 0) {
      map.play(s.charAt(0));
      s.deleteCharAt(0);
    }
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
    map = new DungeonMapTest(guardPersonality);

    StringBuilder s = new StringBuilder();
    s.append("ddsssssdddddssadwwaaaaaaaa");

    while (s.length() > 0) {
      map.play(s.charAt(0));
      s.deleteCharAt(0);
    }
    assertEquals(1, map.checkEndLevel());
  }
}
