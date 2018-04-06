package dkeep.test;

import dkeep.controller.Controller;
import dkeep.logic.OgreMapTest;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dkeep.logic.model.Position;

import org.junit.Test;

public class TestOgreGameLogic {
  private OgreMapTest map;
  public char [][] testMap = {
			{'X','X','X', 'X' ,'X','X'},
			{'I',' ', ' ' ,' ' ,'k','X'},
			{'X',' ', ' ' ,' ' ,' ','X'},
			{'X',' ', ' ' ,' ' ,' ','X'},
			{'X','A', ' ' ,' ' ,'O','X'},
			{'X','X', 'X','X','X','X'}
	};

  
  /**
   * Moves in the ogre direction and stuns him without loosing
   */
  @Test
  public void testMoveHeroIntoTo() {
    Position ogrePosition = new Position(7, 4);
    map = new OgreMapTest("1");
    map.play('d');
    map.play('d');
    assertEquals((byte) 0, map.checkEndLevel());
  }

  /**
   * Moves in the ogre direction and stuns him without loosing
   */
  @Test
  public void testMoveHeroIntoTo2() {
    map = new OgreMapTest(testMap);
    map.play('d');
    assertEquals((byte) 0, map.checkEndLevel());
  }
  
  /**
   * Test to check if the ogre change is character to 'K' when he has the key
   */
  @Test
  public void testChangeToK() {
    map = new OgreMapTest("1");
    assertEquals(new Position(8, 1), map.getHero().getPosition());
    StringBuilder s = new StringBuilder();
    s.append("wwwwwwddddddaa");

    while (s.length() > 0) {
      map.play(s.charAt(0));
      s.deleteCharAt(0);
    }

    assertEquals('K', map.getHero().getCharHeroLvl2());
  }


  /**
   * Test to check if the hero opens the door if he is in an adjacent position
   */
  @Test
  public void movesToCloseDoor() {
    map = new OgreMapTest("1");
    StringBuilder s = new StringBuilder();
    s.append("wwwwwww");
    
    while (s.length() > 0) {
      map.play(s.charAt(0));
      s.deleteCharAt(0);
    }
    assertEquals('I', map.getPlayMap()[1][0]);
    assertEquals((byte) 0, map.checkEndLevel());
  }
  
  /**
   * Test to check if the hero opens the door if he is in an adjacent position
   */
  @Test
  public void movesToCloseDoor2() {
    map = new OgreMapTest(testMap);
    StringBuilder s = new StringBuilder();
    s.append("www");
    
    while (s.length() > 0) {
      map.play(s.charAt(0));
      s.deleteCharAt(0);
    }
    assertEquals('I', map.getPlayMap()[1][0]);
    assertEquals((byte) 0, map.checkEndLevel());
  }

/**
 *Test to check if the door  doesn't open when he has the key
 */
  @Test
  public void movesToOpenDoor() {
    map = new OgreMapTest("1");

    StringBuilder s = new StringBuilder();
    s.append("wwwwwwddddddaaaaaa");

    while (s.length() > 0) {
      map.play(s.charAt(0));
      s.deleteCharAt(0);
    }
    assertEquals('I', map.getPlayMap()[1][0]);
    assertEquals((byte) 0, map.checkEndLevel());
  }

  /**
   * Test to check if the game is won by moving into the open door
   */
  @Test
  public void movesWin() {
    map = new OgreMapTest("1");

    StringBuilder s = new StringBuilder();
    s.append("wwwwwwwddddddddaaaaaaaa");

    while (s.length() > 0) {
      map.play(s.charAt(0));
      s.deleteCharAt(0);
    }
    assertEquals((byte) 1, map.checkEndLevel());
  }

}
