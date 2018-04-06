package dkeep.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dkeep.logic.DungeonMap;
import dkeep.logic.DungeonMapTest;
import dkeep.logic.Position;

class TestDungeonGameLogic {

  private DungeonMap map;
  private static String guardPersonality = "rookie";

  
  /**
   * Test to check if the hero moves into a free cell
   */
  @Test
  void testMoveHeroIntoToFreeCell() {
    map = new DungeonMapTest(guardPersonality);
    assertEquals(new Position(1, 1), map.getHero().getPosition());
    map.play('d');
    assertEquals(new Position(1, 2), map.getHero().getPosition());
    assertEquals(0, map.checkEndLevel());
  }

  /**
   * Test to check if the hero bumps into the wall
   */
  @Test
  void testMoveHeroIntoToWall() {
    map = new DungeonMapTest(guardPersonality);
    assertEquals(new Position(1, 1), map.getHero().getPosition());
    map.play('w');
    assertEquals(new Position(1, 1), map.getHero().getPosition());
    assertEquals(0, map.checkEndLevel());
  }

  /**
   * Test to check if the hero bumps into the wall
   */
  @Test
  void testMoveHeroIntoToWallSuspicious() {
    map = new DungeonMapTest("Suspicious");
    assertEquals(new Position(1, 1), map.getHero().getPosition());
    map.play('w');
    assertEquals(new Position(1, 1), map.getHero().getPosition());
    assertEquals(0, map.checkEndLevel());
  }

  
  /**
   * Test to check if the hero bumps into the wall
   */
  @Test
  void testMoveHeroIntoToWallDrunken() {
    map = new DungeonMapTest("Drunken");
    assertEquals(new Position(1, 1), map.getHero().getPosition());
    map.play('w');
    assertEquals(new Position(1, 1), map.getHero().getPosition());
    assertEquals(0, map.checkEndLevel());
  }
  
  /**
   * Test to check if the hero bumps into the wall
   */
  @Test
  void testMoveHeroIntoToWallRookie() {
    map = new DungeonMapTest("Rookie");
    assertEquals(new Position(1, 1), map.getHero().getPosition());
    map.play('w');
    assertEquals(new Position(1, 1), map.getHero().getPosition());
    assertEquals(0, map.checkEndLevel());
  }
  
  
  /**
   * Test to check if the game is lost in case of "collision" between the hero and the ogre
   */
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

  /**
   * Test to check if the hero doesn't go into a close door
   */
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


  /**
   * Test to check if the hero wins by going into the open door on the left wall
   */
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
